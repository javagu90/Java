<%-- 
    Document   : realizarAc
    Created on : 30/04/2015, 11:56:08 AM
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
        int idOa=Integer.parseInt(request.getParameter("oa"));
        int idAc=Integer.parseInt(request.getParameter("ac"));
        int puntosAc=0;
        String estrategiasAc="";
        String tituloAc=null;
        String objetivoAc=null;
        String descripcionAc=null;
        String tipoRecurso=null;
        String direccion=null;
        try
        {
            cbd.rs=cbd.st.executeQuery("SELECT * FROM actividades as a inner join actividades_has_estrategia_aprendizaje as ahea on(a.idActividades=ahea.Actividades_idActividades) inner join estrategia_aprendizaje as ea on(ea.idEstrategia_aprendizaje=ahea.Estrategia_aprendizaje_idEstrategia_Aprendizaje) where idActividades="+idAc);
            while(cbd.rs.next())
            {
                estrategiasAc+=cbd.rs.getString("nombre_estrategia")+". ";
            }
            cbd.rs=cbd.st.executeQuery("SELECT * FROM actividades where idActividades="+idAc);
            cbd.rs.next();
            tituloAc=cbd.rs.getString("titulo_actividad");
            objetivoAc=cbd.rs.getString("objetivo_actividad");
            descripcionAc=cbd.rs.getString("descripcion_Actividad");
            puntosAc=cbd.rs.getInt("puntosActividad");
            tipoRecurso=cbd.rs.getString("tipo_recurso");
            direccion=cbd.rs.getString("direccion");
            
            
        }
        catch(SQLException sqle)
        {
            System.out.println("error en realizar--estrategias "+sqle.toString());
        }
//actividades del oa
        
        %>
        <!--fin codigo java-->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="javascript:history.back()" ><span class="glyphicon glyphicon-education"></span> SGOA-Realizar Actividad <span class="glyphicon glyphicon-education"></span></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
                        <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
        <div class="container-fluid contenido">
          <div class="panel panel-primary">
            <div class="panel-heading"><b><center>Detalles adicionales de la actividad</center></b></div>
            <ul class="list-group">
                <li class="list-group-item"><b>Titulo: </b><% out.println(tituloAc); %></li>
                <li class="list-group-item"><b>Objetivo: </b><% out.println(objetivoAc); %></li>
                <li class="list-group-item"><b>Descripción: </b><% out.println(descripcionAc); %></li>
                <li class="list-group-item"><b>Puntos a obtener al finalizar la actividad: </b><% out.println(puntosAc); %> puntos</li>
                <li class="list-group-item"><b>Estrategias de aprendizaje enfocadas en esta actividad: </b><% out.println(estrategiasAc); %></li>
                <%
                
                    if(tipoRecurso.equals("URL"))
                    {
                     out.println("<li class=\"list-group-item\"><a href=\""+direccion+"\" target=\"_blank\">Ir al sitio Web</a></li>");   
                    }
                    
                    if(tipoRecurso.equals("Imagen/Audio"))
                    {
                        direccion=direccion.trim();
                        int n=direccion.length();
                        char extension= direccion.charAt(n-1);
                        if(extension=='g')
                        {
                          out.println("<li class=\"list-group-item\"><img src=\"./imgOas/"+direccion+"\"></li>");   

                           System.out.println("es imagen: "+direccion);
                        }
                        if(extension=='3')
                        {
                            System.out.println("es audio");
                            out.println("<audio src=\"./imgOas/"+direccion+"\" controls autoplay preload=\"auto\"></audio>");
                        }
                                           }
                    if(tipoRecurso.equals("Video/PDF"))
                    {
                        direccion=direccion.trim();
                        int n=direccion.length();
                        char extension= direccion.charAt(n-1);
                        
                        if((extension=='4')||(extension=='i'))
                        {
                         System.out.println("es video"); 
                         out.println("<video src=\"./imgOas/"+direccion+"\" controls></video>");
                        }
                        if(extension=='f')
                        {
                            System.out.println("es pdf");
                            //out.println("<li class=\"list-group-item\"><embed src=\"./imgOas/"+direccion+"\" type=\"application/pdf\" width=\"100%\" height=\"100%\"></li>");
                            out.println("<div class=\"embed-responsive embed-responsive-4by3\">\n" +
                            "  <iframe class=\"embed-responsive-item\" src=\"./imgOas/"+direccion+"\"></iframe>\n" +
                            "</div>");
                        }
                        else
                        {
                            if(extension=='>')
                            {
                                out.println(direccion);
                            }
                       
                        }
                    }
                    
                %>
                
                <form method="Post" action="FinalizarActividad">
                <%out.println("<input type=\"hidden\" name=\"actividad\" value=\""+tituloAc+"\">\n" +
                        "<input type=\"hidden\" name=\"oa\" value=\""+idOa+"\">\n" +
                              "<input type=\"hidden\" name=\"puntos\" value=\""+puntosAc+"\">\n" +
"                <li class=\"list-group-item\"><center> <button type=\"submit\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-ok\"></span> Finalizar Actividad</button></center></li>\n" +
"                ");%>    
                </form>
            </ul>
        </div>
        </div>
    </body>
</html>
