/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UpdatePass", urlPatterns = {"/UpdatePass"})
public class UpdatePass extends HttpServlet {

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
        RequestDispatcher rd=null;
        String passViejo, passNuevo, passNuevoR, usuario;
        Coneccion cbd= (Coneccion)request.getSession().getAttribute("conexionbd");
        passViejo=(String)request.getParameter("passViejo");
        passNuevo=(String)request.getParameter("passNuevo");
        passNuevoR=(String)request.getParameter("passNuevoR");
        usuario=(String)request.getParameter("usuario");
        
        boolean ok=cbd.cambiarPass(usuario, passViejo, passNuevo, passNuevoR);
        request.getSession().invalidate();
        if(ok==false)
        {
            if(cbd.error==5)
            {
                response.sendRedirect("error6.html");
            }
            else
            {
                if(cbd.error==6)
                {
                    response.sendRedirect("error9.html");
                }
                else
                {
                    if(cbd.error==7)
                    response.sendRedirect("error3.html");
                    else
                        response.sendRedirect("errorG.html");
                }
                   
                //response.sendRedirect("errorG.html");
            }
                
        }
        else
        {
           response.sendRedirect("index.jsp");
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
