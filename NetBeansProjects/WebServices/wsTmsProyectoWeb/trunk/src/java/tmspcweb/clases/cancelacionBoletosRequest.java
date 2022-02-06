/*
 * cancelacionBoletosRequest.java
 *
 * Created on 26 de noviembre de 2008, 07:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases;

import tmspcweb.clases.datos.CancelaBoletosReq;
import tmspcweb.clases.datos.Sesion;
/**
 *
 * @author ocruz
 */
public class cancelacionBoletosRequest {
    
    /** Creates a new instance of cancelacionBoletosRequest */
    private String uid;
    private Sesion sesion;
    //private String FolioCancelacionBoleto;
    private String viajeRedondo;
    private CancelaBoletosReq datosIda;
    private CancelaBoletosReq datosRegreso;
    
    /** Creates a new instance of cancelacionBoletosRequest */
    public cancelacionBoletosRequest() {
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

    public CancelaBoletosReq getDatosIda() {
        return datosIda;
    }

    public void setDatosIda(CancelaBoletosReq datosIda) {
        this.datosIda = datosIda;
    }

    public CancelaBoletosReq getDatosRegreso() {
        return datosRegreso;
    }

    public void setDatosRegreso(CancelaBoletosReq datosRegreso) {
        this.datosRegreso = datosRegreso;
    }
    
    /*public String getFolioCancelacionNegocio() {
        return this.FolioCancelacionBoleto;
    }

    public void setFolioCancelacionNegocio(String FolioCancelacionNegocio) {
        this.FolioCancelacionBoleto = FolioCancelacionNegocio;
    }*/
    
    public String getViajeRedondo() {
        return this.viajeRedondo.toUpperCase();
    }

    public void setViajeRedondo(String viajeRedondo) {
        this.viajeRedondo = viajeRedondo.toUpperCase();
    }
}
