/*
 * TmsTiposJornadaTbl.java
 *
 * Created on 9 de octubre de 2007, 10:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.entidad;

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
 * Entity class TmsTiposJornadaTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_TIPOS_JORNADA_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTiposJornadaTbl.findByTipoJornadaId", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.tipoJornadaId = :tipoJornadaId"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByNombreTipo", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.nombreTipo = :nombreTipo"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByDescripcion", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByHabilitado", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByCreadoPor", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByFechaCreacion", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsTiposJornadaTbl implements Serializable {

    @Id
    @Column(name = "TIPO_JORNADA_ID", nullable = false)
    private BigDecimal tipoJornadaId;

    @Column(name = "NOMBRE_TIPO", nullable = false)
    private String nombreTipo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoJornadaId")
    private Collection<TmsJornadasServiciosTbl> tmsJornadasServiciosTblCollection;
    
    /** Creates a new instance of TmsTiposJornadaTbl */
    public TmsTiposJornadaTbl() {
    }

    /**
     * Creates a new instance of TmsTiposJornadaTbl with the specified values.
     * @param tipoJornadaId the tipoJornadaId of the TmsTiposJornadaTbl
     */
    public TmsTiposJornadaTbl(BigDecimal tipoJornadaId) {
        this.tipoJornadaId = tipoJornadaId;
    }

    /**
     * Creates a new instance of TmsTiposJornadaTbl with the specified values.
     * @param tipoJornadaId the tipoJornadaId of the TmsTiposJornadaTbl
     * @param nombreTipo the nombreTipo of the TmsTiposJornadaTbl
     */
    public TmsTiposJornadaTbl(BigDecimal tipoJornadaId, String nombreTipo) {
        this.tipoJornadaId = tipoJornadaId;
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the tipoJornadaId of this TmsTiposJornadaTbl.
     * @return the tipoJornadaId
     */
    public BigDecimal getTipoJornadaId() {
        return this.tipoJornadaId;
    }

    /**
     * Sets the tipoJornadaId of this TmsTiposJornadaTbl to the specified value.
     * @param tipoJornadaId the new tipoJornadaId
     */
    public void setTipoJornadaId(BigDecimal tipoJornadaId) {
        this.tipoJornadaId = tipoJornadaId;
    }

    /**
     * Gets the nombreTipo of this TmsTiposJornadaTbl.
     * @return the nombreTipo
     */
    public String getNombreTipo() {
        return this.nombreTipo;
    }

    /**
     * Sets the nombreTipo of this TmsTiposJornadaTbl to the specified value.
     * @param nombreTipo the new nombreTipo
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the descripcion of this TmsTiposJornadaTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsTiposJornadaTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the habilitado of this TmsTiposJornadaTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsTiposJornadaTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsTiposJornadaTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTiposJornadaTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTiposJornadaTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTiposJornadaTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTiposJornadaTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTiposJornadaTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTiposJornadaTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTiposJornadaTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsJornadasServiciosTblCollection of this TmsTiposJornadaTbl.
     * @return the tmsJornadasServiciosTblCollection
     */
    public Collection<TmsJornadasServiciosTbl> getTmsJornadasServiciosTblCollection() {
        return this.tmsJornadasServiciosTblCollection;
    }

    /**
     * Sets the tmsJornadasServiciosTblCollection of this TmsTiposJornadaTbl to the specified value.
     * @param tmsJornadasServiciosTblCollection the new tmsJornadasServiciosTblCollection
     */
    public void setTmsJornadasServiciosTblCollection(Collection<TmsJornadasServiciosTbl> tmsJornadasServiciosTblCollection) {
        this.tmsJornadasServiciosTblCollection = tmsJornadasServiciosTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.tipoJornadaId != null ? this.tipoJornadaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTiposJornadaTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTiposJornadaTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTiposJornadaTbl)) {
            return false;
        }
        TmsTiposJornadaTbl other = (TmsTiposJornadaTbl)object;
        if (this.tipoJornadaId != other.tipoJornadaId && (this.tipoJornadaId == null || !this.tipoJornadaId.equals(other.tipoJornadaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsTiposJornadaTbl[tipoJornadaId=" + tipoJornadaId + "]";
    }
    
}
