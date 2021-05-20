/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * Entitat Login.
 * @author Marta Bruch
 */
public class Login {
    private int id;
    private int id_usuari;
    private Date dia;
    private Time hora;
    private String sistema_operatiu;
    private String navegador;

    public Login() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuari() {
        return id_usuari;
    }

    public void setId_usuari(int id_usuari) {
        this.id_usuari = id_usuari;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getSistema_operatiu() {
        return sistema_operatiu;
    }

    public void setSistema_operatiu(String sistema_operatiu) {
        this.sistema_operatiu = sistema_operatiu;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }
    
}
