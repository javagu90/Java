/*
 * TmsAccionesTbl.java
 *
 * Created on 9 de octubre de 2007, 10:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * Entity class TmsAccionesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ACCIONES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsAccionesTbl.findByAccionId", query = "SELECT t FROM TmsAccionesTbl t WHERE t.accionId = :accionId"),
        @NamedQuery(name = "TmsAccionesTbl.findByAccion", query = "SELECT t FROM TmsAccionesTbl t WHERE t.accion = :accion"),
        @NamedQuery(name = "TmsAccionesTbl.findByDescripcion", query = "SELECT t FROM TmsAccionesTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsAccionesTbl.findByComponente", query = "SELECT t FROM TmsAccionesTbl t WHERE t.componente = :componente"),
        @NamedQuery(name = "TmsAccionesTbl.findByHabilitado", query = "SELECT t FROM TmsAccionesTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsAccionesTbl.findByCreadoPor", query = "SELECT t FROM TmsAccionesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsAccionesTbl.findByFechaCreacion", query = "SELECT t FROM TmsAccionesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsAccionesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsAccionesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsAccionesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsAccionesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsAccionesTbl implements Serializable {

    @Id
    @Column(name = "ACCION_ID", nullable = false)
    private BigDecimal accionId;

    @Column(name = "ACCION", nullable = false)
    private String accion;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "COMPONENTE", nullable = false)
    private BigInteger componente;

    @Column(name = "HABILITADO", nullable = false)
    private String habilitado;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accion1Id")
    private Collection<TmsOperadoresTbl> tmsOperadoresTblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accion2Id")
    private Collection<TmsOperadoresTbl> tmsOperadoresTblCollection1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accion4Id")
    private Collection<TmsOperadoresTbl> tmsOperadoresTblCollection2;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accion3Id")
    private Collection<TmsOperadoresTbl> tmsOperadoresTblCollection3;
    
    /** Creates a new instance of TmsAccionesTbl */
    public TmsAccionesTbl() {
    }

    /**
     * Creates a new instance of TmsAccionesTbl with the specified values.
     * @param accionId the accionId of the TmsAccionesTbl
     */
    public TmsAccionesTbl(BigDecimal accionId) {
        this.accionId = accionId;
    }

    /**
     * Creates a new instance of TmsAccionesTbl with the specified values.
     * @param accionId the accionId of the TmsAccionesTbl
     * @param accion the accion of the TmsAccionesTbl
     * @param componente the componente of the TmsAccionesTbl
     * @param habilitado the habilitado of the TmsAccionesTbl
     * @param creadoPor the creadoPor of the TmsAccionesTbl
     * @param fechaCreacion the fechaCreacion of the TmsAccionesTbl
     */
    public TmsAccionesTbl(BigDecimal accionId, String accion, BigInteger componente, String habilitado, BigInteger creadoPor, Date fechaCreacion) {
        this.accionId = accionId;
        this.accion = accion;
        this.componente = componente;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the accionId of this TmsAccionesTbl.
     * @return the accionId
     */
    public BigDecimal getAccionId() {
        return this.accionId;
    }

    /**
     * Sets the accionId of this TmsAccionesTbl to the specified value.
     * @param accionId the new accionId
     */
    public void setAccionId(BigDecimal accionId) {
        this.accionId = accionId;
    }

    /**
     * Gets the accion of this TmsAccionesTbl.
     * @return the accion
     */
    public String getAccion() {
        return this.accion;
    }

    /**
     * Sets the accion of this TmsAccionesTbl to the specified value.
     * @param accion the new accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * Gets the descripcion of this TmsAccionesTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsAccionesTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the componente of this TmsAccionesTbl.
     * @return the componente
     */
    public BigInteger getComponente() {
        return this.componente;
    }

    /**
     * Sets the componente of this TmsAccionesTbl to the specified value.
     * @param componente the new componente
     */
    public void setComponente(BigInteger componente) {
        this.componente = componente;
    }

    /**
     * Gets the habilitado of this TmsAccionesTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsAccionesTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsAccionesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsAccionesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsAccionesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsAccionesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsAccionesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsAccionesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsAccionesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsAccionesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsOperadoresTblCollection of this TmsAccionesTbl.
     * @return the tmsOperadoresTblCollection
     */
    public Collection<TmsOperadoresTbl> getTmsOperadoresTblCollection() {
        return this.tmsOperadoresTblCollection;
    }

    /**
     * Sets the tmsOperadoresTblCollection of this TmsAccionesTbl to the specified value.
     * @param tmsOperadoresTblCollection the new tmsOperadoresTblCollection
     */
    public void setTmsOperadoresTblCollection(Collection<TmsOperadoresTbl> tmsOperadoresTblCollection) {
        this.tmsOperadoresTblCollection = tmsOperadoresTblCollection;
    }

    /**
     * Gets the tmsOperadoresTblCollection1 of this TmsAccionesTbl.
     * @return the tmsOperadoresTblCollection1
     */
    public Collection<TmsOperadoresTbl> getTmsOperadoresTblCollection1() {
        return this.tmsOperadoresTblCollection1;
    }

    /**
     * Sets the tmsOperadoresTblCollection1 of this TmsAccionesTbl to the specified value.
     * @param tmsOperadoresTblCollection1 the new tmsOperadoresTblCollection1
     */
    public void setTmsOperadoresTblCollection1(Collection<TmsOperadoresTbl> tmsOperadoresTblCollection1) {
        this.tmsOperadoresTblCollection1 = tmsOperadoresTblCollection1;
    }

    /**
     * Gets the tmsOperadoresTblCollection2 of this TmsAccionesTbl.
     * @return the tmsOperadoresTblCollection2
     */
    public Collection<TmsOperadoresTbl> getTmsOperadoresTblCollection2() {
        return this.tmsOperadoresTblCollection2;
    }

    /**
     * Sets the tmsOperadoresTblCollection2 of this TmsAccionesTbl to the specified value.
     * @param tmsOperadoresTblCollection2 the new tmsOperadoresTblCollection2
     */
    public void setTmsOperadoresTblCollection2(Collection<TmsOperadoresTbl> tmsOperadoresTblCollection2) {
        this.tmsOperadoresTblCollection2 = tmsOperadoresTblCollection2;
    }

    /**
     * Gets the tmsOperadoresTblCollection3 of this TmsAccionesTbl.
     * @return the tmsOperadoresTblCollection3
     */
    public Collection<TmsOperadoresTbl> getTmsOperadoresTblCollection3() {
        return this.tmsOperadoresTblCollection3;
    }

    /**
     * Sets the tmsOperadoresTblCollection3 of this TmsAccionesTbl to the specified value.
     * @param tmsOperadoresTblCollection3 the new tmsOperadoresTblCollection3
     */
    public void setTmsOperadoresTblCollection3(Collection<TmsOperadoresTbl> tmsOperadoresTblCollection3) {
        this.tmsOperadoresTblCollection3 = tmsOperadoresTblCollection3;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.accionId != null ? this.accionId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsAccionesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsAccionesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsAccionesTbl)) {
            return false;
        }
        TmsAccionesTbl other = (TmsAccionesTbl)object;
        if (this.accionId != other.accionId && (this.accionId == null || !this.accionId.equals(other.accionId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsAccionesTbl[accionId=" + accionId + "]";
    }
    
}
