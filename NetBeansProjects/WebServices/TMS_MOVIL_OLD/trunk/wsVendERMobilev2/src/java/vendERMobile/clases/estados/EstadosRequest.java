/*
 * EstadosRequest.java
 *
 * Created on 20 de octubre de 2009, 05:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.estados;

/**
 *
 * @author asolis
 */
public class EstadosRequest {
    String uid;
    String cajaNombre;
    /** Creates a new instance of EstadosRequest */
    public EstadosRequest() {
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
