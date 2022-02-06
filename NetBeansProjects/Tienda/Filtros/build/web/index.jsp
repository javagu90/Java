<%-- 
    Document   : index
    Created on : 20/07/2014, 10:24:21 PM
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
        <% request.setAttribute("contry","Canada");%>
       hola: ${contry}
        <% request.getRequestDispatcher("ServletFiltro").forward(request,response);%>
    </body>
</html>
