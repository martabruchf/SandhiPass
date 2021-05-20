/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.core;

import com.marta.sandhipass.controllers.SandhiPassGestor;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author marta
 */
public class SandhiPassRequestResolver {
    // El que hi ha en el case s'ha de posar en un servlet-mapping al fitxer web.xml
    // També els hi hem de dir als formularis amb el action="/SandhiPass/Contrasenyes"

    public static Gestor resolveGestor(HttpServletRequest request) {
        switch (request.getServletPath().replace("/", "")) {
            case "Contrasenyes":
                return new SandhiPassGestor(request);
        }
        return null;
    }
}
