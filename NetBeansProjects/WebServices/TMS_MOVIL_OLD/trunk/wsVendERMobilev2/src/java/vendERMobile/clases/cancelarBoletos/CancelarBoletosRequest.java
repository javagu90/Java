/*
 * CancelarBoletosRequest.java
 *
 * Created on 21 de septiembre de 2009, 01:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.cancelarBoletos;

import vendERMobile.clases.datos.AsientoAutobus;
import vendERMobile.clases.datos.Sesion;

/**
 *
 * @author asolis
 */
public class CancelarBoletosRequest {
    String uid;
    Sesion sesion;
    String motivoCancelacion;
    AsientoAutobus asientosSeleccionados[];
    
    /** Creates a new instance of CancelarBoletosRequest */
    public CancelarBoletosRequest() {
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
    
    public AsientoAutobus[] getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(AsientoAutobus[] asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }
}
