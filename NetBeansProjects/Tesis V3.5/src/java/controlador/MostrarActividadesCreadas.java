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
@WebServlet(name = "MostrarActividadesCreadas", urlPatterns = {"/MostrarActividadesCreadas"})
public class MostrarActividadesCreadas extends HttpServlet {

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
        String tituloAc=null;
        String tipoSaberAc=null;
        String tipoRecursoAc=null;
        int idOAactual=0;
        String tituloOA=cbd.tituloOA;
        System.out.println("CONEXION: "+cbd);
        try (PrintWriter out = response.getWriter()) {
            try
            {
                cbd.rs = cbd.st.executeQuery("SELECT idObjetos_Aprendizaje FROM objetos_aprendizaje WHERE titulo='"+tituloOA+"'");
                cbd.rs.next();
                idOAactual=cbd.rs.getInt("idObjetos_Aprendizaje");
                
                cbd.rs = cbd.st.executeQuery("SELECT titulo_actividad, tipo_saber, tipo_recurso FROM actividades WHERE Objetos_Aprendizaje_idObjetos_Aprendizaje="+idOAactual);
                System.out.println("STATEMENT: "+cbd.st);
                while(cbd.rs.next()){
                
                tituloAc=cbd.rs.getString("titulo_actividad");
                tipoSaberAc=cbd.rs.getString("tipo_saber");
                tipoRecursoAc=cbd.rs.getString("tipo_recurso");
                
                out.println("<tr>\n" +
"             \n" +
"             <td>"+tituloAc+"</td>\n" +
"             <td>"+tipoSaberAc+"</td>\n" +
"             <td>"+tipoRecursoAc+"</td>\n" +
"             <td></td>\n" +
"           </tr>");
            }
                
                
            }
             catch(SQLException sqle)
            {
                System.out.println("ERROR EN CARGAR MostrarActividades Creadas "+sqle.toString());    
            }
                
            System.out.println("CARGADO");
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
