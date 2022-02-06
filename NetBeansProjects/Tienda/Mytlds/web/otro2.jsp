<%-- 
    Document   : otro2
    Created on : 20/07/2014, 05:59:27 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag2" uri="WEB-INF/tag/libreriaTAGS.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Inicio
        <br>
        <tag2:etiquetaSimple2>
            medio
        </tag2:etiquetaSimple2>
            <br>
            fin
            <h1>skip_Body y skip_page se saltan uno el cuerpo y el otro el resto de la pagina</h1>
            <br>
            
            <h1>eval_body_include y eval_Page evaluan el cuerpo y la pagina</h1>
            
            <a href="otro3.jsp">otro3</a>
    </body>
</html>
