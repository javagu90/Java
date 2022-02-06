/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import model.Alumno;
import java.io.*;

/**
 *
 * @author GrayFox
 */
@WebServlet(name = "Controller2", urlPatterns = {"/Controller2"})
public class Controller2 extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre=request.getParameter("nombre");
        String apellido=request.getParameter("apellido");
        String edad=request.getParameter("edad");
        request.setAttribute("nombre", nombre);
        request.setAttribute("apellido", apellido);
        request.setAttribute("edad", edad);
        Alumno alumno =new Alumno(nombre,apellido,edad);
        request.setAttribute("alumno", alumno);
        PrintWriter p= response.getWriter();
        p.println(request.getContextPath()+" ----> ContextPath");
        p.println(request.getQueryString()+" ----> QueryString");
        p.println(request.getRequestURI()+" ----> RequestURI");
        p.println(request.getServletPath()+" ----> ServletPath");
        p.println(request.getRequestURL()+" ----> RequestURL");
        p.println(request.getPathInfo()+" ----> RequestURL");
        //RequestDispatcher rd=request.getRequestDispatcher("view.jsp");
        //rd.forward(request, response);
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
