<%-- 
    Document   : myoas
    Created on : 25/03/2015, 01:06:50 PM
    Author     : Javy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <html lang="en">
      <head>
        <title>Bienvenido a SGOA</title>
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
 
    </head>  <body onload="javascript:loadurl('cabezauniversal.jsp','cabezapagina');javascript:loadurl('puntajes.jsp','ranking')">
        <div id="cabezapagina"></div>
        
       <div class="container-fluid contenido">
           <!--<div class="col-xs-12 col-sm-4">-->
              <div id="ranking"></div>
              
                <aside class="col-xs-12 col-sm-6">
                 <!-- Projects Row -->
                 
                 <h3>Buscar OA's</h3>
                 <div class="row">
            <form method="POST" action="pprofesor.jsp"> 
            <div class="col-md-9">        
                        <input type="text" class="form-control" id="user" name="usuario" placeholder="Buscar OA" required/>
                        </div>
                        <div class="col-md-3">
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> Buscar</button>
                    </div>
            </form>
        


            <div class="col-md-4 portfolio-item">
            </div>
            </div>
                 <hr>
                 <h3>Mis OA's</h3>
                 <hr>
                 
        <div class="row">
            <div class="col-md-4 portfolio-item">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="#">Project Name</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
            <div class="col-md-4 portfolio-item">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="#">Project Name</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
            <div class="col-md-4 portfolio-item">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="#">Project Name</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
        </div>
                 <hr>
                 <h3>Otros OAs creados</h3>
                 <hr>
        <!-- /.row -->
 <!-- Projects Row -->
        <div class="row">
            <div class="col-md-4 portfolio-item">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="#">Project Name</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
            <div class="col-md-4 portfolio-item">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="#">Project Name</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
            <div class="col-md-4 portfolio-item">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="#">Project Name</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
        </div>
        <!-- /.row -->

                </aside>
                <aside class="col-xs-12 col-sm-3">
        <div id="container">
            <div class="leftPanel">
                <div class="userInfo">
                    <span class="disconnected" id="status">Desconectado</span>
                    Nombre: <input type="text" id="userName"/><span class="onLineUserName"></span>
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

</aside>
        </div>    
        <!-- jQuery 
    <script src="js/jquery.js"></script>
-->

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
