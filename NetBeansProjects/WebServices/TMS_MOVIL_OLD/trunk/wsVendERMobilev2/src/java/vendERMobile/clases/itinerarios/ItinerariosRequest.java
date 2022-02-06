/*
 * ItinerariosRequest.java
 *
 * Created on 15 de septiembre de 2009, 05:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.itinerarios;


import vendERMobile.clases.datos.ItinerariosReq;
import vendERMobile.clases.datos.Sesion;

/**
 *
 * @author asolis
 */
public class ItinerariosRequest {
    
    private String uid;
    private Sesion sesion;
    private ItinerariosReq datosIda;
    private ItinerariosReq datosRegreso;    
    
    /** Creates a new instance of itinerariosRequest */
    public ItinerariosRequest() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public ItinerariosReq getDatosIda() {
        return datosIda;
    }

    public void setDatosIda(ItinerariosReq datosIda) {
        this.datosIda = datosIda;
    }

    public ItinerariosReq getDatosRegreso() {
        return datosRegreso;
    }

    public void setDatosRegreso(ItinerariosReq datosRegreso) {
        this.datosRegreso = datosRegreso;
    }    
    
    
}
