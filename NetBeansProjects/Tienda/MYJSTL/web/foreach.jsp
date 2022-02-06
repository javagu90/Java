<%-- 
    Document   : foreach
    Created on : 17/07/2014, 02:45:57 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ejemplo de un foreach de jstl</h1>
        <br>
        <u:forEach var="i" begin="0" end="5">
            Item value: ${i}
            <br>
        </u:forEach>
            <br>
        <% String [] array= new String[]{"Jav","VBNO","GTZ"}; 
           pageContext.setAttribute("arreglo",array);
        %>
        <u:forEach var="items" items="${arreglo}" >
            ${items}
            <br>
        </u:forEach>
    </body>
</html>
