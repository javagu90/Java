/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DataActividad", urlPatterns = {"/DataActividad"})
public class DataActividad extends HttpServlet {

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
        
        String tituloA=null;
        String descripcionA=null;
        String objetivoA=null;
        String tipoSaberA=null;
        String puntosA=null;
        String enlace=null;
        String emb=null;
        String tipoRecurso=null;
        String estrategia1A=null;
        String estrategia2A=null;
        String estrategia3A=null;
        String estrategia4A=null;
        String estrategia5A=null;
        String archivo=null;
        
        
        tituloA=(String)request.getParameter("tituloActividad");
        descripcionA=(String)request.getParameter("descripcionActividad");
        objetivoA=(String)request.getParameter("objetivoActividad");
        puntosA=(String)request.getParameter("puntosActividad");
        tipoSaberA=(String)request.getParameter("tiposaberA");
        enlace=(String)request.getParameter("enlace");
        emb=(String)request.getParameter("emb");
        tipoRecurso=(String)request.getParameter("tipoRecurso");
        estrategia1A=(String)request.getParameter("Ind");
        estrategia2A=(String)request.getParameter("Cool");
        estrategia3A=(String)request.getParameter("PBL");
        estrategia4A=(String)request.getParameter("Basada");
        estrategia5A=(String)request.getParameter("Otras");
        
        archivo=cbd.getRutaMultimedia();
        
        
        /*JOptionPane.showMessageDialog(null,tituloA+" "+descripcionA+" "+objetivoA+" "+puntosA+" "+tipoSaberA+" "+tipoRecurso+" "+estrategia1A+" "+estrategia2A+" "+estrategia3A+" "+estrategia4A+" "+estrategia5A);
        JOptionPane.showMessageDialog(null,"ENLACE= "+enlace);
        JOptionPane.showMessageDialog(null,"Emb= "+emb);*/
        
        int puntosAct=Integer.parseInt(puntosA);
        String saber=null;
        if(tipoSaberA.equals("1"))
        {
           saber=" Saber Ser"; 
        }
        if(tipoSaberA.equals("2"))
        {
            saber="Saber Hacer";
        }
        if(tipoSaberA.equals("3"))
        {
            saber="Saber Conocer";
        }
        
        if(((enlace==null)||(enlace.length()==0)) && (archivo==null) && (emb!=null))
        {
           // JOptionPane.showMessageDialog(null, "ENTRE Al if 1");
        cbd.datosActividad(tituloA, descripcionA, objetivoA, saber, emb, tipoRecurso, puntosAct);
        }
        else
        {    
            if((archivo==null) && ((emb==null)||(emb.length()==0))&& (enlace!=null))
            {
               // JOptionPane.showMessageDialog(null, "ENTRE A if 2");
                cbd.datosActividad(tituloA, descripcionA, objetivoA, saber, enlace, tipoRecurso, puntosAct);
            }
            else
            {
                //JOptionPane.showMessageDialog(null, "ENTRE A ELSE");
                
               // JOptionPane.showMessageDialog(null, "DIRECCION DE ARCHIVO: "+archivo);
                 cbd.datosActividad(tituloA, descripcionA, objetivoA, saber, archivo, tipoRecurso, puntosAct);
                 cbd.setRutaMultimedia(null);
            }
        }
           
        
        
        if(estrategia1A!=null)
        {
            cbd.llenarRelacionAHEAP(tituloA,1);
        }
         if(estrategia2A!=null)
        {
            cbd.llenarRelacionAHEAP(tituloA,2);
        }
          if(estrategia3A!=null)
        {
            cbd.llenarRelacionAHEAP(tituloA,3);
        }
         if(estrategia4A!=null)
        {
            cbd.llenarRelacionAHEAP(tituloA,4);
        }
          if(estrategia5A!=null)
        {
            cbd.llenarRelacionAHEAP(tituloA,5);
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
