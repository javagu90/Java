<%-- 
    Document   : exito
    Created on : 17/04/2015, 10:28:08 PM
    Author     : Javy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <title>Request de Mootools</title>
   <script src="MooTools-Core-1.5.1.js" type="text/javascript"></script>
</head>

<body>
Clase Request de Mootools, un envoltorio de XMLHttpRequest. Para Ajax!
<p>
<form name="formulario">
    <input type="text" name="nombre"/>
<a href="javascript:void(0)" id="mienlace">Pulsa este enlace</a>
</form>
<p>
<div id="cajaactualizacion">
Aquí voy a actualizar el texto!
</div>

<script>
window.addEvent('domready', function(){
   //función a ejecutar cuando esté listo el dom
   
   $('mienlace').addEvent('click', function(evento){
       var v=document.formulario.nombre.value;
      var nuevoRequest = new Request({
         method: 'post', 
         url:'Servlet?valor1='+v, //url: 'solicitud.html',
         onRequest: function() { alert('Empezando esta solicitud Ajax!'); },
         onSuccess: function(texto, xmlrespuesta){ $('cajaactualizacion').set('html',texto);},
         onFailure: function(){alert('Error!');}
      }).send();
      
   });
   
});
</script>
</body>
</html>