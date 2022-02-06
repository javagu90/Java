/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import conexion.Datos;
import entidad.Usuario;
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
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        String nombre=request.getParameter("nombre");
        String pass=request.getParameter("pass");
        
        Datos data= new Datos();
        Usuario u= new Usuario(60,nombre);
        data.insertarUsuario(u);
       //Usuario u=  data.getUsuario(2);
        //out.println("User: "+ u.getNombre());
        out.println("Insertado correctamente");
        
    }
}
