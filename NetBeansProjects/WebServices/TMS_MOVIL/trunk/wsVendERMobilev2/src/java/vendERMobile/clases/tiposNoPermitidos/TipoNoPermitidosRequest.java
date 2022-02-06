/*
 * TipoNoPermitidosRequest.java
 *
 * Created on 12 de octubre de 2009, 04:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.tiposNoPermitidos;

import vendERMobile.clases.datos.DatoGenerico;

/**
 *
 * @author asolis
 */
public class TipoNoPermitidosRequest {
    String uid;
    DatoGenerico origen;
    String corridaId;
    /** Creates a new instance of TipoNoPermitidosRequest */
    public TipoNoPermitidosRequest() {
    }
    
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    
    public void setOrigen(DatoGenerico origen){
        this.origen = origen;
    }
    public DatoGenerico getOrigen(){
        return this.origen;
    }
    
    public void setCorridaId(String corridaId){
        this.corridaId = corridaId;
    }
    public String getCorridaId(){
        return this.corridaId;
    }
}
