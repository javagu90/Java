/*
 * TmsBoletosBoleteraTbl.java
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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsBoletosBoleteraTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_BOLETOS_BOLETERA_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByBoletoBoleteraId", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.boletoBoleteraId = :boletoBoleteraId"),
        
        
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByTarjeta",      query = "select o from TmsBoletosBoleteraTbl o where o.folioTarjeta = :folio and o.origenTarjeta = :origen"),        
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByFolioTarjeta", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.folioTarjeta = :folioTarjeta"),
        
        
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByOrigenTarjeta", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.origenTarjeta = :origenTarjeta"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByFechaHoraBoleto", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.fechaHoraBoleto = :fechaHoraBoleto"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByFolioBoleto", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.folioBoleto = :folioBoleto"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByOrigenBoletoId", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.origenBoletoId = :origenBoletoId"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByDestinoBoletoId", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.destinoBoletoId = :destinoBoletoId"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByImporteBoleto", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.importeBoleto = :importeBoleto"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByTipoPasajero", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.tipoPasajero = :tipoPasajero"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByTipoVenta", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.tipoVenta = :tipoVenta"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByCreadoPor", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByFechaCreacion", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsBoletosBoleteraTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsBoletosBoleteraTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsBoletosBoleteraTbl implements Serializable {

    @Id
    @Column(name = "BOLETO_BOLETERA_ID", nullable = false)
    private BigDecimal boletoBoleteraId;

    @Column(name = "FOLIO_TARJETA", nullable = false)
    private String folioTarjeta;

    @Column(name = "ORIGEN_TARJETA", nullable = false)
    private String origenTarjeta;

    @Column(name = "FECHA_HORA_BOLETO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBoleto;

    @Column(name = "FOLIO_BOLETO")
    private BigInteger folioBoleto;

    @Column(name = "ORIGEN_BOLETO_ID", nullable = false)
    private BigInteger origenBoletoId;

    @Column(name = "DESTINO_BOLETO_ID", nullable = false)
    private BigInteger destinoBoletoId;

    @Column(name = "IMPORTE_BOLETO", nullable = false)
    private BigDecimal importeBoleto;

    @Column(name = "TIPO_PASAJERO")
    private String tipoPasajero;

    @Column(name = "TIPO_VENTA")
    private String tipoVenta;

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
    
    /** Creates a new instance of TmsBoletosBoleteraTbl */
    public TmsBoletosBoleteraTbl() {
    }

    /**
     * Creates a new instance of TmsBoletosBoleteraTbl with the specified values.
     * @param boletoBoleteraId the boletoBoleteraId of the TmsBoletosBoleteraTbl
     */
    public TmsBoletosBoleteraTbl(BigDecimal boletoBoleteraId) {
        this.boletoBoleteraId = boletoBoleteraId;
    }

    /**
     * Creates a new instance of TmsBoletosBoleteraTbl with the specified values.
     * @param boletoBoleteraId the boletoBoleteraId of the TmsBoletosBoleteraTbl
     * @param folioTarjeta the folioTarjeta of the TmsBoletosBoleteraTbl
     * @param origenTarjeta the origenTarjeta of the TmsBoletosBoleteraTbl
     * @param fechaHoraBoleto the fechaHoraBoleto of the TmsBoletosBoleteraTbl
     * @param origenBoletoId the origenBoletoId of the TmsBoletosBoleteraTbl
     * @param destinoBoletoId the destinoBoletoId of the TmsBoletosBoleteraTbl
     * @param importeBoleto the importeBoleto of the TmsBoletosBoleteraTbl
     * @param creadoPor the creadoPor of the TmsBoletosBoleteraTbl
     * @param fechaCreacion the fechaCreacion of the TmsBoletosBoleteraTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsBoletosBoleteraTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsBoletosBoleteraTbl
     */
    public TmsBoletosBoleteraTbl(BigDecimal boletoBoleteraId, String folioTarjeta, String origenTarjeta, Date fechaHoraBoleto, BigInteger origenBoletoId, BigInteger destinoBoletoId, BigDecimal importeBoleto, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.boletoBoleteraId = boletoBoleteraId;
        this.folioTarjeta = folioTarjeta;
        this.origenTarjeta = origenTarjeta;
        this.fechaHoraBoleto = fechaHoraBoleto;
        this.origenBoletoId = origenBoletoId;
        this.destinoBoletoId = destinoBoletoId;
        this.importeBoleto = importeBoleto;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the boletoBoleteraId of this TmsBoletosBoleteraTbl.
     * @return the boletoBoleteraId
     */
    public BigDecimal getBoletoBoleteraId() {
        return this.boletoBoleteraId;
    }

    /**
     * Sets the boletoBoleteraId of this TmsBoletosBoleteraTbl to the specified value.
     * @param boletoBoleteraId the new boletoBoleteraId
     */
    public void setBoletoBoleteraId(BigDecimal boletoBoleteraId) {
        this.boletoBoleteraId = boletoBoleteraId;
    }

    /**
     * Gets the folioTarjeta of this TmsBoletosBoleteraTbl.
     * @return the folioTarjeta
     */
    public String getFolioTarjeta() {
        return this.folioTarjeta;
    }

    /**
     * Sets the folioTarjeta of this TmsBoletosBoleteraTbl to the specified value.
     * @param folioTarjeta the new folioTarjeta
     */
    public void setFolioTarjeta(String folioTarjeta) {
        this.folioTarjeta = folioTarjeta;
    }

    /**
     * Gets the origenTarjeta of this TmsBoletosBoleteraTbl.
     * @return the origenTarjeta
     */
    public String getOrigenTarjeta() {
        return this.origenTarjeta;
    }

    /**
     * Sets the origenTarjeta of this TmsBoletosBoleteraTbl to the specified value.
     * @param origenTarjeta the new origenTarjeta
     */
    public void setOrigenTarjeta(String origenTarjeta) {
        this.origenTarjeta = origenTarjeta;
    }

    /**
     * Gets the fechaHoraBoleto of this TmsBoletosBoleteraTbl.
     * @return the fechaHoraBoleto
     */
    public Date getFechaHoraBoleto() {
        return this.fechaHoraBoleto;
    }

    /**
     * Sets the fechaHoraBoleto of this TmsBoletosBoleteraTbl to the specified value.
     * @param fechaHoraBoleto the new fechaHoraBoleto
     */
    public void setFechaHoraBoleto(Date fechaHoraBoleto) {
        this.fechaHoraBoleto = fechaHoraBoleto;
    }

    /**
     * Gets the folioBoleto of this TmsBoletosBoleteraTbl.
     * @return the folioBoleto
     */
    public BigInteger getFolioBoleto() {
        return this.folioBoleto;
    }

    /**
     * Sets the folioBoleto of this TmsBoletosBoleteraTbl to the specified value.
     * @param folioBoleto the new folioBoleto
     */
    public void setFolioBoleto(BigInteger folioBoleto) {
        this.folioBoleto = folioBoleto;
    }

    /**
     * Gets the origenBoletoId of this TmsBoletosBoleteraTbl.
     * @return the origenBoletoId
     */
    public BigInteger getOrigenBoletoId() {
        return this.origenBoletoId;
    }

    /**
     * Sets the origenBoletoId of this TmsBoletosBoleteraTbl to the specified value.
     * @param origenBoletoId the new origenBoletoId
     */
    public void setOrigenBoletoId(BigInteger origenBoletoId) {
        this.origenBoletoId = origenBoletoId;
    }

    /**
     * Gets the destinoBoletoId of this TmsBoletosBoleteraTbl.
     * @return the destinoBoletoId
     */
    public BigInteger getDestinoBoletoId() {
        return this.destinoBoletoId;
    }

    /**
     * Sets the destinoBoletoId of this TmsBoletosBoleteraTbl to the specified value.
     * @param destinoBoletoId the new destinoBoletoId
     */
    public void setDestinoBoletoId(BigInteger destinoBoletoId) {
        this.destinoBoletoId = destinoBoletoId;
    }

    /**
     * Gets the importeBoleto of this TmsBoletosBoleteraTbl.
     * @return the importeBoleto
     */
    public BigDecimal getImporteBoleto() {
        return this.importeBoleto;
    }

    /**
     * Sets the importeBoleto of this TmsBoletosBoleteraTbl to the specified value.
     * @param importeBoleto the new importeBoleto
     */
    public void setImporteBoleto(BigDecimal importeBoleto) {
        this.importeBoleto = importeBoleto;
    }

    /**
     * Gets the tipoPasajero of this TmsBoletosBoleteraTbl.
     * @return the tipoPasajero
     */
    public String getTipoPasajero() {
        return this.tipoPasajero;
    }

    /**
     * Sets the tipoPasajero of this TmsBoletosBoleteraTbl to the specified value.
     * @param tipoPasajero the new tipoPasajero
     */
    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    /**
     * Gets the tipoVenta of this TmsBoletosBoleteraTbl.
     * @return the tipoVenta
     */
    public String getTipoVenta() {
        return this.tipoVenta;
    }

    /**
     * Sets the tipoVenta of this TmsBoletosBoleteraTbl to the specified value.
     * @param tipoVenta the new tipoVenta
     */
    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    /**
     * Gets the creadoPor of this TmsBoletosBoleteraTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsBoletosBoleteraTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsBoletosBoleteraTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsBoletosBoleteraTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsBoletosBoleteraTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsBoletosBoleteraTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsBoletosBoleteraTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsBoletosBoleteraTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.boletoBoleteraId != null ? this.boletoBoleteraId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsBoletosBoleteraTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsBoletosBoleteraTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsBoletosBoleteraTbl)) {
            return false;
        }
        TmsBoletosBoleteraTbl other = (TmsBoletosBoleteraTbl)object;
        if (this.boletoBoleteraId != other.boletoBoleteraId && (this.boletoBoleteraId == null || !this.boletoBoleteraId.equals(other.boletoBoleteraId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsBoletosBoleteraTbl[boletoBoleteraId=" + boletoBoleteraId + "]";
    }
    
}
