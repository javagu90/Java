/*
 * EmpresasResponse.java
 *
 * Created on 7 de octubre de 2009, 11:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.empresas;

import vendERMobile.clases.datos.DatoGenerico;

/**
 *
 * @author asolis
 */
public class EmpresasResponse {
    DatoGenerico[] empresa;
    String uid;    
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    
    /** Creates a new instance of EmpresasResponse */
    public EmpresasResponse() {
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
    
    public void setEmpresas(DatoGenerico[] empresa){
        this.empresa = empresa;
    }
    public DatoGenerico[] getEmpresas(){
        return this.empresa;
    }
}
