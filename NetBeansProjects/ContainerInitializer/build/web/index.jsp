<%-- 
    Document   : index
    Created on : 30/03/2014, 12:13:27 PM
    Author     : Jav
--%>

<%@page import="Inizializar.Implementando"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% Implementando i= new Implementando();%>
        <%= i.sc %>
        <%= i.set %>
        
    </body>
</html>
