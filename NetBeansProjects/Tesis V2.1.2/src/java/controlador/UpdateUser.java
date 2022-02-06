/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
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
@WebServlet(name = "UpdateUser", urlPatterns = {"/UpdateUser"})
public class UpdateUser extends HttpServlet {

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
        String usuarioViejo,usuarioNuevo, usuarioNuevoR,pass;
        Coneccion cbd= (Coneccion)request.getSession().getAttribute("conexionbd");
        usuarioViejo=(String)request.getParameter("usuarioViejo");
        usuarioNuevo=(String)request.getParameter("usuarioNuevo");
        usuarioNuevoR=(String)request.getParameter("usuarioNuevoR");
        pass=(String)request.getParameter("pass");
        
        boolean cambioExitoso=cbd.cambiarUser(usuarioViejo, usuarioNuevo, usuarioNuevoR, pass);
        if(cambioExitoso==false)
        {
                if(cbd.error==1)
                {
                 response.sendRedirect("error5.html");   
                }
                else
                {
                    if(cbd.error==2)
                    {
                        response.sendRedirect("error6.html");
                    }
                    else
                    {
                        if(cbd.error==3)
                        {
                        response.sendRedirect("error7.html");
                        }
                        else
                        {
                            if(cbd.error==4)
                         response.sendRedirect("error8.html");
                            else
                                response.sendRedirect("errorG.html");
                        }
                    }
                }
                
            }
        else
        {
            /*if tipo user= profesor
            if(cbd.tipo_user.equals("profesor"))
            {
                rd=request.getRequestDispatcher("pprofesor.jsp");
            }
            else
            {
               rd=request.getRequestDispatcher("palumn.jsp");
            }
            rd.forward(request, response);
            //else rd a alumno*/
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
