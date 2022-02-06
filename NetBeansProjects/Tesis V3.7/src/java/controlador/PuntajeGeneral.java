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
@WebServlet(name = "PuntajeGeneral", urlPatterns = {"/PuntajeGeneral"})
public class PuntajeGeneral extends HttpServlet {

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
        String usuarioP=null;
        String puntajeU=null;
        try (PrintWriter out = response.getWriter()) {
           
            try
            {
              cbd.rs = cbd.st.executeQuery("SELECT usuario, puntaje FROM usuarios INNER JOIN puntaje ON idUsuarios=Usuarios_IdUsuarios ORDER BY puntaje DESC LIMIT 10");
               /*select usuarios.usuario,puntaje.puntaje
		from usuarios
			inner join puntaje on usuarios.idUsuarios=puntaje.Usuarios_IdUsuarios
ORDER BY `puntaje`.`puntaje`  DESC LIMIT 10*/
              int i=1;
              while(cbd.rs.next()){
                  usuarioP=cbd.rs.getString("usuario");
                  puntajeU=cbd.rs.getString("puntaje");
                  String compuesto= usuarioP+": "+puntajeU+" puntos";
                  
                  out.println("<li class=\"list-group-item\"><span class=\"glyphicon glyphicon-apple\"></span><b>"+i+" Lugar: </b>"+compuesto+"</li>");
                  i++;
              }
              cbd.rs.close();
            }
            catch(SQLException sqle)
            {
             System.out.println("ERROR EN CARGAR PUNTAJE GENERAL "+ sqle.toString());
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
