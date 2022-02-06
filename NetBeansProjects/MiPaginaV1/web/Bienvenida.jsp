<%-- 
    Document   : Bienvenida
    Created on : 11/05/2014, 10:24:38 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenid@</title>
    </head>
    <body bgcolor="black">
            <h1 align="center"> BIENVENIDO...</h1>
            <font color="white">
            <h3 align="center" font-color="blue"> TU ERES: </h3>
    <center>
            <%= request.getAttribute("nombre")%>
            <%= request.getAttribute("apellidos") %>
            <br>    
            <%= request.getAttribute("edad")%>
            <br>
            <%= request.getAttribute("telefono")%>
    </center>
            </font>
    </body>
</html>
