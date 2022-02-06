/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Javy
 */
@WebServlet(name = "Validador", urlPatterns = {"/Validador"})
public class Validador extends HttpServlet {

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
        String nombre=(String)request.getParameter("nombre");
        String apellidos=(String)request.getParameter("apellidos");
        String pass=(String)request.getParameter("pass1");
        String pass2=(String)request.getParameter("pass2");
        String user=(String)request.getParameter("usuario");
        String edad=(String)request.getParameter("edad");
        String sexo=(String)request.getParameter("sexo");
        String terminos=(String)request.getParameter("terminos");
        
        if(!pass.equals(pass2) || !terminos.equals("acepto"))
        {
            response.sendRedirect("index.html");
        }
        else
        {
            Usuario usuario= new Usuario();
            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setApellidos(apellidos);
            usuario.setPass(pass);
            usuario.setSexo(sexo);
            usuario.setUser(user);
            request.getSession().setAttribute("usuario", usuario);
            HttpSession sesion= request.getSession();
            RequestDispatcher rd=request.getRequestDispatcher("accesso.jsp");
            rd.forward(request, response);
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
