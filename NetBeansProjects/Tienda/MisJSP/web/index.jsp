<%-- 
    Document   : index
    Created on : 9/07/2014, 02:46:46 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%-- ESTE TAG SIVE PARA HACER COMENTARIOS--%>

<%
    /*Crearemos la clase Alumno dentro de este tag
        ESTE TAG SIRVE PAR CREAR CODIGO JAVA
    */
    class Alumno
    {
        public String nombre;
        public Alumno (String nombre )
        {
         this.nombre=nombre;   
        }
        
        public String toString()
        {
            return nombre;
        }
    }
    %>
    
    <!-- Asignando unh nombre de alumno con la etiqueta de DECLARACIONES de jsp-->
    
    <%! String nombre= "javy";
        String hoy= "Hoy es: ";
        java.util.Date dia= new java.util.Date();
    %>
    
    <!-- creando a alumno-->
    
    <% Alumno javy= new Alumno(nombre);%> 
   
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <!-- imprimiendo el nombre con la etiqueta de  expresiones de jsp-->
         <form name="formulario" action="siguiente.jsp" method="POST">     
        Hola: <%=javy+" "+hoy+" "+dia %>
    <button type="submit" value="ok"> de acuerdo </button>
    </form>
    </body>
</html>
