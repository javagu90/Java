/*
 * AsientosReq.java
 *
 * Created on 27 de noviembre de 2008, 09:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

/**
 *
 * @author ocruz
 */
public class AsientosReq {
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
    private String ubicacion;
    private String fechaDefasamiento;
    private int cupoAutobus;
    
    /** Creates a new instance of AsientosReq */
    public AsientosReq() {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaDefasamiento() {
        return fechaDefasamiento;
    }

    public void setFechaDefasamiento(String fechaDefasamiento) {
        this.fechaDefasamiento = fechaDefasamiento;
    }

    public int getCupoAutobus() {
        return cupoAutobus;
    }

    public void setCupoAutobus(int cupoAutobus) {
        this.cupoAutobus = cupoAutobus;
    }
}
