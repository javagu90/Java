/*
 * TmsFlotillasTbl.java
 *
 * Created on 17 de octubre de 2007, 08:11 PM
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
@NamedQuery(name = "TmsFlotillasTbl.findAll", 
    query = "select o from TmsFlotillasTbl o")
@Table(name = "TMS_FLOTILLAS_TBL")
public class TmsFlotillasTbl implements Serializable {
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="DESCRIPCION_FLOTILLA")
    private String descripcionFlotilla;
    @Column(name="EMPRESA_ID")
    private Long empresaId;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Id
    @Column(name="FLOTILLA_ID", nullable = false)
    private Long flotillaId;
    @Column(name="NOMBRE_FLOTILLA", nullable = false)
    private String nombreFlotilla;
    @Column(name="ULTIMA_ACTUALIZACION_POR")
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;

    public TmsFlotillasTbl() {
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getDescripcionFlotilla() {
        return descripcionFlotilla;
    }

    public void setDescripcionFlotilla(String descripcionFlotilla) {
        this.descripcionFlotilla = descripcionFlotilla;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
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

    public String getNombreFlotilla() {
        return nombreFlotilla;
    }

    public void setNombreFlotilla(String nombreFlotilla) {
        this.nombreFlotilla = nombreFlotilla;
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