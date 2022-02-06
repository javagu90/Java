/*
 * EstadoTransaccion.java
 *
 * Created on 27 de noviembre de 2008, 09:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.transacciones;

/**
 *
 * @author ocruz
 */
public class EstadoTransaccion {
    private int codigo;
    private String parent;
    private String codigoMsg;
    /**
     * Creates a new instance of EstadoTransaccion
     */
    public EstadoTransaccion() {
    }

    /**
     * Devuelve un n�mero entero que representa el c�digo de error.
     * @setCodigoMsg( C�digo de error.
     */
    public int getCodigo() {
        return codigo;
    }
    
    private void setCodigo(int numero) {
        this.codigo = numero;
    }

    /**
     * Establece el estado de las variables de error, dicho estado es generado por una solicitud al web service y se
     * valida a nivel de la clase TmsTransacciones.
     * @param pCodigo N�mero entero que representa el c�digo de error.
     * @param pObjeto Un String que contiene el nombre del objeto que realiz� la solicitud.
     * @param nota Un String que contiene un mensaje extra.
     */
    public void setEstadoCodigo(int pCodigo, String nota, String pObjeto) {
        setCodigo(pCodigo);   
        setParent(pObjeto);   
        switch(getCodigo()){
            case -1: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": No fue posible ejecutar procedimiento.");   break;
            case -2: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": Procedimiento fallo al retornar el resultado.");   break;
            case -3: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": Valor(es) requerido(s) para el(los) campo(s): "+nota+".");   break;
            case -4: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": Valor de campo fuera de rango: "+nota+".");   break;
            case -5: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": No fue posible ejecutar procedimiento. No existe dblink para origen: "+nota+".");   break;
            case -6: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": No fue posible ejecutar funcion de libreria: "+nota+".");   break;
            case -7: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": Valor no mayor a cero para campos referentes a disponibilidad de asientos.");   break;
            case -8: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": Datos de regreso no generaron resultados.");   break;
            case -9: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": Se obtuvo la excepcion "+nota);   break;
            case -10: setCodigoMsg("ER->"+getCodigo()+","+getParent()+": Los parametros "+nota+" no devolvieron informaci�n");   break;            
            /*case 29: setCodigoMsg("No es posible reservar m�s de 5 asientos. Por favor intente de nuevo (29)");   break;
            case 30: setCodigoMsg("No es posible comprar m�s de 40 asientos.Por favor intente de nuevo (30)");   break;
            case 31: setCodigoMsg("El Nombre del Pasajero es demasiado largo. Por favor intente de nuevo (31)");   break;*/
            case 1: setCodigoMsg("La transacci�n no pudo ser completada. Por favor intente m�s tarde.");   break;
            case 2: setCodigoMsg("Por favor verifique el n�mero de usuario o contrase�a e intente nuevamente");   break;
            case 3: setCodigoMsg( "No es posible asignarse. El usuario "+nota+" tiene un corte en proceso.");   break;
            case 4: setCodigoMsg( "No es posible asignarse. El usuario "+nota+" no esta dado de alta en el sistema.");   break;
            case 5: setCodigoMsg( "Su sesi�n ha expirado. Por favor salga del sistema e ingrese nuevamente.");   break;
            case 6: setCodigoMsg( "Su sesi�n ha expirado. Por favor salga del sistema e ingrese nuevamente.");   break;
            case 7: setCodigoMsg( "Su caja no est� configurada en el sistema. Por favor verifique su informaci�n."); break;
            case 8: setCodigoMsg( "No fue posible encontrar par�metros de "+nota+" Por favor verifique su informaci�n.");   break;
            case 9: setCodigoMsg( "No existen corridas con los criterios de b�squeda seleccionados. Por favor intente nuevamente.");   break;
            case 10: setCodigoMsg( "El asiento "+nota+" no puede ser ocupado. Por favor intente nuevamente.");   break;
            case 11: setCodigoMsg( "El asiento "+nota+" ya ha sido ocupado. Por favor intente una selecci�n.");   break;
            case 12: setCodigoMsg( "El origen que seleccion� no tiene un esquema asociado. Por favor contacte al Administrador del Sistema.");   break;
            case 14: setCodigoMsg( "La corrida "+nota+" ya no se encuentra disponible para la venta. Por favor intente una selecci�n.");   break;
            case 16: setCodigoMsg( "Ha ocurrido un problema durante la venta y no ha podido completarse. Por favor intente nuevamente.");   break;
            case 17: setCodigoMsg( "Los boletos introducidos no pertenecen a la misma corrida. Por favor verifique su informaci�n.");   break;
            case 18: setCodigoMsg( "No se hallaron boletos con la informaci�n introducida. Por favor verifique e intente nuevamente.");   break;
            case 19: setCodigoMsg( "El "+nota+" seleccionado no coincide con el "+nota+ "del boleto original. Por favor verifique su informaci�n e intente nuevamente.");   break;
            //case 20: setCodigoMsg( "El boleto "+nota+" ya ha sido cancelado. Por favor verifique su informaci�n.");   break;
            case 20: setCodigoMsg( "El boleto no puede ser cancelado. Por favor verifique su informaci�n.");   break;
            case 21: setCodigoMsg( "No es posible realizar la cancelaci�n del boleto, debido a un canje o transferencia previo");   break;
            case 22: setCodigoMsg( "Solo se pueden cancelar boletos pagados en EFECTIVO. Por favor verifique su informaci�n.");   break;
            case 23: setCodigoMsg( "No es posible realizar la operaci�n sobre este boleto, debido a que expir� el periodo permitido de tiempo expir�."); break;
            //case 24: setCodigoMsg( "");   break;
            case 25: setCodigoMsg( "Los n�meros de asiento no corresponden con los folios de boleto proporcionados. Por favor intente nuevamente.");   break;
            case 26: setCodigoMsg( "No puede realizarse la transferencia de boleto, la corrida ya ha sido recaudada.");   break;
            case 27: setCodigoMsg( "No puede realizarse la transferencia de boleto, la corrida ha sido cancelada.");   break;
            //case 28: setCodigoMsg( "El asiento "+nota+" no puede ser bloqueado");   break;
            case 29: setCodigoMsg( "No fue posible encotrar informaci�n acerca de "+nota+". Por favor verifique su informaci�n."); break;
            //case 30: setCodigoMsg( "El origen que seleccion� no tiene un esquema asociado. Contacte al Administrador delSistema");   break;
            //case 31: setCodigoMsg( "El n�mero de asientos de la corrida es diferente ");   break;
            case 32: setCodigoMsg( "No es posible realizar la cancelaci�n de este boleto, debido a que es un boleto abierto.");   break;
            case 33: setCodigoMsg( "No es posible realizar la cancelaci�n del boleto, debido a un canje o transferencia previo."); break;
            case 34: setCodigoMsg( "El boleto "+nota+" no fue encontrado. Por favor intente nuevamente.");   break;
            case 35: setCodigoMsg( "No es posible realizar la operaci�n del boleto, debido a un canje, transferencia o y ha sido cancelado.");   break;
            
            //case 36: setCodigoMsg( "Existe informaci�n no hallada asociada a la b�squeda de "+nota;          
            case 37: setCodigoMsg( "Por favor solicite la clave del supervisor para continuar.");   break;
            case 38: setCodigoMsg( "Ha ocurrido un problema durante la venta y no ha podido completarse. Por favor intente nuevamente.");   break; 
            case 42: setCodigoMsg( "No es posible asignarse. El usuario "+nota+" tiene un corte pendiente en otra caja.");   break;
            case 43: setCodigoMsg( "No es posible asignarse. Existe un un corte pendiente de otro usuario en esta caja.");   break;
            case 44: 
                if(nota.toUpperCase().equals("L"))
                    setCodigoMsg( "El asiento no puede ser liberado. Por favor intente nuevamente.");   
                setCodigoMsg( "El asiento no puede ser ocupado. Por favor intente nuevamente.");   
                break;
            case 45: setCodigoMsg("La consulta no gener� resultados. Intente nuevamente ingresando diferentes datos.");   break;
            case 46: setCodigoMsg("No se encontraron perfiles relacionados al usuario"+nota+". Contacte al Administrador del Sistema.");   break;
            case 47: setCodigoMsg("La caja "+nota+" no tiene configurada una empresa principal");   break;
            case 48: setCodigoMsg("La caja "+nota+" no tiene configurada empresas para venta");   break;
            case 49: setCodigoMsg("La tarjeta de la corrida seleccionada ya ha sido despachada. Por favor, seleccione otra corrida para iniciar su venta");   break;
        }
    }
    
    /**
     * Establece el estado de las variables de error, dicho estado es generado
     * directamente por el store procedure solicitado.
     * @param pCodigo N�mero entero que representa el c�digo de error.
     * @param pMsg Un String que contiene un mensaje extra.
     * @param pObjeto Un String que contiene el nombre del objeto que realiz� la solicitud.
     */
    public void setEstado(int pCodigo, String pMsg, String pObjeto) {
        setCodigo(pCodigo);   
        setCodigoMsg(pMsg);   
        setParent(pObjeto);   
        
    }

    /**
     * Devuelve el mensaje de error.
     * @setCodigoMsg( Un String con el mensaje de error.
     */
    public String getParentMsg() {
        return codigoMsg;
    }

    private void setCodigoMsg(String errorMsg) {
        this.codigoMsg = errorMsg;
    }

    /**
     * Devuelve el nombre del objeto que realiz� la solicitud.
     * @setCodigoMsg( Un String que contiene el nombre del objeto.
     */
    public String getParent() {
        return parent;
    }

    private void setParent(String objeto) {
        this.parent = objeto;
    }
}
