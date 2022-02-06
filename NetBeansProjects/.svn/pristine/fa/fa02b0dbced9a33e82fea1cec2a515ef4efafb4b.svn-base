package tms_venta.entidad;

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
 * Entity class TmsBoletosReferenciadosTbl
 * 
 * @author asolis
 */
@Entity
@Table(name = "TMS_BOLETOS_REFERENCIADOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByBoletoReferenciadoId", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.boletoReferenciadoId = :boletoReferenciadoId"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByBoletoId", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.boletoId = :boletoId"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByBoletoClave", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.boletoClave = :boletoClave"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByBoletoReferenciaNombre", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.boletoReferenciaNombre = :boletoReferenciaNombre"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByBoletoTransaccionId", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.boletoTransaccionId = :boletoTransaccionId"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByEstadoReferencia", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.estadoReferencia = :estadoReferencia"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByAdicional1", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByAdicional2", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByAdicional3", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByAdicional4", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByAdicional5", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByCreadoPor", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByFechaCreacion", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByReplicacionEstado", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsBoletosReferenciadosTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsBoletosReferenciadosTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsBoletosReferenciadosTbl implements Serializable {

    @Id
    @Column(name = "BOLETO_REFERENCIADO_ID", nullable = false)
    private BigDecimal boletoReferenciadoId;

    @Column(name = "BOLETO_ID", nullable = false)
    private BigInteger boletoId;

    @Column(name = "BOLETO_CLAVE", nullable = false)
    private String boletoClave;

    @Column(name = "BOLETO_REFERENCIA_NOMBRE")
    private String boletoReferenciaNombre;

    @Column(name = "BOLETO_TRANSACCION_ID")
    private BigInteger boletoTransaccionId;

    @Column(name = "ESTADO_REFERENCIA", nullable = false)
    private String estadoReferencia;

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
    
    /** Creates a new instance of TmsBoletosReferenciadosTbl */
    public TmsBoletosReferenciadosTbl() {
    }

    /**
     * Creates a new instance of TmsBoletosReferenciadosTbl with the specified values.
     * @param boletoReferenciadoId the boletoReferenciadoId of the TmsBoletosReferenciadosTbl
     */
    public TmsBoletosReferenciadosTbl(BigDecimal boletoReferenciadoId) {
        this.boletoReferenciadoId = boletoReferenciadoId;
    }

    /**
     * Creates a new instance of TmsBoletosReferenciadosTbl with the specified values.
     * @param boletoReferenciadoId the boletoReferenciadoId of the TmsBoletosReferenciadosTbl
     * @param boletoId the boletoId of the TmsBoletosReferenciadosTbl
     * @param boletoClave the boletoClave of the TmsBoletosReferenciadosTbl
     * @param estadoReferencia the estadoReferencia of the TmsBoletosReferenciadosTbl
     * @param creadoPor the creadoPor of the TmsBoletosReferenciadosTbl
     * @param fechaCreacion the fechaCreacion of the TmsBoletosReferenciadosTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsBoletosReferenciadosTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsBoletosReferenciadosTbl
     */
    public TmsBoletosReferenciadosTbl(BigDecimal boletoReferenciadoId, BigInteger boletoId, String boletoClave, String estadoReferencia, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.boletoReferenciadoId = boletoReferenciadoId;
        this.boletoId = boletoId;
        this.boletoClave = boletoClave;
        this.estadoReferencia = estadoReferencia;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the boletoReferenciadoId of this TmsBoletosReferenciadosTbl.
     * @return the boletoReferenciadoId
     */
    public BigDecimal getBoletoReferenciadoId() {
        return this.boletoReferenciadoId;
    }

    /**
     * Sets the boletoReferenciadoId of this TmsBoletosReferenciadosTbl to the specified value.
     * @param boletoReferenciadoId the new boletoReferenciadoId
     */
    public void setBoletoReferenciadoId(BigDecimal boletoReferenciadoId) {
        this.boletoReferenciadoId = boletoReferenciadoId;
    }

    /**
     * Gets the boletoId of this TmsBoletosReferenciadosTbl.
     * @return the boletoId
     */
    public BigInteger getBoletoId() {
        return this.boletoId;
    }

    /**
     * Sets the boletoId of this TmsBoletosReferenciadosTbl to the specified value.
     * @param boletoId the new boletoId
     */
    public void setBoletoId(BigInteger boletoId) {
        this.boletoId = boletoId;
    }

    /**
     * Gets the boletoClave of this TmsBoletosReferenciadosTbl.
     * @return the boletoClave
     */
    public String getBoletoClave() {
        return this.boletoClave;
    }

    /**
     * Sets the boletoClave of this TmsBoletosReferenciadosTbl to the specified value.
     * @param boletoClave the new boletoClave
     */
    public void setBoletoClave(String boletoClave) {
        this.boletoClave = boletoClave;
    }

    /**
     * Gets the boletoReferenciaNombre of this TmsBoletosReferenciadosTbl.
     * @return the boletoReferenciaNombre
     */
    public String getBoletoReferenciaNombre() {
        return this.boletoReferenciaNombre;
    }

    /**
     * Sets the boletoReferenciaNombre of this TmsBoletosReferenciadosTbl to the specified value.
     * @param boletoReferenciaNombre the new boletoReferenciaNombre
     */
    public void setBoletoReferenciaNombre(String boletoReferenciaNombre) {
        this.boletoReferenciaNombre = boletoReferenciaNombre;
    }

    /**
     * Gets the boletoTransaccionId of this TmsBoletosReferenciadosTbl.
     * @return the boletoTransaccionId
     */
    public BigInteger getBoletoTransaccionId() {
        return this.boletoTransaccionId;
    }

    /**
     * Sets the boletoTransaccionId of this TmsBoletosReferenciadosTbl to the specified value.
     * @param boletoTransaccionId the new boletoTransaccionId
     */
    public void setBoletoTransaccionId(BigInteger boletoTransaccionId) {
        this.boletoTransaccionId = boletoTransaccionId;
    }

    /**
     * Gets the estadoReferencia of this TmsBoletosReferenciadosTbl.
     * @return the estadoReferencia
     */
    public String getEstadoReferencia() {
        return this.estadoReferencia;
    }

    /**
     * Sets the estadoReferencia of this TmsBoletosReferenciadosTbl to the specified value.
     * @param estadoReferencia the new estadoReferencia
     */
    public void setEstadoReferencia(String estadoReferencia) {
        this.estadoReferencia = estadoReferencia;
    }

    /**
     * Gets the adicional1 of this TmsBoletosReferenciadosTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsBoletosReferenciadosTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsBoletosReferenciadosTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsBoletosReferenciadosTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsBoletosReferenciadosTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsBoletosReferenciadosTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsBoletosReferenciadosTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsBoletosReferenciadosTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsBoletosReferenciadosTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsBoletosReferenciadosTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the creadoPor of this TmsBoletosReferenciadosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsBoletosReferenciadosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsBoletosReferenciadosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsBoletosReferenciadosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsBoletosReferenciadosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsBoletosReferenciadosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsBoletosReferenciadosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsBoletosReferenciadosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsBoletosReferenciadosTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsBoletosReferenciadosTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsBoletosReferenciadosTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsBoletosReferenciadosTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsBoletosReferenciadosTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsBoletosReferenciadosTbl to the specified value.
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
        hash += (this.boletoReferenciadoId != null ? this.boletoReferenciadoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsBoletosReferenciadosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsBoletosReferenciadosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsBoletosReferenciadosTbl)) {
            return false;
        }
        TmsBoletosReferenciadosTbl other = (TmsBoletosReferenciadosTbl)object;
        if (this.boletoReferenciadoId != other.boletoReferenciadoId && (this.boletoReferenciadoId == null || !this.boletoReferenciadoId.equals(other.boletoReferenciadoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "entidad.TmsBoletosReferenciadosTbl[boletoReferenciadoId=" + boletoReferenciadoId + "]";
    }
    
}
