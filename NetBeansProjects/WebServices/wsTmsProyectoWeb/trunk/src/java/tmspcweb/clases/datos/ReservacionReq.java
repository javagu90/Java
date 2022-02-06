/*
 * ReservacionReq.java
 *
 * Created on 27 de noviembre de 2008, 09:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

import java.util.List;
import tmspcweb.clases.datos.AsientoAutobus;
import tmspcweb.clases.datos.DatoGenerico;

/**
 *
 * @author ocruz
 */
public class ReservacionReq {    
    private AsientoAutobus[] asientosSeleccionados;
    private DatoGenerico origen;
    private DatoGenerico destino;
    private DatoGenerico negocio;
    private DatoGenerico empresa;
    private String fechaSalida;
    private String horaSalida;
    private String numCorrida;
    private String tipoLectura;
    private String claveControl;
    private String secuencia;
    private String fechaDefasamiento;
    private String ubicacion;
    private String fechaInicial;
    private int cupoAutobus;
    private String servicioCorrida;
    private String tipoCorrida;
    private String asientosDisponibles;
    private double tarifaAdulto;
    private double tarifaNinio;
    private double tarifaEstudiante;
    private double tarifaMaestro;
    private double tarifaINSEN;
    private String folioReservacionEK;
    
    /** Creates a new instance of ReservacionReq */
    public ReservacionReq() {
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

    public DatoGenerico getNegocio() {
        return negocio;
    }

    public void setNegocio(DatoGenerico negocio) {
        this.negocio = negocio;
    }

    public DatoGenerico getEmpresa() {
        return empresa;
    }

    public void setEmpresa(DatoGenerico empresa) {
        this.empresa = empresa;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getNumCorrida() {
        return numCorrida;
    }

    public void setNumCorrida(String numCorrida) {
        this.numCorrida = numCorrida;
    }

    public String getTipoLectura() {
        return tipoLectura;
    }

    public void setTipoLectura(String tipoLectura) {
        this.tipoLectura = tipoLectura;
    }

    public String getClaveControl() {
        return claveControl;
    }

    public void setClaveControl(String claveControl) {
        this.claveControl = claveControl;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getFechaDefasamiento() {
        return fechaDefasamiento;
    }

    public void setFechaDefasamiento(String fechaDefasamiento) {
        this.fechaDefasamiento = fechaDefasamiento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public int getCupoAutobus() {
        return cupoAutobus;
    }

    public void setCupoAutobus(int cupoAutobus) {
        this.cupoAutobus = cupoAutobus;
    }

    public String getServicioCorrida() {
        return servicioCorrida;
    }

    public void setServicioCorrida(String servicioCorrida) {
        this.servicioCorrida = servicioCorrida;
    }

    public String getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    public String getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(String asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public double getTarifaAdulto() {
        return tarifaAdulto;
    }

    public void setTarifaAdulto(double tarifaAdulto) {
        this.tarifaAdulto = tarifaAdulto;
    }

    public double getTarifaNinio() {
        return tarifaNinio;
    }

    public void setTarifaNinio(double tarifaNinio) {
        this.tarifaNinio = tarifaNinio;
    }

    public double getTarifaEstudiante() {
        return tarifaEstudiante;
    }

    public void setTarifaEstudiante(double tarifaEstudiante) {
        this.tarifaEstudiante = tarifaEstudiante;
    }

    public double getTarifaMaestro() {
        return tarifaMaestro;
    }

    public void setTarifaMaestro(double tarifaMaestro) {
        this.tarifaMaestro = tarifaMaestro;
    }

    public double getTarifaINSEN() {
        return tarifaINSEN;
    }

    public void setTarifaINSEN(double tarifaINSEN) {
        this.tarifaINSEN = tarifaINSEN;
    }

    public String getFolioReservacionEK() {
        return folioReservacionEK;
    }

    public void setFolioReservacionEK(String folioReservacionEK) {
        this.folioReservacionEK = folioReservacionEK;
    }

    public AsientoAutobus[] getAsientosSeleccionados() {
        return this.asientosSeleccionados;
    }

    public void setAsientosSeleccionados(AsientoAutobus[] asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }    
}
