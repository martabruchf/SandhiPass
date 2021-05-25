/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.servlet;

import com.marta.sandhipass.core.Gestor;
import com.marta.sandhipass.core.SandhiPassRequestResolver;
import com.marta.sandhipass.core.SandhiPassScreenResolver;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe del Servlet.
 * @author Marta Bruch
 */
@WebServlet(name = "SandhiPassServlet", urlPatterns = {"/SandhiPassServlet"})
public class SandhiPassServlet extends HttpServlet {

    // Serveix per dir-li a quin gestor ha d'anar.
    // 1r anar a resoveGestor(request) (control + clic)
    // 4t anar a la carpeta FLSWeb/Web Pages/WEB-INF/web.xml i copiar un servlet-mapping i modificar-lo 
    /**
     * Mètode que rep una invocació i genera una resposta en funció de les dades de la invocació.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException Llança l'excepció ServletException
     * @throws IOException Llança l'excepció IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gestor gestor = SandhiPassRequestResolver.resolveGestor(request);
        gestor.ejecutar(request.getParameterMap());
        request.setAttribute("VO", gestor.getVo());
        request.setAttribute("templatePartName", SandhiPassScreenResolver.resolvePath(request.getServletPath()));
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Mètode per la petició Get.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException Llança l'excepció ServletException
     * @throws IOException Llança l'excepció IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Mètode per la petició Post.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException Llança l'excepció ServletException
     * @throws IOException Llança l'excepció IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);        
    }

    /**
     * Mètode que recupera la informació del Servlet.
     * @return La descripció
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
