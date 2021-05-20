/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.core;

/**
 * Classe que gestiona la ruta que rep de la vista.
 * @author Marta Brucha
 */
public class SandhiPassScreenResolver {
    /**
     * De l'adreça rebuda de la vista, elimina la barra "/".
     * @param path Ruta
     * @return La ruta sense la barra
     */
    public static String resolvePath(String path) {
        return path.replace("/", "");
    }    
}
