package tms_guardias;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class Mensajes{
    String strMensaje="";
    public Mensajes() {
    }
    
    public String getMensajeComun(int mensaje){
        switch (mensaje){
        case  1: strMensaje = "<html>" +
                               "<font color=FF0000>&#171; &#187;</font> Ingresar Criterios | " +
                               "<font color=FF0000>ENTER</font> Buscar guardias | " +
                               "<font color=FF0000>F1</font> Nueva guardia | " +
                               "<font color=FF0000>CTRL+U</font> Calendario | " +
                               "<font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana<br>"+
                               "<font color=FF0000>F4</font> Cerrar aplicacion" +
                               "</html>";
                 break;
        case  2: strMensaje = "<html>" +
                               "<font color=FF0000>&#171; &#187;</font> Ingresar datos | " +
                               "<font color=FF0000>F5</font> Seleccionar operador | " +
                               "<font color=FF0000>F7</font> Guardar guardia | " +
                               "<font color=FF0000>ESC</font> Cancelar nueva guardia |" +
                               "<font color=FF0000>CTRL+U</font> Calendario" +
                               "</html>"; 
                     break;
        case  3: strMensaje = "<html>" +
                               //"<font color=FF0000>F6</font> Modificar guardia | " +
                               "<font color=FF0000>F8</font> Reimprimir Ticket | " +
                               "<font color=FF0000>F9</font> Autorizar guardia | " +
                               "<font color=FF0000>ESC</font> Nueva busqueda |" +
                               "<font color=FF0000>CTRL+U</font> Calendario" +
                               "</html>"; 
                     break;
        case  4: strMensaje = "<html>" +
                               "<font color=FF0000>F7</font> Guardar cambios en guardia | " +
                               "<font color=FF0000>ESC</font> Cancelar modificacion |" +
                               "<font color=FF0000>CTRL+U</font> Calendario" +
                               "</html>"; 
                     break;
        case  5: strMensaje = "<html>" +
                               "<font color=FF0000>&#171; &#187;</font> Ingresar Criterios | " +
                               "<font color=FF0000>ENTER</font> Buscar guardias | " +
                               "<font color=FF0000>F1</font> Nueva guardia | " +
                               "<font color=FF0000>F2</font> Ver Registros del Operador | " +
                               "<font color=FF0000>F3</font> Ver Viajes del Operador | " +
                               "<font color=FF0000>F7</font> Guardar guardia | " +
                               "<font color=FF0000>CTRL+U</font> Calendario | " +
                               "<font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana<br>"+
                               "<font color=FF0000>F4</font> Cerrar aplicacion" +
                               "</html>";
                 break;
        }
        return strMensaje;
    }
}
