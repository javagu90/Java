/*
 * TmsTiposOperadorTbl.java
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
 * Entity class TmsTiposOperadorTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_TIPOS_OPERADOR_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTiposOperadorTbl.findByTipoOperadorId", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.tipoOperadorId = :tipoOperadorId"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByNombreTipo", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.nombreTipo = :nombreTipo"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByDescripcion", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByHabilitado", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByCreadoPor", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByFechaCreacion", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsTiposOperadorTbl.findByCategoria", query = "SELECT t FROM TmsTiposOperadorTbl t WHERE t.categoria = :categoria")
    })
public class TmsTiposOperadorTbl implements Serializable {

    @Id
    @Column(name = "TIPO_OPERADOR_ID", nullable = false)
    private BigDecimal tipoOperadorId;

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

    @Column(name = "CATEGORIA")
    private String categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoOperadorId")
    private Collection<TmsOperadoresTbl> tmsOperadoresTblCollection;
    
    /** Creates a new instance of TmsTiposOperadorTbl */
    public TmsTiposOperadorTbl() {
    }

    /**
     * Creates a new instance of TmsTiposOperadorTbl with the specified values.
     * @param tipoOperadorId the tipoOperadorId of the TmsTiposOperadorTbl
     */
    public TmsTiposOperadorTbl(BigDecimal tipoOperadorId) {
        this.tipoOperadorId = tipoOperadorId;
    }

    /**
     * Creates a new instance of TmsTiposOperadorTbl with the specified values.
     * @param tipoOperadorId the tipoOperadorId of the TmsTiposOperadorTbl
     * @param nombreTipo the nombreTipo of the TmsTiposOperadorTbl
     */
    public TmsTiposOperadorTbl(BigDecimal tipoOperadorId, String nombreTipo) {
        this.tipoOperadorId = tipoOperadorId;
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the tipoOperadorId of this TmsTiposOperadorTbl.
     * @return the tipoOperadorId
     */
    public BigDecimal getTipoOperadorId() {
        return this.tipoOperadorId;
    }

    /**
     * Sets the tipoOperadorId of this TmsTiposOperadorTbl to the specified value.
     * @param tipoOperadorId the new tipoOperadorId
     */
    public void setTipoOperadorId(BigDecimal tipoOperadorId) {
        this.tipoOperadorId = tipoOperadorId;
    }

    /**
     * Gets the nombreTipo of this TmsTiposOperadorTbl.
     * @return the nombreTipo
     */
    public String getNombreTipo() {
        return this.nombreTipo;
    }

    /**
     * Sets the nombreTipo of this TmsTiposOperadorTbl to the specified value.
     * @param nombreTipo the new nombreTipo
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the descripcion of this TmsTiposOperadorTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsTiposOperadorTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the habilitado of this TmsTiposOperadorTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsTiposOperadorTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsTiposOperadorTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTiposOperadorTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTiposOperadorTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTiposOperadorTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTiposOperadorTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTiposOperadorTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTiposOperadorTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTiposOperadorTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the categoria of this TmsTiposOperadorTbl.
     * @return the categoria
     */
    public String getCategoria() {
        return this.categoria;
    }

    /**
     * Sets the categoria of this TmsTiposOperadorTbl to the specified value.
     * @param categoria the new categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Gets the tmsOperadoresTblCollection of this TmsTiposOperadorTbl.
     * @return the tmsOperadoresTblCollection
     */
    public Collection<TmsOperadoresTbl> getTmsOperadoresTblCollection() {
        return this.tmsOperadoresTblCollection;
    }

    /**
     * Sets the tmsOperadoresTblCollection of this TmsTiposOperadorTbl to the specified value.
     * @param tmsOperadoresTblCollection the new tmsOperadoresTblCollection
     */
    public void setTmsOperadoresTblCollection(Collection<TmsOperadoresTbl> tmsOperadoresTblCollection) {
        this.tmsOperadoresTblCollection = tmsOperadoresTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.tipoOperadorId != null ? this.tipoOperadorId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTiposOperadorTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTiposOperadorTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTiposOperadorTbl)) {
            return false;
        }
        TmsTiposOperadorTbl other = (TmsTiposOperadorTbl)object;
        if (this.tipoOperadorId != other.tipoOperadorId && (this.tipoOperadorId == null || !this.tipoOperadorId.equals(other.tipoOperadorId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsTiposOperadorTbl[tipoOperadorId=" + tipoOperadorId + "]";
    }
    
}
