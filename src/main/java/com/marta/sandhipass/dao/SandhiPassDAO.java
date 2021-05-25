/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.dao;

import com.marta.sandhipass.entity.Contrasenya;
import com.marta.sandhipass.entity.Usuari;
import com.marta.sandhipass.mysql.core.LOConnectionMYSQL;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe del DAO (Data Access Object).
 * @author Marta Bruch
 */
public class SandhiPassDAO {
    private LOConnectionMYSQL connectionMYSQL;
    
    /**
     * Crea una instància de la classe SandhiPassDAO.
     */
    public SandhiPassDAO() {
        this.connectionMYSQL = new LOConnectionMYSQL();
    }
    
    /**
     * Mètode que et retorna totes les contrasenyes que té un usuari ordenades
     * pel nom.
     * @param id_usuari id de l'usuari
     * @return Una llista de totes les contrasenyes
     */
    public List<Object> llistaTotesContrasenyes(int id_usuari){
        String query = "SELECT * FROM contrasenya WHERE id_user = " + id_usuari + " ORDER BY nom";
        List<Object> llista = connectionMYSQL.select(query, Contrasenya.class);
        return llista;
    }
    
    /**
     * Mètode que et retorna una contrasenya en concret.
     * @param contrasenya Contrasenya a buscar
     * @return La contrasenya trobada
     */
    public Contrasenya buscarContrasenya(Contrasenya contrasenya){
        Usuari usuari = buscarIdUsuari(contrasenya);
        Contrasenya contrasenyaTrobada = new Contrasenya();
        ArrayList<Contrasenya> llistaC;
        String query = "SELECT id, id_user, nom, url, usuari, cast(aes_decrypt(contrasenya, '" + usuari.getContrasenya() + "') as char) as contrasenya FROM contrasenya WHERE id = " + contrasenya.getId();
        List<Object> llista = connectionMYSQL.select(query, Contrasenya.class);
        llistaC = (ArrayList<Contrasenya>) (Object) llista;
        for (Contrasenya x : llistaC){
            contrasenyaTrobada.setId(x.getId());
            contrasenyaTrobada.setId_user(x.getId_user());
            contrasenyaTrobada.setNom(x.getNom());
            contrasenyaTrobada.setUrl(x.getUrl());
            contrasenyaTrobada.setUsuari(x.getUsuari());
            contrasenyaTrobada.setContrasenya(x.getContrasenya());
        }
        return contrasenyaTrobada;
    }
    
    /**
     * Mètode que et retorna un usuari en concret buscat pel seu id.
     * @param contrasenya Contrasenya la qual conté l'id de l'usuari a buscar
     * @return L'usuari trobat
     */
    public Usuari buscarIdUsuari(Contrasenya contrasenya){
        Usuari usuari = new Usuari();
        ArrayList<Usuari> llistaU;
        String query = "SELECT id, mail, contrasenya FROM usuari WHERE id = " + contrasenya.getId_user();
        List<Object> llista = connectionMYSQL.select(query, Usuari.class);
        llistaU = (ArrayList<Usuari>) (Object) llista;
        for (Usuari x : llistaU){
            usuari.setId(x.getId());
            usuari.setMail(x.getMail());
            usuari.setContrasenya(x.getContrasenya());
        }
        return usuari;
    }
    
    /**
     * Mètode que busca un usuari pel seu mail.
     * @param usuari Usuari a buscar
     * @return L'usuari trobat
     */
    public Usuari buscarMailUsuari(Usuari usuari){        
        ArrayList<Usuari> llistaU;
        Usuari usuariTrobat = new Usuari();
        String query = "SELECT id, mail, contrasenya FROM usuari WHERE mail like '" + usuari.getMail() + "'";
        List<Object> llista = connectionMYSQL.select(query, Usuari.class);
        llistaU = (ArrayList<Usuari>) (Object) llista;
        for (Usuari x : llistaU){
            usuariTrobat.setId(x.getId());
            usuariTrobat.setMail(x.getMail());
            usuariTrobat.setContrasenya(x.getContrasenya());
        }
        return usuariTrobat;
    }
    
    
    /**
     * Mètode que retorna una llista de contrasenyes que conté el criteri de cerca.
     * @param paraula Cadena a cercar
     * @param id_usuari id de l'usuari
     * @return Una llista de les contrasenyes trobades
     */
    public List<Object> buscarContrasenyaPerNom(String paraula, int id_usuari){
        paraula = "%" + paraula + "%";
        String query = "SELECT * FROM contrasenya WHERE id_user = " + id_usuari + " and (nom like '" + paraula + "' or url like '"+ paraula + "' or usuari like '" + paraula + "') order by nom";
        List<Object> llista = connectionMYSQL.select(query, Contrasenya.class);
        return llista;
    }
    
    /**
     * Mètode que insereix una contrasenya nova a la base de dades.
     * Encripta la columna de la contrasenya, utilitzant com a key
     * el resum de l'usuari.
     * @param contrasenya Contrasenya a inserir
     */
    public void insertarContrasenya(Contrasenya contrasenya){
        Usuari usuari = buscarIdUsuari(contrasenya);
        String query = "INSERT INTO contrasenya(id_user, nom, url, usuari, contrasenya) VALUES("+ contrasenya.getId_user() + ", '" + contrasenya.getNom() + "', '" + contrasenya.getUrl() + "', '" + contrasenya.getUsuari() + "', aes_encrypt('" + contrasenya.getContrasenya() + "', '" + usuari.getContrasenya() + "'))";
        connectionMYSQL.insert(query);
    }
    
    /**
     * Mètode que insereix un usuari nou a la base de dades.
     * @param usuari Usuari a inserir
     */
    public void insertarUsuari(Usuari usuari){
        String query = "INSERT INTO usuari(mail, contrasenya) VALUES('" + usuari.getMail() + "', '" + usuari.getContrasenya() + "')";
        connectionMYSQL.insert(query);        
    }
    
    /**
     * Mètode que modifica una contrasenya a la base de dades.
     * Encripta la columna de la contrasenya, utilitzant com a key
     * el resum de l'usuari.
     * @param contrasenya Contrasenya a modificar
     */
    public void modificarContrasenya(Contrasenya contrasenya){
        Usuari usuari = buscarIdUsuari(contrasenya);
        String query = "UPDATE contrasenya SET nom = '" + contrasenya.getNom() + "', url = '" + contrasenya.getUrl() + "', usuari = '" + contrasenya.getUsuari() + "', contrasenya = aes_encrypt('" + contrasenya.getContrasenya() + "', '" + usuari.getContrasenya() + "') WHERE id = " + contrasenya.getId();
        connectionMYSQL.update(query);        
    }
    
    /**
     * Mètode que eliminia una contrasenya de la base de dades.
     * @param contrasenya Contrasenya a eliminar
     */
    public void eliminarContrasenya(Contrasenya contrasenya){
        String query = "DELETE FROM contrasenya WHERE id = " + contrasenya.getId();
        connectionMYSQL.delete(query);
    }
    
}
