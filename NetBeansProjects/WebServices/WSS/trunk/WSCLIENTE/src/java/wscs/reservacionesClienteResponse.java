/*
 * reservacionesClienteResponse.java
 *
 * Created on 4 de octubre de 2008, 04:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wscs;

import wsc.entidades.reservacion;

/**
 *
 * @author vgonzalez
 */
public class reservacionesClienteResponse {
    private String uid = "";
    private boolean success = false;
    private int errorCode = 0;
    private String errorMsg = "";
    private reservacion[] reservaciones;
    
    /** Creates a new instance of reservacionesClienteResponse */
    public reservacionesClienteResponse() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public reservacion[] getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(reservacion[] reservaciones) {
        this.reservaciones = reservaciones;
    }
    
}
