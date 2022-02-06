<%-- 
    Document   : pprofesor
    Created on : 23/03/2015, 11:01:24 PM
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
           
            <div id="ranking"></div>
                
                <aside class="col-xs-12 col-sm-6">
                                          <!-- Project Three -->
                        <h4>Notificacion</h4>
        <div class="row">
            <div class="col-md-7">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x300" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3>Project Three</h3>
                <h4>Subheading</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis, temporibus, dolores, at, praesentium ut unde repudiandae voluptatum sit ab debitis suscipit fugiat natus velit excepturi amet commodi deleniti alias possimus!</p>
                <a class="btn btn-primary" href="#">View Project <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
        <!-- /.row -->

        <hr>
         <div class="row">
            <div class="col-md-7">
                <a href="#">
                    <img class="img-responsive" src="http://placehold.it/700x300" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3>Project Three</h3>
                <h4>Subheading</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis, temporibus, dolores, at, praesentium ut unde repudiandae voluptatum sit ab debitis suscipit fugiat natus velit excepturi amet commodi deleniti alias possimus!</p>
                <a class="btn btn-primary" href="#">View Project <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
        <!-- /.row -->

        <hr>
                </aside>
            
            <div id="mensajeria"></div>
           <!-- </div> -->
        </div>    
    <script src="Bootstrap/js/jquery.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

