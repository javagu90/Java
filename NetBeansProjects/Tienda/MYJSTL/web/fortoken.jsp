<%-- 
    Document   : fortoken
    Created on : 17/07/2014, 03:03:07 PM
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
          <h1>ejemplo de un fortoken de jstl</h1>
          <br>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page import="java.util.*"%>
        
        <% HashSet hs= new HashSet();
            hs.add("hola, como, estas?");
            request.setAttribute("nombres", hs);
        %>
        <c:forTokens items="${requestScope.nombres}" delims="," var="cadenas">
           Hey ${cadenas}
           <br>
        </c:forTokens>
    </body>
</html>
