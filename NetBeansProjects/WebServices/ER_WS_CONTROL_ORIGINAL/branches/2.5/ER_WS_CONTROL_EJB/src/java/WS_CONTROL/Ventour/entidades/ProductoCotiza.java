/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class ProductoCotiza implements Serializable {
  private String Servicio ="";
    private double Importe = 0;
    private double Descuento = 0;
    private double Subtotal_Descuento = 0;
    private double IVA = 0;
    private double TOTAL = 0;
    private Unidad listaUnidades = null;
    private int cantidad=1;

    /**
     * @return the Servicio
     */
    public String getServicio() {
        return Servicio;
    }

    /**
     * @param Servicio the Servicio to set
     */
    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    /**
     * @return the Importe
     */
    public double getImporte() {
        return Importe;
    }

    /**
     * @param Importe the Importe to set
     */
    public void setImporte(double Importe) {
        this.Importe = Importe;
    }

    /**
     * @return the Descuento
     */
    public double getDescuento() {
        return Descuento;
    }

    /**
     * @param Descuento the Descuento to set
     */
    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    /**
     * @return the Subtotal_Descuento
     */
    public double getSubtotal_Descuento() {
        return Subtotal_Descuento;
    }

    /**
     * @param Subtotal_Descuento the Subtotal_Descuento to set
     */
    public void setSubtotal_Descuento(double Subtotal_Descuento) {
        this.Subtotal_Descuento = Subtotal_Descuento;
    }

    /**
     * @return the IVA
     */
    public double getIVA() {
        return IVA;
    }

    /**
     * @param IVA the IVA to set
     */
    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    /**
     * @return the TOTAL
     */
    public double getTOTAL() {
        return TOTAL;
    }

    /**
     * @param TOTAL the TOTAL to set
     */
    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    /**
     * @return the listaUnidades
     */
    public Unidad getListaUnidades() {
        return listaUnidades;
    }

    /**
     * @param listaUnidades the listaUnidades to set
     */
    public void setListaUnidades(Unidad listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

   @Override
    public String toString() {
       // return "touristER_cliente.entidadER.ErEstados[estadoId=" + estadoId + "]";
        return getServicio();
    }
}
