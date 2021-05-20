/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.entity;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author marta
 */
public class Login {
    private int id;
    private int id_usuari;
    private Date dia;
    private Time hora;

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

    
    
    

    
}
