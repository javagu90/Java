/*
 * PermisosResponse.java
 *
 * Created on 8 de septiembre de 2009, 01:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.permisos;

import java.util.Vector;
import vendERMobile.clases.datos.Permisos;
import vendERMobile.clases.datos.Sesion;

/**
 *
 * @author asolis
 */
public class PermisosResponse {
    String uid;
    Permisos[] permisos;
    boolean success = true;
    int errorCode;
    String errorMsg;
    /**
     * Creates a new instance of PermisosResponse
     */
    public PermisosResponse() {
    }
    
    public Permisos[] getPermisos() {
        return permisos;
    }
    public void setPermisos(Permisos[] permisos) {
        this.permisos = permisos;        
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    } 
    
    public void setUid(String uid){
        this.uid = uid;
    }
    
    public String getUid(){
        return this.uid;
    }
}
