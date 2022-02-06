/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
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
@Table(name = "ER_CLIENTE_TRANSACCIONES_TBL")
public class ClienteTransaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLIENTE_TRANSACCION_ID")
    private BigDecimal clienteTransaccionId;
    @Basic(optional = false)
    @Column(name = "CLIENTE_ID")
    private BigInteger clienteId;
    @Basic(optional = false)
    @Column(name = "TIPO_MOVIMIENTO")
    private String tipoMovimiento;
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "NUMERO_TRANSACCION")
    private String numeroTransaccion;
    @Column(name = "MONTO_TRANSACCION")
    private BigDecimal montoTransaccion;
    @Basic(optional = false)
    @Column(name = "FECHA_TRANSACCION")
    @Temporal(TemporalType.DATE)
    private Date fechaTransaccion;
    @Column(name = "SALDO_INICIAL")
    private BigDecimal saldoInicial;
    @Column(name = "SALDO_FINAL")
    private BigDecimal saldoFinal;
    @Column(name = "SALDO_FAVOR")
    private BigDecimal saldoFavor;
    @Column(name = "MONTO_COMISION")
    private BigDecimal montoComision;
    @Column(name = "MONTO_DESCUENTO")
    private BigDecimal montoDescuento;
    private float subtotal;
    private float IVA;
    private float IVARetenido;
    private float Retencion5Millar;
    private float total;


    public ClienteTransaccion() {
    }

    public ClienteTransaccion(String[] campos) {
        this.setClienteTransaccionId(new BigDecimal(campos[0]));
        this.setClienteId(new BigInteger(campos[1]));
        this.setTipoMovimientoId(campos[2]);
        this.setNombreProducto(campos[3]);
        this.setNumeroTransaccion(campos[4]);
        this.setMontoTransaccion(new BigDecimal(campos[5]));
        Timestamp t = null;
        t = t.valueOf(campos[6]);
        this.setFechaTransaccion(new Date(t.getTime()));
        this.setSaldoInicial(new BigDecimal(campos[7]));
        this.setSaldoFinal(new BigDecimal(campos[8]));
        this.setSaldoFavor(new BigDecimal(campos[9]));
        this.setMontoComision(new BigDecimal(campos[10]));
        this.setMontoDescuento(new BigDecimal(campos[11]));
        this.setSubtotal(Float.valueOf(campos[12]));
        this.setIVA(Float.valueOf(campos[13]));
        this.setIVARetenido(Float.valueOf(campos[14]));
        this.setRetencion5Millar(Float.valueOf(campos[15]));
        this.setTotal(Float.valueOf(campos[16]));
    }

    public ClienteTransaccion(BigDecimal clienteTransaccionId) {
        this.clienteTransaccionId = clienteTransaccionId;
    }

    public ClienteTransaccion(BigDecimal clienteTransaccionId, BigInteger clienteId, BigInteger tipoMovimientoId, Date fechaTransaccion, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.clienteTransaccionId = clienteTransaccionId;
        this.clienteId = clienteId;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaTransaccion = fechaTransaccion;
    }

    public BigDecimal getClienteTransaccionId() {
        return clienteTransaccionId;
    }

    public void setClienteTransaccionId(BigDecimal clienteTransaccionId) {
        this.clienteTransaccionId = clienteTransaccionId;
    }

    public BigInteger getClienteId() {
        return clienteId;
    }

    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoMovimientoId() {
        return tipoMovimiento;
    }

    public void setTipoMovimientoId(String tipoMovimientoId) {
        this.tipoMovimiento = tipoMovimientoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(String numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }

    public BigDecimal getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(BigDecimal montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public BigDecimal getSaldoFavor() {
        return saldoFavor;
    }

    public void setSaldoFavor(BigDecimal saldoFavor) {
        this.saldoFavor = saldoFavor;
    }

    public BigDecimal getMontoComision() {
        return montoComision;
    }

    public void setMontoComision(BigDecimal montoComision) {
        this.montoComision = montoComision;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteTransaccionId != null ? clienteTransaccionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteTransaccion)) {
            return false;
        }
        ClienteTransaccion other = (ClienteTransaccion) object;
        if ((this.clienteTransaccionId == null && other.clienteTransaccionId != null) || (this.clienteTransaccionId != null && !this.clienteTransaccionId.equals(other.clienteTransaccionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ClienteTransaccion[clienteTransaccionId=" + clienteTransaccionId + "]";
    }

    /**
     * @return the subtotal
     */
    public float getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the IVA
     */
    public float getIVA() {
        return IVA;
    }

    /**
     * @param IVA the IVA to set
     */
    public void setIVA(float IVA) {
        this.IVA = IVA;
    }

    /**
     * @return the IVARetenido
     */
    public float getIVARetenido() {
        return IVARetenido;
    }

    /**
     * @param IVARetenido the IVARetenido to set
     */
    public void setIVARetenido(float IVARetenido) {
        this.IVARetenido = IVARetenido;
    }

    /**
     * @return the Retencion5Millar
     */
    public float getRetencion5Millar() {
        return Retencion5Millar;
    }

    /**
     * @param Retencion5Millar the Retencion5Millar to set
     */
    public void setRetencion5Millar(float Retencion5Millar) {
        this.Retencion5Millar = Retencion5Millar;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

}
