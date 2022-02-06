/*
 * ItinerariosPorRutaResponse.java
 *
 * Created on 4 de noviembre de 2009, 07:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.itinerariosruta;

import vendERMobile.clases.datos.Itinerario;

/**
 *
 * @author asolis
 */
public class ItinerariosPorRutaResponse {
    private String uid;
    private Itinerario[] itinerario;    
    private boolean success;
    private int errorCode;
    private String  errorMsg;
    
    /** Creates a new instance of ItinerariosPorRutaResponse */
    public ItinerariosPorRutaResponse() {
    }
 
 
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Itinerario[] getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario[] itinerario) {
        this.itinerario = itinerario;
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
