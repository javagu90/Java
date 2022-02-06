/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Coneccion;

/**
 *
 * @author Javy
 */
@WebServlet(name = "FormOA3", urlPatterns = {"/FormOA3"})
public class FormOA3 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Coneccion cbd= (Coneccion)request.getSession().getAttribute("conexionbd");
        String lm=(String)request.getParameter("LM");
        String lv=(String)request.getParameter("LV");
        String e=(String)request.getParameter("E");
        String m=(String)request.getParameter("M");
        String ck=(String)request.getParameter("CK");
        String intra=(String)request.getParameter("INTRA");
        String inter=(String)request.getParameter("INTER");
        String n=(String)request.getParameter("N");
        String palabras=(String)request.getParameter("palabras");
        String tituloOA=null;
        int idUser=0;
        String mensaje=null;
        
        
        
        
        
        System.out.println(lm);
        System.out.println(lv);
        System.out.println(e);
        System.out.println(m);
        System.out.println(ck);
        System.out.println(intra);
        System.out.println(inter);
        System.out.println(n);
        System.out.println(palabras);
        
        if(lm!=null)
        {
            cbd.llenarRelacionOAHI(1);
        }
         if(lv!=null)
        {
            cbd.llenarRelacionOAHI(2);
        }
          if(e!=null)
        {
            cbd.llenarRelacionOAHI(3);
        }
         if(m!=null)
        {
            cbd.llenarRelacionOAHI(4);
        }
          if(ck!=null)
        {
            cbd.llenarRelacionOAHI(5);
        }
           if(intra!=null)
        {
            cbd.llenarRelacionOAHI(6);
        }
            if(inter!=null)
        {
            cbd.llenarRelacionOAHI(7);
        }
             if(n!=null)
        {
            cbd.llenarRelacionOAHI(8);
        }
        
         
        //System.out.println(palabras.replaceAll("\\s","")); 
        String[] arrayPalabras = palabras.split(","); //separando por comas
 
        // En este momento tenemos un array en el que cada elemento es una palabra.
        for (String arrayPalabra : arrayPalabras) {
            arrayPalabra=arrayPalabra.replaceAll("\\s","");//quitando espacios tabulaciones y retornos de carro
            System.out.println(arrayPalabra);
            cbd.palabraClave(arrayPalabra);
            cbd.llenarRelacionOAHPC(arrayPalabra);
        }
        String usuario=(String)request.getSession().getAttribute("usuario");
       tituloOA= cbd.llenarRelacionUHOAs(usuario);
        
        try
        {
           cbd.rs=cbd.st.executeQuery("SELECT idUsuarios FROM Usuarios WHERE usuario='"+usuario+"'");
           cbd.rs.next();
           idUser=cbd.rs.getInt("idUsuarios");
           mensaje="<b>"+usuario+"</b> ha creado el OA: <b>"+tituloOA+"</b>";
           cbd.st.executeUpdate("INSERT INTO notificaciones (notificacion, Usuarios_idUsuarios) VALUES ('"+mensaje+"','"+idUser+"')");

        }
        catch(SQLException sqle)
        {
            System.out.println("error" +sqle.toString());
        }
        //cbd.datosOA1(titulo, objetivo, descripcion, idArea, idCategoria);
      }

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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
