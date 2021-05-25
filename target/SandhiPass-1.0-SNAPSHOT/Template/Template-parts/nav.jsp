<%-- 
    Document   : nav
    Created on : 25 de maig 2021, 16:18:13
    Author     : marta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!-- Barra de navegació -->
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
            <button class="btn btn-dark my-2 my-sm-0" title="Buscar" type="button" onclick="lanzarBuscar('buscar', ${VO.idUsuari})" id="botosearch">Buscar</button>
            <input name="accionEvento" type="hidden" />
            <input name="inputIdUsuari" type="hidden" value="${VO.idUsuari}" />
        </form>
    </div>
</nav>

<div class="container mt-4">
    <div id="titol">
        <img class="mb-4" src="./Template/Sources/images/logo2.png"> 
    </div>
</div>