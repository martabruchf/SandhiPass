/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.mysql.core;

import java.sql.ResultSet;
import java.util.List;

/**
 * Interfícia per la connexió a la base de dades.
 * Aquesta interfícia té les constants per la connexió.
 * @author Marta Bruch
 */
public interface ILOConnectionMYSQL {
    final String DATABASE = "sandhipass";
    final String PORT = "3306";
    final String DRIVER_URL = "jdbc:mysql://127.0.0.1:"+PORT+"/" + DATABASE;
    final String USER = "root";
    final String PASSWORD = "1234";

    void openConnection();
    void insert(String query);
    List<Object> select(String query, Class classType);
    void update(String query);
    void delete(String query);
    List<Object> readResultSet(ResultSet rs, Class classType) throws IllegalAccessException;
    void closeConnection();
}
