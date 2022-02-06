/*
 * LogOutRequest.java
 *
 * Created on 26 de noviembre de 2008, 07:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.logOut;

import vendERMobile.clases.datos.Sesion;


/**
 *
 * @author ocruz
 */
public class LogOutRequest {
    
    private String uid;
    private Sesion sesion;
    /** Creates a new instance of loginRequest */
    public LogOutRequest() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }    
}
