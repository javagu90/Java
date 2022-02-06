<%-- 
    Document   : out
    Created on : 19/07/2014, 07:26:37 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="etiq" uri="WEB-INF/tlds/misEtiquetas.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <etiq:STSuma sumando1="${param.s1}" sumando2="${param.s2}"/>
        <c:set var="sum1" value="${param.s1}"/>
        <c:set var="sum2" value="${param.s2}"/>
        <br>
        <c:out value="la suma echa con core:set es===>"/>
        no se puede porq lo regresa como string y en la clase suma no se manejo esa exceptioin
       <%-- <etiq:STSuma sumando1="sum1" sumando2="sum2"/> --%>
    </body>
</html>
