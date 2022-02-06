<%-- 
    Document   : fiveOut
    Created on : 16/07/2014, 05:22:28 PM
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
        Hello ${param[yourname]}
        <h1>NO MUESTRA EL NOMBRE PORQ PARAM SOLO HACE CASO AL REQUEST DE ESTA PAGINA</h1>
        <BR>
        a < b ${"a"<"b"}
        <br>
        b < a ${"b"<"a"}
        <br>
        pageScope : ${pageScope.yourname} <h1>no muestra nada porque no hay un pageContext.set</h1>
    </body>
</html>
