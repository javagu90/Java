<%-- 
    Document   : pconfiguración
    Created on : 25/03/2015, 11:19:40 AM
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
<form class="form-horizontal" role="form" method="Post" action="Servlet1">
                        <div class="form-group">
                            <label for="nombre" class="col-lg-2 control-label">Nombre</label>
                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="nombre" name="fname" placeholder="Nombre"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="apellido" class="col-lg-2 control-label">Apellidos</label>
                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="apellido" name="lname" placeholder="Apellidos"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mail" class="col-lg-2 control-label">Correo Electrónico</label>
                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="mail" name="correo" placeholder="Correo Electrónico"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rmail" class="col-lg-2 control-label">Reintroduce tu Correo</label>
                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="rmail" name="rcorreo" placeholder="Vuelve a escribir tu Correo Electrónico"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-lg-2 control-label">Contraseña</label>
                            <div class="col-lg-10">
                                <input type="password" class="form-control" id="password" name="pass" placeholder="Contraseña"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rpassword" class="col-lg-2 control-label">Reintroduce Contraseña</label>
                            <div class="col-lg-10">
                                <input type="password" class="form-control" id="rpassword" name="rpass" placeholder="Re-escribe tu Contraseña"/>
                            </div>
                        </div>
                        <!--<div class="form-inline">
                            <label for="nacimiento" class="col-lg-4 control-label">Fecha de Nacimiento</label>
                            <div class="col-lg-8 ">
                                <center>
                                    
                                    <select class="form-control" >
                                        <option></option>
                                        <option></option>
                                        <option></option>
                                    </select>
                                    <select class="form-control">
                                        <option></option>
                                        <option></option>
                                        <option></option>
                                    </select>
                                    <select class="form-control">
                                        <option></option>
                                        <option></option>
                                        <option></option>
                                    </select>
                                </center>
                            </div>
                        </div>
                        <div>
                            <br>
                            <br>
                        </div>-->
                        <div class="form-inline">
                            <label for="sexo" class="col-lg-2 control-label">Sexo</label>
                            <center>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="opciones1" id="op1" value="H"/>
                                        Hombre
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="opciones1" id="op2" value="M"/>
                                        Mujer
                                    </label>
                                </div>
                            </center>
                        </div>
                        <div>
                            <br>
                        </div>
                        <div class="form-inline">
                            <label for="tipo" class="col-lg-2 control-label">Tipo de usuario</label>
                            <center>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="opciones2" id="profe" value="P"/>
                                        Profesor
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="opciones2" id="Alumno" value="A"/>
                                        Alumno
                                    </label>
                                </div>
                            </center>
                        </div>
                        <div>
                            <br>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <center> <button type="submit" class="btn btn-success">Registrarse</button> </center>
                            </div>
                        </div>
                    </form>                
                </aside>
           <div id="mensajeria"></div>
        </div>    
    <script src="Bootstrap/js/jquery.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
