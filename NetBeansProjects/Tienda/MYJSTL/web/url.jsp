<%-- 
    Document   : url
    Created on : 17/07/2014, 07:55:08 PM
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
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <a href="<c:url value="http://www.google.com"/>"> google</a>
        <br>
        <a href="<c:url value="index.jsp"/>"> index</a>
    </body>
</html>
