<%-- 
    Document   : buscador
    Created on : 29/06/2014, 01:57:59 AM
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
        <form action="MyServlet" method="GET">
            Articulo: <input type="text" name="art">
            <button name="bus" type="submit">Buscar</button>
        </form>
    </body>
</html>
