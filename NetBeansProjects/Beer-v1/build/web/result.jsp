<%-- 
    Document   : result
    Created on : 3/08/2014, 07:46:41 AM
    Author     : Jav
--%>

<%@page import="java.util.*"%>
<html>
    <head>
        <title>Result</title>
    </head>
    <body>
        <h1 aling="center">Beer recomendations JSP</h1>
        <p>
            <%
                List sites= (List)request.getAttribute("styles");
                Iterator it= sites.iterator();
                while(it.hasNext())
                {
                    out.println("<br> try: "+ it.next());
                }
            %>
    </body>
</html>
