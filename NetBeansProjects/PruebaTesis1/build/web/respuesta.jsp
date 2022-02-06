<%-- 
    Document   : respuesta
    Created on : 7/03/2015, 08:27:47 PM
    Author     : Javy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String x=(String)request.getAttribute("respuesta");
        out.println(x);
        %>
        <h1>Hello World!</h1>
    </body>
</html>
