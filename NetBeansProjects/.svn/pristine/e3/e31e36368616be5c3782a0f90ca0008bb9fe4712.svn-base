/*
 * TarifasResponse.java
 *
 * Created on 10 de septiembre de 2009, 02:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.tarifas;

import vendERMobile.clases.datos.Tarifas;

/**
 *
 * @author asolis
 */
public class TarifasResponse {
    String uid;
    Tarifas[] tarifas;
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    /** Creates a new instance of TarifasResponse */
    public TarifasResponse() {
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

    public void setTarifas(Tarifas[] tarifas){
        this.tarifas = tarifas;
    }
    public Tarifas[] getTarifas(){
        return this.tarifas;
    }
}
