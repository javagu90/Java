<%-- 
    Document   : view
    Created on : 2/03/2014, 12:30:17 PM
    Author     : GrayFox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%=request.getAttribute("alumno")%>
        <%=request.getAttribute("nombre")%>
        <%=request.getAttribute("apellido")%>
        <%=request.getAttribute("edad")%>
        Datos del alumnoo ${alumno}
    </body>
</html>
