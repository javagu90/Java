/*
 * TmsReglasSustTbl.java
 *
 * Created on 13 de octubre de 2007, 06:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsReglasSustTbl.findAll", 
    query = "select o from TmsReglasSustTbl o")
@Table(name = "TMS_REGLAS_SUST_TBL")
public class TmsReglasSustTbl implements Serializable {
    @Column(name="ACTIVIDAD_ID")
    private Long actividadId;
    @Column(name="CREADO_POR")
    private Long creadoPor;
    @Column(name="FECHA_CREACION")
    private Timestamp fechaCreacion;
    @Column(name="FLOTILLA_ID")
    private Long flotillaId;
    @Column(name="FLOTILLA_SUST_ID")
    private Long flotillaSustId;
    private Long prioridad;
    @Id
    @Column(name="REGLA_SUSTITUCION_ID", nullable = false)
    private Long reglaSustitucionId;
    @Column(name="ULTIMA_ACTUALIZACION_POR")
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;

    public TmsReglasSustTbl() {
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
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

    public Long getFlotillaId() {
        return flotillaId;
    }

    public void setFlotillaId(Long flotillaId) {
        this.flotillaId = flotillaId;
    }

    public Long getFlotillaSustId() {
        return flotillaSustId;
    }

    public void setFlotillaSustId(Long flotillaSustId) {
        this.flotillaSustId = flotillaSustId;
    }

    public Long getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Long prioridad) {
        this.prioridad = prioridad;
    }

    public Long getReglaSustitucionId() {
        return reglaSustitucionId;
    }

    public void setReglaSustitucionId(Long reglaSustitucionId) {
        this.reglaSustitucionId = reglaSustitucionId;
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