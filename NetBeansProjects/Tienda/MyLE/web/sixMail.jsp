<%-- 
    Document   : sixMail
    Created on : 16/07/2014, 06:04:14 PM
    Author     : Jav
--%>
<%  
    session.setAttribute("mail","@session");
    request.setAttribute("mail","@req");
    pageContext.setAttribute("mail",null);
   application.setAttribute("mail", "@application");
   //pageContext.setAttribute("mail","page");
   request.setAttribute("list", new String[1]);
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        contactar a ${mail}
        <br>
        lista Vacia?: ${empty list}
        <br>
        ${true and true}
        <br>
        ${true and false}
        <br>
        ${false and true}
        <br>
        ${true or null}
        <br>
        ${true and null}
        <br>
        ${null and true}
        <br>
        ${false and null}
        <br>
        ${false and false}
        <br>
                ${true eq true}
        <br>
                ${true le true}
        <br>
    </body>
</html>
