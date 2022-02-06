/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jav
 */
@WebServlet(urlPatterns = {"/Cookies"})
public class Cookies extends HttpServlet {

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
            throws ServletException, IOException 
    {
        PrintWriter out= response.getWriter();
        Cookie galleta= new Cookie("Jav","calcetin");
        galleta.setMaxAge(60*60*24);
        response.addCookie(galleta);
        
        Cookie [] cajaDeGalletas =request.getCookies();
        for(Cookie galletita: cajaDeGalletas)
        {
            Cookie galletaActual =galletita;
            String identificador= galletaActual.getName();
            String valor= galletaActual.getValue();
            
            if(identificador.equals("Jav"))
            {
                out.println("HELLOOOOOO IM LINDSAY LOHAN! XD");
                out.println("HOLA DE NUEVO");
            }
            else
            {
                out.println("welcome aboard!!!");
            }
        }
    }

}
