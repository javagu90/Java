/*
 * TmsFlotillasTbl.java
 *
 * Created on 21 de octubre de 2007, 07:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustit.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsFlotillasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_FLOTILLAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsFlotillasTbl.findByFlotillaId", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.flotillaId = :flotillaId"),
        @NamedQuery(name = "TmsFlotillasTbl.findByNombreFlotilla", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.nombreFlotilla = :nombreFlotilla"),
        @NamedQuery(name = "TmsFlotillasTbl.findByDescripcionFlotilla", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.descripcionFlotilla = :descripcionFlotilla"),
        @NamedQuery(name = "TmsFlotillasTbl.findByCreadoPor", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsFlotillasTbl.findByFechaCreacion", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsFlotillasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsFlotillasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsFlotillasTbl.findByEmpresaId", query = "SELECT t FROM TmsFlotillasTbl t WHERE t.empresaId = :empresaId")
    })
public class TmsFlotillasTbl implements Serializable {


    @Id
    @Column(name = "FLOTILLA_ID", nullable = false)
    private BigDecimal flotillaId;

    @Column(name = "NOMBRE_FLOTILLA", nullable = false)
    private String nombreFlotilla;

    @Column(name = "DESCRIPCION_FLOTILLA")
    private String descripcionFlotilla;

    @Column(name = "CREADO_POR", nullable = false)
    private int creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @Column(name = "EMPRESA_ID")
    private BigInteger empresaId;

    @OneToMany(mappedBy = "flotillaId")
    private Collection<TmsReglasSustTbl> tmsReglasSustTblCollection;

    //@OneToMany(mappedBy = "flotillaSustId")
    //private Collection<TmsReglasSustTbl> tmsReglasSustTblCollection1;
    
    /** Creates a new instance of TmsFlotillasTbl */
    public TmsFlotillasTbl() {
    }

    /**
     * Creates a new instance of TmsFlotillasTbl with the specified values.
     * @param flotillaId the flotillaId of the TmsFlotillasTbl
     */
    public TmsFlotillasTbl(BigDecimal flotillaId) {
        this.flotillaId = flotillaId;
    }

    /**
     * Creates a new instance of TmsFlotillasTbl with the specified values.
     * @param flotillaId the flotillaId of the TmsFlotillasTbl
     * @param nombreFlotilla the nombreFlotilla of the TmsFlotillasTbl
     * @param creadoPor the creadoPor of the TmsFlotillasTbl
     * @param fechaCreacion the fechaCreacion of the TmsFlotillasTbl
     */
    public TmsFlotillasTbl(BigDecimal flotillaId, String nombreFlotilla, int creadoPor, Date fechaCreacion) {
        this.flotillaId = flotillaId;
        this.nombreFlotilla = nombreFlotilla;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the flotillaId of this TmsFlotillasTbl.
     * @return the flotillaId
     */
    public BigDecimal getFlotillaId() {
        return this.flotillaId;
    }

    /**
     * Sets the flotillaId of this TmsFlotillasTbl to the specified value.
     * @param flotillaId the new flotillaId
     */
    public void setFlotillaId(BigDecimal flotillaId) {
        this.flotillaId = flotillaId;
    }

    /**
     * Gets the nombreFlotilla of this TmsFlotillasTbl.
     * @return the nombreFlotilla
     */
    public String getNombreFlotilla() {
        return this.nombreFlotilla;
    }

    /**
     * Sets the nombreFlotilla of this TmsFlotillasTbl to the specified value.
     * @param nombreFlotilla the new nombreFlotilla
     */
    public void setNombreFlotilla(String nombreFlotilla) {
        this.nombreFlotilla = nombreFlotilla;
    }

    /**
     * Gets the descripcionFlotilla of this TmsFlotillasTbl.
     * @return the descripcionFlotilla
     */
    public String getDescripcionFlotilla() {
        return this.descripcionFlotilla;
    }

    /**
     * Sets the descripcionFlotilla of this TmsFlotillasTbl to the specified value.
     * @param descripcionFlotilla the new descripcionFlotilla
     */
    public void setDescripcionFlotilla(String descripcionFlotilla) {
        this.descripcionFlotilla = descripcionFlotilla;
    }

    /**
     * Gets the creadoPor of this TmsFlotillasTbl.
     * @return the creadoPor
     */
    public int getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsFlotillasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsFlotillasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsFlotillasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsFlotillasTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsFlotillasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsFlotillasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsFlotillasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the empresaId of this TmsFlotillasTbl.
     * @return the empresaId
     */
    public BigInteger getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsFlotillasTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(BigInteger empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the tmsReglasSustTblCollection of this TmsFlotillasTbl.
     * @return the tmsReglasSustTblCollection
     */
    public Collection<TmsReglasSustTbl> getTmsReglasSustTblCollection() {
        return this.tmsReglasSustTblCollection;
    }

    /**
     * Sets the tmsReglasSustTblCollection of this TmsFlotillasTbl to the specified value.
     * @param tmsReglasSustTblCollection the new tmsReglasSustTblCollection
     */
    public void setTmsReglasSustTblCollection(Collection<TmsReglasSustTbl> tmsReglasSustTblCollection) {
        this.tmsReglasSustTblCollection = tmsReglasSustTblCollection;
    }

//    /**
//     * Gets the tmsReglasSustTblCollection1 of this TmsFlotillasTbl.
//     * @return the tmsReglasSustTblCollection1
//     */
//    public Collection<TmsReglasSustTbl> getTmsReglasSustTblCollection1() {
//        return this.tmsReglasSustTblCollection1;
//    }
//
//    /**
//     * Sets the tmsReglasSustTblCollection1 of this TmsFlotillasTbl to the specified value.
//     * @param tmsReglasSustTblCollection1 the new tmsReglasSustTblCollection1
//     */
//    public void setTmsReglasSustTblCollection1(Collection<TmsReglasSustTbl> tmsReglasSustTblCollection1) {
//        this.tmsReglasSustTblCollection1 = tmsReglasSustTblCollection1;
//    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.flotillaId != null ? this.flotillaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsFlotillasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsFlotillasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsFlotillasTbl)) {
            return false;
        }
        TmsFlotillasTbl other = (TmsFlotillasTbl)object;
        if (this.flotillaId != other.flotillaId && (this.flotillaId == null || !this.flotillaId.equals(other.flotillaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsreglassustit.entidad.TmsFlotillasTbl[flotillaId=" + flotillaId + "]";
    }
    
}
