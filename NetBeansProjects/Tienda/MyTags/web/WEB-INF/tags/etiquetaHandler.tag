<%-- 
    Document   : etiquetaHandler
    Created on : 18/07/2014, 03:51:10 AM
    Author     : Jav
--%>

<%@tag body-content="tagdependent" description="Equite vía Tag Handler" pageEncoding="UTF-8" dynamic-attributes="atributos"%>
<%--<%@variable  %>--%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="mensaje"  description="mensaje" required="false" rtexprvalue="false" %>
<%@attribute name="numero"  description="numero" required="false" rtexprvalue="false"  type="java.lang.Float"%>
<%@attribute name="alumno"  description="Alumno" required="false" rtexprvalue="true" %>

<%! int x=10; %>
<%-- any content can be specified here e.g.: --%>
<h2>${mensaje}</h2>
<h2>${numero+200}</h2>
<h2>${alumno}</h2>
${pageScope.alumno}
 atributos : ${atributos}

<%= x %>

<jsp:doBody/>