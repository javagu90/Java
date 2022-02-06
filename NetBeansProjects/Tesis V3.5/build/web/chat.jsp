<%-- 
    Document   : chat
    Created on : 19/04/2015, 04:59:13 PM
    Author     : Javy
--%>

<%@page import="modelo.Coneccion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <meta charset="UTF-8" name="viewport" content="width=device-width, user-scalable=no,  initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="css/estilos2.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="css/jquery-ui-1.10.0.custom.css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
        <!--<script src="js/headerfij.js"></script>-->
        <script src="js/conversaciones.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
        <script type="text/javascript" src="js/wsclient.js?2032"></script>
        <script src="MooTools-Core-1.5.1.js" type="text/javascript"></script>
 
      <title>SGOA-Chat</title>
    </head>
    <body>
                <!--codigo JAVA para conexion-->
        <%Coneccion cbd= (Coneccion)session.getAttribute("conexionbd");
        System.out.println("coneccion en pagina pprofeser "+ cbd);
        String usuario=(String)session.getAttribute("usuario");
        if(session.getAttribute("usuario")==null)
            response.sendRedirect("index.jsp");
        
        %>
        <!--fin codigo java-->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" ><span class="glyphicon glyphicon-education"></span> SGOA-Chat <span class="glyphicon glyphicon-education"></span></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
                        <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
        
                   <!-- <aside class="col-xs-12 col-sm-3">-->
        <div id="container">
            <div class="leftPanel">
                <div class="userInfo">
                    <br><br><br>
                    <span class="disconnected" id="status">Desconectado</span>
                    Nombre: <input type="text" id="userName" /><span class="onLineUserName"></span>
                </div>
                <div>
                    <button id="connect" onclick="wsclient.connect(document.getElementById('userName').value);">Conectar</button>
                    <button id="disconnect" disabled="disabled" onclick="wsclient.disconnect();">Desconexión</button>
                </div>
                <div id="onLineUsersPanel">
                    <h3>Usuarios conectados:</h3>
                    <ul id="onlineUsers">

                    </ul>
                </div>
            </div>

            <div id="conversations">
                <ul>
                </ul>
            </div>
        </div>

<!--</aside>-->


    </body>
</html>
