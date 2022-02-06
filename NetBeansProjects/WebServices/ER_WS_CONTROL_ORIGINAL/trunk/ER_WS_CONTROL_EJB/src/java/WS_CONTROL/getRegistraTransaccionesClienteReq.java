/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vgonzalez
 */
public class getRegistraTransaccionesClienteReq implements Serializable{
    private long  sesionId;
    private long clienteId;
    private String tipoMovimiento = "";
    private String nombreProducto = "";
    private String numeroTransaccion = "";
    private float montoTransaccion;
    private float montoComision;
    private float montoDescuento;
    private float subtotal;
    private float IVA;
    private float IVARetenido;
    private float Retencion5Millar;
    private float total;
    private Date fechaTransaccion;

    /**
     * @return the sesionId
     */
    public long getSesionId() {
        return sesionId;
    }

    /**
     * @param sesionId the sesionId to set
     */
    public void setSesionId(long sesionId) {
        this.sesionId = sesionId;
    }

    /**
     * @return the clienteId
     */
    public long getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the tipoMovimiento
     */
    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    /**
     * @param tipoMovimiento the tipoMovimiento to set
     */
    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * @return the numeroTransaccion
     */
    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }

    /**
     * @param numeroTransaccion the numeroTransaccion to set
     */
    public void setNumeroTransaccion(String numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }

    /**
     * @return the montoTransaccion
     */
    public float getMontoTransaccion() {
        return montoTransaccion;
    }

    /**
     * @param montoTransaccion the montoTransaccion to set
     */
    public void setMontoTransaccion(float montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    /**
     * @return the montoComision
     */
    public float getMontoComision() {
        return montoComision;
    }

    /**
     * @param montoComision the montoComision to set
     */
    public void setMontoComision(float montoComision) {
        this.montoComision = montoComision;
    }

    /**
     * @return the montoDescuento
     */
    public float getMontoDescuento() {
        return montoDescuento;
    }

    /**
     * @param montoDescuento the montoDescuento to set
     */
    public void setMontoDescuento(float montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    /**
     * @return the fechaTransaccion
     */
    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * @param fechaTransaccion the fechaTransaccion to set
     */
    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
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
