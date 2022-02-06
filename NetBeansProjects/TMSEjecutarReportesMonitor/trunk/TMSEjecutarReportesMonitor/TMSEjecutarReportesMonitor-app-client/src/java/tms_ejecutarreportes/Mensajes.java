package tms_ejecutarreportes;

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
        case  2: strMensaje = "<html>" +
                              "<font color=ff0000>ARRIBA</font>/<font color=ff0000>ABAJO</font> Seleccionar reporte | " +
                              "<font color=ff0000>F10</font> Ejecutar Reporte | " +
                              "<font color=FF0000>CTRL+2</font> Minimizar ventana<br><font color=FF0000>CTRL+1</font> Mostrar siguiente ventana | "+
                              "<font color=ff0000>F4</font> Cerrar Aplicacion" +
                              "</html>";
                 break;
        case  1: strMensaje = "<html>" +
                              "<font color=ff0000>ARRIBA</font>/<font color=ff0000>ABAJO</font> Seleccionar grupo | " +
                              "<font color=ff0000>F1</font> Elegir reporte del grupo | " +
                              "<font color=FF0000>CTRL+2</font> Minimizar ventana<br><font color=FF0000>CTRL+1</font> Mostrar siguiente ventana | "+
                              "<font color=ff0000>F4</font> Cerrar Aplicacion" +
                              "</html>";
                 break;
        }
        return strMensaje;
    }
}
