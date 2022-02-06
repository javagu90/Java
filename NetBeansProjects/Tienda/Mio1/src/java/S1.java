/*import java.io.IOException;
import java.io.PrintWriter;*/
//import javax.servlet.ServletException;
/*import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class S1 extends HttpServlet {

/*    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet S1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet S1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //ServletContext contexto= getServletContext();
        try 
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet S1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(request.getContextPath()+" ----> ContextPath<br>");
            out.println(request.getQueryString()+" ----> QueryString<br>");
            out.println(request.getRequestURI()+" ----> RequestURI<br>");
            out.println(request.getServletPath()+" ----> ServletPath<br>");
            out.println(request.getRequestURL()+" ----> RequestURL<br>");
            out.println(request.getServletContext().getContextPath()+" ----> getServletContext().getContextPath()<br>");
       //     out.println(request.getServletContext()+" ----> getServletContext()<br>");
            out.println("<br>request.getServletContext().getInitParameterNames()");
            Enumeration e=request.getServletContext().getInitParameterNames();
            while(e.hasMoreElements())
            {
                String name= (String) e.nextElement();
                out.println("<br>");
                out.println(name);
                out.println(request.getServletContext().getInitParameter(name));
         
            }
            
            out.flush();
            out.println("<br>");
            out.println(getServletConfig().getInitParameter("name")+" ----> getServletConfig().getInitParameter(name)<br>");
            out.println(getServletConfig().getServletContext().getInitParameter("edad")+" ----> getServletConfig().getServletContext().getInitParameter(edad))<br>");
             //out.println(getServletConfig()+" ----> getServletConfig().getInitParameter(name)<br>");
            
            out.println("</body>");
            out.println("</html>");
        } 
        finally 
        {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
