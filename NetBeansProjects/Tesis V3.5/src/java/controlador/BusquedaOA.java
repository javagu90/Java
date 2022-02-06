/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Coneccion;

/**
 *
 * @author Javy
 */
@WebServlet(name = "BusquedaOA", urlPatterns = {"/BusquedaOA"})
public class BusquedaOA extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Coneccion cbd=(Coneccion) request.getSession().getAttribute("conexionbd");
        String busqueda=(String)request.getParameter("cadenabusqueda");
        String opradio=(String)request.getParameter("optradio");
        int id=0;
        String tituloOA=null;
        String objetivoOA=null;
        String descripcionOA=null;
        String autorOA=null;
       // JOptionPane.showMessageDialog(null, opradio);
        try (PrintWriter out = response.getWriter()) 
        {
            
            if(opradio.equals("G"))
            {
                 out.println(" <table class=\"table table-bordered table-hover\" >\n" +
"                            <caption><center>Resultados</center></caption>\n" +
"                           <colgroup>\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"\n" +
"        \n" +
"        </colgroup>\n" +
"\n" +
"        <thead>\n" +
"           <tr>\n" +
"               <th scope=\"col\" >Id</th>\n" +
"               <th scope=\"col\" >Titulo</th>\n" +
"               <th scope=\"col\" >Objetivo</th>\n" +
"             <th scope=\"col\" >Descripción</th>\n" +
"             <th scope=\"col\">Autor</th>\n" +
"             <th scope=\"col\">   </th>\n" +
"           </tr>\n" +
"        </thead>\n" +
"\n" +
"                        <tbody>");
            try
            {
                cbd.rs = cbd.st.executeQuery("SELECT * FROM objetos_aprendizaje");
                while(cbd.rs.next()){
                
                id=cbd.rs.getInt("idObjetos_Aprendizaje");
                tituloOA=cbd.rs.getString("titulo");
                objetivoOA=cbd.rs.getString("objetivo");
                descripcionOA=cbd.rs.getString("descripcion");
                autorOA=cbd.rs.getString("autor");
                
                out.println("<tr>\n" +
"             \n" +
"             <td>"+id+"</td>\n" +
"             <td>"+tituloOA+"</td>\n" +
"             <td>"+descripcionOA+"</td>\n" +
"             <td>"+objetivoOA+"</td>\n" +
"             <td>"+autorOA+"</td>\n" +
"             <td>"+"<a href=\"realizar.jsp?oa="+id+"\">"+"Realizar</a></td>\n" +
"           </tr>");
            }
                
                out.println("</tbody> \n" +
"                        </table>\n" +
"       ");
            }
             catch(SQLException sqle)
            {
                System.out.println("ERROR EN CARGAR MostrarBusqueda OA TITULO Creadas "+sqle.toString());    
            }
                 
            }
            
            if(opradio.equals("T"))
            {
                 out.println(" <table class=\"table table-bordered table-hover\" >\n" +
"                            <caption><center>Resultados</center></caption>\n" +
"                           <colgroup>\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"\n" +
"        \n" +
"        </colgroup>\n" +
"\n" +
"        <thead>\n" +
"           <tr>\n" +
"               <th scope=\"col\" >Id</th>\n" +
"               <th scope=\"col\" >Titulo</th>\n" +
"               <th scope=\"col\" >Objetivo</th>\n" +
"             <th scope=\"col\" >Descripción</th>\n" +
"             <th scope=\"col\">Autor</th>\n" +
"             <th scope=\"col\">   </th>\n" +
"           </tr>\n" +
"        </thead>\n" +
"\n" +
"                        <tbody>");
            try
            {
                cbd.rs = cbd.st.executeQuery("SELECT * FROM objetos_aprendizaje WHERE titulo='"+busqueda+"'");
                while(cbd.rs.next()){
                
                id=cbd.rs.getInt("idObjetos_Aprendizaje");
                tituloOA=cbd.rs.getString("titulo");
                objetivoOA=cbd.rs.getString("objetivo");
                descripcionOA=cbd.rs.getString("descripcion");
                autorOA=cbd.rs.getString("autor");
                
                out.println("<tr>\n" +
"             \n" +
"             <td>"+id+"</td>\n" +
"             <td>"+tituloOA+"</td>\n" +
"             <td>"+descripcionOA+"</td>\n" +
"             <td>"+objetivoOA+"</td>\n" +
"             <td>"+autorOA+"</td>\n" +
"             <td>"+"<a href=\"realizar.jsp?oa="+id+"\">"+"Realizar</a></td>\n" +
"           </tr>");
            }
                
                out.println("</tbody> \n" +
"                        </table>\n" +
"       ");
            }
             catch(SQLException sqle)
            {
                System.out.println("ERROR EN CARGAR MostrarBusqueda OA TITULO Creadas "+sqle.toString());    
            }
                 
            }
                
            if(opradio.equals("AU"))
            {
                 out.println(" <table class=\"table table-bordered table-hover\" >\n" +
"                            <caption><center>Resultados</center></caption>\n" +
"                           <colgroup>\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"\n" +
"        \n" +
"        </colgroup>\n" +
"\n" +
"        <thead>\n" +
"           <tr>\n" +
"               <th scope=\"col\" >Id</th>\n" +
"               <th scope=\"col\" >Titulo</th>\n" +
"               <th scope=\"col\" >Objetivo</th>\n" +
"             <th scope=\"col\" >Descripción</th>\n" +
"             <th scope=\"col\">Autor</th>\n" +
"             <th scope=\"col\">   </th>\n" +
"           </tr>\n" +
"        </thead>\n" +
"\n" +
"                        <tbody>");
            try
            {
                cbd.rs = cbd.st.executeQuery("SELECT * FROM objetos_aprendizaje WHERE autor like '%"+busqueda+"%'");
                while(cbd.rs.next()){
                
                id=cbd.rs.getInt("idObjetos_Aprendizaje");
                tituloOA=cbd.rs.getString("titulo");
                objetivoOA=cbd.rs.getString("objetivo");
                descripcionOA=cbd.rs.getString("descripcion");
                autorOA=cbd.rs.getString("autor");
                
                out.println("<tr>\n" +
"             \n" +
"             <td>"+id+"</td>\n" +
"             <td>"+tituloOA+"</td>\n" +
"             <td>"+descripcionOA+"</td>\n" +
"             <td>"+objetivoOA+"</td>\n" +
"             <td>"+autorOA+"</td>\n" +
"             <td>"+"<a href=\"realizar.jsp?oa="+id+"\">"+"Realizar</a></td>\n" +
"           </tr>");
            }
                
                out.println("</tbody> \n" +
"                        </table>\n" +
"       ");
            }
             catch(SQLException sqle)
            {
                System.out.println("ERROR EN CARGAR MostrarBusqueda OA TITULO Creadas "+sqle.toString());    
            }
                 
            }
            
          
                
            if(opradio.equals("PC"))
            {
                 out.println(" <table class=\"table table-bordered table-hover\" >\n" +
"                            <caption><center>Resultados</center></caption>\n" +
"                           <colgroup>\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"\n" +
"        \n" +
"        </colgroup>\n" +
"\n" +
"        <thead>\n" +
"           <tr>\n" +
"               <th scope=\"col\" >Id</th>\n" +
"               <th scope=\"col\" >Titulo</th>\n" +
"               <th scope=\"col\" >Objetivo</th>\n" +
"             <th scope=\"col\" >Descripción</th>\n" +
"             <th scope=\"col\">Autor</th>\n" +
"             <th scope=\"col\">   </th>\n" +
"           </tr>\n" +
"        </thead>\n" +
"\n" +
"                        <tbody>");
                 
            try
            {
                
                cbd.rs = cbd.st.executeQuery("SELECT * FROM objetos_aprendizaje as oas inner join objetos_aprendizaje_has_palabras_clave as oahpc on(oas.idObjetos_Aprendizaje=oahpc.Objetos_Aprendizaje_idObjetos_Aprendizaje) inner join palabras_clave as pc on(pc.idPalabras_Clave=oahpc.Palabras_Clave_idPalabras_Clave) WHERE pc.palabras='"+busqueda+"'");
                
               while(cbd.rs.next())
                {
                 id=cbd.rs.getInt("idObjetos_Aprendizaje");
                tituloOA=cbd.rs.getString("titulo");
                objetivoOA=cbd.rs.getString("objetivo");
                descripcionOA=cbd.rs.getString("descripcion");
                autorOA=cbd.rs.getString("autor");
                
                out.println("<tr>\n" +
"             \n" +
"             <td>"+id+"</td>\n" +
"             <td>"+tituloOA+"</td>\n" +
"             <td>"+descripcionOA+"</td>\n" +
"             <td>"+objetivoOA+"</td>\n" +
"             <td>"+autorOA+"</td>\n" +
"             <td>"+"<a href=\"realizar.jsp?oa="+id+"\">"+"Realizar</a></td>\n" +
"           </tr>");
            }
                
                out.println("</tbody> \n" +
"                        </table>\n" +
"       ");
            }
             catch(SQLException sqle)
            {
                System.out.println("ERROR EN CARGAR MostrarBusqueda OA PALABRAS CLAVE "+sqle.toString());    
            }
                 
            }            
            
            if(opradio.equals("AR"))
            {
                 out.println(" <table class=\"table table-bordered table-hover\" >\n" +
"                            <caption><center>Resultados</center></caption>\n" +
"                           <colgroup>\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"           <col />\n" +
"\n" +
"        \n" +
"        </colgroup>\n" +
"\n" +
"        <thead>\n" +
"           <tr>\n" +
"               <th scope=\"col\" >Id</th>\n" +
"               <th scope=\"col\" >Titulo</th>\n" +
"               <th scope=\"col\" >Objetivo</th>\n" +
"             <th scope=\"col\" >Descripción</th>\n" +
"             <th scope=\"col\">Autor</th>\n" +
"             <th scope=\"col\">   </th>\n" +
"           </tr>\n" +
"        </thead>\n" +
"\n" +
"                        <tbody>");
                 
                 
            try
            {
                
                cbd.rs = cbd.st.executeQuery("SELECT * FROM objetos_aprendizaje as oas INNER JOIN area as a on(oas.Area_idArea=a.idArea) where nombre_area='"+busqueda+"'");
                
               while(cbd.rs.next())
                {
                 id=cbd.rs.getInt("idObjetos_Aprendizaje");
                tituloOA=cbd.rs.getString("titulo");
                objetivoOA=cbd.rs.getString("objetivo");
                descripcionOA=cbd.rs.getString("descripcion");
                autorOA=cbd.rs.getString("autor");
                
                out.println("<tr>\n" +
"             \n" +
"             <td>"+id+"</td>\n" +
"             <td>"+tituloOA+"</td>\n" +
"             <td>"+descripcionOA+"</td>\n" +
"             <td>"+objetivoOA+"</td>\n" +
"             <td>"+autorOA+"</td>\n" +
"             <td>"+"<a href=\"realizar.jsp?oa="+id+"\">"+"Realizar</a></td>\n" +
"           </tr>");
            }
                
                out.println("</tbody> \n" +
"                        </table>\n" +
"       ");
            }
             catch(SQLException sqle)
            {
               System.out.println("ERROR EN CARGAR MostrarBusqueda OA PALABRAS CLAVE "+sqle.toString());    
            }
                 
            }            
    
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
