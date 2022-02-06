/*
 * TmsServiciosGastosTbl.java
 *
 * Created on 13 de septiembre de 2007, 12:29 PM
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsServiciosGastosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_SERVICIOS_GASTOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsServiciosGastosTbl.findByServicioGastoId", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.servicioGastoId = :servicioGastoId"),
        @NamedQuery(name = "TmsServiciosGastosTbl.findByServicioId", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.servicioId = :servicioId"),
        @NamedQuery(name = "TmsServiciosGastosTbl.findByGastoValor", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.gastoValor = :gastoValor"),
        @NamedQuery(name = "TmsServiciosGastosTbl.findByCodigoParametro", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.codigoParametro = :codigoParametro"),
        @NamedQuery(name = "TmsServiciosGastosTbl.findByCreadoPor", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsServiciosGastosTbl.findByFechaCreacion", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsServiciosGastosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsServiciosGastosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsServiciosGastosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsServiciosGastosTbl implements Serializable {

    @Id
    @Column(name = "SERVICIO_GASTO_ID", nullable = false)
    private BigDecimal servicioGastoId;

    @Column(name = "GASTO_VALOR")
    private String gastoValor;

    @Column(name = "CODIGO_PARAMETRO")
    private String codigoParametro;

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

    @JoinColumn(name = "EMPRESA_ID", referencedColumnName = "EMPRESA_ID")
    @ManyToOne
    private TmsEmpresasTbl empresaId;

    @JoinColumn(name = "RECAUDACION_GASTO_ID", referencedColumnName = "RECAUDACION_GASTO_ID")
    @ManyToOne
    private TmsRecaudacionGastosTbl recaudacionGastoId;

    //@JoinColumn(name = "SERVICIO_ID", referencedColumnName = "SERVICIO_ID")
    //@ManyToOne
    //private TmsServiciosTbl servicioId;
    @Column(name = "SERVICIO_ID", nullable = false)
    private BigDecimal servicioId;

    
    /** Creates a new instance of TmsServiciosGastosTbl */
    public TmsServiciosGastosTbl() {
    }

    /**
     * Creates a new instance of TmsServiciosGastosTbl with the specified values.
     * @param servicioGastoId the servicioGastoId of the TmsServiciosGastosTbl
     */
    public TmsServiciosGastosTbl(BigDecimal servicioGastoId) {
        this.servicioGastoId = servicioGastoId;
    }

    /**
     * Creates a new instance of TmsServiciosGastosTbl with the specified values.
     * @param servicioGastoId the servicioGastoId of the TmsServiciosGastosTbl
     * @param creadoPor the creadoPor of the TmsServiciosGastosTbl
     * @param fechaCreacion the fechaCreacion of the TmsServiciosGastosTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsServiciosGastosTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsServiciosGastosTbl
     */
    public TmsServiciosGastosTbl(BigDecimal servicioGastoId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.servicioGastoId = servicioGastoId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the servicioGastoId of this TmsServiciosGastosTbl.
     * @return the servicioGastoId
     */
    public BigDecimal getServicioGastoId() {
        return this.servicioGastoId;
    }

    /**
     * Sets the servicioGastoId of this TmsServiciosGastosTbl to the specified value.
     * @param servicioGastoId the new servicioGastoId
     */
    public void setServicioGastoId(BigDecimal servicioGastoId) {
        this.servicioGastoId = servicioGastoId;
    }

    /**
     * Gets the gastoValor of this TmsServiciosGastosTbl.
     * @return the gastoValor
     */
    public String getGastoValor() {
        return this.gastoValor;
    }

    /**
     * Sets the gastoValor of this TmsServiciosGastosTbl to the specified value.
     * @param gastoValor the new gastoValor
     */
    public void setGastoValor(String gastoValor) {
        this.gastoValor = gastoValor;
    }

    /**
     * Gets the codigoParametro of this TmsServiciosGastosTbl.
     * @return the codigoParametro
     */
    public String getCodigoParametro() {
        return this.codigoParametro;
    }

    /**
     * Sets the codigoParametro of this TmsServiciosGastosTbl to the specified value.
     * @param codigoParametro the new codigoParametro
     */
    public void setCodigoParametro(String codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    /**
     * Gets the creadoPor of this TmsServiciosGastosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsServiciosGastosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsServiciosGastosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsServiciosGastosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsServiciosGastosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsServiciosGastosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsServiciosGastosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsServiciosGastosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the empresaId of this TmsServiciosGastosTbl.
     * @return the empresaId
     */
    public TmsEmpresasTbl getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsServiciosGastosTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(TmsEmpresasTbl empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the recaudacionGastoId of this TmsServiciosGastosTbl.
     * @return the recaudacionGastoId
     */
    public TmsRecaudacionGastosTbl getRecaudacionGastoId() {
        return this.recaudacionGastoId;
    }

    /**
     * Sets the recaudacionGastoId of this TmsServiciosGastosTbl to the specified value.
     * @param recaudacionGastoId the new recaudacionGastoId
     */
    public void setRecaudacionGastoId(TmsRecaudacionGastosTbl recaudacionGastoId) {
        this.recaudacionGastoId = recaudacionGastoId;
    }

    /**
     * Gets the servicioId of this TmsServiciosGastosTbl.
     * @return the servicioId
     */
    public BigDecimal getServicioId() {
        return this.servicioId;
    }

    /**
     * Sets the servicioId of this TmsServiciosGastosTbl to the specified value.
     * @param servicioId the new servicioId
     */
    public void setServicioId(BigDecimal servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.servicioGastoId != null ? this.servicioGastoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsServiciosGastosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsServiciosGastosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsServiciosGastosTbl)) {
            return false;
        }
        TmsServiciosGastosTbl other = (TmsServiciosGastosTbl)object;
        if (this.servicioGastoId != other.servicioGastoId && (this.servicioGastoId == null || !this.servicioGastoId.equals(other.servicioGastoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsServiciosGastosTbl[servicioGastoId=" + servicioGastoId + "]";
    }
    
}
