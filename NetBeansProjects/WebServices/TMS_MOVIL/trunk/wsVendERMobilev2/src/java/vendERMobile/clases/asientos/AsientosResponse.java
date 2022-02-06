/*
 * AsientosResponse.java
 *
 * Created on 17 de septiembre de 2009, 01:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.asientos;

import vendERMobile.clases.datos.Corridas;
import vendERMobile.clases.datos.Sesion;

/**
 *
 * @author asolis
 */
public class AsientosResponse {
    private String uid;
    private String mapaAsientos;
    private String mapaAsientosPasajero;
    private boolean success;
    private int errorCode;
    private String errorMsg;
    /** Creates a new instance of AsientosResponse */
    public AsientosResponse() {
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMapaAsientos() {
        return mapaAsientos;
    }

    public void setMapaAsientos(String mapaAsientos) {
        this.mapaAsientos = mapaAsientos;
    }

    public String getMapaAsientosPasajero() {
        return mapaAsientosPasajero;
    }

    public void setMapaAsientosPasajero(String mapaAsientosPasajero) {
        this.mapaAsientosPasajero = mapaAsientosPasajero;
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
