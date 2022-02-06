/*
 * TmsComponenteBusTbl.java
 *
 * Created on 11 de octubre de 2007, 11:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQuery(name = "TmsComponenteBusTbl.findAll", 
            query = "select o from TmsComponenteBusTbl o order by o.componenteBusId")
@Table(name = "TMS_COMPONENTE_BUS_TBL")
public class TmsComponenteBusTbl implements Serializable {
    @Id
    @Column(name = "COMPONENTE_BUS_ID", nullable = false)
    private Long componenteBusId;
    @Column(name = "CREADOR_POR", nullable = false)
    private Long creadorPor;
    @Column(name = "DESCRIPCION_COMPONENTE")
    private String descripcionComponente;
    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name = "NOMBRE_COMPONENTE", nullable = false)
    private String nombreComponente;
    @Column(nullable = false)
    private Long tipo;
    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    public TmsComponenteBusTbl() {
    }

    public Long getComponenteBusId() {
        return componenteBusId;
    }

    public void setComponenteBusId(Long componenteBusId) {
        this.componenteBusId = componenteBusId;
    }

    public Long getCreadorPor() {
        return creadorPor;
    }

    public void setCreadorPor(Long creadorPor) {
        this.creadorPor = creadorPor;
    }

    public String getDescripcionComponente() {
        return descripcionComponente;
    }

    public void setDescripcionComponente(String descripcionComponente) {
        this.descripcionComponente = descripcionComponente;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
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
