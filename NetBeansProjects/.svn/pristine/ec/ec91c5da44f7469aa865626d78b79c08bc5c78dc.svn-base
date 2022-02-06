/*
 * TmsAsientosNoDispTbl.java
 *
 * Created on 7 de diciembre de 2007, 07:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsBolNoDisp.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsAsientosNoDispTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ASIENTOS_NO_DISP_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByAsientoNoDisponibleId", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.asientoNoDisponibleId = :asientoNoDisponibleId"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByCorridaId", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.corridaId = :corridaId"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByNoAsiento", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.noAsiento = :noAsiento"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByEstadoAsiento", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.estadoAsiento = :estadoAsiento"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByTipoPasajero", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.tipoPasajero = :tipoPasajero"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByCreadoPor", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByFechaCreacion", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByReplicacionEstado", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsAsientosNoDispTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsAsientosNoDispTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsAsientosNoDispTbl implements Serializable {

    @Id
    @Column(name = "ASIENTO_NO_DISPONIBLE_ID", nullable = false)
    private BigDecimal asientoNoDisponibleId;

    @Column(name = "CORRIDA_ID", nullable = false)
    private BigInteger corridaId;

    @Column(name = "NO_ASIENTO", nullable = false)
    private BigInteger noAsiento;

    @Column(name = "ESTADO_ASIENTO", nullable = false)
    private String estadoAsiento;

    @Column(name = "TIPO_PASAJERO")
    private String tipoPasajero;

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

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;
    
    /** Creates a new instance of TmsAsientosNoDispTbl */
    public TmsAsientosNoDispTbl() {
    }

    /**
     * Creates a new instance of TmsAsientosNoDispTbl with the specified values.
     * @param asientoNoDisponibleId the asientoNoDisponibleId of the TmsAsientosNoDispTbl
     */
    public TmsAsientosNoDispTbl(BigDecimal asientoNoDisponibleId) {
        this.asientoNoDisponibleId = asientoNoDisponibleId;
    }

    /**
     * Creates a new instance of TmsAsientosNoDispTbl with the specified values.
     * @param asientoNoDisponibleId the asientoNoDisponibleId of the TmsAsientosNoDispTbl
     * @param corridaId the corridaId of the TmsAsientosNoDispTbl
     * @param noAsiento the noAsiento of the TmsAsientosNoDispTbl
     * @param estadoAsiento the estadoAsiento of the TmsAsientosNoDispTbl
     * @param creadoPor the creadoPor of the TmsAsientosNoDispTbl
     * @param fechaCreacion the fechaCreacion of the TmsAsientosNoDispTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsAsientosNoDispTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsAsientosNoDispTbl
     */
    public TmsAsientosNoDispTbl(BigDecimal asientoNoDisponibleId, BigInteger corridaId, BigInteger noAsiento, String estadoAsiento, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.asientoNoDisponibleId = asientoNoDisponibleId;
        this.corridaId = corridaId;
        this.noAsiento = noAsiento;
        this.estadoAsiento = estadoAsiento;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the asientoNoDisponibleId of this TmsAsientosNoDispTbl.
     * @return the asientoNoDisponibleId
     */
    public BigDecimal getAsientoNoDisponibleId() {
        return this.asientoNoDisponibleId;
    }

    /**
     * Sets the asientoNoDisponibleId of this TmsAsientosNoDispTbl to the specified value.
     * @param asientoNoDisponibleId the new asientoNoDisponibleId
     */
    public void setAsientoNoDisponibleId(BigDecimal asientoNoDisponibleId) {
        this.asientoNoDisponibleId = asientoNoDisponibleId;
    }

    /**
     * Gets the corridaId of this TmsAsientosNoDispTbl.
     * @return the corridaId
     */
    public BigInteger getCorridaId() {
        return this.corridaId;
    }

    /**
     * Sets the corridaId of this TmsAsientosNoDispTbl to the specified value.
     * @param corridaId the new corridaId
     */
    public void setCorridaId(BigInteger corridaId) {
        this.corridaId = corridaId;
    }

    /**
     * Gets the noAsiento of this TmsAsientosNoDispTbl.
     * @return the noAsiento
     */
    public BigInteger getNoAsiento() {
        return this.noAsiento;
    }

    /**
     * Sets the noAsiento of this TmsAsientosNoDispTbl to the specified value.
     * @param noAsiento the new noAsiento
     */
    public void setNoAsiento(BigInteger noAsiento) {
        this.noAsiento = noAsiento;
    }

    /**
     * Gets the estadoAsiento of this TmsAsientosNoDispTbl.
     * @return the estadoAsiento
     */
    public String getEstadoAsiento() {
        return this.estadoAsiento;
    }

    /**
     * Sets the estadoAsiento of this TmsAsientosNoDispTbl to the specified value.
     * @param estadoAsiento the new estadoAsiento
     */
    public void setEstadoAsiento(String estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }

    /**
     * Gets the tipoPasajero of this TmsAsientosNoDispTbl.
     * @return the tipoPasajero
     */
    public String getTipoPasajero() {
        return this.tipoPasajero;
    }

    /**
     * Sets the tipoPasajero of this TmsAsientosNoDispTbl to the specified value.
     * @param tipoPasajero the new tipoPasajero
     */
    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    /**
     * Gets the creadoPor of this TmsAsientosNoDispTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsAsientosNoDispTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsAsientosNoDispTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsAsientosNoDispTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsAsientosNoDispTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsAsientosNoDispTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsAsientosNoDispTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsAsientosNoDispTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsAsientosNoDispTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsAsientosNoDispTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsAsientosNoDispTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsAsientosNoDispTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsAsientosNoDispTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsAsientosNoDispTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.asientoNoDisponibleId != null ? this.asientoNoDisponibleId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsAsientosNoDispTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsAsientosNoDispTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsAsientosNoDispTbl)) {
            return false;
        }
        TmsAsientosNoDispTbl other = (TmsAsientosNoDispTbl)object;
        if (this.asientoNoDisponibleId != other.asientoNoDisponibleId && (this.asientoNoDisponibleId == null || !this.asientoNoDisponibleId.equals(other.asientoNoDisponibleId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsBolNoDisp.entidad.TmsAsientosNoDispTbl[asientoNoDisponibleId=" + asientoNoDisponibleId + "]";
    }
    
}
