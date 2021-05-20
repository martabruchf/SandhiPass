<%-- 
    Document   : edicio
    Created on : 7 de maig 2021, 16:05:01
    Author     : marta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>




<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <span class="navbar-brand mb-0 h1">SandhiPass</span>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

        </ul>
        <form action="/SandhiPass/Contrasenyes" name="formSortir" method="POST">
            <button class="btn btn-dark mr-4" title="Sortir" type="button" onclick="lanzarSortir('')" id="botosortir">
                Sortir
            </button>
            <input name="accionEvento" type="hidden" />
            <input name="inputIdUsuari" type="hidden" />
        </form>
        <form class="form-inline my-2 my-lg-0" action="/SandhiPass/Contrasenyes" name="formBuscar" method="POST">
            <input class="form-control mr-sm-2 ml-4" type="search" placeholder="Buscar" aria-label="Search" name="search" id="search">
            <button class="btn btn-dark my-2 my-sm-0" title="Buscar" type="button" onclick="lanzarBuscar('buscar')" id="botosearch">Buscar</button>
            <input name="accionEvento" type="hidden" />
        </form>
    </div>
</nav>

<c:if test="${VO.idUsuari == 0}">
    <jsp:include page="modal.jsp" flush="true"></jsp:include>
</c:if>

<div class="container mt-4">
    <div id="titol">
        <img class="mb-4" src="./Template/Sources/images/logo2.png"> 
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12 alert alert-light" role="alert">              
                <form action="/SandhiPass/Contrasenyes" name="formEdi" method="POST">           
                    <div class="form-group row">
                        <label for="nom" class="col-sm-2 col-form-label">Nom<span class="campObligatori">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="nom" id="nom" placeholder="P.ex.: Twitter, Banc..." value="${VO.contrasenya.nom}" ${VO.bloquejarInput}>
                            <div class="invalid-feedback" style="display:${VO.campNom}">
                                ${VO.missatgeCampObli}
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="url" class="col-sm-2 col-form-label">Adreça web</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="url" id="url" placeholder="www.twitter.cat" value="${VO.contrasenya.url}" ${VO.bloquejarInput}>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="usuari" class="col-sm-2 col-form-label">Usuari<span class="campObligatori">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="usuari" id="usuari" placeholder="Usuari pel lloc web" value="${VO.contrasenya.usuari}" ${VO.bloquejarInput}>
                            <div class="invalid-feedback" style="display:${VO.campUsuari}">
                                ${VO.missatgeCampObli}
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="contrasenya" class="col-sm-2 col-form-label">Contrasenya<span class="campObligatori">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="contrasenya" id="contrasenya" placeholder="Escriu la contrasenya o genera una aleatòria" value="${VO.contrasenya.contrasenya}" ${VO.bloquejarInput}>
                            <div class="invalid-feedback" style="display:${VO.campContrasenya}">
                                ${VO.missatgeCampObli}
                            </div>
                        </div>
                    </div>
                    <div style="display:${VO.display}">
                        <fieldset class="form-group row"">
                            <legend class="col-form-label col-sm-2 float-sm-left pt-0">Requisits</legend>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="majuscules" id="majuscules" value="majuscules" ${VO.checkedTots} ${VO.checkedMaj}>
                                    <label class="form-check-label" for="majuscules">Majúscules</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="minuscules" id="minuscules" value="minuscules" ${VO.checkedTots} ${VO.checkedMin}>
                                    <label class="form-check-label" for="minuscules">Minúscules</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="numeros" id="numeros" value="numeros" ${VO.checkedTots} ${VO.checkedNum}>
                                    <label class="form-check-label" for="numeros">Números</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="simbols" id="simbols" value="simbols" ${VO.checkedTots} ${VO.checkedSim}>
                                    <label class="form-check-label" for="simbols">Símbols</label>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="form-group row">
                            <legend class="col-form-label col-sm-2 float-sm-left pt-0">Longitud</legend>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="longitud" id="20" value="20" ${VO.radio20}>
                                    <label class="form-check-label" for="20">20</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="longitud" id="16" value="16" ${VO.radio16}>
                                    <label class="form-check-label" for="16">16</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="longitud" id="8" value="8" ${VO.radio8}>
                                    <label class="form-check-label" for="8">8</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="longitud" id="6" value="6" ${VO.radio6}>
                                    <label class="form-check-label" for="6">6</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="longitud" id="4" value="4" ${VO.radio4}>
                                    <label class="form-check-label" for="4">4</label>
                                </div>
                            </div>                        
                        </fieldset>
                        <button onclick="lanzarGuardar('generar', '${VO.idGuardar}', ${VO.idUsuari})" class="btn btn-info mb-4" title="Generar contrasenya aleatòria">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-key" viewBox="0 0 16 16">
                            <path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
                            <path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                            </svg>
                            Generar contrasenya
                        </button>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 text-center">
                            <button onclick="lanzarGuardar('guardar', '${VO.idGuardar}', ${VO.idUsuari})" class="btn btn-primary" title="Guardar" style="${VO.botoGuardar}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                <path d="M2 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H9.5a1 1 0 0 0-1 1v7.293l2.646-2.647a.5.5 0 0 1 .708.708l-3.5 3.5a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L7.5 9.293V2a2 2 0 0 1 2-2H14a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h2.5a.5.5 0 0 1 0 1H2z"/>
                                </svg>
                                Guardar
                            </button>
                            <button onclick="lanzarEdi('iniciar', ${VO.idUsuari})" class="btn btn-primary" title="Crear nova contrasenya" style="${VO.botoCrear}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                                <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                </svg>
                                Crear nova contrasenya
                            </button> 
                        </div>
                    </div>
                    <input name="accionEvento" type="hidden" />
                    <input name="inputIdGuardar" type="hidden" />
                    <input name="inputIdUsuari" type="hidden" />
                </form>  
            </div>
        </div>
    </div>
</div>

