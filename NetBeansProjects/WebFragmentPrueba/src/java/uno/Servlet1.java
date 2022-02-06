/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jav
 */
//@WebServlet(urlPatterns = {"/Servlet1"})
/*agregaremos  por WEB-FRAGMENT
CREAREMOS UN JAR, DENTRO DE EL TENDREMOS  paquete donde esten las clases Y META-INF - WEBFRAGMENT.XML CON 
LA ETIQUETA 
<?XML VERSION="1.0" ENCODING="UTF-8"?>
<>
<WEB-FRAGMENT> <SERVLET> <SERVLET-MAPPING> ES HACER LO MISMO Q DEPLOYMENT DESCRIPTOR
SE PUEDEN AGREGAR COMPONENTES POR ANOTACION Y POR WEB-FRAGMENT*/
public class Servlet1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        	PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("= Servlet sin anotacion =");
		out.println("</body>");
		out.println("</html>");
		out.close();
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
