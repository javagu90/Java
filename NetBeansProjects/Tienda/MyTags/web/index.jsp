<%-- 
    Document   : index
    Created on : 18/07/2014, 03:02:54 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="mytag"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    my tag con cuerpo sin scrptting: <mytag:etiquetaTAG nombreAlumno='Javier'>
    
        hallo
        <br>
        ${"hola"}
        <br>
    </mytag:etiquetaTAG>
   <%-- my tag handler <mytag:etiquetaHandler mensaje="hola" numero="9">hello</mytag:etiquetaHandler>
  --%>
  my tag sin cuerpo (empty) <mytag:myTAGempty saludo="Buon Giorno!"></mytag:myTAGempty>
  
      my tag dependet <mytag:myTagdependet message="tagedependent">este es el cuerpo de un tagdependet
  <br>
  ${"hey"}
  </mytag:myTagdependet>
  <mytag:myTagScriptless message="scriptless"> 
      <br>
      ${5*7}
  </mytag:myTagScriptless>
      <br>
  TAG LE: <mytag:TagLE LE="${7*9+89}"/>
  <br>
  TAG LE2: <mytag:TagLE LE='<%= "hoola" %>'/>
  <br>
  TAG CON CUERPO Y CAMBIA SU CUERPO A MAYUSCULAS <mytag:tagBody> esto sera mayusculas</mytag:tagBody>
  <br>
  
    </body>
</html>
