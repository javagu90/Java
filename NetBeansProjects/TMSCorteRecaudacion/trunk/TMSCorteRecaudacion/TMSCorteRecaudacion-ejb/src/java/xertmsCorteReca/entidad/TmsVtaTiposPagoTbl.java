/*
 * TmsVtaTiposPagoTbl.java
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
 * Entity class TmsVtaTiposPagoTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_VTA_TIPOS_PAGO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByTipoPagoId", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.tipoPagoId = :tipoPagoId"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByNombre", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.nombre = :nombre"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByClave", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.clave = :clave"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByDescripcion", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByFechaDesde", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.fechaDesde = :fechaDesde"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByFechaHasta", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.fechaHasta = :fechaHasta"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByCreadoPor", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByFechaCreacion", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsVtaTiposPagoTbl implements Serializable {

    @Id
    @Column(name = "TIPO_PAGO_ID", nullable = false)
    private BigDecimal tipoPagoId;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CLAVE", nullable = false)
    private String clave;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA_DESDE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;

    @Column(name = "CREADO_POR", nullable = false)
    private int creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @OneToMany(mappedBy = "tipoPagoId")
    private Collection<TmsRecoleccionesTbl> tmsRecoleccionesTblCollection;
    
    /** Creates a new instance of TmsVtaTiposPagoTbl */
    public TmsVtaTiposPagoTbl() {
    }

    /**
     * Creates a new instance of TmsVtaTiposPagoTbl with the specified values.
     * @param tipoPagoId the tipoPagoId of the TmsVtaTiposPagoTbl
     */
    public TmsVtaTiposPagoTbl(BigDecimal tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    /**
     * Creates a new instance of TmsVtaTiposPagoTbl with the specified values.
     * @param tipoPagoId the tipoPagoId of the TmsVtaTiposPagoTbl
     * @param nombre the nombre of the TmsVtaTiposPagoTbl
     * @param clave the clave of the TmsVtaTiposPagoTbl
     * @param fechaDesde the fechaDesde of the TmsVtaTiposPagoTbl
     * @param creadoPor the creadoPor of the TmsVtaTiposPagoTbl
     * @param fechaCreacion the fechaCreacion of the TmsVtaTiposPagoTbl
     */
    public TmsVtaTiposPagoTbl(BigDecimal tipoPagoId, String nombre, String clave, Date fechaDesde, int creadoPor, Date fechaCreacion) {
        this.tipoPagoId = tipoPagoId;
        this.nombre = nombre;
        this.clave = clave;
        this.fechaDesde = fechaDesde;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the tipoPagoId of this TmsVtaTiposPagoTbl.
     * @return the tipoPagoId
     */
    public BigDecimal getTipoPagoId() {
        return this.tipoPagoId;
    }

    /**
     * Sets the tipoPagoId of this TmsVtaTiposPagoTbl to the specified value.
     * @param tipoPagoId the new tipoPagoId
     */
    public void setTipoPagoId(BigDecimal tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    /**
     * Gets the nombre of this TmsVtaTiposPagoTbl.
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre of this TmsVtaTiposPagoTbl to the specified value.
     * @param nombre the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the clave of this TmsVtaTiposPagoTbl.
     * @return the clave
     */
    public String getClave() {
        return this.clave;
    }

    /**
     * Sets the clave of this TmsVtaTiposPagoTbl to the specified value.
     * @param clave the new clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Gets the descripcion of this TmsVtaTiposPagoTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsVtaTiposPagoTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the fechaDesde of this TmsVtaTiposPagoTbl.
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this TmsVtaTiposPagoTbl to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this TmsVtaTiposPagoTbl.
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this TmsVtaTiposPagoTbl to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the creadoPor of this TmsVtaTiposPagoTbl.
     * @return the creadoPor
     */
    public int getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsVtaTiposPagoTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsVtaTiposPagoTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsVtaTiposPagoTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsVtaTiposPagoTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsVtaTiposPagoTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsVtaTiposPagoTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsVtaTiposPagoTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsRecoleccionesTblCollection of this TmsVtaTiposPagoTbl.
     * @return the tmsRecoleccionesTblCollection
     */
    public Collection<TmsRecoleccionesTbl> getTmsRecoleccionesTblCollection() {
        return this.tmsRecoleccionesTblCollection;
    }

    /**
     * Sets the tmsRecoleccionesTblCollection of this TmsVtaTiposPagoTbl to the specified value.
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
        hash += (this.tipoPagoId != null ? this.tipoPagoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsVtaTiposPagoTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsVtaTiposPagoTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsVtaTiposPagoTbl)) {
            return false;
        }
        TmsVtaTiposPagoTbl other = (TmsVtaTiposPagoTbl)object;
        if (this.tipoPagoId != other.tipoPagoId && (this.tipoPagoId == null || !this.tipoPagoId.equals(other.tipoPagoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "xertms.entidad.TmsVtaTiposPagoTbl[tipoPagoId=" + tipoPagoId + "]";
    }
    
}
