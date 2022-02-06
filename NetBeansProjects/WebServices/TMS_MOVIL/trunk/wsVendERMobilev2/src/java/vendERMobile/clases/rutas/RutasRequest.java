/*
 * RutasRequest.java
 *
 * Created on 20 de octubre de 2009, 05:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.rutas;

/**
 *
 * @author asolis
 */
public class RutasRequest {
    String uid;
    String cajaNombre;
    /** Creates a new instance of RutasRequest */
    public RutasRequest() {
    }
    
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    
    public String getcajaNombre() {
        return cajaNombre;
    }
    public void setcajaNombre(String cajaNombre) {
        this.cajaNombre = cajaNombre;
    }
}
