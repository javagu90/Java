<%-- 
    Document   : pprofesor
    Created on : 23/03/2015, 11:01:24 PM
    Author     : Javy
--%>
<%@page import="modelo.Coneccion" contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="MooTools-Core-1.5.1.js" type="text/javascript"></script>
 
        <%Coneccion cbd= (Coneccion)request.getSession().getAttribute("conexionbd");%>
        
    </head>
    <body onload="javascript:loadurl('cabezaprofe.jsp','cabezapagina');javascript:loadurl('puntajesprofe.jsp','ranking')">        
        <div id="cabezapagina">
            
            
            
        </div>
       
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

           <!-- </div> -->
        </div>    
    
    <!-- jQuery 
    <script src="js/jquery.js"></script>
-->

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <script>
window.addEvent('domready', function(){
   //función a ejecutar cuando esté listo el dom
   
   $('mienlace').addEvent('click', function(evento){
       var v=document.formulario.nombre.value;
      var nuevoRequest = new Request({
         method: 'post', 
         url:'Servlet?valor1='+v, //url: 'solicitud.html',
         onRequest: function() { alert('Empezando esta solicitud Ajax!'); },
         onSuccess: function(texto, xmlrespuesta){ $('cajaactualizacion').set('html',texto);},
         onFailure: function(){alert('Error!');}
      }).send();
      
   });
   
});
</script>
</body>
</html>

