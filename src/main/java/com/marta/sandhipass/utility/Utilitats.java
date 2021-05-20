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

/**
 * Classe a on hi ha mètodes d'utilitats.
 * @author Marta Bruch
 */
public class Utilitats {

    public String encriptar(Usuari usuari) {
        return null;
    }

    public String desencriptar(String contrasenya) {
        return null;
    }

    /**
     * Mètode que genera una contrasenya aleatòria segons els criteris passats
     * @param majuscules Booleà si la contrasenya ha de contenir com a mínim una majúscula
     * @param minuscules Booleà si la contrasenya ha de contenir com a mínim una minúscula
     * @param numeros Booleà si la contrasenya ha de contenir com a mínim un número
     * @param simbols Booleà si la contrasenya ha de contenir com a mínim un símbol
     * @param llargada Llargada de la contrasenya
     * @return La contrasenya generada
     */
    public String generarContrasenya(Boolean majuscules, Boolean minuscules, Boolean numeros, Boolean simbols, int llargada) {
        String contrasenya = "";
        String llNum = "0123456789";
        String llSimbols = "!#*+-/?@_$%&=";
        String llMinuscules = "abcdefghijklmnopqrstuvwxyz";
        String llMajuscules = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
