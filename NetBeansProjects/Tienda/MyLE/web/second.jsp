<%-- 
    Document   : second
    Created on : 15/07/2014, 01:09:18 AM
    Author     : Jav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        Nombre con param.nombre-----> ${param.nombre}
        <br>
        Nombre con param.["nombre"]-----> ${param["nombre"]}
        <br>
        Nombre con paramValues["nombre"][0]----> ${paramValues["nombre"]['0']}
        <br>
        Nombre con paramValues.nombre[1]-----> ${paramValues.nombre[1]}
        <br>
        Nombres con paramValues.nombre[1]-----> ${paramValues}
        <br>
        aparece 'hola'? ${'hola'}
        <br>
        aparece "hola"? ${"hola"}
        <br>
        aparece "0"+1 ${"0"+1}
        <br>
        aparece "hola"+5 \${"hola"+5}
        <br>
        aparece "hola"+"adios" \${"hola"+" adios"}
        <br>
        aparece "0"+"1" ${"0"+"1"}
        <br>
        aparece '0'+'1' ${'0'+'1'}
        <br>
        operador ternario ${1==0? "si":"no"}
        <br>
        <a href="third.jsp"> tercero</a>
    </body>
</html>
