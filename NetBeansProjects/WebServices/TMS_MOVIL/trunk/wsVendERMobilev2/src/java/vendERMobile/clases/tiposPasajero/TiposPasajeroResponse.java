/*
 * TiposPasajeroResponse.java
 *
 * Created on 10 de septiembre de 2009, 06:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.tiposPasajero;

import vendERMobile.clases.datos.TiposPasajero;

/**
 *
 * @author asolis
 */
public class TiposPasajeroResponse {
    String uid;    
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    TiposPasajero[] tiposPasajero;
    /** Creates a new instance of TiposPasajeroResponse */
    public TiposPasajeroResponse() {
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
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid; 
    } 
    
    public void setTiposPasajero(TiposPasajero[] tiposPasajero){
        this.tiposPasajero = tiposPasajero;
    }
    public TiposPasajero[] getTiposPasajero(){
        return this.tiposPasajero;
    }
}
