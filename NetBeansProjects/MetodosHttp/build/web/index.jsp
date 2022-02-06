<%-- 
    Document   : index
    Created on : 25/07/2014, 11:00:31 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <H1>GET</H1>
        <form name="formulario" action="MyServlet" method="GET">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
        <H1>head</H1>
        <form name="formulario" action="MyServlet" method="head">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
        <H1>post</H1>
        <form name="formulario" action="MyServlet" method="POST">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
        <H1>put</H1>
        <form name="formulario" action="MyServlet" method="PUT">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
        <H1>delete</H1>
        <form name="formulario" action="MyServlet" method="Delete">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
        <H1>trace</H1>
        <form name="formulario" action="MyServlet" method="Trace">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
        <H1>options</H1>
        <form name="formulario" action="MyServlet" method="options">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
        <H1>uri</H1>
        <form name="formulario" action="MyServlet" method="uri">
        User<input type="text" name="user"/>
        Pass<input type="password" name="pass"/>
        <input type="submit" name="ok"/>
        </form>
        <br>
    </body>
</html>
