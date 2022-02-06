/*
 * TmsEmpresasTbl.java
 *
 * Created on 8 de octubre de 2007, 09:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_incidencias.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsEmpresasTbl.findAll", query = "select o from TmsEmpresasTbl o order by o.empresaId")
@Table(name = "TMS_EMPRESAS_TBL")
public class TmsEmpresasTbl implements Serializable {
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    private String descripcion;
    @Id
    @Column(name="EMPRESA_ID", nullable = false)
    private Long empresaId;
    @Column(name="EMPRESA_NOMBRE", nullable = false)
    private String empresaNombre;
    @Column(name="EMPRESA_NOMBRE_CORTO", nullable = false)
    private String empresaNombreCorto;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name="ULTIMA_ACTUALIZACION_POR")
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;

    public TmsEmpresasTbl() {
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public String getEmpresaNombreCorto() {
        return empresaNombreCorto;
    }

    public void setEmpresaNombreCorto(String empresaNombreCorto) {
        this.empresaNombreCorto = empresaNombreCorto;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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