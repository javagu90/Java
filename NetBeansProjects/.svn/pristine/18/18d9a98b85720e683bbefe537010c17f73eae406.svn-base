/*
 * TmsBoletosFacturadosTbl.java
 *
 * Created on 9 de enero de 2009, 05:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity class TmsBoletosFacturadosTbl
 * 
 * @author asolis
 */
@Entity
@Table(name = "TMS_BOLETOS_FACTURADOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsBoletosFacturadosTbl.findByBoletoFacturadoId", query = "SELECT t FROM TmsBoletosFacturadosTbl t WHERE t.boletoFacturadoId = :boletoFacturadoId"),
        @NamedQuery(name = "TmsBoletosFacturadosTbl.findByFolioPreimpreso", query = "SELECT t FROM TmsBoletosFacturadosTbl t WHERE t.folioPreimpreso = :folioPreimpreso"),
        @NamedQuery(name = "TmsBoletosFacturadosTbl.findByBoletoId", query = "SELECT t FROM TmsBoletosFacturadosTbl t WHERE t.boletoId = :boletoId"),
        @NamedQuery(name = "TmsBoletosFacturadosTbl.findByFacturaId", query = "SELECT t FROM TmsBoletosFacturadosTbl t WHERE t.facturaId = :facturaId")
    })
public class TmsBoletosFacturadosTbl implements Serializable {

    @Id
    @Column(name = "BOLETO_FACTURADO_ID", nullable = false)
    private BigDecimal boletoFacturadoId;

    @Column(name = "FOLIO_PREIMPRESO", nullable = false)
    private String folioPreimpreso;

    @Column(name = "BOLETO_ID", nullable = false)
    private BigInteger boletoId;

    @Column(name = "FACTURA_ID", nullable = false)
    private BigInteger facturaId;
    
    /** Creates a new instance of TmsBoletosFacturadosTbl */
    public TmsBoletosFacturadosTbl() {
    }

    /**
     * Creates a new instance of TmsBoletosFacturadosTbl with the specified values.
     * @param boletoFacturadoId the boletoFacturadoId of the TmsBoletosFacturadosTbl
     */
    public TmsBoletosFacturadosTbl(BigDecimal boletoFacturadoId) {
        this.boletoFacturadoId = boletoFacturadoId;
    }

    /**
     * Creates a new instance of TmsBoletosFacturadosTbl with the specified values.
     * @param boletoFacturadoId the boletoFacturadoId of the TmsBoletosFacturadosTbl
     * @param folioPreimpreso the folioPreimpreso of the TmsBoletosFacturadosTbl
     * @param boletoId the boletoId of the TmsBoletosFacturadosTbl
     * @param facturaId the facturaId of the TmsBoletosFacturadosTbl
     */
    public TmsBoletosFacturadosTbl(BigDecimal boletoFacturadoId, String folioPreimpreso, BigInteger boletoId, BigInteger facturaId) {
        this.boletoFacturadoId = boletoFacturadoId;
        this.folioPreimpreso = folioPreimpreso;
        this.boletoId = boletoId;
        this.facturaId = facturaId;
    }

    /**
     * Gets the boletoFacturadoId of this TmsBoletosFacturadosTbl.
     * @return the boletoFacturadoId
     */
    public BigDecimal getBoletoFacturadoId() {
        return this.boletoFacturadoId;
    }

    /**
     * Sets the boletoFacturadoId of this TmsBoletosFacturadosTbl to the specified value.
     * @param boletoFacturadoId the new boletoFacturadoId
     */
    public void setBoletoFacturadoId(BigDecimal boletoFacturadoId) {
        this.boletoFacturadoId = boletoFacturadoId;
    }

    /**
     * Gets the folioPreimpreso of this TmsBoletosFacturadosTbl.
     * @return the folioPreimpreso
     */
    public String getFolioPreimpreso() {
        return this.folioPreimpreso;
    }

    /**
     * Sets the folioPreimpreso of this TmsBoletosFacturadosTbl to the specified value.
     * @param folioPreimpreso the new folioPreimpreso
     */
    public void setFolioPreimpreso(String folioPreimpreso) {
        this.folioPreimpreso = folioPreimpreso;
    }

    /**
     * Gets the boletoId of this TmsBoletosFacturadosTbl.
     * @return the boletoId
     */
    public BigInteger getBoletoId() {
        return this.boletoId;
    }

    /**
     * Sets the boletoId of this TmsBoletosFacturadosTbl to the specified value.
     * @param boletoId the new boletoId
     */
    public void setBoletoId(BigInteger boletoId) {
        this.boletoId = boletoId;
    }

    /**
     * Gets the facturaId of this TmsBoletosFacturadosTbl.
     * @return the facturaId
     */
    public BigInteger getFacturaId() {
        return this.facturaId;
    }

    /**
     * Sets the facturaId of this TmsBoletosFacturadosTbl to the specified value.
     * @param facturaId the new facturaId
     */
    public void setFacturaId(BigInteger facturaId) {
        this.facturaId = facturaId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.boletoFacturadoId != null ? this.boletoFacturadoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsBoletosFacturadosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsBoletosFacturadosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsBoletosFacturadosTbl)) {
            return false;
        }
        TmsBoletosFacturadosTbl other = (TmsBoletosFacturadosTbl)object;
        if (this.boletoFacturadoId != other.boletoFacturadoId && (this.boletoFacturadoId == null || !this.boletoFacturadoId.equals(other.boletoFacturadoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "entidad.TmsBoletosFacturadosTbl[boletoFacturadoId=" + boletoFacturadoId + "]";
    }
    
}
