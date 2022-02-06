/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vgonzalez
 */
@Entity
@Table(name = "ER_PAGOS_FACTURAS_TBL")
public class PagoFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAGOS_FACTURA_ID")
    private BigDecimal pagosFacturaId;
    @Column(name = "PAGO_ID")
    private BigInteger pagoId;
    @Column(name = "FACTURA_ID")
    private BigInteger facturaId;
    @Column(name = "MONTO_APLICADO")
    private BigDecimal montoAplicado;
    private String referenciaPago="";
    private BigDecimal montoPago;


    public PagoFactura() {
    }


    public PagoFactura(String[] campos) {
        this.setFacturaId(new BigInteger(campos[0]));
        this.setPagoId(new BigInteger(campos[1]));
        this.setFacturaId(new BigInteger(campos[2]));
        this.setMontoAplicado(new BigDecimal(campos[3]));
        this.setReferenciaPago(campos[4]);
        this.setMontoPago(new BigDecimal(campos[5]));
    }

    public PagoFactura(BigDecimal pagosFacturaId) {
        this.pagosFacturaId = pagosFacturaId;
    }

    public BigDecimal getPagosFacturaId() {
        return pagosFacturaId;
    }

    public void setPagosFacturaId(BigDecimal pagosFacturaId) {
        this.pagosFacturaId = pagosFacturaId;
    }

    public BigInteger getPagoId() {
        return pagoId;
    }

    public void setPagoId(BigInteger pagoId) {
        this.pagoId = pagoId;
    }

    public BigInteger getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(BigInteger facturaId) {
        this.facturaId = facturaId;
    }

    public BigDecimal getMontoAplicado() {
        return montoAplicado;
    }

    public void setMontoAplicado(BigDecimal montoAplicado) {
        this.montoAplicado = montoAplicado;
    }

   @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagosFacturaId != null ? pagosFacturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoFactura)) {
            return false;
        }
        PagoFactura other = (PagoFactura) object;
        if ((this.pagosFacturaId == null && other.pagosFacturaId != null) || (this.pagosFacturaId != null && !this.pagosFacturaId.equals(other.pagosFacturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.PagoFactura[pagosFacturaId=" + pagosFacturaId + "]";
    }

    /**
     * @return the referenciaPago
     */
    public String getReferenciaPago() {
        return referenciaPago;
    }

    /**
     * @param referenciaPago the referenciaPago to set
     */
    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    /**
     * @return the montoPago
     */
    public BigDecimal getMontoPago() {
        return montoPago;
    }

    /**
     * @param montoPago the montoPago to set
     */
    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

}
