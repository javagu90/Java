/*
 * ServiciosRequest.java
 *
 * Created on 7 de octubre de 2009, 11:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.servicios;

/**
 *
 * @author asolis
 */
public class ServiciosRequest {
    String uid;
    String cajaNombre;
    /**
     * Creates a new instance of ServiciosRequest
     */
    public ServiciosRequest() {
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
