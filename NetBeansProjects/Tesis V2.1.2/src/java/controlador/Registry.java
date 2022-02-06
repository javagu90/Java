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
@WebServlet(name = "Registry", urlPatterns = {"/Registry"})
public class Registry extends HttpServlet {

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
        String nombre,apellidos, correo, rcorreo, usuario, pass, rpass, tipo_usuario;
        Coneccion cbd= (Coneccion)request.getSession().getAttribute("conexionbd");
        nombre=(String)request.getParameter("fname");
        apellidos=(String)request.getParameter("lname");
        correo=(String)request.getParameter("correo");
        rcorreo=(String)request.getParameter("rcorreo");
        usuario=correo;
        pass=(String)request.getParameter("pass");
        rpass=(String)request.getParameter("rpass");
        tipo_usuario=(String)request.getParameter("opciones2");
        
        if(cbd.repetido(usuario)==true)
        {
          response.sendRedirect("error4.html");
        }
        else
        {
            if(pass.length()<8)
            {
                response.sendRedirect("error3.html");  
            }
            else
            {
                if((correo.equals(rcorreo))&&(pass.equals(rpass)))
                {
                    cbd.insertarUsuario(nombre, apellidos, correo, usuario , pass, tipo_usuario);
                    //if
                    String tipo=cbd.tipoUser(usuario);
                    if(tipo.equals("profesor"))
                    {
                        rd = request.getRequestDispatcher("pprofesor.jsp"); 
                        rd.forward(request,response);
                    }
                    else
                    {
                        rd = request.getRequestDispatcher("palumn.jsp"); 
                        rd.forward(request,response);
                    }
                }
                else
                {
                    response.sendRedirect("error2.html");
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
    }// </editor-fold
}
