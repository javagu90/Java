<%-- 
    Document   : myTAGempty
    Created on : 18/07/2014, 09:52:53 PM
    Author     : Jav
--%>

<%@tag description="etiqueta sin cuerpo" pageEncoding="UTF-8" body-content="empty" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="saludo" rtexprvalue="false"%>

<%-- any content can be specified here e.g.: --%>
<h2>${saludo}</h2>
<jsp:doBody/>