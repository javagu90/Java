<%-- 
    Document   : salida
    Created on : 17/07/2014, 06:27:24 PM
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
        Nombre con param.nombre: ${param.nombre}
        <br>
        <br>
        Nombre con param["nombre"]: ${param["nombre"]}
        <br>
        <br>
        Nombre con paramValues.nombre: ${paramValues.nombre}
        <br>
        <br>
        Nombre con paramValues["nombre"]:${paramValues["nombre"]}
        <br>
        <br>
        Nombre con paramValues.nombre["0"]:${paramValues.nombre["0"]}
        <br>
        <br>
        Nombre con paramValues.nombre[0]:${paramValues.nombre[0]}
        <br>
        <br>
        Nombre con paramValues["nombre"]["0"]:${paramValues["nombre"]["0"]}
        <br>
        <a href="cadenas.jsp">parameter values con cadenas</a>
    </body>
</html>
