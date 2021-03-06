<%@page import="java.sql.SQLException"%>
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
                <a class="navbar-brand" href="palumn.jsp"><span class="glyphicon glyphicon-education"></span> SGOA-Alumno(a): <% out.println(usuario);%> <span class="glyphicon glyphicon-education"></span></a>
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
          
          
          
          <!-- SCRIPT MOSTRAR PUNTAJE PROPIO-->
               <script>
		$(document).ready(function () {
                    function refreshPP()
                    {
				$.ajax({
					url: "PuntajePropio", // 
					success:function(msg){
						$("#").html(msg);	
					},
					error: function(){
					}
				});
                            }
                             setInterval(refreshPP, 1000);
			});
            </script>
          <!--FIN SCRIPT MOSTRAR PUNTAJE PROPIO-->
          
                    
			<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-book"></span>Actividades<b class="caret"></b></a>
                        <ul class="dropdown-menu list-group">
                            <li class="list-group-item list-group-item-success">
                                <a href="myoasalumn.jsp"><span class="glyphicon glyphicon-book"></span> Mis OA's</a>
                            </li>
                       </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span> Configuraci??n<b class="caret"></b></a>
                        <ul class="dropdown-menu list-group">
                            <li class="list-group-item list-group-item-success">
                                <a href="#ventanauser" data-toggle="modal" modal="true"><span class=" glyphicon glyphicon-pencil"></span> Cambiar nombre de Usuario</a>
                            </li>
                            <li class="list-group-item list-group-item-info">
                                <a href="#ventanapass" data-toggle="modal" modal="true"><span class=" glyphicon glyphicon-asterisk"></span> Cambiar Contrase??a</a>
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
                            <span class="glyphicon glyphicon-off"></span> Cerrar Sesi??n</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
        <!--fin menu-->

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
                        <center><p>Al pulsar el boton "Guardar" se te redireccionar?? a la p??gina de Inicio para actualizar tu informaci??n</p></center> 
                        </div>
                        <input type="text" class="form-control" id="userold" name="usuarioViejo" placeholder="Usuario o mail anterior" required/>
                        <input type="text" class="form-control" id="usernew" name="usuarioNuevo" placeholder="Usuario o mail nuevo" required/>
                        <input type="text" class="form-control" id="usernewagain" name="usuarioNuevoR" placeholder="Re-escribe el Usuario o mail nuevo" required/>
                        <input type="password" class="form-control" id="contra" name="pass" placeholder=" Verifica con tu Contrase??a" required/>
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
                <center><h3 class="modal-title"><span class=" glyphicon glyphicon-asterisk"></span> Cambio de Contrase??a</h3></center>
            </div>
            <form method="POST" action="UpdatePass">
                <div class="modal-body">   
                    <center>
                        <div class="alert alert-warning">
                        <center><p>Al pulsar el boton "Guardar" se te redireccionar?? a la p??gina de Inicio para actualizar tu informaci??n</p></center> 
                        </div>
                        <input type="text" class="form-control" id="user" name="usuario" placeholder="Usuario o mail" required/>
                        <input type="password" class="form-control" id="contraseniaold" name="passViejo" placeholder="Contrase??a anterior" required/>
                        <input type="password" class="form-control" id="contrasenianew" name="passNuevo" placeholder="Contrase??a nueva" required/>
                        <input type="password" class="form-control" id="contrasenianew" name="passNuevoR" placeholder="Re-escribe tu Contrase??a nueva" required/>
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
                        <center><p>Al pulsar el boton "Guardar" se te redireccionar?? a la p??gina de Inicio para actualizar tu informaci??n</p></center> 
                        </div>
                        <input type="text" class="form-control" id="nomb" name="nombre" placeholder="Nombre" required/>
                        <input type="text" class="form-control" id="ape" name="apellidos" placeholder="Apellidos" required/>
                        <input type="mail" class="form-control" id="mai" name="correo" placeholder="Correo Electr??nico" required/>
                    </center>
                </div>
                <div class="modal-footer">
                    <input type="text" class="form-control" id="u" name="usuario" placeholder="Escribe tu Usuario/correo para verificar los cambios" required/>
                    <input type="password" class="form-control" id="c" name="pass" placeholder=" Escribe tu contrase??a para verificar los cambios" required/>
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

<!-- CONTENIDO-->
<div class="container-fluid contenido">
           
                       <aside class="col-xs-12 col-sm-3">
                           
                           <div class="panel panel-success">
                               <div class="panel-heading"><span class="glyphicon glyphicon-king"></span>  <b>Datos competitivos</b>  <span class="glyphicon glyphicon-king"></span></div>
 <%
  String rango=null;
  int puntajeMeta=0;
  final int maximo=100;
  int puntos=0;
  try
 {
    cbd.rs=cbd.st.executeQuery("SELECT puntaje FROM puntaje as p inner join usuarios as u on(p.Usuarios_idUsuarios=u.idUsuarios) where usuario='"+usuario+"'");
    //JOptionPane.showMessageDialog(null, cbd.rs.toString());
    cbd.rs.next();
    puntos=cbd.rs.getInt("puntaje");
 }
 catch(SQLException sqle)
 {
     System.out.println("Error en la pagina palumn.jpg "+sqle.toString());
 }
  //puntajes de 20 (peon, caballo, alfil, torre, dama <-- ranking)
  if(puntos<20)
  {
      puntajeMeta=20-puntos;
      int n=puntos-0;
      int porcentaje=(n*100)/20;
      
           out.println("  <ul class=\"list-group\">\n" +
"      <li class=\"list-group-item\">Rango: <b>Pe??n </b><span class=\"glyphicon glyphicon-pawn\"></span></li>\n" +
"      <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-pawn\"></span>  Mi puntaje: <b>"+puntos+" puntos</b></li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-pawn\"></span>  Puntaje para llegar al siguiente rango (20 puntos):<b>"+puntajeMeta+"puntos</b>\n" +
"    </li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-pawn\"></span>  Progreso:  \n" +
"        <div class=\"progress\">\n" +
"  <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\""+porcentaje+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+porcentaje+"%\">\n" +
"    <span class=\"sr-only\">"+porcentaje+"% Complete</span> <b>"+porcentaje+"%</b>\n" +
"  </div>\n" +
"</div>\n" +
"    </li>\n" +
"    </ul>");
      
  }
  if( (puntos>=20) &&(puntos<40))
  {
      //caballo
      puntajeMeta=40-puntos;
      int n=puntos-20;
      int porcentaje=(n*100)/20;
      
           out.println("  <ul class=\"list-group\">\n" +
"      <li class=\"list-group-item\">Rango: <b>Caballero </b><span class=\"glyphicon glyphicon-knight\"></span></li>\n" +
"      <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-knight\"></span>  Mi puntaje: <b>"+puntos+" puntos</b></li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-knight\"></span>  Puntaje para llegar al siguiente rango (40 puntos):<b>"+puntajeMeta+"puntos</b>\n" +
"    </li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-knight\"></span>  Progreso:  \n" +
"        <div class=\"progress\">\n" +
"  <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\""+porcentaje+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+porcentaje+"%\">\n" +
"    <span class=\"sr-only\">"+porcentaje+"% Complete</span> <b>"+porcentaje+"%</b>\n" +
"  </div>\n" +
"</div>\n" +
"    </li>\n" +
"    </ul>");
  }
  if((puntos>=40)&&(puntos<60))
  {
      //alfil
      puntajeMeta=60-puntos;
      int n=puntos-40;
      int porcentaje=(n*100)/20;
      
           out.println("  <ul class=\"list-group\">\n" +
"      <li class=\"list-group-item\">Rango: <b>Espadach??n </b><span class=\"glyphicon glyphicon-bishop\"></span></li>\n" +
"      <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-bishop\"></span>  Mi puntaje: <b>"+puntos+" puntos</b></li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-bishop\"></span>  Puntaje para llegar al siguiente rango (60 puntos):<b>"+puntajeMeta+"puntos</b>\n" +
"    </li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-bishop\"></span>  Progreso:  \n" +
"        <div class=\"progress\">\n" +
"  <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\""+porcentaje+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+porcentaje+"%\">\n" +
"    <span class=\"sr-only\">"+porcentaje+"% Complete</span> <b>"+porcentaje+"%</b>\n" +
"  </div>\n" +
"</div>\n" +
"    </li>\n" +
"    </ul>");
  }
  if((puntos>=60)&&(puntos<80))
  {
      //torre
      puntajeMeta=80-puntos;
      int n=puntos-60;
      int porcentaje=(n*100)/20;
      
           out.println("  <ul class=\"list-group\">\n" +
"      <li class=\"list-group-item\">Rango: <b>Guardia </b><span class=\"glyphicon glyphicon-tower\"></span></li>\n" +
"      <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-tower\"></span>  Mi puntaje: <b>"+puntos+" puntos</b></li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-tower\"></span>  Puntaje para llegar al siguiente rango (80 puntos):<b>"+puntajeMeta+"puntos</b>\n" +
"    </li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-tower\"></span>  Progreso:  \n" +
"        <div class=\"progress\">\n" +
"  <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\""+porcentaje+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+porcentaje+"%\">\n" +
"    <span class=\"sr-only\">"+porcentaje+"% Complete</span> <b>"+porcentaje+"%</b>\n" +
"  </div>\n" +
"</div>\n" +
"    </li>\n" +
"    </ul>");
          
 
  }
  if(puntos>=80)
  {
      puntajeMeta=maximo-puntos;
      int n=puntos-80;
      int porcentaje=(n*100)/20;
      //monarca
      
           out.println("  <ul class=\"list-group\">\n" +
"      <li class=\"list-group-item\">Rango: <b>Monarca </b><span class=\"glyphicon glyphicon-king\"></span></li>\n" +
"      <li class=\"list-group-item\"><span class=\"glyphicon glyphicon-king\"></span>  Mi puntaje: <b>"+puntos+" puntos</b></li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-king\"></span>  Puntaje para llegar al m??ximo (100 puntos):<b>"+puntajeMeta+"puntos</b>\n" +
"    </li>\n" +
"    <li class=\"list-group-item\">\n" +
"        <span class=\"glyphicon glyphicon-king\"></span>  Progreso:  \n" +
"        <div class=\"progress\">\n" +
"  <div class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\""+porcentaje+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+porcentaje+"%\">\n" +
"    <span class=\"sr-only\">"+porcentaje+"% Complete</span> <b>"+porcentaje+"%</b>\n" +
"  </div>\n" +
"</div>\n" +
"    </li>\n" +
"    </ul>");
          
  }
 %>
 
  <!-- List group -->
  <!--<ul class="list-group">
      <li class="list-group-item">Rango:  <span class="glyphicon glyphicon-king"></span></li>
      <li class="list-group-item"><span class="glyphicon glyphicon-king"></span>  Mi puntaje</li>
    <li class="list-group-item">
        <span class="glyphicon glyphicon-king"></span>  puntaje para llegar al siguiente rango:09 puntos
    </li>
    <li class="list-group-item">
        <span class="glyphicon glyphicon-king"></span>  Progreso:  
        <div class="progress">
  <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 99%">
    <span class="sr-only">45% Complete</span>
  </div>
</div>
    </li>
    </ul>-->
</div>
                        
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
 <aside class="col-xs-12 col-sm-6" id="publicaciones">
                                          <!-- Project Three -->
                          <script>
		$(document).ready(function () {
                    function refreshPublicaciones()
                    {
				$.ajax({
					url: "CargaPublicaciones", // 
					success:function(msg){
						$("#publicaciones").html(msg);	
					},
					error: function(){
						
                                                
					}
				});
                            }
                             setInterval(refreshPublicaciones, 3000);
			});
            </script>

                </aside>          <!--FIN CONTENIDO CENTRAL-->
          
          <!--CONTENIDO AVISOS-->
            <aside class="col-xs-12 col-sm-3">
                                         
Aside
</aside>
          <!--FIN CONTENIDO AVISOS-->

        </div>   
<!--FIN CONTENIDO-->
       
<!--jQuery -->
    <script src="Bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="Bootstrap/js/bootstrap.min.js"></script>

<!--fin body-->
</body>
<!--FIN HTML-->
</html>