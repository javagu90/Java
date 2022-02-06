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
import java.util.Enumeration;

/**
 *
 * @author Jav
 */
@WebServlet(urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doHead(request,response);
        PrintWriter out= response.getWriter();
        Enumeration e= request.getHeaderNames();
        while(e.hasMoreElements())
        {
            String valor= (String) e.nextElement();
            out.println(valor+":"+request.getHeader(valor));
        }
        out.println(request.getMethod());
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
          PrintWriter out= response.getWriter();
      out.println(request.getMethod());
    }
        
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {   
        response.setContentLength(17);
        response.setContentType("text/html");
                  PrintWriter out= response.getWriter();
                  out.print("<html><body><h1>hola</h1></body></html>");
    }
            
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
    }
                
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
    }
                    
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
    }
                        
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
    }
                            
    protected void doUri(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
    }                            
}
