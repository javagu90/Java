/*
 * EstadosResponse.java
 *
 * Created on 20 de octubre de 2009, 05:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.estados;

import vendERMobile.clases.datos.DatoGenerico;

/**
 *
 * @author asolis
 */
public class EstadosResponse {
    DatoGenerico estados[];
    String uid;    
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    
    /** Creates a new instance of EstadosResponse */
    public EstadosResponse() {
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
    
    public void setEstados(DatoGenerico[] estados){
        this.estados = estados;
    }
    public DatoGenerico[] getEstados(){
        return this.estados;
    }
    
}
