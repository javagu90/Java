<%-- 
    Document   : if}
    Created on : 17/07/2014, 02:09:31 AM
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
        <h1>ejemplo de condicional if de jstl</h1>
        <br>
        
        <% pageContext.setAttribute("salario", "20000"); %>
        pageContext.setAttribute("salario", "20000")
        <br>
        
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        
        <c:if test="${salario>2000}" var="mensaje" scope="page" >
            Mi salario es de: ${salario}
        </c:if>
            <br>
            
            <%! int i=9; %>
            <c:out value="i"> </c:out>
            <br>
            <br>
        Message con c:out value="\${mensaje}" --> <c:out value="${mensaje}"/>
        <br>
        AHORA CON C:SET VAR="SALARY" VALUE="1500" SCOPE="SESSION"
        <BR>
        
        <c:set var="salary" value="1500" scope="session"/>
        
        <c:if test="${salary lt 2000}" var="mensaje" scope="session" >
            Mi salario es de: ${salary}
        </c:if>
      
    </body>
</html>
