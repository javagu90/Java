

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
@WebServlet(urlPatterns = {"/Hello Servlet"})
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getServletContext().getRequestDispatcher("/hello.jsp");
        response.sendRedirect("hello.jsp");
        getServletContext().getRequestDispatcher("/hello.jsp");//.forward(request, response);
        /*response.setContentType("text/html; charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<p> hello</p>");
        out.close();3*/
       /*1 response.getWriter().print('a');
        request.getRequestDispatcher("hello.jsp").forward(request, response);
        response.getWriter().print('a');
        response.getWriter().close();*/
       //2request.getRequestDispatcher("http://google.com.mx").forward(request, response); lanza 404
    }
}
