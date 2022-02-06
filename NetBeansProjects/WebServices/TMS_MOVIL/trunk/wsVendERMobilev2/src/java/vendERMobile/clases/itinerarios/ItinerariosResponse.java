/*
 * ItinerariosResponse.java
 *
 * Created on 15 de septiembre de 2009, 05:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.itinerarios;

import vendERMobile.clases.datos.Itinerario;

/**
 *
 * @author asolis
 */
public class ItinerariosResponse {
    
   private String uid;
    private Itinerario[] itinerarioIda;
    private Itinerario[] itinerarioRegreso;
    private boolean success;
    private int errorCode;
    private String  errorMsg;
    
    /** Creates a new instance of itinerariosResponse */
    public ItinerariosResponse() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Itinerario[] getItinerarioIda() {
        return itinerarioIda;
    }

    public void setItinerarioIda(Itinerario[] itinerarioIda) {
        this.itinerarioIda = itinerarioIda;
    }

    public Itinerario[] getItinerarioRegreso() {
        return itinerarioRegreso;
    }

    public void setItinerarioRegreso(Itinerario[] itinerarioRegreso) {
        this.itinerarioRegreso = itinerarioRegreso;
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
