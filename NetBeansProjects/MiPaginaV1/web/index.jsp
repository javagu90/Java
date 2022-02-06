<%-- 
    Document   : index
    Created on : 11/05/2014, 07:50:08 PM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Principal</title>
    </head>
    <body bgcolor="GRAY">
        <form action="BienvenidaPersonal" method="GET">
        <table border="1" bordercolor="White" align="center">
            <h1 align="center"> BIENVENIDO... REGITRATE PARA CONTINUAR! </h1>
            <font color="white">
            <h3 align="center" font-color="red"> Los campos sombreados son abligatorios </h3>
            </font>
            <tr> <!-- espaciado-->
                <th> Nombre: <input type="text" name="nombre" value="inserta tu nombre aqui"> </th> <!-- celda-->
            </tr>
            <tr>
                <th> Apellidos: <input type="text" name="apellido" value="inserta tus apellidos aqui"> </th>
            </tr>
            <tr>
                <td> Direccion: <input type="text" name="direccion" value="inserta tu dirección aquí"> </td>
            </tr>
            <tr>
                <th> Edad: <input type="text" name="edad" value="inserta tu edad aquí"> </th>
            </tr>
            <tr>
                <td> Telefono: <input type="text" name="telefono" value="inserta tu telefono aquí"> </td>
            </tr>
        </table>
    <center>
        <button name="Aceptar" type="submit">Aceptar</button>
        <button name="Cancelar" type="reset">Cancelar</button>
    </center>
            </form>
    </body>
</html>
