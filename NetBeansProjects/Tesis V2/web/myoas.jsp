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
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/estilos2.css"/>
        <script src="Bootstrap/js/jquery.js"></script>
        <script src="js/headerfij.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
         <script type="text/javascript" src="js/wsclient.js?2032"></script>
         
    </head>
    <body onload="javascript:loadurl('cabezauniversal.jsp','cabezapagina'); javascript:loadurl('chat.jsp','mensajeria');javascript:loadurl('puntajes.jsp','ranking')">
        <div id="cabezapagina"></div>
        
       <div class="container-fluid contenido">
           <!--<div class="col-xs-12 col-sm-4">-->
              <div id="ranking"></div>
              
                <aside class="col-xs-12 col-sm-6">
                 <!-- Projects Row -->
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
                <div id="mensajeria"></div>
        </div>    
    <script src="Bootstrap/js/jquery.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
