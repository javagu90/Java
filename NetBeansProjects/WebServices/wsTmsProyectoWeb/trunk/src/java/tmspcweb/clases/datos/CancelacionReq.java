/*
 * CancelacionReq.java
 *
 * Created on 27 de noviembre de 2008, 09:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

/**
 *
 * @author ocruz
 */
public class CancelacionReq {    
    private DatoGenerico origen;
    private DatoGenerico negocio;
    private String numCorrida;
    private String fecha;
    private AsientoAutobus[] asientosSeleccionados;
    
    /** Creates a new instance of CancelacionReq */
    public CancelacionReq() {
    }

    public DatoGenerico getOrigen() {
        return origen;
    }

    public void setOrigen(DatoGenerico origen) {
        this.origen = origen;
    }

    public DatoGenerico getNegocio() {
        return negocio;
    }

    public void setNegocio(DatoGenerico negocio) {
        this.negocio = negocio;
    }

    public String getNumCorrida() {
        return numCorrida;
    }

    public void setNumCorrida(String numCorrida) {
        this.numCorrida = numCorrida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public AsientoAutobus[] getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(AsientoAutobus[] asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }    
}
