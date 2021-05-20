<%-- 
    Document   : index
    Created on : 7 de maig 2021, 16:01:02
    Author     : marta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>SandhiPass</title>
        <jsp:include page="./Template/Template-subparts/header.jsp" flush="true"></jsp:include>
    </head>
    <body>
        <jsp:include page="./Template/Template-parts/edicio.jsp" flush="true"></jsp:include>
        <jsp:include page="./Template/Template-parts/info.jsp" flush="true"></jsp:include>
        <jsp:include page="./Template/Template-subparts/footer.jsp" flush="true"></jsp:include>
    </body>
    
</html>
