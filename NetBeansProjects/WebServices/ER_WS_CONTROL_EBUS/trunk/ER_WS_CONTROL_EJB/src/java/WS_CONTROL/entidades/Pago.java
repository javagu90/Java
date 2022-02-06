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
@Table(name = "ER_PAGOS_TBL")
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAGO_ID")
    private BigDecimal pagoId;
    @Basic(optional = false)
    @Column(name = "CLIENTE_ID")
    private BigInteger clienteId;
    @Column(name = "TIPO_PAGO")
    private String tipoPago;
    @Column(name = "FORMA_PAGO_ID")
    private BigInteger formaPagoId;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "FECHA_CONTABLE")
    @Temporal(TemporalType.DATE)
    private Date fechaContable;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "NO_TARJETA")
    private String noTarjeta;

    public Pago() {
    }

    public Pago(BigDecimal pagoId) {
        this.pagoId = pagoId;
    }

    public Pago(BigDecimal pagoId, BigInteger clienteId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.pagoId = pagoId;
        this.clienteId = clienteId;
    }

    public BigDecimal getPagoId() {
        return pagoId;
    }

    public void setPagoId(BigDecimal pagoId) {
        this.pagoId = pagoId;
    }

    public BigInteger getClienteId() {
        return clienteId;
    }

    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigInteger getFormaPagoId() {
        return formaPagoId;
    }

    public void setFormaPagoId(BigInteger formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(Date fechaContable) {
        this.fechaContable = fechaContable;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoId != null ? pagoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.pagoId == null && other.pagoId != null) || (this.pagoId != null && !this.pagoId.equals(other.pagoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.Pagos[pagoId=" + pagoId + "]";
    }

}
