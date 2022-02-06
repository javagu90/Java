/*
 * cancelacionRequest.java
 *
 * Created on 26 de noviembre de 2008, 07:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases;

import tmspcweb.clases.datos.CancelacionReq;
import tmspcweb.clases.datos.Sesion;

/**
 *
 * @author ocruz
 */
public class cancelacionRequest {
    
    private String uid;
    private Sesion sesion;
    private String motivoCancelacion;
    private String folioReservacionNegocio;
    private CancelacionReq datosIda;
    private CancelacionReq datosRegreso;
    
    /** Creates a new instance of cancelacionRequest */
    public cancelacionRequest() {
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

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getFolioReservacionNegocio() {
        return folioReservacionNegocio;
    }

    public void setFolioReservacionNegocio(String folioReservacionNegocio) {
        this.folioReservacionNegocio = folioReservacionNegocio;
    }

    public CancelacionReq getDatosIda() {
        return datosIda;
    }

    public void setDatosIda(CancelacionReq datosIda) {
        this.datosIda = datosIda;
    }

    public CancelacionReq getDatosRegreso() {
        return datosRegreso;
    }

    public void setDatosRegreso(CancelacionReq datosRegreso) {
        this.datosRegreso = datosRegreso;
    }
    
}
