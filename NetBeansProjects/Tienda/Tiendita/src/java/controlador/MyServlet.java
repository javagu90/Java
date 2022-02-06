/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import modelo.Coneccion;

/**
 *
 * @author Jav
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected Coneccion conectar;
     protected String articulo;
     protected String datos="";
     protected String query;
     protected String precio;
     protected Statement st;
     protected ResultSet rs;
     protected Connection con;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        articulo=request.getParameter("art");
        request.setAttribute("art", articulo);
        query="select * from productos where (producto like '%";
        try {
               conectar=new Coneccion();
               con=conectar.conectar();
               st=con.createStatement();
               System.out.println(articulo);
               rs=st.executeQuery(query+articulo+"%')");
               while(rs.next())
               {
                   //7JOptionPane.showMessageDialog(null,"producto="+rs.getObject("producto")+
                   //", precio="+rs.getObject("precio"));
                   precio=rs.getString("precio");
                   articulo=rs.getString("producto");
                   datos+="<br>"+articulo+" "+precio+"<br>";
                   request.setAttribute("data",datos);
                   //request.setAttribute("art", articulo);
                   //request.setAttribute("prec",precio);
               }
               datos="";
               rs.close();
            } 
        catch(SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //response.sendRedirect(".jsp");
        RequestDispatcher rd=request.getRequestDispatcher("resultados.jsp");
        rd.forward(request, response);
   
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
