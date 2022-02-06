<%-- 
    Document   : third
    Created on : 15/07/2014, 01:40:17 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String [] names= new String[]{"Javier", "Vbno", "GTZ"};
   pageContext.setAttribute("nombres", names);
    request.setAttribute("nombres", names);
   session.setAttribute("nombres", names);
   //response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hola ${pageScope.nombres[0]} page</h1>
        <h1>Hola ${requestScope.nombres[1]} request</h1>
        <h1>Hola ${sessionScope.nombres[2]} session</h1>
        <h1>Hey: ${paramValues.nombres[2]}</h1>
        init param ${initParam.nombres}
    </body>
</html>
