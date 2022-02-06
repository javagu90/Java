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
@WebServlet(name = "CargarMisOas", urlPatterns = {"/CargarMisOas"})
public class CargarMisOas extends HttpServlet {

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
        int idOa;
        String titulo;
        String descripcion;
        String usuario=(String)request.getSession().getAttribute("usuario");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try
            {
                
                cbd.rs = cbd.st.executeQuery("SELECT * FROM objetos_aprendizaje as oas inner join usuarios_has_objetos_aprendizaje as uhoa on(oas.idObjetos_Aprendizaje=uhoa.Objetos_Aprendizaje_idObjetos_Aprendizaje) inner join usuarios as u on(u.idUsuarios=uhoa.Usuarios_idUsuarios) WHERE u.usuario='"+usuario+"'" );
                while(cbd.rs.next()){
                    
                    idOa=cbd.rs.getInt("idObjetos_Aprendizaje");
                    titulo=cbd.rs.getString("titulo");
                    descripcion=cbd.rs.getString("descripcion");
                    String compuesto=idOa+"  "+titulo;
                     out.println("<div class=\"col-md-4 portfolio-item\">\n" +
"                <a href=\"realizar.jsp?oa="+idOa+"\">\n" +
"                    <img class=\"img-responsive\" src=\"./img/img8.jpg\" alt=\"\">\n" +
"                </a>\n" +
"                <h3>\n" +
"                    <a href=\"realizar.jsp?oa="+idOa+"\">#"+compuesto+"</a>\n" +
"                </h3>\n" +
"                <p><b>Descripción:</b>"+descripcion+"</p>\n" +
"                    <a href=\"EliminarOa?oa="+idOa+"\">Eliminar</a>\n" +
"            </div>");
                }
            }
            catch(SQLException sqle)
            {
                System.out.println("Error en cargar Mis Oas"+ sqle.toString());
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
