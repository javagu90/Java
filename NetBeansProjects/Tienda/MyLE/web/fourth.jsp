<%-- 
    Document   : fourth
    Created on : 16/07/2014, 05:08:17 PM
    Author     : Jav
--%>

<% request.setAttribute("1007", Integer.valueOf(10)); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        1ero
        Number is ${requestScope["1007"]} <h1>esto es posible por request.setAttribute y requesScope</h1>
        <br>
        <!--Hey: \${param["1007"]} -->
    </body>
</html>
