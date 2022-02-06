/*
 * TmsEstadosTarjetaViajeTbl.java
 *
 * Created on 9 de octubre de 2007, 10:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsEstadosTarjetaViajeTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ESTADOS_TARJETA_VIAJE_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByEstadoTarjetaViajeId", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.estadoTarjetaViajeId = :estadoTarjetaViajeId"),
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByNombreEstado", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.nombreEstado = :nombreEstado"),
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByHabilitado", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByCreadoPor", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByFechaCreacion", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsEstadosTarjetaViajeTbl.findByDescripcionEstado", query = "SELECT t FROM TmsEstadosTarjetaViajeTbl t WHERE t.descripcionEstado = :descripcionEstado")
    })
public class TmsEstadosTarjetaViajeTbl implements Serializable {

    @Id
    @Column(name = "ESTADO_TARJETA_VIAJE_ID", nullable = false)
    private BigDecimal estadoTarjetaViajeId;

    @Column(name = "NOMBRE_ESTADO", nullable = false)
    private String nombreEstado;

    @Column(name = "HABILITADO")
    private String habilitado;

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

    @Column(name = "DESCRIPCION_ESTADO")
    private String descripcionEstado;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoTarjetaId")
   // private Collection<TmsTarjetasViajeTbl> tmsTarjetasViajeTblCollection;
    
    /** Creates a new instance of TmsEstadosTarjetaViajeTbl */
    public TmsEstadosTarjetaViajeTbl() {
    }

    /**
     * Creates a new instance of TmsEstadosTarjetaViajeTbl with the specified values.
     * @param estadoTarjetaViajeId the estadoTarjetaViajeId of the TmsEstadosTarjetaViajeTbl
     */
    public TmsEstadosTarjetaViajeTbl(BigDecimal estadoTarjetaViajeId) {
        this.estadoTarjetaViajeId = estadoTarjetaViajeId;
    }

    /**
     * Creates a new instance of TmsEstadosTarjetaViajeTbl with the specified values.
     * @param estadoTarjetaViajeId the estadoTarjetaViajeId of the TmsEstadosTarjetaViajeTbl
     * @param nombreEstado the nombreEstado of the TmsEstadosTarjetaViajeTbl
     */
    public TmsEstadosTarjetaViajeTbl(BigDecimal estadoTarjetaViajeId, String nombreEstado) {
        this.estadoTarjetaViajeId = estadoTarjetaViajeId;
        this.nombreEstado = nombreEstado;
    }

    /**
     * Gets the estadoTarjetaViajeId of this TmsEstadosTarjetaViajeTbl.
     * @return the estadoTarjetaViajeId
     */
    public BigDecimal getEstadoTarjetaViajeId() {
        return this.estadoTarjetaViajeId;
    }

    /**
     * Sets the estadoTarjetaViajeId of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param estadoTarjetaViajeId the new estadoTarjetaViajeId
     */
    public void setEstadoTarjetaViajeId(BigDecimal estadoTarjetaViajeId) {
        this.estadoTarjetaViajeId = estadoTarjetaViajeId;
    }

    /**
     * Gets the nombreEstado of this TmsEstadosTarjetaViajeTbl.
     * @return the nombreEstado
     */
    public String getNombreEstado() {
        return this.nombreEstado;
    }

    /**
     * Sets the nombreEstado of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param nombreEstado the new nombreEstado
     */
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    /**
     * Gets the habilitado of this TmsEstadosTarjetaViajeTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsEstadosTarjetaViajeTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsEstadosTarjetaViajeTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsEstadosTarjetaViajeTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsEstadosTarjetaViajeTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the descripcionEstado of this TmsEstadosTarjetaViajeTbl.
     * @return the descripcionEstado
     */
    public String getDescripcionEstado() {
        return this.descripcionEstado;
    }

    /**
     * Sets the descripcionEstado of this TmsEstadosTarjetaViajeTbl to the specified value.
     * @param descripcionEstado the new descripcionEstado
     */
    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    /**
     * Gets the tmsTarjetasViajeTblCollection of this TmsEstadosTarjetaViajeTbl.
     * @return the tmsTarjetasViajeTblCollection
     */
//    public Collection<TmsTarjetasViajeTbl> getTmsTarjetasViajeTblCollection() {
//        return this.tmsTarjetasViajeTblCollection;
//    }
//
//    /**
//     * Sets the tmsTarjetasViajeTblCollection of this TmsEstadosTarjetaViajeTbl to the specified value.
//     * @param tmsTarjetasViajeTblCollection the new tmsTarjetasViajeTblCollection
//     */
//    public void setTmsTarjetasViajeTblCollection(Collection<TmsTarjetasViajeTbl> tmsTarjetasViajeTblCollection) {
//        this.tmsTarjetasViajeTblCollection = tmsTarjetasViajeTblCollection;
//    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.estadoTarjetaViajeId != null ? this.estadoTarjetaViajeId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsEstadosTarjetaViajeTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsEstadosTarjetaViajeTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsEstadosTarjetaViajeTbl)) {
            return false;
        }
        TmsEstadosTarjetaViajeTbl other = (TmsEstadosTarjetaViajeTbl)object;
        if (this.estadoTarjetaViajeId != other.estadoTarjetaViajeId && (this.estadoTarjetaViajeId == null || !this.estadoTarjetaViajeId.equals(other.estadoTarjetaViajeId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsEstadosTarjetaViajeTbl[estadoTarjetaViajeId=" + estadoTarjetaViajeId + "]";
    }
    
}
