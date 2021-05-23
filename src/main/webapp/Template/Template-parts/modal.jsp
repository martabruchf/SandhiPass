<%-- 
    Document   : modal
    Created on : 17 de maig 2021, 16:12:54
    Author     : marta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="java.util.*" %>


<!-- Modal del login -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div id="titol">
                    <img class="mb-4" src="./Template/Sources/images/logo2.png"> 
                </div>      
            </div>
            <div class="modal-body">
                <form action="/SandhiPass/Contrasenyes" name="formModal" method="POST">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email<span class="campObligatori">*</span></label>
                        <input type="email" class="form-control" name="registreMail" id="registreMail" aria-describedby="emailHelp" value="${VO.usuari.mail}">
                        <div class="invalid-feedback" style="display:${VO.errorUsuari}">
                            ${VO.missatgeUsuari}
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Contrasenya<span class="campObligatori">*</span></label>
                        <input type="password" class="form-control" name="registreContrasenya" id="registreContrasenya" value="${VO.usuari.contrasenya}">
                        <div class="invalid-feedback" style="display:${VO.errorContrasenya}">
                            ${VO.missatgeContrasenya}
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button onclick="lanzarModal('registrar')" class="btn btn-primary" title="Registrar-se">Registrar-se</button>
                        <button onclick="lanzarModal('iniciarsessio')" class="btn btn-primary" title="Iniciar sessió">Inicar sessió</button>
                    </div>
                    <input name="accionEvento" type="hidden" />
                </form>
            </div>
        </div>
    </div>
</div>
