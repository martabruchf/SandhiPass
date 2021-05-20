/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.mysql.core;

import com.marta.sandhipass.entity.Contrasenya;
import com.marta.sandhipass.entity.Usuari;
import com.marta.sandhipass.entity.Login;
import com.marta.sandhipass.entity.Versions;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marta
 */
public class LOConnectionMYSQL implements ILOConnectionMYSQL {

    private Connection connection;
    private Statement statement;

    /**
     * Mètode que es connecta a la base de dades
     */
    @Override
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Es carrega el driver
            this.connection = DriverManager.getConnection(DRIVER_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LOConnectionMYSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mètode que retorna la instància de la classe passada.
     * @param classType
     * @return 
     */
    private Object getObjectByClassType(Class classType) {
        switch (classType.getSimpleName()) {
            case "Contrasenya":
                return new Contrasenya();
            case "Usuari":
                return new Usuari();
            case "Login":
                return new Login();
            case "Versions":
                return new Versions();
        }
        return null;
    }

    @Override
    public void insert(String query) {
        try {
            this.openConnection();
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate(query);
        } catch (SQLException ex) {
            this.closeConnection();
            Logger.getLogger(LOConnectionMYSQL.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public List<Object> select(String query, Class classType) {
        try {
            this.openConnection();
            this.statement = this.connection.createStatement();
            return readResultSet(this.statement.executeQuery(query), classType);
        } catch (SQLException ex) {
            this.closeConnection();
            Logger.getLogger(LOConnectionMYSQL.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LOConnectionMYSQL.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection();
        }
        return null;
    }

    @Override
    public void update(String query) {
        try {
            this.openConnection();
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate(query);
        } catch (SQLException ex) {
            this.closeConnection();
            Logger.getLogger(LOConnectionMYSQL.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void delete(String query) {
        try {
            this.openConnection();
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate(query);
        } catch (SQLException ex) {
            this.closeConnection();
            Logger.getLogger(LOConnectionMYSQL.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public List<Object> readResultSet(ResultSet rs, Class classType) throws IllegalAccessException {
        List<Object> list = new ArrayList();
        Object object = getObjectByClassType(classType);
        Field[] fields = object.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        try {
            while (rs.next()) {
                object = getObjectByClassType(classType);
                for (Field field : fields) {
                    String type = field.getType().getSimpleName();
                    String name = field.getName();
                    switch (type) {
                        case "String":
                            field.set(object, rs.getString(name));
                            break;
                        case "int":
                            field.set(object, rs.getInt(name));
                            break;
                        case "Date":
                            field.set(object, rs.getDate(name));
                            break;
                        case "Time":
                            field.set(object, rs.getTime(name));
                            break;
                        default:
                            field.set(object, null);
                            break;
                    }
                }
                list.add(object);
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LOConnectionMYSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LOConnectionMYSQL.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
