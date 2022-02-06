/*
 * EstadoTransaccion.java
 *
 * Created on 27 de noviembre de 2008, 09:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.transacciones;

/**
 *
 * @author ocruz
 */
public class EstadoTransaccion {
    private int codigo;
    private String objeto;
    private String codigoMsg;    
    /**
     * Creates a new instance of EstadoTransaccion
     */
    public EstadoTransaccion() {
    }

    /**
     * Devuelve un nomero entero que representa el codigo de error.
     * @return Codigo de error.
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
     * @param pCodigo Nomero entero que representa el codigo de error.
     * @param pObjeto Un String que contiene el nombre del objeto que realizo la solicitud.
     * @param nota Un String que contiene un mensaje extra.
     */
    public void setEstadoCodigo(int pCodigo, String pObjeto, String nota) {
        setCodigo(pCodigo);
        setObjeto(pObjeto);
        switch(getCodigo()){
            /*case -1: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": No fue posible ejecutar procedimiento."); break;
            case -2: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": Procedimiento fallo al retornar el resultado."); break;
            case -3: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": Valor(es) requerido(s) para el(los) campo(s): "+nota+"."); break;
            case -4: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": Valor de campo fuera de rango: "+nota+"."); break;
            case -5: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": No fue posible ejecutar procedimiento. No existe dblink para origen: "+nota+"."); break;
            case -6: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": No fue posible ejecutar funcion de libreria: "+nota+"."); break;
            case -7: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": Valor no mayor a cero para campos referentes a disponibilidad de asientos."); break;
            case -8: setCodigoMsg("ER->"+getCodigo()+","+getObjeto()+": Datos de regreso no generaron resultados."); break;
            case 29: setCodigoMsg("No es posible reservar mos de 5 asientos. Por favor intente de nuevo (29)"); break;
            case 30: setCodigoMsg("No es posible comprar mos de 40 asientos.Por favor intente de nuevo (30)"); break;
            case 31: setCodigoMsg("El Nombre del Pasajero es demasiado largo. Por favor intente de nuevo (31)"); break;*/
            case -1: setCodigoMsg(getCodigo()+","+getObjeto()+": No fue posible ejecutar procedimiento."); break;
            case -2: setCodigoMsg(getCodigo()+","+getObjeto()+": Procedimiento fallo al retornar el resultado."); break;
            case -3: setCodigoMsg(getCodigo()+","+getObjeto()+": Valor(es) requerido(s) para el(los) campo(s): "+nota+"."); break;
            case -4: setCodigoMsg(getCodigo()+","+getObjeto()+": Valor de campo fuera de rango: "+nota+"."); break;
            case -5: setCodigoMsg(getCodigo()+","+getObjeto()+": No fue posible ejecutar procedimiento. No existe dblink para origen: "+nota+"."); break;
            case -6: setCodigoMsg(getCodigo()+","+getObjeto()+": No fue posible ejecutar funcion de libreria: "+nota+"."); break;
            case -7: setCodigoMsg(getCodigo()+","+getObjeto()+": Valor no mayor a cero para campos referentes a disponibilidad de asientos."); break;
            case -8: setCodigoMsg(getCodigo()+","+getObjeto()+": Datos de regreso no generaron resultados."); break;
            case 29: setCodigoMsg("No es posible reservar mos de 5 asientos. Por favor intente de nuevo (29)"); break;
            case 30: setCodigoMsg("No es posible comprar mos de 40 asientos.Por favor intente de nuevo (30)"); break;
            case 31: setCodigoMsg("El Nombre del Pasajero es demasiado largo. Por favor intente de nuevo (31)"); break;
            
        }
    }
    
    /**
     * Establece el estado de las variables de error, dicho estado es generado
     * directamente por el store procedure solicitado.
     * @param pCodigo Nomero entero que representa el codigo de error.
     * @param pMsg Un String que contiene un mensaje extra.
     * @param pObjeto Un String que contiene el nombre del objeto que realizo la solicitud.
     */
    public void setEstado(int pCodigo, String pMsg, String pObjeto) {
        setCodigo(pCodigo);
        setCodigoMsg(pMsg);
        setObjeto(pObjeto);
    }

    /**
     * Devuelve el mensaje de error.
     * @return Un String con el mensaje de error.
     */
    public String getCodigoMsg() {
        return codigoMsg;
    }

    private void setCodigoMsg(String errorMsg) {
        this.codigoMsg = errorMsg;
    }

    /**
     * Devuelve el nombre del objeto que realizo la solicitud.
     * @return Un String que contiene el nombre del objeto.
     */
    public String getObjeto() {
        return objeto;
    }

    private void setObjeto(String objeto) {
        this.objeto = objeto;
    }
}
