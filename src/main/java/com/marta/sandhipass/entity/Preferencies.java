/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.entity;

/**
 * Entitat Preferències.
 * @author Marta Bruch
 */
public class Preferencies {
    private int id;
    private int id_user;
    private String majuscules;
    private String minuscules;
    private String numeros;
    private String simbols;
    private String llargada;

    public Preferencies() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getMajuscules() {
        return majuscules;
    }

    public void setMajuscules(String majuscules) {
        this.majuscules = majuscules;
    }

    public String getMinuscules() {
        return minuscules;
    }

    public void setMinuscules(String minuscules) {
        this.minuscules = minuscules;
    }

    public String getNumeros() {
        return numeros;
    }

    public void setNumeros(String numeros) {
        this.numeros = numeros;
    }

    public String getSimbols() {
        return simbols;
    }

    public void setSimbols(String simbols) {
        this.simbols = simbols;
    }

    public String getLlargada() {
        return llargada;
    }

    public void setLlargada(String llargada) {
        this.llargada = llargada;
    }
    
}
