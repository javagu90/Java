<%-- 
    Document   : etiquetaTAG
    Created on : 18/07/2014, 03:08:29 AM
    Author     : Jav
--%>
<%class Alumno
{
  private String nombre;
  public Alumno(){}
  
  public void setNombre(String nombre)
  {
      this.nombre=nombre;
  }
  
  public String getNombre()
  {
      return nombre;
  }
}
%>

<% Alumno a= new Alumno(); %>

<% request.setAttribute("Javy", a);     %>

<%@tag description="Regresa el nombre del alumno" pageEncoding="UTF-8" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="nombreAlumno" required="false" rtexprvalue="false"%>


<%-- any content can be specified here e.g.: --%>
<h2>${nombreAlumno}</h2>
<jsp:doBody/>