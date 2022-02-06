<%-- 
    Document   : accesso
    Created on : 31/08/2015, 09:16:23 PM
    Author     : Javy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(request.getSession()==null)
{
    response.sendRedirect("index.html");
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Acceso permitido al usuario </h1>
        <a href="Logout"><input type="button" value="Cerrar Sesión"/></a>
    </body>
</html>
