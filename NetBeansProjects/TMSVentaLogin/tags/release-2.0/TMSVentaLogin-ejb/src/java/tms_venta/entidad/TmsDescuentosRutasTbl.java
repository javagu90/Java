/*
 * TmsDescuentosRutasTbl.java
 *
 * Created on 24 de octubre de 2008, 06:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsDescuentosRutasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_DESCUENTOS_RUTAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByDecuentoRutaId", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.decuentoRutaId = :decuentoRutaId"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByRutaId", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.rutaId = :rutaId"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByTipoPasajeroId", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.tipoPasajeroId = :tipoPasajeroId"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByPctDescuento", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.pctDescuento = :pctDescuento"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByAdicional1", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByAdicional2", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByAdicional3", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByAdicional4", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByAdicional5", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByCreadoPor", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByFechaCreacion", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByReplicacionEstado", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsDescuentosRutasTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsDescuentosRutasTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsDescuentosRutasTbl implements Serializable {

    @Id
    @Column(name = "DECUENTO_RUTA_ID", nullable = false)
    private BigDecimal decuentoRutaId;

    @Column(name = "RUTA_ID", nullable = false)
    private BigInteger rutaId;

    @JoinColumn(name = "TIPO_PASAJERO_ID", nullable = false)
    @OneToOne
    private TmsTiposPasajeroTbl tipoPasajeroId;

    @Column(name = "PCT_DESCUENTO", nullable = false)
    private BigDecimal pctDescuento;

    @Column(name = "ADICIONAL1")
    private String adicional1;

    @Column(name = "ADICIONAL2")
    private String adicional2;

    @Column(name = "ADICIONAL3")
    private String adicional3;

    @Column(name = "ADICIONAL4")
    private String adicional4;

    @Column(name = "ADICIONAL5")
    private String adicional5;

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
    
    /** Creates a new instance of TmsDescuentosRutasTbl */
    public TmsDescuentosRutasTbl() {
    }

    /**
     * Creates a new instance of TmsDescuentosRutasTbl with the specified values.
     * @param decuentoRutaId the decuentoRutaId of the TmsDescuentosRutasTbl
     */
    public TmsDescuentosRutasTbl(BigDecimal decuentoRutaId) {
        this.decuentoRutaId = decuentoRutaId;
    }

    /**
     * Creates a new instance of TmsDescuentosRutasTbl with the specified values.
     * @param decuentoRutaId the decuentoRutaId of the TmsDescuentosRutasTbl
     * @param rutaId the rutaId of the TmsDescuentosRutasTbl
     * @param tipoPasajeroId the tipoPasajeroId of the TmsDescuentosRutasTbl
     * @param pctDescuento the pctDescuento of the TmsDescuentosRutasTbl
     * @param creadoPor the creadoPor of the TmsDescuentosRutasTbl
     * @param fechaCreacion the fechaCreacion of the TmsDescuentosRutasTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsDescuentosRutasTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsDescuentosRutasTbl
     */
    public TmsDescuentosRutasTbl(BigDecimal decuentoRutaId, BigInteger rutaId, TmsTiposPasajeroTbl tipoPasajeroId, BigDecimal pctDescuento, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.decuentoRutaId = decuentoRutaId;
        this.rutaId = rutaId;
        this.tipoPasajeroId = tipoPasajeroId;
        this.pctDescuento = pctDescuento;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the decuentoRutaId of this TmsDescuentosRutasTbl.
     * @return the decuentoRutaId
     */
    public BigDecimal getDecuentoRutaId() {
        return this.decuentoRutaId;
    }

    /**
     * Sets the decuentoRutaId of this TmsDescuentosRutasTbl to the specified value.
     * @param decuentoRutaId the new decuentoRutaId
     */
    public void setDecuentoRutaId(BigDecimal decuentoRutaId) {
        this.decuentoRutaId = decuentoRutaId;
    }

    /**
     * Gets the rutaId of this TmsDescuentosRutasTbl.
     * @return the rutaId
     */
    public BigInteger getRutaId() {
        return this.rutaId;
    }

    /**
     * Sets the rutaId of this TmsDescuentosRutasTbl to the specified value.
     * @param rutaId the new rutaId
     */
    public void setRutaId(BigInteger rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * Gets the tipoPasajeroId of this TmsDescuentosRutasTbl.
     * @return the tipoPasajeroId
     */
    public TmsTiposPasajeroTbl getTipoPasajeroId() {
        return this.tipoPasajeroId;
    }

    /**
     * Sets the tipoPasajeroId of this TmsDescuentosRutasTbl to the specified value.
     * @param tipoPasajeroId the new tipoPasajeroId
     */
    public void setTipoPasajeroId(TmsTiposPasajeroTbl tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    /**
     * Gets the pctDescuento of this TmsDescuentosRutasTbl.
     * @return the pctDescuento
     */
    public BigDecimal getPctDescuento() {
        return this.pctDescuento;
    }

    /**
     * Sets the pctDescuento of this TmsDescuentosRutasTbl to the specified value.
     * @param pctDescuento the new pctDescuento
     */
    public void setPctDescuento(BigDecimal pctDescuento) {
        this.pctDescuento = pctDescuento;
    }

    /**
     * Gets the adicional1 of this TmsDescuentosRutasTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsDescuentosRutasTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsDescuentosRutasTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsDescuentosRutasTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsDescuentosRutasTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsDescuentosRutasTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsDescuentosRutasTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsDescuentosRutasTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsDescuentosRutasTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsDescuentosRutasTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the creadoPor of this TmsDescuentosRutasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsDescuentosRutasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsDescuentosRutasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsDescuentosRutasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsDescuentosRutasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsDescuentosRutasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsDescuentosRutasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsDescuentosRutasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsDescuentosRutasTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsDescuentosRutasTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsDescuentosRutasTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsDescuentosRutasTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsDescuentosRutasTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsDescuentosRutasTbl to the specified value.
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
        hash += (this.decuentoRutaId != null ? this.decuentoRutaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsDescuentosRutasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsDescuentosRutasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsDescuentosRutasTbl)) {
            return false;
        }
        TmsDescuentosRutasTbl other = (TmsDescuentosRutasTbl)object;
        if (this.decuentoRutaId != other.decuentoRutaId && (this.decuentoRutaId == null || !this.decuentoRutaId.equals(other.decuentoRutaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.entidad.TmsDescuentosRutasTbl[decuentoRutaId=" + decuentoRutaId + "]";
    }
    
}
