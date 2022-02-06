<%-- 
    Document   : index
    Created on : 4/05/2014, 11:05:12 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mi_taglib" uri="/WEB-INF/tlds/Funcionalidades" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%! utilidad.Alumno a= new utilidad.Alumno("Javier", "23" ); %>
        ${mi_taglib:imprimir()}
        
    </body>
</html>
