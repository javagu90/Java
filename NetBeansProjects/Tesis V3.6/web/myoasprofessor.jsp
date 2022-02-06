<%-- 
    Document   : myoas
    Created on : 25/03/2015, 01:06:50 PM
    Author     : Javy
--%>

<%@page import="modelo.Coneccion" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--head-->
    <head>
        <title>SGOA-Muro</title>
        <meta charset="UTF-8" name="viewport" content="width=device-width, user-scalable=no,  initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">        
         <!-- Bootstrap Core CSS -->
        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="Bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/estilos2.css"/>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
      
                 <script>
		$(document).ready(function () {
                    function refreshM()
                    {
				$.ajax({
					url: "MuestraNotify", // 
					success:function(msg){
						$("#listanotificaciones").html(msg);	
					},
					error: function(){
					}
				});
                            }
                             setInterval(refreshM, 3000);
			});
            </script>
        
    </head>
    <!--fin head-->
    <!--body-->
    <body> 
        
        <!--codigo JAVA para conexion-->
        <%Coneccion cbd= (Coneccion)session.getAttribute("conexionbd");
        System.out.println("coneccion en pagina pprofeser "+ cbd);
        String usuario=(String)session.getAttribute("usuario");
        if(session.getAttribute("usuario")==null)
            response.sendRedirect("index.jsp");
        
        %>
        <!--fin codigo java-->
        
        <!-- Navigation (menu) -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="pprofesor.jsp"><span class="glyphicon glyphicon-education"></span> SGOA-Profesor(a): <% out.println(usuario);%> <span class="glyphicon glyphicon-education"></span></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-bell"></span> Notificaciones <span class="badg badg-info" id="n" ></span></a>
                        
<!-- SCRIPT MOSTRAR NUMERO NOTIFICACIONES-->                        
            <script>
		$(document).ready(function () {
                    function refreshN()
                    {
				$.ajax({
					url: "MuestraNumNotify", // 
					success:function(msg){
						$("#n").html(msg);	
					},
					error: function(){
						
                                                
					}
				});
                            }
                             setInterval(refreshN,  1000);
			});
            </script>
<!--FIN SCRIPT MOSTRAR NUMERO NOTIFICACIONES-->                        
       
                        <ul class="dropdown-menu list-group" id="listanotificaciones">
                        </ul> 
                    </li>
                    
            <!-- SCRIPT MOSTRAR NOTIFICACIONES-->
      
          <!--FIN SCRIPT MOSTRAR NOTIFICACIONES-->
          
          
			<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-book"></span>Actividades<b class="caret"></b></a>
                        <ul class="dropdown-menu list-group">
                            <li class="list-group-item list-group-item-success">
                                <a href="myoasprofessor.jsp"><span class="glyphicon glyphicon-book"></span> Mis OA's</a>
                            </li>
                            
                            <li class="list-group-item list-group-item-info">
                                <a id="mostrarAyC" href="#ventanaOA1"  data-toggle="modal" modal="true"><span class="glyphicon glyphicon-book"></span> Crear un OA</a>
                            </li>
                            
<!-- SCRIPT MODAL PARA AGREGAR AVISO-->
<script>
    
    function refreshAreas()
    {
        $.ajax(
               {
                    url: "CrearComboArea", // 
                    success: function(msg)
                    {
                        $("#opcionesArea").html(msg);
                    //$("#form-content").modal('hide');	
                    },
                    error: function()
                    {
                        alert("falló");
                    }
		});
    }
    
    function refreshCategorias()
    {
	$.ajax({
		url: "CrearComboCategoria", // 
		success: function(msg){
		$("#opcionesCategoria").html(msg);
		//$("#form-content").modal('hide');	
		},
		error: function(){
		alert("falló");
		}
            });
    }
		$(document).ready(function () {
                    
			$("a#mostrarAyC").click(refreshAreas());
                        
                        //$("a#mostrarAyC").click(refreshCategorias());
                        
                        $("#opcionesArea").change(function(event){
                            var id = $("#opcionesArea").find(':selected').val();
                            $("#opcionesCategoria").load('CrearComboCategoria?id='+id);
                          });
		});
    </script>
    
    
<!-- FIN SCRIPT Modal ventana Aviso-->
                            
                            <li class="list-group-item list-group-item-success">
                                <a href="#ventanaAviso"  data-toggle="modal" modal="true" ><span class="glyphicon glyphicon-book"></span> Generar un Aviso</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span> Configuración<b class="caret"></b></a>
                        <ul class="dropdown-menu list-group">
                            <li class="list-group-item list-group-item-success">
                                <a href="#ventanauser" data-toggle="modal" modal="true"><span class=" glyphicon glyphicon-pencil"></span> Cambiar nombre de Usuario</a>
                            </li>
                            <li class="list-group-item list-group-item-info">
                                <a href="#ventanapass" data-toggle="modal" modal="true"><span class=" glyphicon glyphicon-asterisk"></span> Cambiar Contraseña</a>
                            </li>
                            <li class="list-group-item list-group-item-success">
                                <a href="#ventanadata" data-toggle="modal" modal="true"><span class="glyphicon glyphicon-list-alt"></span> Cambiar Datos personales</a>
                            </li>
                        </ul>
                    </li>
                    
                    <li>
                        <!-- SCRIPT ABRIR CHAT EN VENTANA NUEVA-->
            
                        <script> 
                            function abrir() { 
                                open('chat.jsp','','top=115,left=985,width=300,height=500') ; 
                            } 
                        </script> 
                    <!--FIN SCRIPT ABRIR CHAT EN VENTANA NUEVA-->
                    
                        <a href="#" onclick="javascript:abrir();"><span class="glyphicon glyphicon-envelope"></span> Chat</a>
                    </li>
                    
                    <li>
                    	<a href="Logout">
                            <span class="glyphicon glyphicon-off"></span> Cerrar Sesión</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
        <!--fin menu-->
        
        <!--Modal ventana Aviso-->

    
<div class="modal fade bs-example-modal-sm" id="ventanaAviso" >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Generar Aviso</h3></center>
            </div>
            
                <div class="modal-body">
                    <form class="formularioAAviso" name="formularioAAviso">
                    <input type="text" name="titulo" class="form-control" placeholder="Titulo del Aviso" required/>
                    <input type="text" name="remitente" class="form-control" placeholder="Remitente del Aviso" required/>
                    <input type="text" name="aviso" class="form-control" placeholder="Aviso" required/>
                </form>
                </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <button class="btn btn-primary" type="submit" value="Agregar" id="submitAviso" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></button>
            </div>
        </div>
    </div>
</div>
<!--FIN MODAL PARA AGREGAR AVISO-->

<!-- SCRIPT MODAL PARA AGREGAR AVISO-->
<script>
		$(document).ready(function () {
			$("button#submitAviso").click(function(){
				$.ajax({
					type: "POST",
					url: "FormAviso", // 
					data: $('form.formularioAAviso').serialize(),
					success: function(){//function(msg){
						alert("Hecho");//$("#thanks").html(msg)
						//$("#form-content").modal('hide');	
					},
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>
<!-- FIN SCRIPT Modal ventana Aviso-->

<!--Modal ventana CAMBIO USER-->
<div class="modal fade bs-example-modal-sm" id="ventanauser">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class=" glyphicon glyphicon-pencil"></span> Cambio de nombre de Usuario</h3></center>
            </div>
            <form method="POST" action="UpdateUser">
                <div class="modal-body">   
                    <center>
                        <div class="alert alert-warning">
                        <center><p>Al pulsar el boton "Guardar" se te redireccionará a la página de Inicio para actualizar tu información</p></center> 
                        </div>
                        <input type="text" class="form-control" id="userold" name="usuarioViejo" placeholder="Usuario o mail anterior" required/>
                        <input type="text" class="form-control" id="usernew" name="usuarioNuevo" placeholder="Usuario o mail nuevo" required/>
                        <input type="text" class="form-control" id="usernewagain" name="usuarioNuevoR" placeholder="Re-escribe el Usuario o mail nuevo" required/>
                        <input type="password" class="form-control" id="contra" name="pass" placeholder=" Verifica con tu Contraseña" required/>
                        </center>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-floppy-saved"></span> Guardar</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-remove"></span> Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!--FIN Modal ventana CAMBIO USER-->

<!--Modal ventana CAMBIO PASS-->
<div class="modal fade bs-example-modal-sm" id="ventanapass">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class=" glyphicon glyphicon-asterisk"></span> Cambio de Contraseña</h3></center>
            </div>
            <form method="POST" action="UpdatePass">
                <div class="modal-body">   
                    <center>
                        <div class="alert alert-warning">
                        <center><p>Al pulsar el boton "Guardar" se te redireccionará a la página de Inicio para actualizar tu información</p></center> 
                        </div>
                        <input type="text" class="form-control" id="user" name="usuario" placeholder="Usuario o mail" required/>
                        <input type="password" class="form-control" id="contraseniaold" name="passViejo" placeholder="Contraseña anterior" required/>
                        <input type="password" class="form-control" id="contrasenianew" name="passNuevo" placeholder="Contraseña nueva" required/>
                        <input type="password" class="form-control" id="contrasenianew" name="passNuevoR" placeholder="Re-escribe tu Contraseña nueva" required/>
                        </center>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-floppy-saved"></span> Guardar</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-remove"></span> Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--FIN Modal ventana CAMBIO PASS-->


<!--Modal ventana CAMBIO DATOS-->
<div class="modal fade bs-example-modal-sm" id="ventanadata">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-list-alt"></span> Cambio de Datos personales</h3></center>
            </div>
            <form method="POST" action="UpdatePersonalData">
                <div class="modal-body">   
                    <center>
                        <div class="alert alert-warning">
                        <center><p>Al pulsar el boton "Guardar" se te redireccionará a la página de Inicio para actualizar tu información</p></center> 
                        </div>
                        <input type="text" class="form-control" id="nomb" name="nombre" placeholder="Nombre" required/>
                        <input type="text" class="form-control" id="ape" name="apellidos" placeholder="Apellidos" required/>
                        <input type="mail" class="form-control" id="mai" name="correo" placeholder="Correo Electrónico" required/>
                    </center>
                </div>
                <div class="modal-footer">
                    <input type="text" class="form-control" id="u" name="usuario" placeholder="Escribe tu Usuario/correo para verificar los cambios" required/>
                    <input type="password" class="form-control" id="c" name="pass" placeholder=" Escribe tu contraseña para verificar los cambios" required/>
                    <br/>
                    <br/>
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-floppy-saved"></span> Guardar</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-remove"></span> Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--FINModal ventana CAMBIO DATOS-->

        <!-- VENTANA 1 OA-->
<div class="modal fade bs-example-modal-sm" id="ventanaOA1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Creando un OA: Paso 1</h3>
                <h5>Datos generales de tu objeto de aprendizaje</h5>
                </center>
            </div>
                <div class="modal-body">
                <div class="alert alert-info">
                        <center><b>Instrucción:</b><p>Inserta y selecciona los datos generales de tu OA. Si no encuentras el área o categoría, puedes agregarla presionado el botón <span class="glyphicon glyphicon-plus"></span></p></center> 
                    </div>    
                    <center>
                        <form class="formularioV1OA" name="formularioV1OA">
                        <input type="text" class="form-control"  name="tituloOA" placeholder="Titulo del OA" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control"  name="objetivoOA" placeholder="Objetivo del OA" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control"  name="descripcionOA" placeholder="Descripción del OA" required></textarea>
                        <br/>
                        Área:
                        <!--<select id="opcionesArea">-->
                        <select id="opcionesArea" name="opcionesArea">
                        </select>
                        
                        <a style="text-decoration: none;" href="#agregarArea" data-toggle="modal" modal="true" id="nArea" title="Agregar Área"><span class="glyphicon glyphicon-plus"></span></a>
                        &nbsp &nbsp &nbsp
                        Categoría:
                        <select id="opcionesCategoria" name="opcionesCategoria"><!--<select id="opcionesCategoria">-->
                        </select> &nbsp
                        <a style="text-decoration: none;" href="#agregarCategoria" data-toggle="modal" modal="true" id="nCategoria" title="Agregar Categoría"><span class="glyphicon glyphicon-plus"></span></a>
                        <br/>
                        </form>
                    </center>
                </div>
                <div class="modal-footer">
                    <button  type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a id="submitV1OA" href="#ventanaOA2" data-toggle="modal" modal="true" type="submit" class="btn btn-primary" data-dismiss="modal">Siguiente Paso <span class="glyphicon glyphicon-chevron-right"></span></a>
                
                </div>
        </div>
    </div>
</div>
<!-- VENTANA 1 OA-->

<!--SCRIPT VENTANA 1 OA-->
        <script>
		$(document).ready(function () {
			$("a#submitV1OA").click(function(){
				$.ajax({
					type: "POST",
					url: "FormOA1", // 
					data: $('form.formularioV1OA').serialize(),
					success: function(){//function(msg){
						alert("Hecho"),//$("#thanks").html(msg)
						$(".formularioV1OA")[0].reset();
                                                
                                                
					},
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>
    <!--FIN SCRIPT VENTANA 1 OA-->

        <!-- VENTANA 2 OA-->
<div class="modal fade bs-example-modal-sm" id="ventanaOA2">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Creando un OA: Paso 2</h3>
                <h5>Selección de recursos multimedia</h5>
                </center>
            </div>
                <div class="modal-body">   

                    <div class="alert alert-info">
                        <center><b>Instrucción:</b><p>Presiona el botón del recurso que deseas agregar. Si deseas ver los recursos que haz agregado, presiona el boton "Ver recursos agregados"</p></center> 
                    </div> 
                    <center>
                    <a href="#ventanaR1"data-toggle="modal" modal="true" class="btn btn-default"> <img src="./img/video.png"/><br/>Video</a>
                    <a href="#ventanaR2"data-toggle="modal" modal="true" class="btn btn-default"> <img src="./img/music.png"/><br/>Audio</a>
                    <a href="#ventanaR2"data-toggle="modal" modal="true" class="btn btn-default"> <img src="./img/picture.png"/><br/>Imagen</a>
                    <a href="#ventanaR1"data-toggle="modal" modal="true" class="btn btn-default"> <img src="./img/pdf.png"/><br/>PDF</a>
                    <a href="#ventanaR3"data-toggle="modal" modal="true" class="btn btn-default"> <img src="./img/enlace.png"/><br/>Enlace</a>
                    </center>
                    <br/>
                    <form class="mostrar" name="mostrar"></form>
                    <center><a id="mostrarActivities" href="#ventanatabla" class="btn btn-primary" data-toggle="modal" modal="true">Ver recursos agregados</a></center>
                                   
                </div>
            
            <script>
		$(document).ready(function () {
			$("a#mostrarActivities").click(function(){
				$.ajax({
					type: "POST",
					url: "MostrarActividadesCreadas", // 
					data: $('form.mostrar').serialize(),
					success: function(msg){
						$("#tablaActividades").html(msg);
							
					},
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>
            
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a href="#ventanaOA3" data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Siguiente Paso <span class="glyphicon glyphicon-chevron-right"></span></a>
                </div>
        </div>
    </div>
</div>
<!--FIN VENTANA 2 OA-->

<!-- VENTANA 3 OA-->
<div class="modal fade bs-example-modal-lg" id="ventanaOA3">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Creando un OA: Paso 3</h3>
                <h5>Selección de inteligencias a desarrollar e insercción de palabras clave</h5>
                </center>
            </div>
                <div class="modal-body">  
                    <div class="alert alert-info">
                        <center><b>Instrucción:</b><p>Selecciona el tipo de inteligencias que impulsa este OA, al terminar tu selección y si lo deseas, puedes insertar palabras claves para identificar facilmente este OA</p></center> 
                    </div>
                    <form class="formInteligencias" name="formInteligencias">
                    <div class="container-fluid">
                        <div class="row">
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input  type="checkbox" name="LM" >Lógica-matemática</label>
                            </div>

                            <div class="col-sm-3">
                                <label class="checkbox"><input  type="checkbox" name="LV">Ligüistica-verbal</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input  type="checkbox" name="E" >Espacial</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input  type="checkbox" name="M" >Músical</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input  type="checkbox" name="CK" >Corporal-Kinestésica</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input  type="checkbox" name="INTRA" >Intrapersonal</label>
                            </div>   
                            
                            <div class="col-sm-3">                                                   
                                <label class="checkbox"><input  type="checkbox" name="INTER" >Interpersonal</label>
                            </div>                                                            
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input  type="checkbox" name="N" >Naturista</label>
                            </div>
                            <textarea type="text" class="form-control"  name="palabras" placeholder="Palabras clave (por ejem. 'Matemáticas, Programación, Ciencias') "required></textarea>
                        </div>                    
                    </div>
                </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a href="#" id="submitVOA3" type="submit" class="btn btn-success" data-dismiss="modal">Finalizar <span class="glyphicon glyphicon-chevron-right"></span></a>
                </div>
        </div>
    </div>
</div>
<!--FIN VENTANA 3 OA-->

<!--SCRIPT VENTANA 3 OA-->
        <script>
		$(document).ready(function () {
			$("a#submitVOA3").click(function(){
				$.ajax({
					type: "POST",
					url: "FormOA3", // 
					data: $('form.formInteligencias').serialize(),
					success: function(){//function(msg){
						alert("Hecho"),//$("#thanks").html(msg)
                                            $(".formInteligencias")[0].reset();
                                                
    },
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>
    <!--FIN SCRIPT VENTANA 3 OA-->




<!-- VENTANA  R1-->
<div class="modal fade bs-example-modal-lg" id="ventanaR1" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregando Actividad</h3></center>
            </div>
                <div class="modal-body">
                    <form class="formularioActividad1" name="formularioActividad1">
                     <input type="text" class="form-control"  name="tituloActividad" placeholder="Escribe el título de la actividad" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control"name="descripcionActividad" placeholder="Escribe la descripción de la actividad" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control" name="objetivoActividad" placeholder="Escribe el objetivo de la actividad" required></textarea>
                        <br/>
                        <input type="number" min="1" max="2" class="form-control"  name="puntosActividad" placeholder="Puntos a asignar" required/>
                        <input type="hidden" class="form-control" name="tipoRecurso" value="Video/PDF"  readonly="readonly"/>
          
                        <br/>
                        
                        Tipo de Saber: 
                        <select name="tiposaberA">
                           <option value="0">Seleccione una opción</option>
                           <option value="1">Saber Ser</option>
                           <option value="2">Saber Hacer</option>
                           <option value="3">Saber Conocer</option>
                        </select> &nbsp
                        <br/><br/>
                         
                        Fuente del recurso: 
                        
                        <select id="combov1">
                           <option value="0">Seleccione una opción</option>
                           <option value="1">Local</option>
                          <!-- <option value="2">Enlace</option>-->
                           <option value="3">Codigo Embebido</option>
                        </select> &nbsp
                        </form>
                         <!--Script para mostrar  algun div al cambio del select-option-->        
                          <script>
            $(document).ready(function(){
                $(".contentv1").hide();
                $("#combov1").change(function(){
                $(".contentv1").hide();
                    $("#divV1_" + $(this).val()).show();
                });
            });
        </script>
                
        <div id="divV1_1" class="contentv1">
            <br>
            
            <script>
    
     $(function(){
        $("input[name='filevid']").on("change", function(){
            var formData = new FormData($("#formularioA1")[0]);
            var ruta = "SubirArchivo";
            $.ajax({
                url: ruta,
                type: "POST",
                data: formData,
                contentType: false,
                processData: false,
                success: function(datos)
                {
                    $("#respuesta1").html(datos);
                }
            });
        });
     });
    </script>
 
  <form method="post" id="formularioA1" enctype="multipart/form-data">
      Subir archivo: <input type="file" name="filevid">
</form>
            <div id="respuesta1"></div>
        </div>
        <form class="formularioActividad1" name="formularioActividad1">
        <div id="divV1_20" class="contentv17">
            <br>
            <input type="HIDDEN" name="enlace" id="enlac1" placeholder="enlace del recurso que quieres agregar">
        </div>
        <div id="divV1_3" class="contentv1">
            <br>
            <textarea name="emb" id="emb" placeholder="Incrusta el codigo embebido aquí" ></textarea>
        </div>
            </form>
        <!--fin Script para mostrar  algun div al cambio del select-option-->
                        <br/>
                        <br/>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <center><h4>Estrategia de aprendizaje</h4></center>
                            </div>
                            <div class="panel-body">
                                <div class="container-fluid">
                                <div class="row">
                                    <form class="formularioActividad1" name="formularioActividad1">
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Ind" >Individual</label>
                                    </div>

                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Cool" >Coolaborativa</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="PBL">PBL</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Basada">Basada en casos</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Otras" >Otras</label>
                                    </div>
                                        </form>
                                </div>                    
                            </div>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a id="submitA1" data-toggle="modal" modal="true" type="submit" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>

<script>
		$(document).ready(function () {
			$("a#submitA1").click(function(){
				$.ajax({
					type: "POST",
					url: "DataActividad", // 
					data: $('form.formularioActividad1').serialize(),
					success: function(){//function(msg){
						alert("Hecho"),//$("#thanks").html(msg)
						$(".formularioActividad1")[0].reset(),
                                                $("#enlac1").val(''),
                                                $("#emb").val('');
					},
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>

<!--FIN VENTANA  R1-->


<!-- VENTANA  R2-->
<div class="modal fade bs-example-modal-lg" id="ventanaR2" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregando Actividad</h3></center>
            </div>
                <div class="modal-body">
                    <form class="formularioActividad2" name="formularioActividad2">
                     <input type="text" class="form-control"  name="tituloActividad" placeholder="Escribe el título de la actividad" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control" name="descripcionActividad" placeholder="Escribe la descripción de la actividad" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control" name="objetivoActividad" placeholder="Escribe el objetivo de la actividad" required></textarea>
                        <br/>
                        <input type="number" min="1" max="2" class="form-control"  name="puntosActividad" placeholder="Puntos a asignar" required/>
                        <br/>
                        
                        Tipo de Saber: 
                        <select name="tiposaberA">
                           <option value="0">Seleccione una opción</option>
                           <option value="1" >Saber Ser</option>
                           <option value="2">Saber Hacer</option>
                           <option value="3">Saber Conocer</option>
                        </select> &nbsp
                        <br/><br/>
                    <input type="hidden" class="form-control" name="tipoRecurso" value="Imagen/Audio" readonly="readonly"/>
                            
                    </form>
                        Fuente del recurso: 
                        <select id="combov2" name="FRecursoA2">
                           <option>Seleccione una opción</option>
                           <option value="1">Local</option>
                           <!--<option value="2" >Enlace</option>-->
                        </select> &nbsp
                       
                <!--Script para mostrar  algun div al cambio del select-option-->        
                          <script>
            $(document).ready(function(){
                $(".contentv2").hide();
                $("#combov2").change(function(){
                $(".contentv2").hide();
                    $("#divV2_" + $(this).val()).show();
                });
            });
        </script>
                
        <div id="divV2_1" class="contentv2">
            <br>
            
            <script>
    
     $(function(){
        $("input[name='fileimg']").on("change", function(){
            var formData = new FormData($("#formularioA2")[0]);
            var ruta = "SubirArchivo";
            $.ajax({
                url: ruta,
                type: "POST",
                data: formData,
                contentType: false,
                processData: false,
                success: function(datos)
                {
                    $("#respuesta").html(datos);
                }
            });
        });
     });
    </script>
 
  <form method="post" id="formularioA2" enctype="multipart/form-data">
      Subir archivo: <input type="file"  name="fileimg">
</form>
            <div id="respuesta"></div>
        </div>
        <form class="formularioActividad2" name="formularioActividad2">
        <div id="divV2_20" class="contentv27">
            <br>
            <input type="HIDDEN" name="enlace" id="enlac2" placeholder="enlace del recurso que quieres agregar">
        </div>
            </form>
        <!--fin Script para mostrar  algun div al cambio del select-option-->
                        <br/>
                        <br/>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <center><h4>Estrategia de aprendizaje</h4></center>
                            </div>
                            <div class="panel-body">
                                <div class="container-fluid">
                                <div class="row">
                                    <form class="formularioActividad2" name="formularioActividad2">
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Ind" >Individual</label>
                                    </div>

                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Cool" >Coolaborativa</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="PBL">PBL</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Basada">Basada en casos</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Otras" >Otras</label>
                                    </div>
                                        </form>
                                </div>                    
                            </div>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a id="submitA2" data-toggle="modal" modal="true" type="submit" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>


<script>
		$(document).ready(function () {
			$("a#submitA2").click(function(){
				$.ajax({
					type: "POST",
					url: "DataActividad", // 
					data: $('form.formularioActividad2').serialize(),
					success: function(){//function(msg){
						alert("hecho"),//$("#thanks").html(msg)
						$(".formularioActividad2")[0].reset(),
                                                $("#enlac2").val('');
                                               
					},
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>

<!--FIN VENTANA  R2-->

<!-- VENTANA  R3-->
<div class="modal fade bs-example-modal-lg" id="ventanaR3" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form class="formularioActividad3" name="formularioActividad3">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregando Actividad</h3></center>
            </div>
                <div class="modal-body">
                   
                     <input type="text" class="form-control"  name="tituloActividad" placeholder="Escribe el título de la actividad" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control" name="descripcionActividad" placeholder="Escribe la descripción de la actividad" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control"  name="objetivoActividad" placeholder="Escribe el objetivo de la actividad" required></textarea>
                        <br/>
                        <input type="number" min="1" max="2" class="form-control"  name="puntosActividad" placeholder="Puntos a asignar" required/>
                        <br/>
                        
                        Tipo de Saber: 
                        <select name="tiposaberA">
                           <option value="0">Seleccione una opción</option>
                           <option value="1">Saber Ser</option>
                           <option value="2">Saber Hacer</option>
                           <option value="3">Saber Conocer</option>
                        </select> &nbsp
                        <br/><br/>
                        <input type="text" class="form-control"  name="enlace" placeholder="Introduce la dirección Web (URL)" required></input>
                        <input type="hidden" class="form-control" name="tipoRecurso" value="URL"  readonly="readonly"/>
                        <br/>
                        <br/>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <center><h4>Estrategia de aprendizaje</h4></center>
                            </div>
                            <div class="panel-body">
                                <div class="container-fluid">
                                <div class="row">
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Ind" >Individual</label>
                                    </div>

                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Cool" >Coolaborativa</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="PBL">PBL</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Basada">Basada en casos</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input type="checkbox" name="Otras" >Otras</label>
                                    </div>
                                </div>                    
                            </div>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a  id="submitA3" data-toggle="modal" modal="true" type="submit" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
                </form>
        </div>
    </div>
</div>


<script>
		$(document).ready(function () {
			$("a#submitA3").click(function(){
				$.ajax({
					type: "POST",
					url: "DataActividad", // 
					data: $('form.formularioActividad3').serialize(),
					success: function(){//function(msg){
						alert("hecho");//$("#thanks").html(msg)
						$(".formularioActividad3")[0].reset();
                                                
					},
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>


<!--FIN VENTANA  R3-->

<!-- mODAL PARA AGREGAR CATEGORIA-->
<div class="modal fade bs-example-modal-sm" id="agregarCategoria" >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregar Categoria</h3></center>
            </div>
                <div class="modal-body">
                    <form class="formularioAC" name="formularioAC">
                     <input type="text" class="form-control"  name="nombre_categoria" placeholder="Escribe el nombre de la Categoria" required/>
           
                    </form>
                </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
               <button class="btn btn-primary" type="submit" value="Agregar" id="submitACategoria" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></button>
            </div>
        </div>
    </div>
</div>
<!--FIN mODAL PARA AGREGAR CATEGORIA-->

<!-- SCRIPT MODAL PARA AGREGAR CATEGORIA-->
<script>
    
		$(document).ready(function () {
                   
			$("button#submitACategoria").click(function(){
                             
				$.ajax({
					type: "POST",
					url: "AddCtegoria", // 
					data: $('form.formularioAC').serialize(),
					success: function(){//function(msg){
						alert("hecho");//$("#thanks").html(msg)
						//$("#form-content").modal('hide');
                                                
					},
					error: function(){
						alert("falló");
					}
				});
                                refreshCategorias();
			});
		});
    </script>
<!--FIN SCRIPT MODAL PARA AGREGAR CATEGORIA-->

<!-- mODAL PARA AGREGAR AREA-->

<div class="modal fade bs-example-modal-sm" id="agregarArea" >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregar Área</h3></center>
            </div>
            
                <div class="modal-body">
                    <form class="formularioAA" name="formularioAA">
                    <input type="text" name="nombre_area" class="form-control" placeholder="Escribe el nombre del Área" required/>
                </form>
                </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <button class="btn btn-primary" type="submit" value="Agregar" id="submitAArea" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></button>
            </div>
        </div>
    </div>
</div>
<!--FIN MODAL PARA AGREGAR AREA-->

<!-- SCRIPT MODAL PARA AGREGAR AREA-->
<script>
		$(document).ready(function () {
			$("button#submitAArea").click(function(){
				$.ajax({
					type: "POST",
					url: "AddArea", // 
					data: $('form.formularioAA').serialize(),
					success: function(){//function(msg){
						alert("hecho");//$("#thanks").html(msg)
						//$("#form-content").modal('hide');
                                                
					},
					error: function(){
						alert("falló");
					}
				});
                                refreshAreas();
			});
		});
    </script>
    <!--FIN SCRIPT MODAL PARA AGREGAR AREA-->

<!-- mODAL PARA VER TABLA-->
<div class="modal fade bs-example-modal-lg" id="ventanatabla" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregando Actividad</h3></center>
            </div>
                <div class="modal-body">
                     <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading"><center>Actividades creadas</center></div>
                        <div class="panel-body"></div>
                        
                       <!-- Table -->
                            <!-- Table -->
                        <table class="table table-bordered table-hover" >
                            <caption><center>Actividades Añadidas al OA</center></caption>
                           <colgroup>
           <col />
           <col />
           <col />
           <col />

        
        </colgroup>

        <thead>
           <tr>
               <th scope="col" >Titulo</th>
             <th scope="col" >Tipo de Saber</th>
             <th scope="col">Tipo de recurso</th>
             <th scope="col"></th>
           </tr>
        </thead>

        <tbody id="tablaActividades">
           </tbody> 
                        </table>
                     </div>
                </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cerrar</button>
                   <!-- <a data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>-->
            </div>
        </div>
    </div>
</div>
<!--FIN mODAL PARA VER TABLA-->
       
<!-- CONTENIDO-->
<div class="container-fluid contenido">
           
                       <aside class="col-xs-12 col-sm-3">
                        
  <div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading"><span class="glyphicon glyphicon-apple"></span>Posiciones (Ranking)<span class="glyphicon glyphicon-apple"></span></div>

  <!-- List group -->
  <ul class="list-group" id="ranking">
      
      
                  <script>
		$(document).ready(function () {
                    function refresPuntos()
                    {
				$.ajax({
					url: "PuntajeGeneral", // 
					success:function(msg){
						$("#ranking").html(msg);	
					},
					error: function(){
						
                                                
					}
				});
                            }
                             setInterval(refresPuntos, 3000);
			});
            </script>

      
      
    <li class="list-group-item"> 1er lugar</li>
    <li class="list-group-item">2do lugar</li>
    <li class="list-group-item">3er lugar</li>
    <li class="list-group-item">4to lugar</li>
    <li class="list-group-item">5to lugar</li>
    <li class="list-group-item">6to lugar</li>
    <li class="list-group-item">7mo lugar</li>
    <li class="list-group-item">8vo lugar</li>
    <li class="list-group-item">9no lugar</li>
    <li class="list-group-item">10mo lugar</li>
  </ul>
</div>                
                </aside>
     
                
            <!--CONTENIDO CENTRAL-->
              
                <aside class="col-xs-12 col-sm-6">
                 <!-- Projects Row -->
                 
                 <h3>Buscar OA's</h3>
                 <div class="row">
            <div class="col-md-9">
                <form class="busqueda" name="busqueda"> 
                        <input type="text" class="form-control" name="cadenabusqueda" placeholder="Buscar OA" required/>
                        <center><b>Busqueda:</b></center>
               <center>    
                 <label class="radio-inline">
                     <input type="radio" name="optradio" checked="TRUE" value="G">General
                </label>
                        <label class="radio-inline">
                  <input type="radio" name="optradio" value="T">Titulo
                </label>
                <label class="radio-inline">
                    <input type="radio" name="optradio" value="AU">Autor
                </label>
                <label class="radio-inline">
                    <input type="radio" name="optradio" value="PC">Palabra Clave
                </label>
                <label class="radio-inline">
                    <input type="radio" name="optradio" value="AR">Area
                </label>
                        </center>
                </form>
            </div>
                        <div class="col-md-3">
                    <button id="submitbuscar" type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> Buscar</button>
                    </div>
                     <div id="resultadobusqueda"></div>

                     
                     <script>
		$(document).ready(function () {
			$("button#submitbuscar").click(function(){
				$.ajax({
					type: "POST",
					url: "BusquedaOA", // 
					data: $('form.busqueda').serialize(),
					success: function(msg){
						$("#resultadobusqueda").html(msg)
						//$("#form-content").modal('hide');
                                                
					},
					error: function(){
						alert("falló");
					}
				});
			});
		});
    </script>

                     


            <div class="col-md-4 portfolio-item">
            </div>
            </div>
                 <hr>
                 <h3>Mis OA's</h3>
                 <hr>
                 
        <div class="row" id="MisOas">
            
<script>
		$(document).ready(function () {
                    function refreshMisOas()
                    {
				$.ajax({
					url: "CargarMisOas", // 
					success:function(msg){
						$("#MisOas").html(msg);	
					},
					error: function(){
						
                                                
					}
				});
                            }
                             setInterval(refreshMisOas, 3000);
			});
            </script>

        </div>
                 <hr>
                 <h3>Otros OAs creados</h3>
                 <hr>
        <!-- /.row -->
 <!-- Projects Row -->
        <div class="row" id="OtrosOas">
            
                        
<script>
		$(document).ready(function () {
                    function refreshOtrosOas()
                    {
				$.ajax({
					url: "CargarOtrosOas", // 
					success:function(msg){
						$("#OtrosOas").html(msg);	
					},
					error: function(){
						
                                                
					}
				});
                            }
                             setInterval(refreshOtrosOas, 3000);
			});
            </script>

            
        </div>
        <!-- /.row -->
</div>
       
<!--jQuery -->
    <script src="Bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
