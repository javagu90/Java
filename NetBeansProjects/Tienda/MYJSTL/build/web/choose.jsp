<%-- 
    Document   : choose
    Created on : 17/07/2014, 03:25:11 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:useBean id="h" scope="page" type="String">
            <c:out value="h">hola como estan</c:out>
        </jsp:useBean>
        
        <c:set var="salario" value="567"/>
          <h1>ejemplo de un choose-when-otherwise de jstl</h1>
          <br>
          <c:choose>
              <c:when test="${salario le 1000}">
                  salario mil o menor
              </c:when>
              <c:when test="${salario gt 1000 and salario le 2500}">
                  salario entre 1000 y 2500
              </c:when>
              <c:when test="${salario gt 2500 and  salario le 3000}">
                  salario entre 2500 y 3000
              </c:when>
              <c:otherwise>
                  ganas mas de 3000
              </c:otherwise>
          </c:choose>
    </body>
</html>
