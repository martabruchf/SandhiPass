/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.vo;

import com.marta.sandhipass.entity.Contrasenya;
import com.marta.sandhipass.entity.Usuari;
import java.util.ArrayList;

/**
 * Mètode que conté els atributs per enviar a la vista.
 * @author Marta Bruch
 */
public class SandhiPassVO {
    private ArrayList <Contrasenya> arrayListContrasenya;
    private Contrasenya contrasenya;
    private Usuari usuari;
    private String bloquejarInput;
    private String display;
    private String botoGuardar;
    private String botoCrear;
    private String checkedTots;
    private String checkedMaj;
    private String checkedMin;
    private String checkedNum;
    private String checkedSim;
    private String radio20;
    private String radio16;
    private String radio8;
    private String radio6;
    private String radio4;
    private String missatge;
    private String error;
    private String missatgeCampObli;
    private String campNom;
    private String campUsuari;
    private String campContrasenya;
    private int idGuardar;
    private int idUsuari;

    public SandhiPassVO() {
    }

    public ArrayList<Contrasenya> getArrayListContrasenya() {
        return arrayListContrasenya;
    }

    public void setArrayListContrasenya(ArrayList<Contrasenya> arrayListContrasenya) {
        this.arrayListContrasenya = arrayListContrasenya;
    }

    public Contrasenya getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(Contrasenya contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public String getBloquejarInput() {
        return bloquejarInput;
    }

    public void setBloquejarInput(String bloquejarInput) {
        this.bloquejarInput = bloquejarInput;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getBotoGuardar() {
        return botoGuardar;
    }

    public void setBotoGuardar(String botoGuardar) {
        this.botoGuardar = botoGuardar;
    }

    public String getBotoCrear() {
        return botoCrear;
    }

    public void setBotoCrear(String botoCrear) {
        this.botoCrear = botoCrear;
    }

    public String getCheckedTots() {
        return checkedTots;
    }

    public void setCheckedTots(String checkedTots) {
        this.checkedTots = checkedTots;
    }

    public String getCheckedMaj() {
        return checkedMaj;
    }

    public void setCheckedMaj(String checkedMaj) {
        this.checkedMaj = checkedMaj;
    }

    public String getCheckedMin() {
        return checkedMin;
    }

    public void setCheckedMin(String checkedMin) {
        this.checkedMin = checkedMin;
    }

    public String getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(String checkedNum) {
        this.checkedNum = checkedNum;
    }

    public String getCheckedSim() {
        return checkedSim;
    }

    public void setCheckedSim(String checkedSim) {
        this.checkedSim = checkedSim;
    }

    public String getRadio20() {
        return radio20;
    }

    public void setRadio20(String radio20) {
        this.radio20 = radio20;
    }

    public String getRadio16() {
        return radio16;
    }

    public void setRadio16(String radio16) {
        this.radio16 = radio16;
    }

    public String getRadio8() {
        return radio8;
    }

    public void setRadio8(String radio8) {
        this.radio8 = radio8;
    }

    public String getRadio6() {
        return radio6;
    }

    public void setRadio6(String radio6) {
        this.radio6 = radio6;
    }

    public String getRadio4() {
        return radio4;
    }

    public void setRadio4(String radio4) {
        this.radio4 = radio4;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMissatgeCampObli() {
        return missatgeCampObli;
    }

    public void setMissatgeCampObli(String missatgeCampObli) {
        this.missatgeCampObli = missatgeCampObli;
    }

    public String getCampNom() {
        return campNom;
    }

    public void setCampNom(String campNom) {
        this.campNom = campNom;
    }

    public String getCampUsuari() {
        return campUsuari;
    }

    public void setCampUsuari(String campUsuari) {
        this.campUsuari = campUsuari;
    }

    public String getCampContrasenya() {
        return campContrasenya;
    }

    public void setCampContrasenya(String campContrasenya) {
        this.campContrasenya = campContrasenya;
    }

    public int getIdGuardar() {
        return idGuardar;
    }

    public void setIdGuardar(int idGuardar) {
        this.idGuardar = idGuardar;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }
        
}
