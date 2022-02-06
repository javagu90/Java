/*
 * CorridasResponse.java
 *
 * Created on 14 de septiembre de 2009, 06:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.corridas;

import vendERMobile.clases.datos.Corridas;

/**
 *
 * @author asolis
 */
public class CorridasResponse {
    String uid;
    Corridas[] corridas;
    boolean success;
    int errorCode;
    String errorMsg;
    
    
    /**
     * Creates a new instance of CorridasResponse
     */
    public CorridasResponse() {
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Corridas[] getCorridas() {
        return corridas;
    }

    public void setCorridas(Corridas[] corridas) {
        this.corridas = corridas;
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
}
