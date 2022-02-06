/*
 * TmsActAdicionalesTbl.java
 *
 * Created on 19 de noviembre de 2007, 12:05 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_guardias.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsActAdicionalesTbl.findAll", 
    query = "select o from TmsActAdicionalesTbl o where o.actividadClave = 'GUA' and o.habilitado = 'S'")
@Table(name = "TMS_ACT_ADICIONALES_TBL")
public class TmsActAdicionalesTbl implements Serializable {
    @Column(name="ACCION_ID", nullable = false)
    private Long accionId;
    @Column(name="ACTIVIDAD_AFAVOR", nullable = false)
    private String actividadAfavor;
    @Column(name="ACTIVIDAD_CLAVE", nullable = false)
    private String actividadClave;
    @Column(name="ACTIVIDAD_EDITABLE", nullable = false)
    private String actividadEditable;
    @Column(name="APLICA_RECAUDACION", nullable = false)
    private String aplicaRecaudacion;
    @Column(name="CODIGO_PARAMETRO")
    private String codigoParametro;
    @Column(nullable = false)
    private String configurable;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(nullable = false)
    private String habilitado;
    @Column(name="INCIDENCIA_DURACION")
    private String incidenciaDuracion;
    @Column(name="INCIDENCIA_VALOR1")
    private String incidenciaValor1;
    @Column(name="INCIDENCIA_VALOR2")
    private String incidenciaValor2;
    @Column(name="REPLICACION_ESTADO")
    private String replicacionEstado;
    @Column(name="REPLICACION_INTENTOS")
    private Long replicacionIntentos;
    @Column(name="REPLICACION_ORIGEN")
    private String replicacionOrigen;
    @Id
    @Column(name="TIPO_ACTIVIDAD_ADICIONAL_ID", nullable = false)
    private Long tipoActividadAdicionalId;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    public TmsActAdicionalesTbl() {
    }

    public Long getAccionId() {
        return accionId;
    }

    public void setAccionId(Long accionId) {
        this.accionId = accionId;
    }

    public String getActividadAfavor() {
        return actividadAfavor;
    }

    public void setActividadAfavor(String actividadAfavor) {
        this.actividadAfavor = actividadAfavor;
    }

    public String getActividadClave() {
        return actividadClave;
    }

    public void setActividadClave(String actividadClave) {
        this.actividadClave = actividadClave;
    }

    public String getActividadEditable() {
        return actividadEditable;
    }

    public void setActividadEditable(String actividadEditable) {
        this.actividadEditable = actividadEditable;
    }

    public String getAplicaRecaudacion() {
        return aplicaRecaudacion;
    }

    public void setAplicaRecaudacion(String aplicaRecaudacion) {
        this.aplicaRecaudacion = aplicaRecaudacion;
    }

    public String getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(String codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    public String getConfigurable() {
        return configurable;
    }

    public void setConfigurable(String configurable) {
        this.configurable = configurable;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public String getIncidenciaDuracion() {
        return incidenciaDuracion;
    }

    public void setIncidenciaDuracion(String incidenciaDuracion) {
        this.incidenciaDuracion = incidenciaDuracion;
    }

    public String getIncidenciaValor1() {
        return incidenciaValor1;
    }

    public void setIncidenciaValor1(String incidenciaValor1) {
        this.incidenciaValor1 = incidenciaValor1;
    }

    public String getIncidenciaValor2() {
        return incidenciaValor2;
    }

    public void setIncidenciaValor2(String incidenciaValor2) {
        this.incidenciaValor2 = incidenciaValor2;
    }

    public String getReplicacionEstado() {
        return replicacionEstado;
    }

    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    public Long getReplicacionIntentos() {
        return replicacionIntentos;
    }

    public void setReplicacionIntentos(Long replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    public String getReplicacionOrigen() {
        return replicacionOrigen;
    }

    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    public Long getTipoActividadAdicionalId() {
        return tipoActividadAdicionalId;
    }

    public void setTipoActividadAdicionalId(Long tipoActividadAdicionalId) {
        this.tipoActividadAdicionalId = tipoActividadAdicionalId;
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
}