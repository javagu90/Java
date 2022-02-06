/*
 * TmsTiposPasajeroTbl.java
 *
 * Created on 15 de agosto de 2007, 05:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
 * Entity class TmsTiposPasajeroTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_TIPOS_PASAJERO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByTipoPasajeroId", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.tipoPasajeroId = :tipoPasajeroId"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByNombreTipo", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.nombreTipo = :nombreTipo"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByLetraTipo", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.letraTipo = :letraTipo"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByPctDescuento", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.pctDescuento = :pctDescuento"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByFechaDesde", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.fechaDesde = :fechaDesde"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByFechaHasta", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.fechaHasta = :fechaHasta"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByCreadoPor", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByFechaCreacion", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsTiposPasajeroTbl implements Serializable {

    @Id
    @Column(name = "TIPO_PASAJERO_ID", nullable = false)
    private BigDecimal tipoPasajeroId;

    @Column(name = "NOMBRE_TIPO", nullable = false)
    private String nombreTipo;

    @Column(name = "LETRA_TIPO")
    private String letraTipo;

    @Column(name = "PCT_DESCUENTO", nullable = false)
    private BigDecimal pctDescuento;

    @Column(name = "FECHA_DESDE")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;

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

    @OneToMany(mappedBy = "tipoPasajeroId")
    private Collection<TmsRecoleccionesTbl> tmsRecoleccionesTblCollection;
    
    /** Creates a new instance of TmsTiposPasajeroTbl */
    public TmsTiposPasajeroTbl() {
    }

    /**
     * Creates a new instance of TmsTiposPasajeroTbl with the specified values.
     * @param tipoPasajeroId the tipoPasajeroId of the TmsTiposPasajeroTbl
     */
    public TmsTiposPasajeroTbl(BigDecimal tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    /**
     * Creates a new instance of TmsTiposPasajeroTbl with the specified values.
     * @param tipoPasajeroId the tipoPasajeroId of the TmsTiposPasajeroTbl
     * @param nombreTipo the nombreTipo of the TmsTiposPasajeroTbl
     * @param pctDescuento the pctDescuento of the TmsTiposPasajeroTbl
     */
    public TmsTiposPasajeroTbl(BigDecimal tipoPasajeroId, String nombreTipo, BigDecimal pctDescuento) {
        this.tipoPasajeroId = tipoPasajeroId;
        this.nombreTipo = nombreTipo;
        this.pctDescuento = pctDescuento;
    }

    /**
     * Gets the tipoPasajeroId of this TmsTiposPasajeroTbl.
     * @return the tipoPasajeroId
     */
    public BigDecimal getTipoPasajeroId() {
        return this.tipoPasajeroId;
    }

    /**
     * Sets the tipoPasajeroId of this TmsTiposPasajeroTbl to the specified value.
     * @param tipoPasajeroId the new tipoPasajeroId
     */
    public void setTipoPasajeroId(BigDecimal tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    /**
     * Gets the nombreTipo of this TmsTiposPasajeroTbl.
     * @return the nombreTipo
     */
    public String getNombreTipo() {
        return this.nombreTipo;
    }

    /**
     * Sets the nombreTipo of this TmsTiposPasajeroTbl to the specified value.
     * @param nombreTipo the new nombreTipo
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the letraTipo of this TmsTiposPasajeroTbl.
     * @return the letraTipo
     */
    public String getLetraTipo() {
        return this.letraTipo;
    }

    /**
     * Sets the letraTipo of this TmsTiposPasajeroTbl to the specified value.
     * @param letraTipo the new letraTipo
     */
    public void setLetraTipo(String letraTipo) {
        this.letraTipo = letraTipo;
    }

    /**
     * Gets the pctDescuento of this TmsTiposPasajeroTbl.
     * @return the pctDescuento
     */
    public BigDecimal getPctDescuento() {
        return this.pctDescuento;
    }

    /**
     * Sets the pctDescuento of this TmsTiposPasajeroTbl to the specified value.
     * @param pctDescuento the new pctDescuento
     */
    public void setPctDescuento(BigDecimal pctDescuento) {
        this.pctDescuento = pctDescuento;
    }

    /**
     * Gets the fechaDesde of this TmsTiposPasajeroTbl.
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this TmsTiposPasajeroTbl.
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the creadoPor of this TmsTiposPasajeroTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTiposPasajeroTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTiposPasajeroTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTiposPasajeroTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTiposPasajeroTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTiposPasajeroTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTiposPasajeroTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsRecoleccionesTblCollection of this TmsTiposPasajeroTbl.
     * @return the tmsRecoleccionesTblCollection
     */
    public Collection<TmsRecoleccionesTbl> getTmsRecoleccionesTblCollection() {
        return this.tmsRecoleccionesTblCollection;
    }

    /**
     * Sets the tmsRecoleccionesTblCollection of this TmsTiposPasajeroTbl to the specified value.
     * @param tmsRecoleccionesTblCollection the new tmsRecoleccionesTblCollection
     */
    public void setTmsRecoleccionesTblCollection(Collection<TmsRecoleccionesTbl> tmsRecoleccionesTblCollection) {
        this.tmsRecoleccionesTblCollection = tmsRecoleccionesTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.tipoPasajeroId != null ? this.tipoPasajeroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTiposPasajeroTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTiposPasajeroTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTiposPasajeroTbl)) {
            return false;
        }
        TmsTiposPasajeroTbl other = (TmsTiposPasajeroTbl)object;
        if (this.tipoPasajeroId != other.tipoPasajeroId && (this.tipoPasajeroId == null || !this.tipoPasajeroId.equals(other.tipoPasajeroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "xertms.entidad.TmsTiposPasajeroTbl[tipoPasajeroId=" + tipoPasajeroId + "]";
    }
    
}
