/*
 * TmsEmpresasTbl.java
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
 * Entity class TmsEmpresasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_EMPRESAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsEmpresasTbl.findByEmpresaId", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.empresaId = :empresaId"),
        @NamedQuery(name = "TmsEmpresasTbl.findByEmpresaNombreCorto", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.empresaNombreCorto = :empresaNombreCorto"),
        @NamedQuery(name = "TmsEmpresasTbl.findByEmpresaNombre", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.empresaNombre = :empresaNombre"),
        @NamedQuery(name = "TmsEmpresasTbl.findByDescripcion", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsEmpresasTbl.findByCreadoPor", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsEmpresasTbl.findByFechaCreacion", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsEmpresasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsEmpresasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsEmpresasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsEmpresasTbl implements Serializable {

    @Id
    @Column(name = "EMPRESA_ID", nullable = false)
    private BigDecimal empresaId;

    @Column(name = "EMPRESA_NOMBRE_CORTO", nullable = false)
    private String empresaNombreCorto;

    @Column(name = "EMPRESA_NOMBRE", nullable = false)
    private String empresaNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaId")
    private Collection<TmsCorridasTbl> tmsCorridasTblCollection;
    
    /** Creates a new instance of TmsEmpresasTbl */
    public TmsEmpresasTbl() {
    }

    /**
     * Creates a new instance of TmsEmpresasTbl with the specified values.
     * @param empresaId the empresaId of the TmsEmpresasTbl
     */
    public TmsEmpresasTbl(BigDecimal empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Creates a new instance of TmsEmpresasTbl with the specified values.
     * @param empresaId the empresaId of the TmsEmpresasTbl
     * @param empresaNombreCorto the empresaNombreCorto of the TmsEmpresasTbl
     * @param empresaNombre the empresaNombre of the TmsEmpresasTbl
     * @param creadoPor the creadoPor of the TmsEmpresasTbl
     * @param fechaCreacion the fechaCreacion of the TmsEmpresasTbl
     */
    public TmsEmpresasTbl(BigDecimal empresaId, String empresaNombreCorto, String empresaNombre, BigInteger creadoPor, Date fechaCreacion) {
        this.empresaId = empresaId;
        this.empresaNombreCorto = empresaNombreCorto;
        this.empresaNombre = empresaNombre;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the empresaId of this TmsEmpresasTbl.
     * @return the empresaId
     */
    public BigDecimal getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsEmpresasTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(BigDecimal empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the empresaNombreCorto of this TmsEmpresasTbl.
     * @return the empresaNombreCorto
     */
    public String getEmpresaNombreCorto() {
        return this.empresaNombreCorto;
    }

    /**
     * Sets the empresaNombreCorto of this TmsEmpresasTbl to the specified value.
     * @param empresaNombreCorto the new empresaNombreCorto
     */
    public void setEmpresaNombreCorto(String empresaNombreCorto) {
        this.empresaNombreCorto = empresaNombreCorto;
    }

    /**
     * Gets the empresaNombre of this TmsEmpresasTbl.
     * @return the empresaNombre
     */
    public String getEmpresaNombre() {
        return this.empresaNombre;
    }

    /**
     * Sets the empresaNombre of this TmsEmpresasTbl to the specified value.
     * @param empresaNombre the new empresaNombre
     */
    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    /**
     * Gets the descripcion of this TmsEmpresasTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsEmpresasTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the creadoPor of this TmsEmpresasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsEmpresasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsEmpresasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsEmpresasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsEmpresasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsEmpresasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsEmpresasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsEmpresasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsCorridasTblCollection of this TmsEmpresasTbl.
     * @return the tmsCorridasTblCollection
     */
    public Collection<TmsCorridasTbl> getTmsCorridasTblCollection() {
        return this.tmsCorridasTblCollection;
    }

    /**
     * Sets the tmsCorridasTblCollection of this TmsEmpresasTbl to the specified value.
     * @param tmsCorridasTblCollection the new tmsCorridasTblCollection
     */
    public void setTmsCorridasTblCollection(Collection<TmsCorridasTbl> tmsCorridasTblCollection) {
        this.tmsCorridasTblCollection = tmsCorridasTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.empresaId != null ? this.empresaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsEmpresasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsEmpresasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsEmpresasTbl)) {
            return false;
        }
        TmsEmpresasTbl other = (TmsEmpresasTbl)object;
        if (this.empresaId != other.empresaId && (this.empresaId == null || !this.empresaId.equals(other.empresaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsEmpresasTbl[empresaId=" + empresaId + "]";
    }
    
}
