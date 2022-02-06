/*
 * CancelaBoletosReq.java
 *
 * Created on 27 de noviembre de 2008, 09:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

import tmspcweb.clases.datos.AsientoAutobus;
import tmspcweb.clases.datos.DatoGenerico;
/**
 *
 * @author ocruz
 */
public class CancelaBoletosReq {
    
    private DatoGenerico origen;
    private String numCorrida;
    private AsientoAutobus[] asientosSeleccionados;
    
    /** Creates a new instance of CancelaBoletosReq */
    public CancelaBoletosReq() {
    }
    
    public DatoGenerico getOrigen() {
        return origen;
    }

    public void setOrigen(DatoGenerico origen) {
        this.origen = origen;
    }

    public String getNumCorrida() {
        return numCorrida;
    }

    public void setNumCorrida(String numCorrida) {
        this.numCorrida = numCorrida;
    }
    
    public AsientoAutobus[] getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(AsientoAutobus[] asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }    
}
