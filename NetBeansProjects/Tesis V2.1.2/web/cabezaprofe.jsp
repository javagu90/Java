<%-- 
    Document   : cabezaalumno
    Created on : 26/03/2015, 11:47:46 AM
    Author     : Javy
--%>
    <%@page import="modelo.Coneccion"%>
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
                <a class="navbar-brand" href="pprofesor.jsp"><span class="glyphicon glyphicon-education"></span> Sistema Gestor de Objetos de Aprendizaje <span class="glyphicon glyphicon-education"></span></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-bell"></span> Notificaciones <span class="badg badg-info">10</span></a>
                        <ul class="dropdown-menu list-group">
                            <li class="list-group-item list-group-item-success"><a><span class="glyphicon glyphicon-education"></span> Juanito te a superado con 345 puntos</a></li>
                            <li class="list-group-item list-group-item-success"><a><span class="glyphicon glyphicon-education"></span> Juanito gano en OA challenge!</a></li>
                            <li class="list-group-item list-group-item-success"><a><span class="glyphicon glyphicon-education"></span> Juanito selecciono programacion extrema</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="myperfil.jsp"><span class="glyphicon glyphicon-user"></span> Mi Perfil</a>
                    </li>
					<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-book"></span> Mis OA's<b class="caret"></b></a>
                        <ul class="dropdown-menu list-group">
                            <li class="list-group-item list-group-item-success">
                                <a href="myoas.jsp"><span class="glyphicon glyphicon-book"></span> Mis OA's Usados</a>
                            </li>
                            <li class="list-group-item list-group-item-info">
                                <a href="#ventanaOA1"  data-toggle="modal" modal="true" ><span class="glyphicon glyphicon-book"></span> Crear un OA</a>
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
                    	<a href="index.jsp" onclick="<%request.getSession().invalidate(); %>">
                            <span class="glyphicon glyphicon-off"></span> Cerrar Sesión</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


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
                        <input type="text" class="form-control" id="user" name="usuario" placeholder="Titulo del OA" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Objetivo del OA" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Descripción del OA" required></textarea>
                        <br/>
                        Área:
                        <select>
                            <option>u</option>
                        </select> &nbsp
                        <a style="text-decoration: none;" href="#agregarArea" data-toggle="modal" modal="true" id="nArea" title="Agregar Área"><span class="glyphicon glyphicon-plus"></span></a>
                        &nbsp &nbsp &nbsp
                        Categoría:
                        <select>
                            <option>u</option>
                        </select> &nbsp
                        <a style="text-decoration: none;" href="#agregarCategoria" data-toggle="modal" modal="true" id="nCategoria" title="Agregar Categoría"><span class="glyphicon glyphicon-plus"></span></a>
                        <br/>
                        
                    </center>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a href="#ventanaOA2" data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Siguiente Paso <span class="glyphicon glyphicon-chevron-right"></span></a>
                </div>
        </div>
    </div>
</div>



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
                    <a href="#ventanaR1"data-toggle="modal" modal="true" class="btn btn-default"> <img src="../web/img/video.png"/><br/>Video</a>
                    <a href="#ventanaR2"data-toggle="modal" modal="true" class="btn btn-default"> <img src="../web/img/music.png"/><br/>Audio</a>
                    <a href="#ventanaR2"data-toggle="modal" modal="true" class="btn btn-default"> <img src="../web/img/picture.png"/><br/>Imagen</a>
                    <a href="#ventanaR1"data-toggle="modal" modal="true" class="btn btn-default"> <img src="../web/img/pdf.png"/><br/>PDF</a>
                    <a href="#ventanaR3"data-toggle="modal" modal="true" class="btn btn-default"> <img src="../web/img/enlace.png"/><br/>Enlace</a>
                    </center>
                    <br/>
                    <center><a href="#ventanatabla" class="btn btn-primary" data-toggle="modal" modal="true">Ver recursos agregados</a></center>
                </div>                                                                                                                
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a href="#ventanaOA3" data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Siguiente Paso <span class="glyphicon glyphicon-chevron-right"></span></a>
                </div>
        </div>
    </div>
</div>



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
                    <div class="container-fluid">
                        <div class="row">
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="1">Lógica-matemática</label>
                            </div>

                            <div class="col-sm-3">
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="2">Ligüistica-verbal</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="3">Espacial</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="4">Músical</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="5">Corporal-Kinestésica</label>
                            </div>
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="6">Intrapersonal</label>
                            </div>   
                            
                            <div class="col-sm-3">                                                   
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="7">Interpersonal</label>
                            </div>                                                            
                            
                            <div class="col-sm-3">
                                <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="8">Naturista</label>
                            </div>
                            <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Palabras clave (por ejem. 'Matemáticas, Programación, Ciencias') "required></textarea>
                        </div>                    
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a href="" type="submit" class="btn btn-success">Finalizar <span class="glyphicon glyphicon-chevron-right"></span></a>
                </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-lg" id="ventanaR1" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregando Actividad</h3></center>
            </div>
                <div class="modal-body">
                     <input type="text" class="form-control" id="user" name="usuario" placeholder="Escribe el título de la actividad" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Escribe la descripción de la actividad" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Escribe el objetivo de la actividad" required></textarea>
                        <br/>
                        
                        Tipo de Saber: 
                        <select>
                           <option>Seleccione una opción</option>
                           <option>Saber Ser</option>
                           <option>Saber Hacer</option>
                           <option>Saber Conocer</option>
                        </select> &nbsp
                        <br/><br/>
                        Fuente del recurso: 
                        <select>
                           <option>Seleccione una opción</option>
                           <option>Local</option>
                           <option>Enlace</option>
                           <option>Codigo Embebido</option>
                        </select> &nbsp
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
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="1">Individual</label>
                                    </div>

                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="2">Coolaborativa</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="3">PBL</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="4">Basada en casos</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="5">Otras</label>
                                    </div>
                                </div>                    
                            </div>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>




<div class="modal fade bs-example-modal-lg" id="ventanaR2" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregando Actividad</h3></center>
            </div>
                <div class="modal-body">
                     <input type="text" class="form-control" id="user" name="usuario" placeholder="Escribe el título de la actividad" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Escribe la descripción de la actividad" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Escribe el objetivo de la actividad" required></textarea>
                        <br/>
                        
                        Tipo de Saber: 
                        <select>
                           <option>Seleccione una opción</option>
                           <option>Saber Ser</option>
                           <option>Saber Hacer</option>
                           <option>Saber Conocer</option>
                        </select> &nbsp
                        <br/><br/>
                        Fuente del recurso: 
                        <select>
                           <option>Seleccione una opción</option>
                           <option>Local</option>
                           <option>Enlace</option>
                        </select> &nbsp
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
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="1">Individual</label>
                                    </div>

                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="2">Coolaborativa</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="3">PBL</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="4">Basada en casos</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="5">Otras</label>
                                    </div>
                                </div>                    
                            </div>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-lg" id="ventanaR3" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregando Actividad</h3></center>
            </div>
                <div class="modal-body">
                     <input type="text" class="form-control" id="user" name="usuario" placeholder="Escribe el título de la actividad" required/>
                        <br/>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Escribe la descripción de la actividad" required></textarea>
                        <br/>
                        <textarea type="text" class="form-control" id="user" name="usuario" placeholder="Escribe el objetivo de la actividad" required></textarea>
                        <br/>
                        
                        Tipo de Saber: 
                        <select>
                           <option>Seleccione una opción</option>
                           <option>Saber Ser</option>
                           <option>Saber Hacer</option>
                           <option>Saber Conocer</option>
                        </select> &nbsp
                        <br/><br/>
                        <input type="text" class="form-control" id="user" name="usuario" placeholder="Introduce la dirección Web (URL)" required></input>
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
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="1">Individual</label>
                                    </div>

                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="2">Coolaborativa</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="3">PBL</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="4">Basada en casos</label>
                                    </div>
                            
                                    <div class="col-sm-2">
                                        <label class="checkbox"><input id="cba1" type="checkbox" name="aprendizaje[]" value="5">Otras</label>
                                    </div>
                                </div>                    
                            </div>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>

<!-- mODAL PARA AGREGAR CATEGORIA-->
<div class="modal fade bs-example-modal-sm" id="agregarCategoria" >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregar Categoria</h3></center>
            </div>
                <div class="modal-body">
                     <input type="text" class="form-control" id="user" name="usuario" placeholder="Escribe el nombre de la Categoria" required/>
                        
                </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <a data-toggle="modal" modal="true" type="button" class="btn btn-primary" data-dismiss="modal">Agregar <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>


<!-- mODAL PARA AGREGAR AREA-->
<div class="modal fade bs-example-modal-sm" id="agregarArea" >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button tyle="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h3 class="modal-title"><span class="glyphicon glyphicon-book"></span> Agregar Área</h3></center>
            </div>
            <form onsubmit="javascript:loadurl('AddArea','agregarArea')">
                <div class="modal-body">
                    <input type="text" class="form-control" id="areaid" name="nombre_area" placeholder="Escribe el nombre del Área" required/>
                </div>
            <div class="modal-footer">

                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    <button  modal="true" type="submit" class="btn btn-primary" data-dismiss="modal" 
                       >Agregar <span class="glyphicon glyphicon-chevron-right"></span></button>
                    
                    
            
                       
            </div>
                                  </form>
        </div>
    </div>
</div>

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
                        <div class="panel-heading">Panel heading</div>
                        <div class="panel-body"></div>
                        <!-- Table -->
                        <table class="table">
                        ...
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
