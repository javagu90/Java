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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jav
 */

@WebServlet(urlPatterns = {"/Ssession"})
public class Ssession extends HttpServlet {
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
            throws ServletException, IOException 
    {
        
        HttpSession sesion=request.getSession(false);
        
        if(sesion==null)
        {
            sesion=request.getSession(true);
            Integer conteo=new Integer(0);
            sesion.setAttribute("contar",conteo);
        }
        Integer conteo=(Integer)sesion.getAttribute("contar");
        conteo=new Integer(conteo.intValue()+1);
        sesion.setAttribute("contar", conteo);
        response.getWriter().print("el numero de veces q haz estado en esta pagina es de : "+conteo.intValue());
        
        sesion.setMaxInactiveInterval(60*60*24);
    }      
}