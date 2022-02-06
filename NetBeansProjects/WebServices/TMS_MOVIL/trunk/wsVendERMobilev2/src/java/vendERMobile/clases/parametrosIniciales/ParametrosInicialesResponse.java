/*
 * ParametrosInicialesResponse.java
 *
 * Created on 9 de septiembre de 2009, 01:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.parametrosIniciales;

import vendERMobile.clases.datos.ParametrosIniciales;

/**
 *
 * @author asolis
 */
public class ParametrosInicialesResponse {
   String uid;
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    private ParametrosIniciales[] parametrosIniciales;
    /** Creates a new instance of ParametrosInicialesRequest */
    public ParametrosInicialesResponse() {
    }
    
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
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
    
    public void setParametrosIniciales(ParametrosIniciales[] parametrosIniciales){
        this.parametrosIniciales = parametrosIniciales;
    }
    public ParametrosIniciales[] getParametrosIniciales(){
        return this.parametrosIniciales;
    }
}
