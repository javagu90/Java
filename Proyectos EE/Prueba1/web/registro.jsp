<%-- 
    Document   : registro
    Created on : 31/08/2015, 09:15:32 PM
    Author     : Javy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="Post" action="Validador">
      <div>
            <label>Nombre</label>
            <input type="text" name="nombre" id="nombre" required/>
            <br/><br/>
            <label>Apellidos</label>
            <input type="text" name="apellidos" id="apellidos" required/>
            <br/><br/>
            <label>Edad</label>
            <input type="number" min="18" max="99"/>
            <br/><br/>
            <label>Sexo</label>
            <label>Hombre</label><input type="radio" name="sexo" value="Masculino" id="Masculino" >
            <label>Mujer</label><input type="radio"  name="sexo" value="Femenino" id="Femenino">
            <br/><br/>
            <label>Usuario</label>
            <input type="text" name="user" id="user" required/>
            <br/><br/>
            <label>Contraseña</label>
            <input type="password" name="pass1" id="pass1" required/>
            <br/><br/>
            <label>Repetir contraseña</label>
            <input type="password" name="pass2" id="pass2" required/>
            <br/><br/>
            <label>Acepto terminos y condiciones<input type="checkbox" name="terminos" id="aceptoTYC" value="acepto" required/></label>
             <br/><br/>
            <input type="submit" value="Registrarse"/>
        </div>
            </form>
    </body>
</html>
