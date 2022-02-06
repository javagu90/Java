<%-- 
    Document   : realizar
    Created on : 29/04/2015, 09:15:44 PM
    Author     : Javy
--%>


<%@page import="java.sql.SQLException"%>
<%@page import="modelo.Coneccion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
                <title>SGOA-RealizarOA</title>
        <meta charset="UTF-8" name="viewport" content="width=device-width, user-scalable=no,  initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">        
         <!-- Bootstrap Core CSS -->
        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="Bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/estilos2.css"/>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
    </head>
    <body>
                     <!--codigo JAVA para conexion-->
        <%Coneccion cbd= (Coneccion)session.getAttribute("conexionbd");
        System.out.println("coneccion en pagina pprofeser "+ cbd);
        String usuario=(String)session.getAttribute("usuario");
        if(session.getAttribute("usuario")==null)
            response.sendRedirect("index.jsp");
        System.out.println(request.toString());
        int idOa=Integer.parseInt(request.getParameter("oa"));
        String categoria=null;
        String autor=null;
        String tituloOA=null;
        String descripcionOA=null;
        String area=null;
        String objetivoOa=null;
        String inteligenciasOA="";
        String mensaje=null;
        int idUser=0;
        //actividades del oa
        //String estategiasAprendizaje=null;
        
        try
        {
           cbd.rs=cbd.st.executeQuery("SELECT idUsuarios FROM Usuarios WHERE usuario='"+usuario+"'");
           cbd.rs.next();
           idUser=cbd.rs.getInt("idUsuarios");
                      
           cbd.rs=cbd.st.executeQuery("SELECT nombre_area FROM area as a inner join objetos_aprendizaje as oas on(a.idArea=oas.Area_idArea) WHERE idobjetos_aprendizaje="+idOa);
           cbd.rs.next();
            area=cbd.rs.getString("nombre_area");
            cbd.rs=cbd.st.executeQuery("SELECT nombre_categoria FROM categoria as c inner join objetos_aprendizaje as oas on(c.idCategoria=oas.Categoria_idCategoria) WHERE idobjetos_aprendizaje="+idOa);
            cbd.rs.next();
            categoria=cbd.rs.getString("nombre_categoria");
            cbd.rs=cbd.st.executeQuery("SELECT titulo, descripcion, objetivo, autor FROM objetos_aprendizaje where idObjetos_aprendizaje="+idOa);
            cbd.rs.next();
            tituloOA=cbd.rs.getString("titulo");
            descripcionOA=cbd.rs.getString("descripcion");
            objetivoOa=cbd.rs.getString("objetivo");
            autor=cbd.rs.getString("autor");
            mensaje="<b>"+usuario+"</b> ha seleccionado el OA <b>"+tituloOA+"</b>";
           cbd.st.executeUpdate("INSERT INTO notificaciones (notificacion, Usuarios_idUsuarios) VALUES ('"+mensaje+"','"+idUser+"')");
        
           cbd.rs=cbd.st.executeQuery("SELECT nombre_inteligencia FROM objetos_aprendizaje as oas inner join objetos_aprendizaje_has_inteligencias as oahi on(oas.idObjetos_Aprendizaje=oahi.Objetos_Aprendizaje_idObjetos_Aprendizaje) inner join inteligencias as i on(i.idinteligencias=oahi.inteligencias_idinteligencias) where idObjetos_Aprendizaje="+idOa);
            while(cbd.rs.next())
            {
                inteligenciasOA+=cbd.rs.getString("nombre_inteligencia");
                inteligenciasOA+=".  ";
            }
                        
        }
        catch(SQLException sqle)
        {
            System.out.println("error en realizar.jsp"+ sqle.toString());   
        }
        %>
        <!--fin codigo java-->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="javascript:history.back()" ><span class="glyphicon glyphicon-education"></span> SGOA-Realizar OA <span class="glyphicon glyphicon-education"></span></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
                        <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
        <div class="container-fluid contenido">

                                   <div class="panel panel-success">

                                       <div class="panel-heading"><b><center>Datos del Objeto de Aprendizaje</center></b></div>
        
                                   
              <ul class="list-group">
                  <li class="list-group-item"><b>Título del OA: </b><%out.println(tituloOA);%></li>
                  <li class="list-group-item"><b>Objetivo del OA: </b><%out.println(objetivoOa);%></li>
                  <li class="list-group-item"><b>Descripción del OA: </b><%out.println(descripcionOA);%></li>
                  <li class="list-group-item"><b>Area del OA: </b><%out.println(area);%></li>
                  <li class="list-group-item"><b>Categoria del OA: </b><%out.println(categoria);%></li>
                  <li class="list-group-item"><b>Autor del OA: </b><%out.println(autor);%></li>
                  <li class="list-group-item"><b>Inteligencias a desarrollar dentro del OA: </b><%out.println(inteligenciasOA);%></li>
              </ul>
                                   </div>
              
              
                               <div class="panel panel-primary">

                                       <div class="panel-heading"><b><center>Actividades contenidas dentro del OA</center></b></div>
        
                                   
              <ul class="list-group">
                  <table class="table table-bordered table-hover" >
                            <caption><center>Actividades Añadidas al OA</center></caption>
                           <colgroup>
           <col />
           <col />
           <col />
           <col />
           <col />
           <col />

        
        </colgroup>

        <thead>
           <tr>
               <th scope="col" >Titulo</th>
             <th scope="col" >Descripción</th>
             <th scope="col">Objetivo</th>
             <th scope="col">Tipo de saber</th>
             <th scope="col">Tipo de recurso</th>
             <th scope="col"></th>
           </tr>
        </thead>

        <tbody>
            <%cbd.rs = cbd.st.executeQuery("SELECT * FROM actividades as a inner join objetos_aprendizaje as oas on(a.Objetos_Aprendizaje_idObjetos_Aprendizaje=oas.idObjetos_Aprendizaje) WHERE Objetos_Aprendizaje_idObjetos_Aprendizaje="+idOa);
            String tituloAc=null;
            String descripcionAc=null;
            String objetivoAc=null;
            String tipoSaberAc=null;
            String tipoRecursoAc=null;
            int idAc=0;
            while(cbd.rs.next())
            {
                tituloAc=cbd.rs.getString("titulo_actividad");
                descripcionAc=cbd.rs.getString("descripcion_actividad");
                objetivoAc=cbd.rs.getString("objetivo_actividad");
                tipoSaberAc=cbd.rs.getString("tipo_saber");
                tipoRecursoAc=cbd.rs.getString("tipo_recurso");
                idAc=cbd.rs.getInt("idActividades");
                 out.println("<tr>\n" +
"             \n" +
"             <td>"+tituloAc+"</td>\n" +
"             <td>"+descripcionAc+"</td>\n" +
"             <td>"+objetivoAc+"</td>\n" +
"             <td>"+tipoSaberAc+"</td>\n" +
"             <td>"+tipoRecursoAc+"</td>\n" +
"             <td>"+"<a href=\"realizarAc.jsp?ac="+idAc+"&oa="+idOa+"\">"+"Realizar</a></td>\n" +
"           </tr>");
            }
            
            %>
        
           </tbody> 
        </table>
    </ul>
  </div>
 </div>
<!--jQuery -->
    <script src="Bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="Bootstrap/js/bootstrap.min.js"></script>
        
    </body>
</html>
