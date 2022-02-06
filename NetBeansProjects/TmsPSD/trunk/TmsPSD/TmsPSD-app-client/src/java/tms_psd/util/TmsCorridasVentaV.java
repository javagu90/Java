/*
 * TmsCorridasVentaV.java
 *
 * Created on 22 de octubre de 2007, 10:08 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_psd.util;

import java.sql.Timestamp;

/**
 *
 * @author ocruz
 */
public class TmsCorridasVentaV {
    
    /** Creates a new instance of TmsCorridasVentaV */
    public TmsCorridasVentaV() {
    }
    
    private Long autobusId;
    private Long autobusOriginalId;
    private String claveCorrida;
    private Long corridaId;
    private Long creadoPor;
    private Long destinoId;
    private Long empresaId;
    private Long estadoCorridaId;
    private Timestamp fechaCreacion;
    private Timestamp fechaHoraCorrida;
    private Double montoAnticipos;
    private String numeroContrato;
    private String numeroOrden;
    private Long operadorId;
    private Long operadorOriginalId;
    private Long origenId;
    private Long plantillaId;
    private Long rutaId;
    private Long servicioId;
    private Double sueldoOperador;
    private String tipoCorrida;
    private Long ultimaActualizacionPor;
    private Timestamp ultimaFechaActualizacion;
    private Long corridaRelacionadaId;
    private Long cortesiasCorrida;
    private String empresa;
    private String servicio;
    private String origen;
    private String destino;
    private String autobus;
    private String autobusOriginal;
    private String operador;
    private String operadorOriginal;
    private String estadoCorrida;
    private Long menoresCorrida;
    private Long profesoresCorrida;
    private Long senectudCorrida;
    private Long estudiantesCorrida;
    private Timestamp HoraCorrida;
    private String operadorA;
    private String rutaNumero;
    private String rutaNombre;

    public String getRutaNumero() {
        return rutaNumero;
    }

    public void setRutaNumero(String rutaNumero) {
        this.rutaNumero = rutaNumero;
    }
    
    public String getRutaNombre() {
        return rutaNombre;
    }

    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }
    
    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    public Long getAutobusOriginalId() {
        return autobusOriginalId;
    }

    public void setAutobusOriginalId(Long autobusOriginalId) {
        this.autobusOriginalId = autobusOriginalId;
    }

    public String getClaveCorrida() {
        return claveCorrida;
    }

    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    public Long getCorridaId() {
        return corridaId;
    }

    public void setCorridaId(Long corridaId) {
        this.corridaId = corridaId;
    }


    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getEstadoCorridaId() {
        return estadoCorridaId;
    }

    public void setEstadoCorridaId(Long estadoCorridaId) {
        this.estadoCorridaId = estadoCorridaId;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaHoraCorrida() {
        return fechaHoraCorrida;
    }

    public void setFechaHoraCorrida(Timestamp fechaHoraCorrida) {
        this.fechaHoraCorrida = fechaHoraCorrida;
    }
    
    public Timestamp getHoraCorrida() {
        return HoraCorrida;
    }

    public void setHoraCorrida(Timestamp HoraCorrida) {
        this.HoraCorrida = HoraCorrida;
    }

    public Double getMontoAnticipos() {
        return montoAnticipos;
    }

    public void setMontoAnticipos(Double montoAnticipos) {
        this.montoAnticipos = montoAnticipos;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }

    public Long getOperadorOriginalId() {
        return operadorOriginalId;
    }

    public void setOperadorOriginalId(Long operadorOriginalId) {
        this.operadorOriginalId = operadorOriginalId;
    }

    public Long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(Long origenId) {
        this.origenId = origenId;
    }

    public Long getPlantillaId() {
        return plantillaId;
    }

    public void setPlantillaId(Long plantillaId) {
        this.plantillaId = plantillaId;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public Double getSueldoOperador() {
        return sueldoOperador;
    }

    public void setSueldoOperador(Double sueldoOperador) {
        this.sueldoOperador = sueldoOperador;
    }

    public String getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    public Long getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(Long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Timestamp getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    public Long getCorridaRelacionadaId() {
        return corridaRelacionadaId;
    }

    public void setCorridaRelacionadaId(Long corridaRelacionadaId) {
        this.corridaRelacionadaId = corridaRelacionadaId;
    }
    
    //
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
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
    
    public String getAutobus() {
        return autobus;
    }

    public void setAutobus(String autobus) {
        this.autobus = autobus;
    }

    public String getAutobusOriginal() {
        return autobusOriginal;
    }

    public void setAutobusOriginal(String autobusOriginal) {
        this.autobusOriginal = autobusOriginal;
    }
    
    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
    
    public String getOperadorAdicional() {
        return operadorA;
    }

    public void setOperadorAdicional(String operadorA) {
        this.operadorA = operadorA;
    }

    public String getOperadorOriginal() {
        return operadorOriginal;
    }

    public void setOperadorOriginal(String operadorOriginal) {
        this.operadorOriginal = operadorOriginal;
    }
    
    public String getEstadoCorrida() {
        return estadoCorrida;
    }

    public void setEstadoCorrida(String estadoCorrida) {
        this.estadoCorrida = estadoCorrida;
    }
    
    public Long getMenoresCorrida() {
        return menoresCorrida;
    }

    public void setMenoresCorrida(Long menoresCorrida) {
        this.menoresCorrida = menoresCorrida;
    }
    
    public Long getProfesoresCorrida() {
        return profesoresCorrida;
    }

    public void setProfesoresCorrida(Long profesoresCorrida) {
        this.profesoresCorrida = profesoresCorrida;
    }

    public Long getSenectudCorrida() {
        return senectudCorrida;
    }

    public void setSenectudCorrida(Long senectudCorrida) {
        this.senectudCorrida = senectudCorrida;
    }
    
    public Long getEstudiantesCorrida() {
        return estudiantesCorrida;
    }

    public void setEstudiantesCorrida(Long estudiantesCorrida) {
        this.estudiantesCorrida = estudiantesCorrida;
    }
    
    public Long getCortesiasCorrida() {
        return cortesiasCorrida;
    }

    public void setCortesiasCorrida(Long cortesiasCorrida) {
        this.cortesiasCorrida = cortesiasCorrida;
    }
}
