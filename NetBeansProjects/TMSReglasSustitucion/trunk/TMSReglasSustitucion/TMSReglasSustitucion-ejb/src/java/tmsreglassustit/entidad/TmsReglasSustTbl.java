/*
 * TmsReglasSustTbl.java
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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsReglasSustTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_REGLAS_SUST_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsReglasSustTbl.findByReglaSustitucionId", query = "SELECT t FROM TmsReglasSustTbl t WHERE t.reglaSustitucionId = :reglaSustitucionId"),
        @NamedQuery(name = "TmsReglasSustTbl.findByPrioridad", query = "SELECT t FROM TmsReglasSustTbl t WHERE t.prioridad = :prioridad"),
        @NamedQuery(name = "TmsReglasSustTbl.findByCreadoPor", query = "SELECT t FROM TmsReglasSustTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsReglasSustTbl.findByFechaCreacion", query = "SELECT t FROM TmsReglasSustTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsReglasSustTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsReglasSustTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsReglasSustTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsReglasSustTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsReglasSustTbl implements Serializable {

    @SequenceGenerator(name="TMS_RGL_SEQ",sequenceName="TMS_REGLAS_SUST_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_RGL_SEQ")        
    @Id
    @Column(name = "REGLA_SUSTITUCION_ID", nullable = false)
    private BigDecimal reglaSustitucionId;

    @Column(name = "PRIORIDAD")
    private BigInteger prioridad;

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

    @JoinColumn(name = "ACTIVIDAD_ID", referencedColumnName = "ESTADO_ID")
    @ManyToOne
    private TmsEstadosTbl actividadId;

    @JoinColumn(name = "FLOTILLA_ID", referencedColumnName = "FLOTILLA_ID")
    @ManyToOne
    private TmsFlotillasTbl flotillaId;

    @JoinColumn(name = "FLOTILLA_SUST_ID", referencedColumnName = "FLOTILLA_ID")
    @ManyToOne
    private TmsFlotillasTbl flotillaSustId;
    
    /** Creates a new instance of TmsReglasSustTbl */
    public TmsReglasSustTbl() {
    }

    /**
     * Creates a new instance of TmsReglasSustTbl with the specified values.
     * @param reglaSustitucionId the reglaSustitucionId of the TmsReglasSustTbl
     */
    public TmsReglasSustTbl(BigDecimal reglaSustitucionId) {
        this.reglaSustitucionId = reglaSustitucionId;
    }

    /**
     * Gets the reglaSustitucionId of this TmsReglasSustTbl.
     * @return the reglaSustitucionId
     */
    public BigDecimal getReglaSustitucionId() {
        return this.reglaSustitucionId;
    }

    /**
     * Sets the reglaSustitucionId of this TmsReglasSustTbl to the specified value.
     * @param reglaSustitucionId the new reglaSustitucionId
     */
    public void setReglaSustitucionId(BigDecimal reglaSustitucionId) {
        this.reglaSustitucionId = reglaSustitucionId;
    }

    /**
     * Gets the prioridad of this TmsReglasSustTbl.
     * @return the prioridad
     */
    public BigInteger getPrioridad() {
        return this.prioridad;
    }

    /**
     * Sets the prioridad of this TmsReglasSustTbl to the specified value.
     * @param prioridad the new prioridad
     */
    public void setPrioridad(BigInteger prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Gets the creadoPor of this TmsReglasSustTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsReglasSustTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsReglasSustTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsReglasSustTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsReglasSustTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsReglasSustTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsReglasSustTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsReglasSustTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the actividadId of this TmsReglasSustTbl.
     * @return the actividadId
     */
    public TmsEstadosTbl getActividadId() {
        return this.actividadId;
    }

    /**
     * Sets the actividadId of this TmsReglasSustTbl to the specified value.
     * @param actividadId the new actividadId
     */
    public void setActividadId(TmsEstadosTbl actividadId) {
        this.actividadId = actividadId;
    }

    /**
     * Gets the flotillaId of this TmsReglasSustTbl.
     * @return the flotillaId
     */
    public TmsFlotillasTbl getFlotillaId() {
        return this.flotillaId;
    }

    /**
     * Sets the flotillaId of this TmsReglasSustTbl to the specified value.
     * @param flotillaId the new flotillaId
     */
    public void setFlotillaId(TmsFlotillasTbl flotillaId) {
        this.flotillaId = flotillaId;
    }

    /**
     * Gets the flotillaSustId of this TmsReglasSustTbl.
     * @return the flotillaSustId
     */
    public TmsFlotillasTbl getFlotillaSustId() {
        return this.flotillaSustId;
    }

    /**
     * Sets the flotillaSustId of this TmsReglasSustTbl to the specified value.
     * @param flotillaSustId the new flotillaSustId
     */
    public void setFlotillaSustId(TmsFlotillasTbl flotillaSustId) {
        this.flotillaSustId = flotillaSustId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.reglaSustitucionId != null ? this.reglaSustitucionId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsReglasSustTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsReglasSustTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsReglasSustTbl)) {
            return false;
        }
        TmsReglasSustTbl other = (TmsReglasSustTbl)object;
        if (this.reglaSustitucionId != other.reglaSustitucionId && (this.reglaSustitucionId == null || !this.reglaSustitucionId.equals(other.reglaSustitucionId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsreglassustit.entidad.TmsReglasSustTbl[reglaSustitucionId=" + reglaSustitucionId + "]";
    }
    
}
