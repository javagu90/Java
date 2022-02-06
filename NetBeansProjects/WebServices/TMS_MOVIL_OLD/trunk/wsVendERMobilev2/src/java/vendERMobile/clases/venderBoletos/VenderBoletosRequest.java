/*
 * VenderBoletosRequest.java
 *
 * Created on 18 de septiembre de 2009, 12:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.venderBoletos;

import java.util.Date;
import vendERMobile.clases.datos.AsientoAutobus;
import vendERMobile.clases.datos.Corridas;
import vendERMobile.clases.datos.DatoGenerico;
import vendERMobile.clases.datos.Sesion;
import vendERMobile.clases.datos.TiposPago;

/**
 *
 * @author asolis
 */
public class VenderBoletosRequest {
    String uid;
    Sesion sesion;
    DatoGenerico empresaNombre;
    DatoGenerico tipoServicio;
    DatoGenerico origen;
    DatoGenerico destino;
    Date fechaCorrida;
    String tipoBoleto;
    Corridas corrida;
    AsientoAutobus asientosSeleccionados[];
    TiposPago tipoPago;
    String referenciaPago;
    String fdc;
    /**
     * Creates a new instance of VenderBoletosRequest
     */
    public VenderBoletosRequest() {
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

    public DatoGenerico getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(DatoGenerico tipoServicio) {
        this.tipoServicio = tipoServicio;
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
    
    public DatoGenerico getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(DatoGenerico empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public Date getFechaCorrida() {
        return fechaCorrida;
    }

    public void setFechaCorrida(Date fechaCorrida) {
        this.fechaCorrida = fechaCorrida;
    }

    public String getTipoBoleto() {
        return tipoBoleto;
    }

    public void setTipoBoleto(String tipoBoleto) {
        this.tipoBoleto = tipoBoleto;
    }
    
    public AsientoAutobus[] getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(AsientoAutobus[] asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }
    
    public void setCorridas(Corridas corridas){
        this.corrida = corridas;
    }
    public Corridas getCorridas(){
        return this.corrida;
    }
    
    public void setTiposPago(TiposPago tiposPago){
        this.tipoPago = tiposPago;
    }
    public TiposPago getTiposPago(){
        return this.tipoPago;
    }
    
    public void setReferenciaPago(String referenciaPago){
        this.referenciaPago = referenciaPago;
    }
    public String getReferenciaPago(){
        return this.referenciaPago;
    }
    public String getFDC() {
        System.out.println("fdc "+fdc);
        return this.fdc;
    }

    public void setFDC(String fdc) {
        System.out.println("fdc "+fdc);
        this.fdc = fdc;
        System.out.println("this.fdc "+this.fdc);
    }
}
