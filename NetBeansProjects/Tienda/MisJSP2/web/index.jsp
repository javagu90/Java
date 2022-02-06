<%-- 
    Document   : index
    Created on : 13/07/2014, 12:31:57 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.swing.JOptionPane" %>
<%@include file="cabeza.html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Standard tag useBean</title>
    </head>
    <body>
        <h1>Utilizando useBean tag con scriptting</h1>
        <jsp:useBean id="vecino1" scope="request" class="fraccionamiento.Vecino">
            <% vecino1.setNombre("Jav");
               vecino1.setDireccion("Nevada #37");
               out.println(vecino1.getNombre()+"\n"+vecino1.getDireccion());
            %>
        </jsp:useBean>
        
        <jsp:useBean id="vecino2"  class="fraccionamiento.Vecino" />
        <jsp:setProperty name="vecino2" property="nombre" value="Alex" />
        Vecino2 con LE:  ${vecino2.nombre}
        <br>
        <jsp:useBean id="vecino3" class="fraccionamiento.Vecino">
            <jsp:setProperty name="vecino3" property="direccion" value="Winsconsin 6"/>
            <jsp:expression>vecino3.getDireccion()</jsp:expression>
            
            <jsp:setProperty name="vecino1" property="direccion" param="W3"/>
            Vecino1 con LE:  ${vecino1.direccion}
        </jsp:useBean>
            <br>
            <jsp:include page="pie.html"/> 
                <jsp:forward page="final.jsp">
                    <jsp:param name="saludo" value="adios"/>
                </jsp:forward>
           <%-- </jsp:include> --%>
        
    </body>
</html>
