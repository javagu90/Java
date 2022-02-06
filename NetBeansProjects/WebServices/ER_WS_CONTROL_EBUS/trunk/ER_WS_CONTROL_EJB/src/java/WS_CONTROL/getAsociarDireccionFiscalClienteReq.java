/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ErClientesDirFiscalesTbl;
import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getAsociarDireccionFiscalClienteReq implements Serializable {
    private Long SesionId;
    private Long clienteId;
    private ErClientesDirFiscalesTbl direccionesFidcales[];

    public getAsociarDireccionFiscalClienteReq(){

    }

    /**
     * @return the SesionId
     */
    public Long getSesionId() {
        return SesionId;
    }

    /**
     * @param SesionId the SesionId to set
     */
    public void setSesionId(Long SesionId) {
        this.SesionId = SesionId;
    }

    /**
     * @return the clienteId
     */
    public Long getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the direccionesFidcales
     */
    public ErClientesDirFiscalesTbl[] getDireccionesFidcales() {
        return direccionesFidcales;
    }

    /**
     * @param direccionesFidcales the direccionesFidcales to set
     */
    public void setDireccionesFidcales(ErClientesDirFiscalesTbl[] direccionesFidcales) {
        this.direccionesFidcales = direccionesFidcales;
    }

}
