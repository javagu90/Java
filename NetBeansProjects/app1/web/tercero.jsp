<%-- 
    Document   : tercero
    Created on : 23/03/2014, 12:00:11 PM
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
        <h1> Alumno con Session Get Attribute: <%= session.getAttribute("alumno") %> </h1>
        <h1> Alumno 2 con Session Get Attribute: <%= session.getAttribute("alumno2") %> </h1>
        <h1> Alumno con request Get Attribute: <%= request.getAttribute("alumno") %> </h1>
        <h1> Alumno 2 con request Get Attribute: <%= request.getAttribute("alumno2") %> </h1>
        <%= response%>
    </body>
</html>
