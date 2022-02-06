<%-- 
    Document   : siguiente
    Created on : 9/07/2014, 03:22:14 PM
    Author     : Jav
--%>

<%@page import="javax.swing.JOptionPane" import="java.util.*, java.io.*" %>
<%@page session="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/index.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DIRECTIVAS JSP <br>
            page<br>
            <%JOptionPane.showMessageDialog(null, "directiva page atributo import"); %>
            include<br>
            taglib
        </h1>
        
    </body>
</html>
