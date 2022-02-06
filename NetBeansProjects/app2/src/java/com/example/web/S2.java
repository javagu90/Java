package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class S2 extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
		{
			response.setContentType("text/html");
			PrintWriter out= response.getWriter();
			out.println(getServletConfig().getInitParameter("S1"));
                        out.println(getServletConfig().getServletContext().getInitParameter("App"));
			
		}
}
