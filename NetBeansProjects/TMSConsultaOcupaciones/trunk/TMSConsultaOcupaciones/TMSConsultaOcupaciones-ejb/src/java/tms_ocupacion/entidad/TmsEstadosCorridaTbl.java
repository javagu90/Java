/*
 * TmsEstadosCorridaTbl.java
 *
 * Created on 1 de noviembre de 2007, 10:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsEstadosCorridaTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ESTADOS_CORRIDA_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByEstadoCorridaId", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.estadoCorridaId = :estadoCorridaId"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByNombreEstado", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.nombreEstado = :nombreEstado"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByNombreCortoEstado", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.nombreCortoEstado = :nombreCortoEstado"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByDescripcionEstado", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.descripcionEstado = :descripcionEstado"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByActivo", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.activo = :activo"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByCreadoPor", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByFechaCreacion", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsEstadosCorridaTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsEstadosCorridaTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsEstadosCorridaTbl implements Serializable {

    @Id
    @Column(name = "ESTADO_CORRIDA_ID", nullable = false)
    private BigDecimal estadoCorridaId;

    @Column(name = "NOMBRE_ESTADO", nullable = false)
    private String nombreEstado;

    @Column(name = "NOMBRE_CORTO_ESTADO", nullable = false)
    private String nombreCortoEstado;

    @Column(name = "DESCRIPCION_ESTADO")
    private String descripcionEstado;

    @Column(name = "ACTIVO")
    private String activo;

    @Column(name = "CREADO_POR")
    private Integer creadoPor;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;
    
    /** Creates a new instance of TmsEstadosCorridaTbl */
    public TmsEstadosCorridaTbl() {
    }

    /**
     * Creates a new instance of TmsEstadosCorridaTbl with the specified values.
     * @param estadoCorridaId the estadoCorridaId of the TmsEstadosCorridaTbl
     */
    public TmsEstadosCorridaTbl(BigDecimal estadoCorridaId) {
        this.estadoCorridaId = estadoCorridaId;
    }

    /**
     * Creates a new instance of TmsEstadosCorridaTbl with the specified values.
     * @param estadoCorridaId the estadoCorridaId of the TmsEstadosCorridaTbl
     * @param nombreEstado the nombreEstado of the TmsEstadosCorridaTbl
     * @param nombreCortoEstado the nombreCortoEstado of the TmsEstadosCorridaTbl
     */
    public TmsEstadosCorridaTbl(BigDecimal estadoCorridaId, String nombreEstado, String nombreCortoEstado) {
        this.estadoCorridaId = estadoCorridaId;
        this.nombreEstado = nombreEstado;
        this.nombreCortoEstado = nombreCortoEstado;
    }

    /**
     * Gets the estadoCorridaId of this TmsEstadosCorridaTbl.
     * @return the estadoCorridaId
     */
    public BigDecimal getEstadoCorridaId() {
        return this.estadoCorridaId;
    }

    /**
     * Sets the estadoCorridaId of this TmsEstadosCorridaTbl to the specified value.
     * @param estadoCorridaId the new estadoCorridaId
     */
    public void setEstadoCorridaId(BigDecimal estadoCorridaId) {
        this.estadoCorridaId = estadoCorridaId;
    }

    /**
     * Gets the nombreEstado of this TmsEstadosCorridaTbl.
     * @return the nombreEstado
     */
    public String getNombreEstado() {
        return this.nombreEstado;
    }

    /**
     * Sets the nombreEstado of this TmsEstadosCorridaTbl to the specified value.
     * @param nombreEstado the new nombreEstado
     */
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    /**
     * Gets the nombreCortoEstado of this TmsEstadosCorridaTbl.
     * @return the nombreCortoEstado
     */
    public String getNombreCortoEstado() {
        return this.nombreCortoEstado;
    }

    /**
     * Sets the nombreCortoEstado of this TmsEstadosCorridaTbl to the specified value.
     * @param nombreCortoEstado the new nombreCortoEstado
     */
    public void setNombreCortoEstado(String nombreCortoEstado) {
        this.nombreCortoEstado = nombreCortoEstado;
    }

    /**
     * Gets the descripcionEstado of this TmsEstadosCorridaTbl.
     * @return the descripcionEstado
     */
    public String getDescripcionEstado() {
        return this.descripcionEstado;
    }

    /**
     * Sets the descripcionEstado of this TmsEstadosCorridaTbl to the specified value.
     * @param descripcionEstado the new descripcionEstado
     */
    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    /**
     * Gets the activo of this TmsEstadosCorridaTbl.
     * @return the activo
     */
    public String getActivo() {
        return this.activo;
    }

    /**
     * Sets the activo of this TmsEstadosCorridaTbl to the specified value.
     * @param activo the new activo
     */
    public void setActivo(String activo) {
        this.activo = activo;
    }

    /**
     * Gets the creadoPor of this TmsEstadosCorridaTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsEstadosCorridaTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsEstadosCorridaTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsEstadosCorridaTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsEstadosCorridaTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsEstadosCorridaTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsEstadosCorridaTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsEstadosCorridaTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.estadoCorridaId != null ? this.estadoCorridaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsEstadosCorridaTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsEstadosCorridaTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsEstadosCorridaTbl)) {
            return false;
        }
        TmsEstadosCorridaTbl other = (TmsEstadosCorridaTbl)object;
        if (this.estadoCorridaId != other.estadoCorridaId && (this.estadoCorridaId == null || !this.estadoCorridaId.equals(other.estadoCorridaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_ocupacion.entidad.TmsEstadosCorridaTbl[estadoCorridaId=" + estadoCorridaId + "]";
    }
    
}
