/*
 * TmsEstadosCorridaTbl.java
 *
 * Created on 17 de octubre de 2007, 11:48 PM
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
@NamedQuery(name = "TmsEstadosCorridaTbl.findAll", 
    query = "select o from TmsEstadosCorridaTbl o")
@Table(name = "TMS_ESTADOS_CORRIDA_TBL")
public class TmsEstadosCorridaTbl implements Serializable {
    private String activo;
    @Column(name="CREADO_POR")
    private Long creadoPor;
    @Column(name="DESCRIPCION_ESTADO")
    private String descripcionEstado;
    @Id
    @Column(name="ESTADO_CORRIDA_ID", nullable = false)
    private Long estadoCorridaId;
    @Column(name="FECHA_CREACION")
    private Timestamp fechaCreacion;
    @Column(name="NOMBRE_CORTO_ESTADO", nullable = false)
    private String nombreCortoEstado;
    @Column(name="NOMBRE_ESTADO", nullable = false)
    private String nombreEstado;
    @Column(name="ULTIMA_ACTUALIZACION_POR")
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;

    public TmsEstadosCorridaTbl() {
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
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

    public String getNombreCortoEstado() {
        return nombreCortoEstado;
    }

    public void setNombreCortoEstado(String nombreCortoEstado) {
        this.nombreCortoEstado = nombreCortoEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
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