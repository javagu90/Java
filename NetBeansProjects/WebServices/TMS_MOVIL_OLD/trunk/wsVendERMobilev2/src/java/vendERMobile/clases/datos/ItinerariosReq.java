/*
 * ItinerariosReq.java
 *
 * Created on 27 de noviembre de 2008, 09:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.datos;

import vendERMobile.clases.datos.DatoGenerico;

/**
 *
 * @author asolis
 */
public class ItinerariosReq {
    
    private DatoGenerico servicio;
    private DatoGenerico empresa;
    private String rutaId;
    private DatoGenerico origen;
    private DatoGenerico destino;
    private String horaSalida;
    private String tipoCorrida;

    /** Creates a new instance of ItinerariosReq */
    public ItinerariosReq() {
    }
    
    public DatoGenerico getServicio() {
        return servicio;
    }

    public void setServicio(DatoGenerico servicio) {
        this.servicio = servicio;
    }

    public DatoGenerico getEmpresa() {
        return empresa;
    }

    public void setEmpresa(DatoGenerico empresa) {
        this.empresa = empresa;
    }

    public String getRutaId() {
        return rutaId;
    }

    public void setRutaId(String rutaId) {
        this.rutaId = rutaId;
    }

    public DatoGenerico getOrigen() {
        return origen;
    }

    public void setOrigen(DatoGenerico origen) {
        this.origen = origen;
    }

    public DatoGenerico getDestino() {
        return destino;
    }

    public void setDestino(DatoGenerico destino) {
        this.destino = destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }
}
