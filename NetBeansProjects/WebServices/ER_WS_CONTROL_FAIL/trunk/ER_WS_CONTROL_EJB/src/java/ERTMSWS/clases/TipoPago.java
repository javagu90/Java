/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clases;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class TipoPago implements Serializable {
    private long TipoPagoId;
    private String PagoNombre;
    private String PagoClave;
    private String PagoDescripcion;

    public TipoPago(){

    }

    /**
     * @return the TipoPagoId
     */
    public long getTipoPagoId() {
        return TipoPagoId;
    }

    /**
     * @param TipoPagoId the TipoPagoId to set
     */
    public void setTipoPagoId(long TipoPagoId) {
        this.TipoPagoId = TipoPagoId;
    }

    /**
     * @return the PagoNombre
     */
    public String getPagoNombre() {
        return PagoNombre;
    }

    /**
     * @param PagoNombre the PagoNombre to set
     */
    public void setPagoNombre(String PagoNombre) {
        this.PagoNombre = PagoNombre;
    }

    /**
     * @return the PagoClave
     */
    public String getPagoClave() {
        return PagoClave;
    }

    /**
     * @param PagoClave the PagoClave to set
     */
    public void setPagoClave(String PagoClave) {
        this.PagoClave = PagoClave;
    }

    /**
     * @return the PagoDescripcion
     */
    public String getPagoDescripcion() {
        return PagoDescripcion;
    }

    /**
     * @param PagoDescripcion the PagoDescripcion to set
     */
    public void setPagoDescripcion(String PagoDescripcion) {
        this.PagoDescripcion = PagoDescripcion;
    }

}
