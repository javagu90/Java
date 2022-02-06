/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getPromocionesReq implements Serializable {
private long  sesionId;
private String nombrePromocion = "";
private String codigoPromocion = "";
private String nombreProducto = "";

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
     * @return the nombrePromocion
     */
    public String getNombrePromocion() {
        return nombrePromocion;
    }

    /**
     * @param nombrePromocion the nombrePromocion to set
     */
    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    /**
     * @return the codigoPromocion
     */
    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    /**
     * @param codigoPromocion the codigoPromocion to set
     */
    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
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

}
