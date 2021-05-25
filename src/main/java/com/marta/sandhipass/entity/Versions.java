/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.entity;

import java.sql.Date;

/**
 * Entitat Versions.
 * @author Marta Bruch
 */
public class Versions {
    private int id;
    private int id_contrasenya;
    private String contrasenya;
    private Date data_baixa;

    /**
     * Crea una instància de la classe Versions
     */
    public Versions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_contrasenya() {
        return id_contrasenya;
    }

    public void setId_contrasenya(int id_contrasenya) {
        this.id_contrasenya = id_contrasenya;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Date getData_baixa() {
        return data_baixa;
    }

    public void setData_baixa(Date data_baixa) {
        this.data_baixa = data_baixa;
    }
    
    
}
