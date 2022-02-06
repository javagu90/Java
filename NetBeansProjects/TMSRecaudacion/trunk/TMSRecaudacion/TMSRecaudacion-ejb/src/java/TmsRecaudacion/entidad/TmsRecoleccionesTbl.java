/*
 * TmsRecoleccionesTbl.java
 *
 * Created on 3 de noviembre de 2007, 03:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.entidad;

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
 * Entity class TmsRecoleccionesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_RECOLECCIONES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRecoleccionesTbl.findByRecoleccionId", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.recoleccionId = :recoleccionId"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findBySesionActividadId", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.sesionActividadId = :sesionActividadId"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByReferencia", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.referencia = :referencia"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByCantidad", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.cantidad = :cantidad"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByMonto", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.monto = :monto"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByAutorizadoPor", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByCreadoPor", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByFechaCreacion", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsRecoleccionesTbl implements Serializable {

    @SequenceGenerator(name="TMS_RECOLEC_SEQ",sequenceName="TMS_RECOLECCIONES_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_RECOLEC_SEQ")
    @Id
    @Column(name = "RECOLECCION_ID", nullable = false)
    private BigDecimal recoleccionId;

    @Column(name = "SESION_ACTIVIDAD_ID", nullable = false)
    private BigInteger sesionActividadId;

    @Column(name = "REFERENCIA")
    private String referencia;

    @Column(name = "CANTIDAD")
    private BigInteger cantidad;

    @Column(name = "MONTO")
    private BigDecimal monto;

    @Column(name = "AUTORIZADO_POR", nullable = false)
    private BigInteger autorizadoPor;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @JoinColumn(name = "TIPO_PAGO_ID", referencedColumnName = "TIPO_PAGO_ID")
    @ManyToOne
    private TmsVtaTiposPagoTbl tipoPagoId;
    
    /** Creates a new instance of TmsRecoleccionesTbl */
    public TmsRecoleccionesTbl() {
    }

    /**
     * Creates a new instance of TmsRecoleccionesTbl with the specified values.
     * @param recoleccionId the recoleccionId of the TmsRecoleccionesTbl
     */
    public TmsRecoleccionesTbl(BigDecimal recoleccionId) {
        this.recoleccionId = recoleccionId;
    }

    /**
     * Creates a new instance of TmsRecoleccionesTbl with the specified values.
     * @param recoleccionId the recoleccionId of the TmsRecoleccionesTbl
     * @param sesionActividadId the sesionActividadId of the TmsRecoleccionesTbl
     * @param autorizadoPor the autorizadoPor of the TmsRecoleccionesTbl
     * @param creadoPor the creadoPor of the TmsRecoleccionesTbl
     * @param fechaCreacion the fechaCreacion of the TmsRecoleccionesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRecoleccionesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRecoleccionesTbl
     */
    public TmsRecoleccionesTbl(BigDecimal recoleccionId, BigInteger sesionActividadId, BigInteger autorizadoPor, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.recoleccionId = recoleccionId;
        this.sesionActividadId = sesionActividadId;
        this.autorizadoPor = autorizadoPor;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the recoleccionId of this TmsRecoleccionesTbl.
     * @return the recoleccionId
     */
    public BigDecimal getRecoleccionId() {
        return this.recoleccionId;
    }

    /**
     * Sets the recoleccionId of this TmsRecoleccionesTbl to the specified value.
     * @param recoleccionId the new recoleccionId
     */
    public void setRecoleccionId(BigDecimal recoleccionId) {
        this.recoleccionId = recoleccionId;
    }

    /**
     * Gets the sesionActividadId of this TmsRecoleccionesTbl.
     * @return the sesionActividadId
     */
    public BigInteger getSesionActividadId() {
        return this.sesionActividadId;
    }

    /**
     * Sets the sesionActividadId of this TmsRecoleccionesTbl to the specified value.
     * @param sesionActividadId the new sesionActividadId
     */
    public void setSesionActividadId(BigInteger sesionActividadId) {
        this.sesionActividadId = sesionActividadId;
    }

    /**
     * Gets the referencia of this TmsRecoleccionesTbl.
     * @return the referencia
     */
    public String getReferencia() {
        return this.referencia;
    }

    /**
     * Sets the referencia of this TmsRecoleccionesTbl to the specified value.
     * @param referencia the new referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Gets the cantidad of this TmsRecoleccionesTbl.
     * @return the cantidad
     */
    public BigInteger getCantidad() {
        return this.cantidad;
    }

    /**
     * Sets the cantidad of this TmsRecoleccionesTbl to the specified value.
     * @param cantidad the new cantidad
     */
    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the monto of this TmsRecoleccionesTbl.
     * @return the monto
     */
    public BigDecimal getMonto() {
        return this.monto;
    }

    /**
     * Sets the monto of this TmsRecoleccionesTbl to the specified value.
     * @param monto the new monto
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Gets the autorizadoPor of this TmsRecoleccionesTbl.
     * @return the autorizadoPor
     */
    public BigInteger getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsRecoleccionesTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(BigInteger autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    /**
     * Gets the creadoPor of this TmsRecoleccionesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRecoleccionesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRecoleccionesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRecoleccionesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRecoleccionesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRecoleccionesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRecoleccionesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRecoleccionesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tipoPagoId of this TmsRecoleccionesTbl.
     * @return the tipoPagoId
     */
    public TmsVtaTiposPagoTbl getTipoPagoId() {
        return this.tipoPagoId;
    }

    /**
     * Sets the tipoPagoId of this TmsRecoleccionesTbl to the specified value.
     * @param tipoPagoId the new tipoPagoId
     */
    public void setTipoPagoId(TmsVtaTiposPagoTbl tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.recoleccionId != null ? this.recoleccionId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRecoleccionesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRecoleccionesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRecoleccionesTbl)) {
            return false;
        }
        TmsRecoleccionesTbl other = (TmsRecoleccionesTbl)object;
        if (this.recoleccionId != other.recoleccionId && (this.recoleccionId == null || !this.recoleccionId.equals(other.recoleccionId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsRecoleccionesTbl[recoleccionId=" + recoleccionId + "]";
    }
    
}
