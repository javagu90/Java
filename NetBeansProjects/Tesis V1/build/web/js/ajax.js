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