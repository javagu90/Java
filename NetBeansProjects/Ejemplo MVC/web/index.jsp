<%-- 
    Document   : index
    Created on : 2/03/2014, 12:17:29 PM
    Author     : GrayFox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="Controller" method="GET">
        Nombre:   <input type="text" name="nombre">
        Apellido: <input type="text" name="apellido">
        Edad:     <input type="text" name="edad">
        <input type="submit" name="Enviar">
        </form>
    </body>
</html>
