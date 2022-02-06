/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jav.controler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jav
 */
public class SessionLogin extends HttpServlet {

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out= response.getWriter();
        response.setContentType("text/html");
        String nombre= request.getParameter("user");
        String pass= request.getParameter("pass");
        //HttpSession sesion= request.getSession(); crea una sesion
        HttpSession sesion= request.getSession(false); //verifica si existio alguna sesion
        if(sesion==null)
        {
             out.println("no hay sesion<br>");
             sesion= request.getSession();
          
        }
        else
        {
                out.println("hay sesion<br>");
       
        }
        out.println(sesion.isNew()+" Sesion: "+sesion.getId()+"<br> galleta: "+response.getHeader("Set-Cookie")+"<br>"+ request.getHeader("Cookie"));
        sesion.setAttribute("nombre", nombre);
        sesion.setAttribute("pase",pass);
        if(sesion.isNew())
        {
            out.println("<br>bienvenido "+ sesion.getAttribute("nombre"));
        }
        else
        {
            out.println("<br>que bueno verte otra vez "+ sesion.getAttribute("nombre"));
        }
    }

   
}
