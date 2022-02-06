/*
 * TiposPasajero.java
 *
 * Created on 10 de septiembre de 2009, 02:25 PM
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
public class TiposPasajero {
    int idTipoPasajero;
    String tipoPasajeroNombre;
    String tipoPasajeroClave;
    int pct_descuento;
    /** Creates a new instance of TiposPasajero */
    public TiposPasajero() {
    }
    
    public TiposPasajero(String idTipoPasajero){
        setIdTipoPasajero(Integer.parseInt(idTipoPasajero));
        /*setTipoPasajeroNombre(tipoPasajeroNombre);
        setTipoPasajeroClave(tipoPasajeroClave);
        setPct_descuento(Integer.parseInt(pct_descuento));*/
    }
    
    public TiposPasajero(Vector vec){
        setIdTipoPasajero(Integer.parseInt(vec.get(0).toString()));
        setTipoPasajeroNombre(vec.get(1).toString());
        setTipoPasajeroClave(vec.get(2).toString());
        setPct_descuento(Integer.parseInt(vec.get(3).toString()));
    }
    
    public void setIdTipoPasajero(int idTipoPasajero){
        this.idTipoPasajero = idTipoPasajero;
    }
    public int getIdTipoPasajero(){
        return this.idTipoPasajero ;
    }
    
    public void setTipoPasajeroNombre(String tipoPasajeroNombre){
        this.tipoPasajeroNombre = tipoPasajeroNombre;
    }
    public String getTipoPasajeroNombre(){
        return this.tipoPasajeroNombre ;
    }
    
     public void setTipoPasajeroClave(String tipoPasajeroClave){
        this.tipoPasajeroClave = tipoPasajeroClave;
    }
    public String getTipoPasajeroClave(){
        return this.tipoPasajeroClave ;
    }
    
    public void setPct_descuento(int pct_descuento){
        this.pct_descuento = pct_descuento;
    }
    public int getPct_descuento(){
        return this.pct_descuento ;
    }
}
