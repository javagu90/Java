/*
 * corridasResponse.java
 *
 * Created on 26 de noviembre de 2008, 07:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases;

import tmspcweb.clases.datos.CorridaAutobus;
import tmspcweb.clases.datos.DatoGenerico;

/**
 *
 * @author ocruz
 */
public class corridasResponse {
    
    private String uid;
    private CorridaAutobus[] corridas;
    private boolean success;
    private int errorCode;
    private String errorMsg;
    /** Creates a new instance of corridasResponse */
    public corridasResponse() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public CorridaAutobus[] getCorridas() {
        return corridas;
    }

    public void setCorridas(CorridaAutobus[] corridas) {
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
        //CorridaAutobus ca = new CorridaAutobus();
        //ca.getPlantilla();
        this.errorMsg = errorMsg;
    }    
}
