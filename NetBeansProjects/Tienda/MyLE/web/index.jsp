<%-- 
    Document   : index
    Created on : 14/07/2014, 05:58:51 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="p1" class="model.Persona" scope="page"/>
        <jsp:setProperty name="p1" property="nombre" value="Javier"/>
        
        
        <jsp:useBean id="PEDRO" class="model.Persona" scope="request"/>
        <jsp:setProperty name="PEDRO" property="nombre" value="PEDRO"/>
        
        <jsp:useBean id="Pablo" class="model.Persona" scope="session"/>
        <jsp:setProperty name="Pablo" property="nombre" value="Pablo"/>
        
        <jsp:useBean id="JACINTO" class="model.Persona" scope="application"/>
        <jsp:setProperty name="JACINTO" property="nombre" value="Jacinto"/>
        <br>
        Page context---> ${pageContext}
        <br>
       param---> ${param}
       <br>
        paramValues:---> ${paramValues}
        <br>
        header ---> ${header}
        <br>
        headerValues :---> ${headerValues}
        <br>
        cookie ---> ${cookie}
        <br>
        init param ---> ${initParam}
        <br>
        <form name="form" method="get" action="scoping.jsp">
        ALUMNO DEFINIDO EN PAGE
        <h1>SCOPE</h1>
        <h1>PAGE SCOPE LOCAL-->PRIVATE</h1>
        <BR>
        p1 nombre con scope : ${pageScope.p1.nombre}
        <BR>
        pEDRO nombre con scope : ${page.PEDRO.nombre}
        <br>
        pablo nombre con scope : ${pageScope.Pablo.nombre}
        <BR>
        jacinto nombre con scope : ${pageScope.JACINTO.nombre}
        <BR>
        <h1>REQUEST SCOPE</h1>
        <BR>
        p1 nombre con scope : ${requestScope.p1.nombre}
        <BR>
        pEDRO nombre con scope : ${requestScope.PEDRO.nombre}
        <br>
        pablo nombre con scope : ${requestScope.Pablo.nombre}
        <BR>
        jacinto nombre con scope : ${requestScope.JACINTO.nombre}
        <BR>
        <h1>SESSION SCOPE</h1>
        <BR>
        p1 nombre con scope : ${sessionScope.p1.nombre}
        <BR>
        pEDRO nombre con scope : ${sessionScope.PEDRO.nombre}
        <br>
        pablo nombre con scope : ${sessionScope.Pablo.nombre}
        <BR>
        jacinto nombre con scope : ${sessionScope.JACINTO.nombre}
        <BR>
        <h1>APPLICATION SCOPE</h1>
        <BR>
        p1 nombre con scope : ${applicationScope.p1.nombre}
        <BR>
        pEDRO nombre con scope : ${applicationScope.PEDRO.nombre}
        <br>
        pablo nombre con scope : ${applicationScope.Pablo.nombre}
        <BR>
        jacinto nombre con scope : ${applicationScope.JACINTO.nombre}
        <BR>
        <BR>
        p1 nombre: ${p1.nombre}
        <BR>
        pedro nombre: ${PEDRO.nombre}
        <br>
        pablo nombre: ${Pablo.nombre}
        <br>
        jacinto nombre: ${JACINTO.nombre}
        
        <input type="submit" name="ok" value="okas"> 
    </form>
  
    </body>
</html>
