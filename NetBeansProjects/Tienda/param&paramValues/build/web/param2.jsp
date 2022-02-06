<%-- 
    Document   : param2
    Created on : 17/07/2014, 07:03:24 PM
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
        
        <form action="salida.jsp" method="post">
            <input type="text" name="nombre" value="b">
        <input type="submit" name="Acepto">
        </form>
        p: ${param.nombre}
    </body>
</html>
