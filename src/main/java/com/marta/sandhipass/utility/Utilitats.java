/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.utility;

import com.marta.sandhipass.entity.Usuari;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Classe a on hi ha mètodes d'utilitats.
 * @author Marta Bruch
 */
public class Utilitats {

    /**
     * Mètode que crea un resum a través de la contrasenya de l'usuari. Guardem
     * el resum a l'atribut contrasenya.
     * @param usuari Usuari del qual volem fer un resum de la seva contrasenya
     * @return L'usuari amb el resum com a contrasenya
     */
    public Usuari crearResum(Usuari usuari) {
        String resum = BCrypt.hashpw(usuari.getContrasenya(), BCrypt.gensalt(16));
        usuari.setContrasenya(resum);
        return usuari;
    }

    /**
     * Mètode que compara si una contrasenya coincideix amb el resum guardat.
     * @param usuari Usuari el qual té la contrasenya a comparar
     * @param usuariGuardat Usuari el qual té el resum a comparar
     * @return Un Booleà dient true si la contrasenya coincideix amb el resum o false si no coincideix
     */
    public Boolean compararContrasenya(Usuari usuari, Usuari usuariGuardat) {
        Boolean coincideix = BCrypt.checkpw(usuari.getContrasenya(), usuariGuardat.getContrasenya());
        return coincideix;
    }

    /**
     * Mètode que genera una contrasenya aleatòria segons els criteris passats.
     * No hi ha el zero, l'u, les eles ni les is, per no crear confusió.
     * @param majuscules Booleà si la contrasenya ha de contenir com a mínim una majúscula
     * @param minuscules Booleà si la contrasenya ha de contenir com a mínim una minúscula
     * @param numeros Booleà si la contrasenya ha de contenir com a mínim un número
     * @param simbols Booleà si la contrasenya ha de contenir com a mínim un símbol
     * @param llargada Llargada de la contrasenya
     * @return La contrasenya generada
     */
    public String generarContrasenya(Boolean majuscules, Boolean minuscules, Boolean numeros, Boolean simbols, int llargada) {
        String contrasenya = "";
        String llNum = "23456789";
        String llSimbols = "!#*+-/?@_$%&=";
        String llMinuscules = "abcdefghijkmnpqrstuvwxyz";
        String llMajuscules = "ABCDEFGHJKLMNPQRSTUVWXYZ";
        String llista = "";
        try {
            SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
            // Omplim la llista per tenir els caràcters a utilitzar
            // I afegim un caràcter a la contrasenya
            if (numeros) {
                llista = llista + llNum;
                contrasenya = contrasenya + Character.toString(llNum.charAt(rand.nextInt(llNum.length())));
                llargada --;
            }
            if (minuscules) {
                llista = llista + llMinuscules;
                contrasenya = contrasenya + Character.toString(llMinuscules.charAt(rand.nextInt(llMinuscules.length())));
                llargada --;
            }

            if (simbols) {
                llista = llista + llSimbols;
                contrasenya = contrasenya + Character.toString(llSimbols.charAt(rand.nextInt(llSimbols.length())));
                llargada --;
            }
            if (majuscules) {
                llista = llista + llMajuscules;
                contrasenya = contrasenya + Character.toString(llMajuscules.charAt(rand.nextInt(llMajuscules.length())));
                llargada --;
            }
            // Omplim la contrasenya
            for (int i = 0; i < llargada; i++) {
                contrasenya = contrasenya + Character.toString(llista.charAt(rand.nextInt(llista.length())));
            }
        } catch (NoSuchAlgorithmException nsae) {
            // Forward to handler
        }
        return contrasenya;
    }

}
