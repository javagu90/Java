<%-- 
    Document   : index
    Created on : 4/05/2014, 08:45:26 AM
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
        Jsp: <% request.setAttribute("mensaje.uno","hola"); %>
        <br>
        a:${mensaje.uno} :d
        <br>
        antes: ${param.nombre}:despues //agregamos querystring nombre=julio
        <br>
        la respuesta a lo anterior sseria
        <br>
        a: ${requestScope[mensaje]} :d los conchetes resuelven el problema de la convencion <br> va entre comillas porque
        <br>
        antes: ${cookie}: despues
        <br>
        antes: ${request}: despues
        <br>
        antes: ${pageContext.request}: despues
        <br>
        <% session.setAttribute("arreglo", new String []{"HOLA","ADIOS"}); %>
        <br>
        <br>
       <% java.util.ArrayList l= new java.util.ArrayList();
        l.add("curso");
        l.add("java");
        l.add("web componet"); %>
        <% application.setAttribute("lista", l); %>
        
        <br>
        <h1>Hello World!</h1>
    </body>
</html>
