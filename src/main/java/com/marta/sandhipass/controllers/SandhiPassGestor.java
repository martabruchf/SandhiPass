/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marta.sandhipass.controllers;

import com.marta.sandhipass.vo.SandhiPassVO;
import com.marta.sandhipass.core.Gestor;
import com.marta.sandhipass.dao.SandhiPassDAO;
import com.marta.sandhipass.entity.Contrasenya;
import com.marta.sandhipass.entity.Usuari;
import com.marta.sandhipass.utility.Utilitats;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Gestor de l'aplicació.
 *
 * @author Marta Bruch
 */
public class SandhiPassGestor extends Gestor {

    public SandhiPassGestor(HttpServletRequest request) {
        super(request);
    }

    /**
     * Mètode principal del gestor.
     *
     * @param parameters Paràmetres que rep de la vista
     */
    @Override
    public void ejecutar(Map parameters) {
        SandhiPassVO passVO = new SandhiPassVO();
        SandhiPassDAO passDAO = new SandhiPassDAO();
        Contrasenya contrasenya = new Contrasenya();
        Usuari usuari = new Usuari();
        int id_usuari = 0;
        if (getRequest().getParameter("inputIdUsuari") != null) {
            id_usuari = Integer.parseInt(getRequest().getParameter("inputIdUsuari"));
        }
        contrasenya.setId_user(id_usuari);
        String evento = getRequest().getParameter("accionEvento");
        if (evento == null) {
            evento = "iniciar";
        } else if ("".equals(evento)) {
            evento = "buscar";
        }

        switch (evento) {
            case "registrar":
                usuari.setMail(getRequest().getParameter("registreMail"));
                usuari.setContrasenya(getRequest().getParameter("registreContrasenya"));
                actionRegistrar(passVO, passDAO, usuari);
                break;
            case "iniciarsessio":
                usuari.setMail(getRequest().getParameter("registreMail"));
                usuari.setContrasenya(getRequest().getParameter("registreContrasenya"));
                actionIniciarSessio(passVO, passDAO, usuari);
                break;
            case "iniciar":
                actionIniciar(passVO, passDAO, id_usuari);
                String botoCrear = "display: none;";
                String botoGuardar = "display: inline;";
                String radio16 = "checked";
                String checkedTots = "checked";
                passVO.setBotoCrear(botoCrear);
                passVO.setBotoGuardar(botoGuardar);
                passVO.setRadio16(radio16);
                passVO.setCheckedTots(checkedTots);
                setVo(passVO);
                break;
            case "consultar":
                contrasenya.setId(Integer.parseInt(getRequest().getParameter("inputid")));
                actionConsultar(passVO, passDAO, contrasenya);
                actionIniciar(passVO, passDAO, id_usuari);
                break;
            case "editar":
                contrasenya.setId(Integer.parseInt(getRequest().getParameter("inputid")));
                actionEditar(passVO, passDAO, contrasenya);
                actionIniciar(passVO, passDAO, id_usuari);
                break;
            case "eliminar":
                contrasenya.setId(Integer.parseInt(getRequest().getParameter("inputid")));
                actionEliminar(passVO, passDAO, contrasenya, id_usuari);
                break;
            case "guardar":
                actionGuardar(passVO, passDAO, contrasenya, id_usuari);
                break;
            case "buscar":
                actionBuscar(passVO, passDAO, id_usuari);
                break;
            case "generar":
                actionGenerarContrasenya(passVO, contrasenya, id_usuari);
                actionIniciar(passVO, passDAO, id_usuari);
                break;
        }
    }

    /**
     * Mètode que si un usuari no existeix l'insereix a la base de dades. I tot
     * seguit li inicia la sessió.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param usuari Usuari per registrar
     */
    private void actionRegistrar(SandhiPassVO passVO, SandhiPassDAO passDAO, Usuari usuari) {
        Utilitats util = new Utilitats();
        Usuari usuariTrobat = new Usuari();
        Usuari usuariOriginal = new Usuari();
        /* Guardem les dades originals en un altre usuari per poder iniciar sessió
        i poder comparar la contrasenya amb el resum*/
        usuariOriginal.setContrasenya(usuari.getContrasenya());
        usuariOriginal.setMail(usuari.getMail());
        usuariOriginal.setId(usuari.getId());
        usuariTrobat = passDAO.buscarMailUsuari(usuari);
        String missatgeUsuari = "";
        String errorUsuari = "none;";
        String missatgeContrasenya = "";
        String errorContrasenya = "none;";
        // Si no ha entrat la contrasenya
        if (usuari.getContrasenya().isEmpty()) {
            missatgeContrasenya = "Camp obligatori.";
            errorContrasenya = "inline;";
        } else if (usuari.getMail().isEmpty()) {
            missatgeUsuari = "Camp obligatori.";
            errorUsuari = "inline;";
        } else {
            if (usuariTrobat.getMail() == null) {
                // Usuari nou
                usuari = util.crearResum(usuari);
                passDAO.insertarUsuari(usuari);
                actionIniciarSessio(passVO, passDAO, usuariOriginal);
            } else {
                missatgeUsuari = "Aquest usuari ja existeix. Prova d'iniciar sessió.";
                errorUsuari = "inline;";
                passVO.setMissatgeUsuari(missatgeUsuari);
                passVO.setErrorUsuari(errorUsuari);
            }
        }
        passVO.setUsuari(usuari);
        passVO.setMissatgeUsuari(missatgeUsuari);
        passVO.setErrorUsuari(errorUsuari);
        passVO.setMissatgeContrasenya(missatgeContrasenya);
        passVO.setErrorContrasenya(errorContrasenya);
        setVo(passVO);
    }

    /**
     * Mètode que inicia la sessió d'un usuari registrat.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param usuari Usuari
     */
    private void actionIniciarSessio(SandhiPassVO passVO, SandhiPassDAO passDAO, Usuari usuari) {
        Utilitats util = new Utilitats();
        Usuari usuariTrobat = new Usuari();
        usuariTrobat = passDAO.buscarMailUsuari(usuari);
        String missatge = "";
        String error = "none;";
        if (usuariTrobat.getMail() != null) {
            if (util.compararContrasenya(usuari, usuariTrobat)) {
                actionIniciar(passVO, passDAO, usuariTrobat.getId());
                String botoCrear = "display: none;";
                String botoGuardar = "display: inline;";
                String radio16 = "checked";
                String checkedTots = "checked";
                int idUsuari = usuariTrobat.getId();
                passVO.setBotoCrear(botoCrear);
                passVO.setBotoGuardar(botoGuardar);
                passVO.setRadio16(radio16);
                passVO.setCheckedTots(checkedTots);
                passVO.setIdUsuari(idUsuari);
                setVo(passVO);
            } else {
                missatge = "Usuari i/o contrasenya incorrecte.";
                error = "inline;";
                passVO.setMissatgeContrasenya(missatge);
                passVO.setErrorContrasenya(error);
                passVO.setUsuari(usuari);
                setVo(passVO);
            }
        } else {
            missatge = "Usuari no trobat. Prova de registrar-se.";
            error = "inline;";
            passVO.setMissatgeUsuari(missatge);
            passVO.setErrorUsuari(error);
            passVO.setUsuari(usuari);
            setVo(passVO);
        }
    }

    /**
     * Mètode que crida al DAO per recuperar la llista completa de les
     * contrasenyes d'un usuari en concret. I envia la llista al VO.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param id_usuari int
     */
    private void actionIniciar(SandhiPassVO passVO, SandhiPassDAO passDAO, int id_usuari) {
        ArrayList<Contrasenya> llistaContrasenyes;
        llistaContrasenyes = (ArrayList<Contrasenya>) (Object) passDAO.llistaTotesContrasenyes(id_usuari);
        passVO.setArrayListContrasenya(llistaContrasenyes);
        passVO.setIdUsuari(id_usuari);
    }

    /**
     * Mètode que crida al DAO per consultar una contrasenya en concret. I envia
     * la contrasenya al VO. També oculta de la vista la part de generar una
     * contrasenya i el botó de guardar i mostra el botó de crear nova
     * contrasenya.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param contrasenya Contrasenya
     */
    private void actionConsultar(SandhiPassVO passVO, SandhiPassDAO passDAO, Contrasenya contrasenya) {
        String botoCrear = "display: inline;";
        String botoGuardar = "display: none;";
        String display = "none";
        String bloquejarInput = "disabled=''";
        String radio16 = "checked";
        String checkedTots = "checked";
        contrasenya = passDAO.buscarContrasenya(contrasenya);
        passVO.setContrasenya(contrasenya);
        passVO.setBloquejarInput(bloquejarInput);
        passVO.setDisplay(display);
        passVO.setBotoCrear(botoCrear);
        passVO.setBotoGuardar(botoGuardar);
        passVO.setRadio16(radio16);
        passVO.setCheckedTots(checkedTots);
        passVO.setIdUsuari(contrasenya.getId());
        setVo(passVO);
    }

    /**
     * Mètode que crida al DAO per consultar una contrasenya en concret. I envia
     * la contrasenya al VO per poder-la editar. Mostra la part de la vista per
     * generar una contrasenya.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param contrasenya Contrasenya a editar
     */
    private void actionEditar(SandhiPassVO passVO, SandhiPassDAO passDAO, Contrasenya contrasenya) {
        String botoCrear = "display: none;";
        String botoGuardar = "display: inline;";
        String display = "";
        String bloquejarInput = "";
        String radio16 = "checked";
        String checkedTots = "checked";
        int idGuardar = contrasenya.getId();
        contrasenya = passDAO.buscarContrasenya(contrasenya);
        passVO.setContrasenya(contrasenya);
        passVO.setBloquejarInput(bloquejarInput);
        passVO.setDisplay(display);
        passVO.setBotoCrear(botoCrear);
        passVO.setBotoGuardar(botoGuardar);
        passVO.setIdGuardar(idGuardar);
        passVO.setRadio16(radio16);
        passVO.setCheckedTots(checkedTots);
        passVO.setIdUsuari(contrasenya.getId());
        setVo(passVO);
    }

    /**
     * Mètode que crida al DAO per guardar una contrasenya. Si la contrasenya
     * passada no té id, vol dir que és una contrasenya nova i la insereix a la
     * base de dades. Si la contrasenya passada té id, vol dir que la
     * contrasenya ja existeix, per tant, guardarà els canvis de la contrasenya
     * en concret.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param contrasenya contrasenya
     * @param id_usuari id de l'usuari
     */
    private void actionGuardar(SandhiPassVO passVO, SandhiPassDAO passDAO, Contrasenya contrasenya, int id_usuari) {
        Boolean validar;
        String idContrasenya = getRequest().getParameter("inputIdGuardar");
        int idGuardar = Integer.parseInt(getRequest().getParameter("inputIdGuardar"));
        contrasenya.setId_user(id_usuari);
        contrasenya.setNom(getRequest().getParameter("nom"));
        contrasenya.setUrl(getRequest().getParameter("url"));
        contrasenya.setUsuari(getRequest().getParameter("usuari"));
        contrasenya.setContrasenya(getRequest().getParameter("contrasenya"));
        validar = validarCamps(passVO, contrasenya);
        // Si tots els camps estan emplenats
        if (validar) {
            // Per guardar una contrasenya nova
            if ("0".equals(idContrasenya) || idContrasenya == null) {
                passDAO.insertarContrasenya(contrasenya);
            } else {
                // Per modificar una contrasenya
                contrasenya.setId(Integer.parseInt(getRequest().getParameter("inputIdGuardar")));
                passDAO.modificarContrasenya(contrasenya);
            }
        } else {
            contrasenya.setId(Integer.parseInt(getRequest().getParameter("inputIdGuardar")));
            passVO.setContrasenya(contrasenya);
            passVO.setIdGuardar(idGuardar);
        }
        actionIniciar(passVO, passDAO, id_usuari);
        String botoCrear = "display: none;";
        String botoGuardar = "display: inline;";
        String radio16 = "checked";
        String checkedTots = "checked";
        passVO.setBotoCrear(botoCrear);
        passVO.setBotoGuardar(botoGuardar);
        passVO.setRadio16(radio16);
        passVO.setCheckedTots(checkedTots);
        passVO.setIdUsuari(id_usuari);
        setVo(passVO);
    }

    /**
     * Mètode que comprova si els camps nom, usuari i contrasenyes estan
     * emplenats.
     *
     * @param passVO SandhiPassVO
     * @param contrasenya Contrasenya per comprovar els camps
     * @return Retorna true si tots els camps estan emplenats
     */
    private Boolean validarCamps(SandhiPassVO passVO, Contrasenya contrasenya) {
        Boolean campNom, campUsuari, campContrasenya;
        String error, missatge;
        campNom = campUsuari = campContrasenya = true;
        missatge = "Camp obligatori.";
        error = "inline;";

        if (contrasenya.getNom().isEmpty()) {
            campNom = false;
            passVO.setMissatgeCampObli(missatge);
            passVO.setCampNom(error);
        }
        if (contrasenya.getUsuari().isEmpty()) {
            campUsuari = false;
            passVO.setMissatgeCampObli(missatge);
            passVO.setCampUsuari(error);
        }
        if (contrasenya.getContrasenya().isEmpty()) {
            campContrasenya = false;
            passVO.setMissatgeCampObli(missatge);
            passVO.setCampContrasenya(error);
        }
        return campNom && campUsuari && campContrasenya == true;
    }

    /**
     * Mètode que crida al DAO per buscar les contrasenyes que continguin el
     * criteri de cerca. Envia al VO la llista de les contrasenyes.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param id_usuari id de l'usuari
     */
    private void actionBuscar(SandhiPassVO passVO, SandhiPassDAO passDAO, int id_usuari) {
        String nom;
        String botoCrear = "display: none;";
        String botoGuardar = "display: inline;";
        String radio16 = "checked";
        String checkedTots = "checked";
        ArrayList<Contrasenya> llistaContrasenyes;
        nom = getRequest().getParameter("search");
        llistaContrasenyes = (ArrayList<Contrasenya>) (Object) passDAO.buscarContrasenyaPerNom(nom, id_usuari);
        if (llistaContrasenyes.size() > 0) {
            passVO.setArrayListContrasenya(llistaContrasenyes);
            passVO.setBotoCrear(botoCrear);
            passVO.setRadio16(radio16);
            passVO.setCheckedTots(checkedTots);
            passVO.setIdUsuari(id_usuari);
            setVo(passVO);
        } else {
            actionIniciar(passVO, passDAO, id_usuari);
            passVO.setBotoCrear(botoCrear);
            passVO.setBotoGuardar(botoGuardar);
            passVO.setRadio16(radio16);
            passVO.setCheckedTots(checkedTots);
            passVO.setIdUsuari(id_usuari);
            setVo(passVO);
        }
    }

    /**
     * Mètode que crida al DAO per eliminar una contrasenya.
     *
     * @param passVO SandhiPassVO
     * @param passDAO SandhiPassDAO
     * @param contrasenya Contrasenya per eliminar
     * @param id_usuari id de l'usuari
     */
    private void actionEliminar(SandhiPassVO passVO, SandhiPassDAO passDAO, Contrasenya contrasenya, int id_usuari) {
        passDAO.eliminarContrasenya(contrasenya);
        actionIniciar(passVO, passDAO, id_usuari);
        String botoCrear = "display: none;";
        String botoGuardar = "display: inline;";
        String radio16 = "checked";
        String checkedTots = "checked";
        passVO.setBotoCrear(botoCrear);
        passVO.setBotoGuardar(botoGuardar);
        passVO.setRadio16(radio16);
        passVO.setCheckedTots(checkedTots);
        passVO.setIdUsuari(id_usuari);
        setVo(passVO);
    }

    /**
     * Mètode que genera una contrasenya aleatòria segons els criteris que ha
     * triat l'usuari.
     *
     * @param passVO SandhiPassVO
     * @param contrasenya Contrasenya que s'està creant
     */
    private void actionGenerarContrasenya(SandhiPassVO passVO, Contrasenya contrasenya, int id_usuari) {
        int longitud, idGuardar = -1;
        Boolean majuscules, minuscules, numeros, simbols;
        String radio20, radio16, radio8, radio6, radio4, checkedMaj, checkedMin, checkedNum, checkedSim;
        String[] checkbox = new String[1];
        Utilitats utility = new Utilitats();
        String idContrasenya = getRequest().getParameter("inputIdGuardar");
        String botoCrear = "display: none;";
        String botoGuardar = "display: inline;";
        String campRequisits = "none;";
        String missatge = "";
        majuscules = minuscules = numeros = simbols = false;
        radio20 = radio16 = radio8 = radio6 = radio4 = checkedMaj = checkedMin = checkedNum = checkedSim = "checked";
        contrasenya.setNom(getRequest().getParameter("nom"));
        contrasenya.setUrl(getRequest().getParameter("url"));
        contrasenya.setUsuari(getRequest().getParameter("usuari"));
        longitud = Integer.parseInt(getRequest().getParameter("longitud"));
        // Recuperem si han clicat en algun checkbox
        checkbox = getRequest().getParameterValues("majuscules");
        if (checkbox != null) {
            majuscules = true;
            passVO.setCheckedMaj(checkedMaj);
        }
        checkbox = getRequest().getParameterValues("minuscules");
        if (checkbox != null) {
            minuscules = true;
            passVO.setCheckedMin(checkedMin);
        }
        checkbox = getRequest().getParameterValues("numeros");
        if (checkbox != null) {
            numeros = true;
            passVO.setCheckedNum(checkedNum);
        }
        checkbox = getRequest().getParameterValues("simbols");
        if (checkbox != null) {
            simbols = true;
            passVO.setCheckedSim(checkedSim);
        }

        if (longitud == 20) {
            passVO.setRadio20(radio20);
        }
        if (longitud == 16) {
            passVO.setRadio16(radio16);
        }
        if (longitud == 8) {
            passVO.setRadio8(radio8);
        }
        if (longitud == 6) {
            passVO.setRadio6(radio6);
        }
        if (longitud == 4) {
            passVO.setRadio4(radio4);
        }

        if (!"0".equals(idContrasenya) || idContrasenya != null) {
            idGuardar = Integer.parseInt(getRequest().getParameter("inputIdGuardar"));
            passVO.setIdGuardar(idGuardar);
        }
        // Comprovem que almenys hagin clicat en algun checkbox
        if (!majuscules && !minuscules && !numeros && !simbols) {
            campRequisits = "inline;";
            missatge = "Com a mínim ha de selecionar un requisit.";
            passVO.setMissatgeCampObli(missatge);
            passVO.setCampRequisits(campRequisits);
        } else {
            // Si hi ha algun checkbox clicat F
            // Cridem el mètode perquè ens generi una contrasenya aleatòria
            contrasenya.setContrasenya(utility.generarContrasenya(majuscules, minuscules, numeros, simbols, longitud));
        }
        passVO.setContrasenya(contrasenya);
        passVO.setBotoCrear(botoCrear);
        passVO.setBotoGuardar(botoGuardar);
        passVO.setIdUsuari(id_usuari);
        setVo(passVO);
    }

}
