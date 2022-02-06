/*
 * TmsRecaudacionLineasTbl.java
 *
 * Created on 10 de septiembre de 2007, 08:56 PM
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
 * Entity class TmsRecaudacionLineasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_RECAUDACION_LINEAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByRecaudacionLineaId", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.recaudacionLineaId = :recaudacionLineaId"),
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByTipoMovimiento", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.tipoMovimiento = :tipoMovimiento"),
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByRecaudacionValor", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.recaudacionValor = :recaudacionValor"),
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByGastoImpuesto", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.gastoImpuesto = :gastoImpuesto"),
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByCreadoPor", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByFechaCreacion", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRecaudacionLineasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRecaudacionLineasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsRecaudacionLineasTbl implements Serializable {

    @SequenceGenerator(name="TMS_REC_LIN_SEQ",sequenceName="TMS_RECAUDACION_LIN_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_REC_LIN_SEQ")
    @Id
    @Column(name = "RECAUDACION_LINEA_ID", nullable = false)
    private BigDecimal recaudacionLineaId;

    @Column(name = "TIPO_MOVIMIENTO")
    private String tipoMovimiento;

    @Column(name = "RECAUDACION_VALOR")
    private String recaudacionValor;

    @Column(name = "GASTO_IMPUESTO")
    private String gastoImpuesto;

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

    @JoinColumn(name = "RECAUDACION_GASTO_ID", referencedColumnName = "RECAUDACION_GASTO_ID")
    @ManyToOne
    private TmsRecaudacionGastosTbl recaudacionGastoId;

    @JoinColumn(name = "RECAUDACION_ID", referencedColumnName = "RECAUDACION_ID")
    @ManyToOne
    private TmsRecaudacionTbl recaudacionId;
    
    /** Creates a new instance of TmsRecaudacionLineasTbl */
    public TmsRecaudacionLineasTbl() {
    }

    /**
     * Creates a new instance of TmsRecaudacionLineasTbl with the specified values.
     * @param recaudacionLineaId the recaudacionLineaId of the TmsRecaudacionLineasTbl
     */
    public TmsRecaudacionLineasTbl(BigDecimal recaudacionLineaId) {
        this.recaudacionLineaId = recaudacionLineaId;
    }

    /**
     * Creates a new instance of TmsRecaudacionLineasTbl with the specified values.
     * @param recaudacionLineaId the recaudacionLineaId of the TmsRecaudacionLineasTbl
     * @param creadoPor the creadoPor of the TmsRecaudacionLineasTbl
     * @param fechaCreacion the fechaCreacion of the TmsRecaudacionLineasTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRecaudacionLineasTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRecaudacionLineasTbl
     */
    public TmsRecaudacionLineasTbl(BigDecimal recaudacionLineaId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.recaudacionLineaId = recaudacionLineaId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the recaudacionLineaId of this TmsRecaudacionLineasTbl.
     * @return the recaudacionLineaId
     */
    public BigDecimal getRecaudacionLineaId() {
        return this.recaudacionLineaId;
    }

    /**
     * Sets the recaudacionLineaId of this TmsRecaudacionLineasTbl to the specified value.
     * @param recaudacionLineaId the new recaudacionLineaId
     */
    public void setRecaudacionLineaId(BigDecimal recaudacionLineaId) {
        this.recaudacionLineaId = recaudacionLineaId;
    }

    /**
     * Gets the tipoMovimiento of this TmsRecaudacionLineasTbl.
     * @return the tipoMovimiento
     */
    public String getTipoMovimiento() {
        return this.tipoMovimiento;
    }

    /**
     * Sets the tipoMovimiento of this TmsRecaudacionLineasTbl to the specified value.
     * @param tipoMovimiento the new tipoMovimiento
     */
    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * Gets the recaudacionValor of this TmsRecaudacionLineasTbl.
     * @return the recaudacionValor
     */
    public String getRecaudacionValor() {
        return this.recaudacionValor;
    }

    /**
     * Sets the recaudacionValor of this TmsRecaudacionLineasTbl to the specified value.
     * @param recaudacionValor the new recaudacionValor
     */
    public void setRecaudacionValor(String recaudacionValor) {
        this.recaudacionValor = recaudacionValor;
    }

    /**
     * Gets the gastoImpuesto of this TmsRecaudacionLineasTbl.
     * @return the gastoImpuesto
     */
    public String getGastoImpuesto() {
        return this.gastoImpuesto;
    }

    /**
     * Sets the gastoImpuesto of this TmsRecaudacionLineasTbl to the specified value.
     * @param gastoImpuesto the new gastoImpuesto
     */
    public void setGastoImpuesto(String gastoImpuesto) {
        this.gastoImpuesto = gastoImpuesto;
    }

    /**
     * Gets the creadoPor of this TmsRecaudacionLineasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRecaudacionLineasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRecaudacionLineasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRecaudacionLineasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRecaudacionLineasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRecaudacionLineasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRecaudacionLineasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRecaudacionLineasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the recaudacionGastoId of this TmsRecaudacionLineasTbl.
     * @return the recaudacionGastoId
     */
    public TmsRecaudacionGastosTbl getRecaudacionGastoId() {
        return this.recaudacionGastoId;
    }

    /**
     * Sets the recaudacionGastoId of this TmsRecaudacionLineasTbl to the specified value.
     * @param recaudacionGastoId the new recaudacionGastoId
     */
    public void setRecaudacionGastoId(TmsRecaudacionGastosTbl recaudacionGastoId) {
        this.recaudacionGastoId = recaudacionGastoId;
    }

    /**
     * Gets the recaudacionId of this TmsRecaudacionLineasTbl.
     * @return the recaudacionId
     */
    public TmsRecaudacionTbl getRecaudacionId() {
        return this.recaudacionId;
    }

    /**
     * Sets the recaudacionId of this TmsRecaudacionLineasTbl to the specified value.
     * @param recaudacionId the new recaudacionId
     */
    public void setRecaudacionId(TmsRecaudacionTbl recaudacionId) {
        this.recaudacionId = recaudacionId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.recaudacionLineaId != null ? this.recaudacionLineaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRecaudacionLineasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRecaudacionLineasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRecaudacionLineasTbl)) {
            return false;
        }
        TmsRecaudacionLineasTbl other = (TmsRecaudacionLineasTbl)object;
        if (this.recaudacionLineaId != other.recaudacionLineaId && (this.recaudacionLineaId == null || !this.recaudacionLineaId.equals(other.recaudacionLineaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsRecaudacionLineasTbl[recaudacionLineaId=" + recaudacionLineaId + "]";
    }
    
}
