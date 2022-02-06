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
@WebServlet(urlPatterns = {"/source"})
public class S1 extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
   {
       request.getRequestDispatcher("target").forward(request, response);
      // response.sendRedirect("target");
   }
}
