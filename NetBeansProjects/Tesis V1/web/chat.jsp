<%-- 
    Document   : chat
    Created on : 26/03/2015, 12:30:42 PM
    Author     : Javy
--%>
<script type="text/javascript">
        $(function(){
            $('#conversations').tabs();
        });
    </script>
<aside class="col-xs-12 col-sm-3">
    <div id="container">
        <div class="leftPanel">
            <div class="userInfo">
                <span class="disconnected" id="status">Desconectado</span>
                Nombre: <input type="text" id="userName"/><span class="onLineUserName"></span>
            </div>
            <div>
                <button id="connect" onclick="wsclient.connect(document.getElementById('userName').value);">Conectar</button>
                <button id="disconnect" disabled="disabled" onclick="wsclient.disconnect();">Desconexión</button>
            </div>
            <div id="onLineUsersPanel">
                <h3>Usuarios conectados:</h3>
                <ul id="onlineUsers">
                </ul>
            </div>
        </div>
        <div id="conversations">
        <ul>
        </ul>
        </div>
    </div>
 ASIDE               
</aside>
      