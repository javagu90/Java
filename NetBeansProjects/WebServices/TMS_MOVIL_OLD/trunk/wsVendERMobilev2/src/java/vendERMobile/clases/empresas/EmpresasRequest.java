/*
 * EmpresasRequest.java
 *
 * Created on 7 de octubre de 2009, 11:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.empresas;

/**
 *
 * @author asolis
 */
public class EmpresasRequest {
    String uid;
    String cajaNombre;
    /** Creates a new instance of EmpresasRequest */
    public EmpresasRequest() {
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
