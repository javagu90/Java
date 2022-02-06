/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author brojas
 */
public class ErProductoCliente implements Serializable {
    private String nombre_producto="";
    private String referencia_producto="";
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal iva_retenido;
    private BigDecimal ret_5millar;
    private BigDecimal descuento;
    private BigDecimal total;  

    /**
     * @return the nombre_producto
     */

     public ErProductoCliente() {
    }

    public String getNombre_producto()  {
        return nombre_producto;
    }


     public ErProductoCliente(String[] campos) {

        this.setNombre_producto(campos[0]);
        this.setReferencia_producto(campos[1]);
        this.setSubtotal(new BigDecimal(campos[2]));
        this.setIva(new BigDecimal(campos[3]));
        this.setIva_retenido(new BigDecimal(campos[4]));
        this.setDescuento(new BigDecimal(campos[5]));
        this.setTotal(new BigDecimal(campos[5]));


    }



    /**
     * @param nombre_producto the nombre_producto to set
     */
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    /**
     * @return the referencia_producto
     */
    public String getReferencia_producto() {
        return referencia_producto;
    }

    /**
     * @param referencia_producto the referencia_producto to set
     */
    public void setReferencia_producto(String referencia_producto) {
        this.referencia_producto = referencia_producto;
    }

    /**
     * @return the subtotal
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the iva_retenido
     */
    public BigDecimal getIva_retenido() {
        return iva_retenido;
    }

    /**
     * @param iva_retenido the iva_retenido to set
     */
    public void setIva_retenido(BigDecimal iva_retenido) {
        this.iva_retenido = iva_retenido;
    }

    /**
     * @return the ret_5millar
     */
    public BigDecimal getRet_5millar() {
        return ret_5millar;
    }

    /**
     * @param ret_5millar the ret_5millar to set
     */
    public void setRet_5millar(BigDecimal ret_5millar) {
        this.ret_5millar = ret_5millar;
    }

    /**
     * @return the descuento
     */
    public BigDecimal getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
