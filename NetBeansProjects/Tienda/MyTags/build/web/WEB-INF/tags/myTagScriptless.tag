<%-- 
    Document   : myTagScriptless
    Created on : 19/07/2014, 03:25:29 AM
    Author     : Jav
--%>

<%@tag description="tag con scrptless" pageEncoding="UTF-8" body-content="scriptless"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message" rtexprvalue="false"%>

<%-- any content can be specified here e.g.: --%>
<h2>${message}</h2>

<jsp:doBody/>