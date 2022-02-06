<%-- 
    Document   : cabezaalumno
    Created on : 26/03/2015, 11:47:46 AM
    Author     : Javy
--%>
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
                <a class="navbar-brand" href="palumn.jsp"><span class="glyphicon glyphicon-education"></span> Sistema Gestor de Objetos de Aprendizaje <span class="glyphicon glyphicon-education"></span></a>
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
                            <span class="glyphicon glyphicon-off"></span> Cerrar Sesión
                        </a>
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
</div>
</div>



