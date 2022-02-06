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
@WebServlet(name = "CargaPublicaciones", urlPatterns = {"/CargaPublicaciones"})
public class CargaPublicaciones extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<h4>Notificacion</h4>");
            Coneccion cbd=(Coneccion) request.getSession().getAttribute("conexionbd");
        String user=null;
        String notificacion=null;
        //String usuario=(String)request.getSession().getAttribute("usuario");
        try
        {
           cbd.rs = cbd.st.executeQuery("SELECT * FROM notificaciones as n INNER JOIN usuarios as u on(n.Usuarios_idUsuarios=u.idUsuarios) ORDER BY idnotificaciones DESC" );
           while(cbd.rs.next())
           {
               user=cbd.rs.getString("usuario");
               notificacion=cbd.rs.getString("notificacion");
               
            out.println("       <div class=\"row\">\n" +
"            <div class=\"col-md-7\">\n" +
"                    <img class=\"img-responsive\" src=\"./img/img4.jpg\" alt=\"\">\n" +
"            </div>\n" +
"            <div class=\"col-md-5\">\n" +
"                <h3>"+user+"</h3>\n" +
"                <p><b>Ha realizado lo siguiente: </b>"+notificacion+"</p>\n" +
"            </div>\n" +
"        </div>\n" +
"        <!-- /.row -->\n" +
"\n" +
"        <hr>\n" +
"");
           }
        }
        catch(SQLException sqle)
        {}
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
