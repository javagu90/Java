<%-- 
    Document   : index
    Created on : 2/03/2014, 12:18:29 PM
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
        <h1>BIENVENIDO </h1>
        <form action="Controlador" method="POST">
        Nombre <input type="text" name="nombre">
        Apellido <input type="text" name="apellido">
        Edad <input type="text" name="edad">
        <input type="submit" name="enviar">
        </form>
    </body>
</html>
