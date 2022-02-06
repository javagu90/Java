<%-- 
    Document   : index
    Created on : 19/07/2014, 06:21:10 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="etiketas" uri="WEB-INF/tlds/misEtiquetas.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <etiketas:simpletagsuport1/>
    <br>
    etiqueta sts con attributos<etiketas:STSuma sumando1="4" sumando2="5"/>
    <br>
    etiqueta sts con attributos<etiketas:STSuma sumando1="${40}" sumando2="${5}"/>
    <form action="out.jsp">
        S1: <input type="text" name="s1"/>
        S2: <input type="text" name="s2"/>
        <input type="submit" name="enviar"/>
    </form>
    <br>
    convierte a mayusculas: <etiketas:Mayusculas nombre="Javier">hola como estas?</etiketas:Mayusculas>
    <br>
    <a href="otro.jsp">tag support</a>
    </body>
</html>
