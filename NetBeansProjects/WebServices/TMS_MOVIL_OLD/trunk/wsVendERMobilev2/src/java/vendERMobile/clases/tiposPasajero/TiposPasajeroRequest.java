/*
 * TiposPasajeroRequest.java
 *
 * Created on 10 de septiembre de 2009, 06:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.tiposPasajero;

/**
 *
 * @author asolis
 */
public class TiposPasajeroRequest {
    String uid;
    String cajaNombre;
    /** Creates a new instance of TiposPasajeroRequest */
    public TiposPasajeroRequest() {
    }
    
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    
    public void setCajaNombre(String cajaNombre){
        this.cajaNombre = cajaNombre;
    }
    public String getCajaNombre(){
        return this.cajaNombre;
    }
}
