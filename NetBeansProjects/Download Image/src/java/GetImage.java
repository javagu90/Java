/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetImage extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("application/jar");
        
        ServletContext contexto= getServletContext();
        InputStream entrada= contexto.getResourceAsStream("/source.jar");
        
        int lectura=0;
        byte [] bytes= new byte[1024];
        
        OutputStream salida= response.getOutputStream();
        while((lectura=entrada.read(bytes)) !=-1)
        {
            salida.write(bytes, 0, lectura);
        }
        salida.flush();
        salida.close();
        
    }
}