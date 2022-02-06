<%-- 
    Document   : view
    Created on : 2/03/2014, 12:29:19 PM
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
        <%= request.getParameter("nombre")%>
        <%= request.getParameter("apellido")%>
        <%= request.getParameter("edad")%>
        
        atributte
        <%= request.getAttribute("nombre")%>
        <%= request.getAttribute("apellido")%>
        <%= request.getAttribute("edad")%>
        
        Datos del alumno ${alumno}
    </body>
</html>
