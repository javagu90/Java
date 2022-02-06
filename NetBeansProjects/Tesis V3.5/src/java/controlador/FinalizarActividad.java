/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "FinalizarActividad", urlPatterns = {"/FinalizarActividad"})
public class FinalizarActividad extends HttpServlet {

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
        int idOa=0;
        int puntos=0;
        int idUser=0;
        int puntajeAnterior=0;
        int puntajeNuevo=0;
        String mensaje=null;
        String actividad=null;
        try
        {
         idOa=Integer.parseInt(request.getParameter("oa"));
         puntos=Integer.parseInt(request.getParameter("puntos"));
         actividad=(String)request.getParameter("actividad");
        }
        catch(Exception e)
        {
            System.out.println("eror en id oa"+ e.toString());
        }
        //JOptionPane.showMessageDialog(null, idOa);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

           Coneccion cbd= (Coneccion)request.getSession().getAttribute("conexionbd");
           String usuario=(String)request.getSession().getAttribute("usuario");
           try
           {
           cbd.rs=cbd.st.executeQuery("SELECT idUsuarios FROM usuarios WHERE usuario='"+usuario+"'");
           cbd.rs.next();
           idUser=cbd.rs.getInt("idUsuarios");
           cbd.rs=cbd.st.executeQuery("SELECT puntaje FROM puntaje where Usuarios_idUsuarios="+idUser);
           cbd.rs.next();
           puntajeAnterior=cbd.rs.getInt("puntaje");
           puntajeNuevo=puntos+puntajeAnterior;
           cbd.updatePuntaje(puntajeNuevo,idUser);
           mensaje="<b>"+usuario+"</b> ha finalizado la actividad <b>"+actividad+"</b> y ha ganado <b>"+puntos+" puntos</b>";
           cbd.st.executeUpdate("INSERT INTO notificaciones (notificacion, Usuarios_idUsuarios) VALUES ('"+mensaje+"','"+idUser+"')");
//cbd.agregarNotificacion(usuario,mensaje); INSERT INTO notificaciones (notificacion, Usuarios_idUsuarios) VALUES ("MENSAJE", 2)
           
           }
           catch(SQLException sqle)
           {
               System.out.println(sqle.toString());
           }
           response.sendRedirect("realizar.jsp?oa="+idOa);
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