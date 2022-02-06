package com.example.web;

import com.example.model.BeerExpert;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class BeerSelect extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
		{
			//response.setContentType("text/html");
			//PrintWriter out= response.getWriter();
			//out.println("Beer selector Advice<br>");
			String c= request.getParameter("color");
			//out.println("<br>Got beer color "+c);
                        
                        BeerExpert be= new BeerExpert();
                        List result =be.getBrands(c);
                        /*Iterator it= result.iterator();
                        while(it.hasNext())
                        {
                            out.println("<br> try: "+it.next());
                        }*/
                        
                        request.setAttribute("styles", result);
                        RequestDispatcher view= request.getRequestDispatcher("result.jsp");
                        view.forward(request, response);
		}
}
