/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Coneccion;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Javy
 */
@WebServlet(name = "SubirArchivo", urlPatterns = {"/SubirArchivo"})
public class SubirArchivo extends HttpServlet {

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
         String archivo=null;
         FileItemFactory factory = new DiskFileItemFactory();
         ServletFileUpload upload = new ServletFileUpload(factory);
         List items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            Logger.getLogger(SubirArchivo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en Parserequest "+ex.toString());
        }
         
         for(Object item:items){
             FileItem uploaded=(FileItem) item;
             if(!uploaded.isFormField())
             {
                 File fichero=new File("/home/webmilab/SGOAs/apache-tomcat-7.0.59/webapps/Tesis_V3.5/imgOas/", uploaded.getName());
                 try {
                     //JOptionPane.showMessageDialog(null, uploaded.getName());
                     Coneccion cbd= (Coneccion)request.getSession().getAttribute("conexionbd");
                     cbd.setRutaMultimedia(uploaded.getName());
                     uploaded.write(fichero);
                     //JOptionPane.showMessageDialog(null,"TO STRING: "+fichero.toString()+" TO URI: "+fichero.toURI()+" TO PATH: "+fichero.toPath());
                    try (PrintWriter out = response.getWriter()) {
                 out.println("Archivo cargado con <b>EXITO</b>");
                     /*    + "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/E2mGKfeeecs\" frameborder=\"0\" allowfullscreen></iframe>"
                         + "<img class=\"img-responsive\" src=\"./img/img5.jpg\" alt=\"\">");
                 out.println("<img class=\"img-responsive\" src=\""+archivo+"\" alt=\"\">");
                 out.println("");*/
                    }
                 } catch (Exception ex) {
                     Logger.getLogger(SubirArchivo.class.getName()).log(Level.SEVERE, null, ex);
                     //JOptionP"error en uploadfile "+ex.toString());
                     System.out.println("error en uploadfile "+ex.toString());
                     try (PrintWriter out = response.getWriter()) {
                     out.println("<b>ERROR</b>");
                     }
                 }
                 
             }
             else
             {
                 String key=uploaded.getFieldName();
                 String valor=uploaded.getString();
                 System.out.println("Key: "+key+" Valor: "+valor);
                 
             }
         }
        
        
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SubirArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SubirArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
