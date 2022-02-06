<%-- 
    Document   : scoping
    Created on : 14/07/2014, 08:47:52 PM
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
       PAGE SCOPE DE P1 ---> ${pageScope.p1.nombre}
       <BR>
       REQUEST SCOPE DE PEDRO---> ${requestScope.PEDRO.nombre}
       <BR>
       SESSION SCOPE DE PABLO---> ${sessionScope.Pablo.nombre}
       <BR>
       APPLICATION SCOPE DE PEDRO---> \${applicationScope.JACINTO.nombre}
       ${applicationScope.p1.nombre}
       <a href="implicit.jsp">implicit</a>
    </body>
</html>
