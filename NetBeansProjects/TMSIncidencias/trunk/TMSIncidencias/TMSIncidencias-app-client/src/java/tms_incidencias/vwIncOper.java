/*
 * vwIncOper.java
 *
 * Created on 19 de noviembre de 2007, 07:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_incidencias;

import java.sql.Timestamp;

/**
 *
 * @author ocruz
 */
public class vwIncOper {

    private Long operIncidenciaId;

    private Long operadorId;

    private String claveOperador;

    private String operadorNombreCompleto;

    private Long incidenciaId;

    private String incidenciaClave;

    private String incidenciaNombre;

    private String aplicaRecaudacion;

    private String configurable;

    private Timestamp fechaFinal;

    private Timestamp fechaInicial;

    private String incidenciaValor;

    private String duracion;

    private String retencion;

    private String monto;

    private String incidenciaAutorizada;

    private Long servicioId;

    private String servicioNombre;
    
    private String Observacion;
    
    /** Creates a new instance of vwIncOper */
    public vwIncOper() {
    }
    
    public Long getOperIncidenciaId() {
        return operIncidenciaId;
    }

    public void setOperIncidenciaId(Long operIncidenciaId) {
        this.operIncidenciaId = operIncidenciaId;
    }
    
    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }
    
    public String getClaveOperador() {
        return claveOperador;
    }

    public void setClaveOperador(String claveOperador) {
        this.claveOperador = claveOperador;
    }
    
    public String getOperadorNombreCompleto() {
        return operadorNombreCompleto;
    }

    public void setOperadorNombreCompleto(String operadorNombreCompleto) {
        this.operadorNombreCompleto = operadorNombreCompleto;
    }
    
    public Long getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(Long incidenciaId) {
        this.incidenciaId = incidenciaId;
    }
    
    public String getIncidenciaClave() {
        return incidenciaClave;
    }

    public void setIncidenciaClave(String incidenciaClave) {
        this.incidenciaClave = incidenciaClave;
    }
    
    public String getIncidenciaNombre() {
        return incidenciaNombre;
    }

    public void setIncidenciaNombre(String incidenciaNombre) {
        this.incidenciaNombre = incidenciaNombre;
    }
    
    public String getAplicaRecaudacion() {
        return aplicaRecaudacion;
    }

    public void setAplicaRecaudacion(String aplicaRecaudacion) {
        this.aplicaRecaudacion = aplicaRecaudacion;
    }
    
    public String getConfigurable() {
        return configurable;
    }

    public void setConfigurable(String configurable) {
        this.configurable = configurable;
    }
    
    public Timestamp getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Timestamp fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Timestamp getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Timestamp fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    
    public String getIncidenciaValor() {
        return incidenciaValor;
    }

    public void setIncidenciaValor(String incidenciaValor) {
        this.incidenciaValor = incidenciaValor;
    }
    
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String incidenciaValor) {
        this.duracion = incidenciaValor;
    }
    
    public String getRetencion() {
        return retencion;
    }

    public void setRetencion(String incidenciaValor) {
        this.retencion = incidenciaValor;
    }
    
    public String getMonto() {
        return monto;
    }

    public void setMonto(String incidenciaValor) {
        this.monto = incidenciaValor;
    }

    
 
    public void setIncidenciaAutorizada(String adicional1) {
        this.incidenciaAutorizada = adicional1;
    }

    public String getIncidenciaAutorizada() {
        return incidenciaAutorizada;
    }
    
    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }
    
    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }
    
    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
}