<%-- 
    Document   : index
    Created on : 14/04/2015, 07:12:18 PM
    Author     : Javy
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="modelo.Coneccion" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
    
        <title>Sistema Gestor de Objetos de Aprendizaje</title>

        <!-- Bootstrap Core CSS -->
        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="Bootstrap/css/modern-business.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="Bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <!-- Navigation -->
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
                    <a class="navbar-brand" href="index.jsp"><span class="glyphicon glyphicon-education"></span> Sistema Gestor de Objetos de aprendizaje <span class="glyphicon glyphicon-education"></span> </a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#ventanaregistro" data-toggle="modal" modal="true"><span class="glyphicon glyphicon-check"></span> Registrarse</a>
                        </li>
                        <li>
                            <a href="#ventanalogin" data-toggle="modal" modal="true"><span class="glyphicon glyphicon-log-in"></span> Iniciar sesión</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
        <!-- /.container -->
        </nav>

        <!-- Header Carousel -->
        <header id="myCarousel" class="carousel slide">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
            <div class="item active">
                <div class="fill" ><img class="img-responsive" src="./img/img1.jpg"></div>
                
            </div>
            <div class="item">
                
                <div class="fill" ><img class="img-responsive" src="./img/img2.jpg"></div>
                
            </div>
            <div class="item">
                <div class="fill" ><img class="img-responsive" src="./img/img3.jpg"></div>
                
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </header>

    <!-- Page Content -->
    <div class="container-fluid">

        <!-- Marketing Icons Section -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    <center><span class="glyphicon glyphicon-education"></span> Últimos OA's creados y Avisos <span class="glyphicon glyphicon-education"></span></center>
                </h1>
            </div>
            
        <% 
            session.setAttribute("conexionbd",new Coneccion());
            
            Coneccion conexion=(Coneccion)session.getAttribute("conexionbd");
             String tituloOA=null;
             String des=null;
             String obj=null;
             String autor=null;
             try
            {
                conexion.rs = conexion.st.executeQuery("SELECT * FROM objetos_aprendizaje ORDER BY idObjetos_Aprendizaje DESC LIMIT 5");
                System.out.println("STATEMENT: "+conexion.st);
                conexion.rs.next();
                
                tituloOA=conexion.rs.getString("titulo");
                des=conexion.rs.getString("descripcion");
                obj=conexion.rs.getString("objetivo");
                autor=conexion.rs.getString("autor");
                
            }
             catch(SQLException sqle)
            {
                System.out.println("ERROR EN CARGAR INDEX "+sqle.toString());    
            }
       
       
        %>
            <div class="col-md-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4><span class="glyphicon glyphicon-pushpin"></span> <%out.println(tituloOA);%></h4>
                        <img class="img-responsive" src="./img/img4.jpg" alt="">
                    </div>
                    <div class="panel-body">
                        <p><b>Objetivo: </b><%out.println(obj);%></p>
                        <p><b>Descripción: </b><%out.println(des);%></p>
                        <p><b>Autor: </b><%out.println(autor);%></p>
                   </div>
                </div>
            </div>
                        <% conexion.rs.next();
                          tituloOA=conexion.rs.getString("titulo");
                          des=conexion.rs.getString("descripcion"); 
                          obj=conexion.rs.getString("objetivo");
                          autor=conexion.rs.getString("autor");
                        %>
            <div class="col-md-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4><span class="glyphicon glyphicon-pushpin"></span> <%out.println(tituloOA);%></h4>
                        <img class="img-responsive" src="./img/img5.jpg" alt="">
                    </div>
                    <div class="panel-body">
                        <p><b>Objetivo: </b><%out.println(obj);%></p>
                        <p><b>Descripción: </b><%out.println(des);%></p>
                        <p><b>Autor: </b><%out.println(autor);%></p>
                    </div>
                </div>
            </div>
                        <% conexion.rs.next();
                          tituloOA=conexion.rs.getString("titulo");
                          des=conexion.rs.getString("descripcion");  
                         obj=conexion.rs.getString("objetivo");
                         autor=conexion.rs.getString("autor");
                        %>
            <div class="col-md-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4><span class="glyphicon glyphicon-pushpin"></span> <%out.println(tituloOA);%></h4>
                        <img class="img-responsive" src="./img/img6.jpg" alt="">
                    </div>
                    <div class="panel-body">
                        <p><b>Objetivo: </b><%out.println(obj);%></p>
                        <p><b>Descripción: </b><%out.println(des);%></p>
                        <p><b>Autor: </b><%out.println(autor);%></p>
                    </div>
                </div>
            </div>
                        <% conexion.rs.next();
                          tituloOA=conexion.rs.getString("titulo");
                          des=conexion.rs.getString("descripcion");  
                        obj=conexion.rs.getString("objetivo");
                        autor=conexion.rs.getString("autor");
                        %>
            
            <div class="col-md-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4><span class="glyphicon glyphicon-pushpin"></span><%out.println(tituloOA);%></h4>
                        <img class="img-responsive" src="./img/img7.jpg" alt="">
                    </div>
                    <div class="panel-body">
                        <p><b>Objetivo: </b><%out.println(obj);%></p>
                        <p><b>Descripción: </b><%out.println(des);%></p>
                        <p><b>Autor: </b><%out.println(autor);%></p>
                    </div>
                </div>
            </div>
                           <% conexion.rs.next();
                          tituloOA=conexion.rs.getString("titulo");
                          des=conexion.rs.getString("descripcion");  
                          obj=conexion.rs.getString("objetivo");
                          autor=conexion.rs.getString("autor");
                           %>
            <div class="col-md-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4><span class="glyphicon glyphicon-pushpin"></span><%out.println(tituloOA);%></h4>
                        <img class="img-responsive" src="./img/img8.jpg" alt="">
                    </div>
                    <div class="panel-body">
                        <p><b>Objetivo: </b><%out.println(obj);%></p>
                        <p><b>Descripción: </b><%out.println(des);%></p>
                        <p><b>Autor: </b><%out.println(autor);%></p>
                    </div>
                </div>
            </div>

            <div class="col-md-2">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4><span class="glyphicon glyphicon-globe"></span> Avisos</h4>
                    </div>
                    <div class="panel-body">
                        <aside> <div id="myCarouseltext" class="carousel slide">
            <!-- Indicators -->
            
            <%
             String aviso=null;
             String remitente=null;
             String tituloAviso=null;
                        
             try
            {
                conexion.rs = conexion.st.executeQuery("SELECT * FROM avisos ORDER BY idAvisos DESC LIMIT 5");
                System.out.println("STATEMENT: "+conexion.st);
                conexion.rs.next();
                
                aviso=conexion.rs.getString("Aviso");
                remitente=conexion.rs.getString("Remitente");
                tituloAviso=conexion.rs.getString("TituloAviso");
                
            }
             catch(SQLException sqle)
            {
                System.out.println("ERROR EN CARGAR INDEX "+sqle.toString());    
            }
            %>
            
            
            
            
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
            
            <div class="item active">
                    <h2><%out.println(tituloAviso);%></h2>
                    <p><%out.println(aviso);%></p>
                    <p><b>Atentamente: <%out.println(remitente);%></b></p>
            </div>
            
                           <% conexion.rs.next();
                aviso=conexion.rs.getString("Aviso");
                remitente=conexion.rs.getString("Remitente");
                tituloAviso=conexion.rs.getString("TituloAviso");
                           %>
           
            
            <div class="item">
                    <h2><%out.println(tituloAviso);%></h2>
                    <p><%out.println(aviso);%></p>
                    <p><b>Atentamente: <%out.println(remitente);%></b></p>
            </div>
            
                        <% conexion.rs.next();
                aviso=conexion.rs.getString("Aviso");
                remitente=conexion.rs.getString("Remitente");
                tituloAviso=conexion.rs.getString("TituloAviso");
                           %>
            
            <div class="item">
                    <h2><%out.println(tituloAviso);%></h2>
                    <p><%out.println(aviso);%></p>
                    <p><b>Atentamente: <%out.println(remitente);%></b></p>
            </div>
        
                        <% conexion.rs.next();
                aviso=conexion.rs.getString("Aviso");
                remitente=conexion.rs.getString("Remitente");
                tituloAviso=conexion.rs.getString("TituloAviso");
                           %>
           
            <div class="item">
                    <h2><%out.println(tituloAviso);%></h2>
                    <p><%out.println(aviso);%></p>
                    <p><b>Atentamente: <%out.println(remitente);%></b></p>
            </div>
           
                        <% conexion.rs.next();
                aviso=conexion.rs.getString("Aviso");
                remitente=conexion.rs.getString("Remitente");
                tituloAviso=conexion.rs.getString("TituloAviso");
                           %>
           
            
            <div class="item">
                    <h2><%out.println(tituloAviso);%></h2>
                    <p><%out.println(aviso);%></p>
                    <p><b>Atentamente: <%out.println(remitente);%></b></p>
            </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#myCarouseltext" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarouseltext" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </div>
        </div>                
        </aside>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <!-- /.row -->
        <hr>
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; JaVaGuSolutions 2015</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- modals-->

<div class="modal fade" id="ventanaregistro">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-check"></span> Registro</h3></center>
            </div>
            <form class="form-horizontal" role="form" method="Post" action="Registry">
            <div class="modal-body">
                <center>
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
                                <input type="email" class="form-control" id="mail" name="correo" placeholder="Correo Electrónico" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rmail" class="col-lg-2 control-label">Reintroduce tu Correo</label>
                            <div class="col-lg-10">
                                <input type="email" class="form-control" id="rmail" name="rcorreo" placeholder="Re- escribe tu Correo Electrónico (debe de coincidir con el anterior)" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-lg-2 control-label">Contraseña</label>
                            <div class="col-lg-10">
                                <input type="password" class="form-control" id="password" name="pass" placeholder="Contraseña (debe de contener 8 caractéres o más)" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rpassword" class="col-lg-2 control-label">Reintroduce Contraseña</label>
                            <div class="col-lg-10">
                                <input type="password" class="form-control" id="rpassword" name="rpass" placeholder="Re-escribe tu Contraseña (debe coincidir con la anterior)" required/>
                            </div>
                        </div>
                        <div class="form-inline">
                            <label for="tipo" class="col-lg-2 control-label">Tipo de usuario</label>
                            <center>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="opciones2" id="profe" value="profesor" required/>
                                        Profesor
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="opciones2" id="Alumno" value="alumno" required/>
                                        Alumno
                                    </label>
                                </div>
                            </center>
                        </div>
                            <div class="col-lg-offset-2 col-lg-10">
                            </div>
                <br/>
      </center>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> Registrarse</button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cerrar</button>
            </div>
                </form>
        </div>
    </div>
</div>




<div class="modal fade bs-example-modal-sm" id="ventanalogin">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-log-in"></span> Acceso</h3></center>
            </div>
            <form method="POST" action="Login" ><!--"pprofesor.jsp">-->
                <div class="modal-body">   
                    <center>
                        <input type="text" class="form-control" id="user" name="usuario" placeholder="Usuario o mail" required/>
                        <input type="password" class="form-control" id="contrasenia" name="pass" placeholder="Contraseña" required/>
                        </center>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> Entrar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cerrar</button>
                </div>
            </form>
        </div>
    </div>
</div>



    <!--/modals-->


    <!-- jQuery -->
    <script src="Bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="Bootstrap/js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    });
    </script>

</body>

</html>
