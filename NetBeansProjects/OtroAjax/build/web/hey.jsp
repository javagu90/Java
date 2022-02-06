<%-- 
    Document   : hey
    Created on : 17/04/2015, 02:43:04 AM
    Author     : Javy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <script>
            
            /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadurl(url,id){
	var pagecnx = createXMLHttpRequest();
	pagecnx.onreadystatechange=function(){
		if (pagecnx.readyState == 4 && (pagecnx.status==200 || window.location.href.indexOf("http")==-1))
		document.getElementById(id).innerHTML=pagecnx.responseText;
	}
	pagecnx.open('GET',url,true);
	pagecnx.send();
}

function loadurlkey(e,url,id){
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla==13)
		loadurl(url,id);
}

function createXMLHttpRequest(){
	var xmlHttp=null;
	if (window.ActiveXObject) 
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	else if (window.XMLHttpRequest) 
		xmlHttp = new XMLHttpRequest();
	return xmlHttp;
}
            
        </script>
        
    </head>
    <body>
         <form >
        <input type='text' id="nombre"/>
        <button type='submit' onclick="loadurl('Servlet1','prueba');">dale 2</button>
    </form>
        
        <div id='prueba'></div>
    </body>
</html>
