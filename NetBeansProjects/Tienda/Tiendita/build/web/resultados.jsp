<%-- 
    Document   : resultados
    Created on : 29/06/2014, 03:45:46 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--<h2>Producto: <%= request.getAttribute("art")%></h2>
        <h1>Precio:    <%= request.getAttribute("prec")%></h1>--%>
        <h1>Datos: <%= request.getAttribute("data")%></h1>
        <H3> <a href="buscador.jsp">realizar otra busqueda:</a>
        <a href="index.jsp">Inicio</a></H3>
    </body>
</html>
