<%-- 
    Document   : tagBody
    Created on : 19/07/2014, 03:38:00 PM
    Author     : Jav
--%>

<%@tag description="etiqueta que modifica el cuerpo que tenga a mayusculas" pageEncoding="UTF-8" body-content="scriptless"%>

<jsp:doBody var="theBody" scope="session"/>
<% String cambioCuerpo= (String)session.getAttribute("theBody");
   cambioCuerpo=cambioCuerpo.toUpperCase();
   session.setAttribute("theBody",cambioCuerpo);
%>
<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<h2>${sessionScope.theBody}</h2>