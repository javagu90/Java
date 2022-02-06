<%-- 
    Document   : otro
    Created on : 20/07/2014, 03:52:46 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="tag" uri="WEB-INF/tag/libreriaTAGS.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        principio
        <br>
        <tag:etiquetaSimple>medio
            <h1>SKYP_BODY IGNORA TODA LA PAGINA DESPUES DE LA ETIQUETA</h1>
            <a href="otro2.jsp">otro 2</a>
        </tag:etiquetaSimple>
        <br>
        fin
    </body>
</html>
