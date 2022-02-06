/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jav
 */
@WebServlet(urlPatterns = {"/target"})
public class S2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //response.getWriter().println(request.getAttribute("javax.servlet.forward.servlet_path"));
        response.getWriter().println(request.getAttribute("javax.servlet.forward.servlet_path"));
        response.getWriter().println(request.getParameter("pass"));
        response.getWriter().println(request.getAttribute("javax.servlet.forward.query_string"));
        
        response.getWriter().println(request.getAttribute("javax.servlet.forward.context_path"));
    }
}
