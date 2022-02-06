/*
 * TiposPago.java
 *
 * Created on 11 de septiembre de 2009, 08:23 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.datos;

import java.util.Vector;

/**
 *
 * @author asolis
 */
public class TiposPago {
    int idTipoPago;
    String tipoPagoNombre;
    String tipoPagoClave;
    /** Creates a new instance of TiposPago */
    public TiposPago() {
    }
    
    public TiposPago(Vector tipos) {
        setIdTipoPago(Integer.parseInt(tipos.get(0).toString()));
        setTipoPagoNombre(tipos.get(1).toString());
        setTipoPagoClave(tipos.get(2).toString());
    }
    
    public void setIdTipoPago(int idTipoPago){
        this.idTipoPago = idTipoPago;
    }
    public int getIdTipoPago(){
        return idTipoPago;
    }
    
    public void setTipoPagoNombre(String tipoPagoNombre){
        this.tipoPagoNombre = tipoPagoNombre;
    }
    public String getTipoPagoNombre(){
        return tipoPagoNombre;
    }
    
    public void setTipoPagoClave(String tipoPagoClave){
        this.tipoPagoClave = tipoPagoClave;
    }
    public String getTipoPagoClave(){
        return tipoPagoClave;
    }
}
