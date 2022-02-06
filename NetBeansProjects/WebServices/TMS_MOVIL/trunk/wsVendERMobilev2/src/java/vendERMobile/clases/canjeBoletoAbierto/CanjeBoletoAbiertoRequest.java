/*
 * CanjeBoletoAbiertoRequest.java
 *
 * Created on 6 de octubre de 2009, 11:17 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.canjeBoletoAbierto;

import vendERMobile.clases.datos.AsientoAutobus;
import vendERMobile.clases.datos.Corridas;
import vendERMobile.clases.datos.Sesion;

/**
 *
 * @author asolis
 */
public class CanjeBoletoAbiertoRequest {
    String uid;
    Sesion sesion;
    Corridas corridaDestino;
    AsientoAutobus[] asientosSeleccionadosDestino;    
    AsientoAutobus[] asientosSeleccionadosOrigen;
    
    /** Creates a new instance of CanjeBoletoAbiertoRequest */
    public CanjeBoletoAbiertoRequest() {
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

    public Corridas getCorridasDestino() {
        return corridaDestino;
    }

    public void setCorridasDestino(Corridas corridaDestino) {
        this.corridaDestino = corridaDestino;
    }
    
    public AsientoAutobus[] getAsientosSeleccionadosOrigen() {
        return asientosSeleccionadosOrigen;
    }

    public void setAsientosSeleccionadosOrigen(AsientoAutobus[] asientosSeleccionadosOrigen) {
        this.asientosSeleccionadosOrigen = asientosSeleccionadosOrigen;
    }   
    
    public AsientoAutobus[] getAsientosSeleccionadosDestino() {
        return asientosSeleccionadosDestino;
    }

    public void setAsientosSeleccionadosDestino(AsientoAutobus[] asientosSeleccionadosDestino) {
        this.asientosSeleccionadosDestino = asientosSeleccionadosDestino;
    }   
    
}
