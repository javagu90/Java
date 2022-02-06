package subProcesos;

import tms_venta.JClsColoresInterfaz;


public class Mensajes{
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
    String strMensaje="";
    public Mensajes() {
    }
    
    public String getMensajeVta(int mensaje){
        switch (mensaje){
        case  1: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">&#171; &#187;</font> Criterios | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Buscar corrida | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F2</font> Refoliar | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F4</font> Terminar sesion | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F5</font> Recoleccion | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F6</font> Venta BA | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F7</font> Canje BA | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F8</font> Cancelacion | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F3</font> Venta Tiempo Aire | <br>" +
                               "<font color="+ColoresInterfaz.cHTML1+">F9</font> Canje venta referenciada | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F11</font> Venta Reservacion | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F12</font> Cambio horario | " +
                               "<font color="+ColoresInterfaz.cHTML1+">CTRL+F1</font> Calendario | " +
                               "<font color="+ColoresInterfaz.cHTML1+">CTRL+F2</font> Buscar Boletos Referenciados" +
                               "</html>";
                 break;
        case  2: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">&#166;</font> Navegar entre corridas | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Seleccionar corrida | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Nueva busqueda" +
                               "</html>"; 
                     break;
        case  3: strMensaje = "<html>" +
                               "Ingresar numero de asiento | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Tipo pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Corridas | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva busqueda" +
                               "</html>"; 
                     break;
        case  4: strMensaje = "<html>" +
                               "Ingresar tipo pasajero: <font color="+ColoresInterfaz.cHTML1+">A</font> Adulto <font color="+ColoresInterfaz.cHTML1+">M</font> Menor <font color="+ColoresInterfaz.cHTML1+">E</font> Estudiante <font color="+ColoresInterfaz.cHTML1+">P</font> Profesor <font color="+ColoresInterfaz.cHTML1+">S</font> Senectud | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Numero de asiento | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda" +
                               "</html>"; 
                     break;
        case  5: strMensaje = "<html>" +
                               "Ingresar nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F8</font> Tipo Venta (<font color="+ColoresInterfaz.cHTML1+">N</font> Normal/<font color="+ColoresInterfaz.cHTML1+">F</font> Referenciada) | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F9</font> Reservar | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F10</font> Cobro | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Tipo pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda" +
                               "</html>"; 
                     break;
        case  51: strMensaje = "<html>" +
                               "Ingresar nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F8</font> Tipo Venta (<font color="+ColoresInterfaz.cHTML1+">N</font> Normal/<font color="+ColoresInterfaz.cHTML1+">F</font> Referenciada) | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F10</font> Cobro | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F12</font> Marcar boleto (<font color="+ColoresInterfaz.cHTML1+">S</font>encillo/<font color="+ColoresInterfaz.cHTML1+">R</font>edondo) | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Tipo pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda" +
                               "</html>"; 
                     break;
        case  6: strMensaje = "<html>" +    
                               "<font color="+ColoresInterfaz.cHTML1+">ARRIBA/ABAJO</font> Seleccionar Forma de Pago | <font color="+ColoresInterfaz.cHTML1+">ENTER</font> Aceptar Cantidad Recibida | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Cancelar Venta | <font color="+ColoresInterfaz.cHTML1+">F7</font> Ingresar Cupones |<br><font color="+ColoresInterfaz.cHTML1+">F10</font> Imprimir Boleto " +
                               "</html>";
                     break;
        case  61: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">¡Espere...!</font> Registro de venta en proceso." +
                               "</html>";
                     break;
        case  62: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">ARRIBA/ABAJO</font> Seleccionar Forma de Pago | <font color="+ColoresInterfaz.cHTML1+">ENTER</font> Aceptar Cantidad Recibida | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Cancelar Venta | <font color="+ColoresInterfaz.cHTML1+">F5</font> Cobro con Tarjeta Bancaria | <font color="+ColoresInterfaz.cHTML1+">F7</font> Ingresar Cupones |<br><font color="+ColoresInterfaz.cHTML1+">F10</font> Imprimir Boleto " +
                               "</html>";
                     break;
        case  7: strMensaje = "<html>" +
                               "Tome el Nuevo Boleto " +
                               "| <font color="+ColoresInterfaz.cHTML1+">ENTER</font> Continuar..." +
                               "</html>";
                 break;
        case  8: strMensaje = "<html>" +
                               "Ingrese la Cantidad Correcta Recibida " +
                               "| <font color="+ColoresInterfaz.cHTML1+">ENTER</font> Continuar..." +
                               "</html>";
                 break;
        case  9: strMensaje = "<html>" +
                               "Venta Cancelada " +
                               "| <font color="+ColoresInterfaz.cHTML1+">ENTER</font> Nueva Busqueda" +
                               "</html>";
                 break;
        case 10: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">ARRIBA/ABAJO</font> Seleccionar Forma de Pago | <font color="+ColoresInterfaz.cHTML1+">ENTER</font> Aceptar Forma de Pago Recibida | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F2</font> Nombre Pasajero | <font color="+ColoresInterfaz.cHTML1+">F1</font> Cancelar Venta |<br><font color="+ColoresInterfaz.cHTML1+">F10</font> Imprimir Boleto" +
                               "</html>";
                 break;
        case 11: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">ARRIBA/ABAJO</font> Seleccionar Terminal | <font color="+ColoresInterfaz.cHTML1+">ENTER</font> Aceptar Terminal | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Cancelar" +
                               "</html>";
                 break;
        case 12: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML3+">IZQDER </font> Ingresar Criterios | <font color="+ColoresInterfaz.cHTML3+">ENTER</font> Buscar Reservacion | <font color="+ColoresInterfaz.cHTML3+">ESC</font> Salir" +
                              "</html>";
                 break;
        case 13: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML3+">F8</font> Cancelar Reservacion | <font color="+ColoresInterfaz.cHTML3+">F10</font> Venta Reservacion | " +
                              "<font color="+ColoresInterfaz.cHTML3+">ENTER</font> Transaccion Parcial | <font color="+ColoresInterfaz.cHTML3+">ESC</font> Nueva Busqueda" +
                              "</html>";
                 break;
        case 131: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML3+">F8</font> Cancelar Reservacion | " +
                              "<font color="+ColoresInterfaz.cHTML3+">ENTER</font> Transaccion Parcial | <font color="+ColoresInterfaz.cHTML3+">ESC</font> Nueva Busqueda" +
                              "</html>";
                 break;
        case 14: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML3+">F8</font> Cancelar Reservacion Parcial | <font color="+ColoresInterfaz.cHTML3+">F10</font> Venta Reservacion Parcial | " +
                              "<font color="+ColoresInterfaz.cHTML3+">F11</font> Boleto Redondo | <font color="+ColoresInterfaz.cHTML3+">F12</font> Marcar (VT/CN) Reservacion | <font color="+ColoresInterfaz.cHTML3+">ESC</font> Seleccionar Reservacion" +
                              "</html>";
                 break;
        case 141: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML3+">F8</font> Cancelar Reservacion Parcial | " +
                              "<font color="+ColoresInterfaz.cHTML3+">F11</font> Boleto Redondo | <font color="+ColoresInterfaz.cHTML3+">F12</font> Marcar (VT/CN) Reservacion | <font color="+ColoresInterfaz.cHTML3+">ESC</font> Seleccionar Reservacion" +
                              "</html>";
                 break;
        case 15: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML1+">F6</font> Ingresar cantidad de pasajeros | <font color="+ColoresInterfaz.cHTML1+">ESC</font> Cancelar venta boleto abierto" +
                              "</html>";
                 break;
        case 16: strMensaje = "...";
                 break;
        case 17: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda | " +
                              "<font color="+ColoresInterfaz.cHTML1+">F8</font> Tipo Venta (<font color="+ColoresInterfaz.cHTML1+">N</font> Normal/<font color="+ColoresInterfaz.cHTML1+">F</font> Referenciada) | " +                
                              "<font color="+ColoresInterfaz.cHTML1+">F10</font> Cobro " +
                              "</html>";
                break;
        case 18: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Numero de asiento | <font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda | <font color="+ColoresInterfaz.cHTML1+">F10</font> Canjear" +
                              "</html>";
                break;
        case 19: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">&#171; &#187;</font> Criterios | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Buscar corrida | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Cancelar Canje | " +
                               "<font color="+ColoresInterfaz.cHTML1+">CTRL+F1</font> Calendario" +
                               "</html>";
                 break;
        case 20: strMensaje = "<html>" +
                               "Ingresar numero de asiento | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Nombre de Pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Corridas" +
                               "</html>";
                 break;
        case 21: strMensaje = "<html>" +
                              "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda | <font color="+ColoresInterfaz.cHTML1+">F10</font> Canjear" +
                              "</html>";
                break;
        case 22: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Ingresar Recoleccion | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Consultar Corridas" +
                               "</html>"; 
                break;
        case 23: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Regresar a boletos | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Registrar nombre Autorizado" +
                               "</html>"; 
                break;
        }
        return strMensaje;
    }
    
    public String getMensajeCallCenter(int mensaje){
        switch (mensaje){
        case  1: strMensaje = "<html>" +
                               "<font color="+ColoresInterfaz.cHTML1+">&#171; &#187;</font> Criterios | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ENTER</font> Buscar corrida | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F4</font> Terminar sesion | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F6</font> Venta BA | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F3</font> Venta Tiempo Aire | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F11</font> Consultar Reservaciones | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F12</font> Cambio horario | " +
                               "<font color="+ColoresInterfaz.cHTML1+">CTRL+F1</font> Calendario | " +
                               "<font color="+ColoresInterfaz.cHTML1+">CTRL+F2</font> Buscar Boletos Referenciados | " +                
                               "<font color="+ColoresInterfaz.cHTML1+">CTRL+2</font> Minimizar ventana | <font color="+ColoresInterfaz.cHTML1+">CTRL+1</font> Mostrar siguiente ventana"+
                               "</html>";
                 break;
        case  5: strMensaje = "<html>" +
                               "Ingresar nombre pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F8</font> Tipo Venta (<font color="+ColoresInterfaz.cHTML1+">N</font> Normal/<font color="+ColoresInterfaz.cHTML1+">F</font> Referenciada) | " +                
                               "<font color="+ColoresInterfaz.cHTML1+">F9</font> Reservar | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F10</font> Cobro | " +
                               "<font color="+ColoresInterfaz.cHTML1+">ESC</font> Tipo pasajero | " +
                               "<font color="+ColoresInterfaz.cHTML1+">F1</font> Nueva Busqueda" +
                               "</html>"; 
                     break;
        }
        return strMensaje;
    }
}
