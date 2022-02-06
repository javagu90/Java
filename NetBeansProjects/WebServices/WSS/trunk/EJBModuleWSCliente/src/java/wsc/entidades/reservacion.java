/*
 * reservacion.java
 *
 * Created on 4 de octubre de 2008, 04:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsc.entidades;

import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class reservacion {
    private String fechaCorrida;
    private String origen;
    private String destino;
    private String servcio;
    private String empresa;
    private long noAsiento;
    private String tipoPasajero;
    private String responsableReservacion;
    private double importe;
    private String claveRservacion;
    private String tipoReservacion;
    private String estadoReservacion;
    private String fechaReservacion;
    
    /** Creates a new instance of reservacion */
    public reservacion() {
    }

    public reservacion(Vector vres) {
        this.setFechaCorrida(vres.get(0).toString());
        this.setOrigen(vres.get(1).toString());
        this.setDestino(vres.get(2).toString());
        this.setServcio(vres.get(3).toString());
        this.setEmpresa(vres.get(4).toString());
        this.setNoAsiento(Long.valueOf(vres.get(5).toString()));
        this.setTipoPasajero(vres.get(6).toString());
        this.setImporte(Double.valueOf(vres.get(7).toString()));
        this.setResponsableReservacion(vres.get(8).toString());
        this.setClaveRservacion(vres.get(9).toString());
        this.setTipoReservacion(vres.get(10).toString());
        this.setEstadoReservacion(vres.get(11).toString());
        this.setFechaReservacion(vres.get(12).toString());
    }

    public String getFechaCorrida() {
        return fechaCorrida;
    }

    public void setFechaCorrida(String fechaCorrida) {
        this.fechaCorrida = fechaCorrida;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getServcio() {
        return servcio;
    }

    public void setServcio(String servcio) {
        this.servcio = servcio;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public long getNoAsiento() {
        return noAsiento;
    }

    public void setNoAsiento(long noAsiento) {
        this.noAsiento = noAsiento;
    }

    public String getTipoPasajero() {
        return tipoPasajero;
    }

    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    public String getResponsableReservacion() {
        return responsableReservacion;
    }

    public void setResponsableReservacion(String responsableReservacion) {
        this.responsableReservacion = responsableReservacion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getClaveRservacion() {
        return claveRservacion;
    }

    public void setClaveRservacion(String claveRservacion) {
        this.claveRservacion = claveRservacion;
    }

    public String getTipoReservacion() {
        return tipoReservacion;
    }

    public void setTipoReservacion(String tipoReservacion) {
        this.tipoReservacion = tipoReservacion;
    }

    public String getEstadoReservacion() {
        return estadoReservacion;
    }

    public void setEstadoReservacion(String estadoReservacion) {
        this.estadoReservacion = estadoReservacion;
    }

    public String getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }
    
}
