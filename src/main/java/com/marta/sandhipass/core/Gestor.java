/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.core;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe abstracte del Gestor.
 * @author Marta Bruch
 */
public abstract class Gestor {
    private final HttpServletRequest request;
    private Object vo;

    public Gestor(HttpServletRequest request) {
        this.request = request;
    }

    public abstract void ejecutar(Map parameters);
    
    public HttpServletRequest getRequest() {
        return request;
    }

    public Object getVo() {
        return vo;
    }

    public void setVo(Object vo) {
        this.vo = vo;
    }

}
