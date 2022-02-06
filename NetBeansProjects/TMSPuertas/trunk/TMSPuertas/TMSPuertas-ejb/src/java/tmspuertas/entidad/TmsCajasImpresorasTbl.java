/*
 * TmsCajasImpresorasTbl.java
 *
 * Created on 26 de febrero de 2008, 01:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.entidad;

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
 * Entity class TmsCajasImpresorasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_CAJAS_IMPRESORAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByCajaImpresoraId", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.cajaImpresoraId = :cajaImpresoraId"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByFormatoBoletoId", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.formatoBoletoId = :formatoBoletoId"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByActividadImpresora", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.actividadImpresora = :actividadImpresora"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByCreadoPor", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByFechaCreacion", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findBySalidaImpresion", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.salidaImpresion = :salidaImpresion"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByReplicacionEstado", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsCajasImpresorasTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsCajasImpresorasTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsCajasImpresorasTbl implements Serializable {

    @Id
    @Column(name = "CAJA_IMPRESORA_ID", nullable = false)
    private BigDecimal cajaImpresoraId;

    @Column(name = "FORMATO_BOLETO_ID", nullable = false)
    private BigInteger formatoBoletoId;

    @Column(name = "ACTIVIDAD_IMPRESORA", nullable = false)
    private String actividadImpresora;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @Column(name = "SALIDA_IMPRESION")
    private String salidaImpresion;

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;

    @JoinColumn(name = "CAJA_ID", referencedColumnName = "CAJA_ID")
    @ManyToOne
    private TmsCajasTbl cajaId;

    @JoinColumn(name = "EMPRESA_ID", referencedColumnName = "EMPRESA_ID")
    @ManyToOne
    private TmsEmpresasTbl empresaId;

    @JoinColumn(name = "IMPRESORA_ID", referencedColumnName = "IMPRESORA_ID")
    @ManyToOne
    private TmsImpresorasTbl impresoraId;
    
    /** Creates a new instance of TmsCajasImpresorasTbl */
    public TmsCajasImpresorasTbl() {
    }

    /**
     * Creates a new instance of TmsCajasImpresorasTbl with the specified values.
     * @param cajaImpresoraId the cajaImpresoraId of the TmsCajasImpresorasTbl
     */
    public TmsCajasImpresorasTbl(BigDecimal cajaImpresoraId) {
        this.cajaImpresoraId = cajaImpresoraId;
    }

    /**
     * Creates a new instance of TmsCajasImpresorasTbl with the specified values.
     * @param cajaImpresoraId the cajaImpresoraId of the TmsCajasImpresorasTbl
     * @param formatoBoletoId the formatoBoletoId of the TmsCajasImpresorasTbl
     * @param actividadImpresora the actividadImpresora of the TmsCajasImpresorasTbl
     * @param creadoPor the creadoPor of the TmsCajasImpresorasTbl
     * @param fechaCreacion the fechaCreacion of the TmsCajasImpresorasTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsCajasImpresorasTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsCajasImpresorasTbl
     */
    public TmsCajasImpresorasTbl(BigDecimal cajaImpresoraId, BigInteger formatoBoletoId, String actividadImpresora, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.cajaImpresoraId = cajaImpresoraId;
        this.formatoBoletoId = formatoBoletoId;
        this.actividadImpresora = actividadImpresora;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the cajaImpresoraId of this TmsCajasImpresorasTbl.
     * @return the cajaImpresoraId
     */
    public BigDecimal getCajaImpresoraId() {
        return this.cajaImpresoraId;
    }

    /**
     * Sets the cajaImpresoraId of this TmsCajasImpresorasTbl to the specified value.
     * @param cajaImpresoraId the new cajaImpresoraId
     */
    public void setCajaImpresoraId(BigDecimal cajaImpresoraId) {
        this.cajaImpresoraId = cajaImpresoraId;
    }

    /**
     * Gets the formatoBoletoId of this TmsCajasImpresorasTbl.
     * @return the formatoBoletoId
     */
    public BigInteger getFormatoBoletoId() {
        return this.formatoBoletoId;
    }

    /**
     * Sets the formatoBoletoId of this TmsCajasImpresorasTbl to the specified value.
     * @param formatoBoletoId the new formatoBoletoId
     */
    public void setFormatoBoletoId(BigInteger formatoBoletoId) {
        this.formatoBoletoId = formatoBoletoId;
    }

    /**
     * Gets the actividadImpresora of this TmsCajasImpresorasTbl.
     * @return the actividadImpresora
     */
    public String getActividadImpresora() {
        return this.actividadImpresora;
    }

    /**
     * Sets the actividadImpresora of this TmsCajasImpresorasTbl to the specified value.
     * @param actividadImpresora the new actividadImpresora
     */
    public void setActividadImpresora(String actividadImpresora) {
        this.actividadImpresora = actividadImpresora;
    }

    /**
     * Gets the creadoPor of this TmsCajasImpresorasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsCajasImpresorasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsCajasImpresorasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsCajasImpresorasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsCajasImpresorasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsCajasImpresorasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsCajasImpresorasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsCajasImpresorasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the salidaImpresion of this TmsCajasImpresorasTbl.
     * @return the salidaImpresion
     */
    public String getSalidaImpresion() {
        return this.salidaImpresion;
    }

    /**
     * Sets the salidaImpresion of this TmsCajasImpresorasTbl to the specified value.
     * @param salidaImpresion the new salidaImpresion
     */
    public void setSalidaImpresion(String salidaImpresion) {
        this.salidaImpresion = salidaImpresion;
    }

    /**
     * Gets the replicacionEstado of this TmsCajasImpresorasTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsCajasImpresorasTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsCajasImpresorasTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsCajasImpresorasTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsCajasImpresorasTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsCajasImpresorasTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the cajaId of this TmsCajasImpresorasTbl.
     * @return the cajaId
     */
    public TmsCajasTbl getCajaId() {
        return this.cajaId;
    }

    /**
     * Sets the cajaId of this TmsCajasImpresorasTbl to the specified value.
     * @param cajaId the new cajaId
     */
    public void setCajaId(TmsCajasTbl cajaId) {
        this.cajaId = cajaId;
    }

    /**
     * Gets the empresaId of this TmsCajasImpresorasTbl.
     * @return the empresaId
     */
    public TmsEmpresasTbl getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsCajasImpresorasTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(TmsEmpresasTbl empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the impresoraId of this TmsCajasImpresorasTbl.
     * @return the impresoraId
     */
    public TmsImpresorasTbl getImpresoraId() {
        return this.impresoraId;
    }

    /**
     * Sets the impresoraId of this TmsCajasImpresorasTbl to the specified value.
     * @param impresoraId the new impresoraId
     */
    public void setImpresoraId(TmsImpresorasTbl impresoraId) {
        this.impresoraId = impresoraId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.cajaImpresoraId != null ? this.cajaImpresoraId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsCajasImpresorasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsCajasImpresorasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsCajasImpresorasTbl)) {
            return false;
        }
        TmsCajasImpresorasTbl other = (TmsCajasImpresorasTbl)object;
        if (this.cajaImpresoraId != other.cajaImpresoraId && (this.cajaImpresoraId == null || !this.cajaImpresoraId.equals(other.cajaImpresoraId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuertas.entidad.TmsCajasImpresorasTbl[cajaImpresoraId=" + cajaImpresoraId + "]";
    }
    
}
