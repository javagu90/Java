/*
 * CorridasRequest.java
 *
 * Created on 14 de septiembre de 2009, 06:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.corridas;

import vendERMobile.clases.datos.DatoGenerico;
import vendERMobile.clases.datos.Sesion;

/**
 *
 * @author asolis
 */
public class CorridasRequest {
    String uid;
    Sesion sesion;
    DatoGenerico empresaNombre;
    DatoGenerico tipoServicio;
    DatoGenerico origen;
    DatoGenerico destino;    
    String fechaSalida;
    String horarioSalida;        
    /**
     * Creates a new instance of CorridasRequest
     */
    public CorridasRequest() {
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

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }
    
    
}
