<%-- 
    Document   : myTagdependet
    Created on : 19/07/2014, 03:07:03 AM
    Author     : Jav
--%>

<%@tag description="tag con tagdependet" pageEncoding="UTF-8" body-content="tagdependent" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message" rtexprvalue="false" %>

<%-- any content can be specified here e.g.: --%>
<h2>${message}</h2>
<jsp:doBody/>