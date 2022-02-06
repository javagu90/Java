/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ERClientesDireccionesTbl;
import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getAsociarDireccionClienteReq implements Serializable {
    private Long SesionId;
    private Long clienteId;
    private ERClientesDireccionesTbl direcciones[];

    public getAsociarDireccionClienteReq(){

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
     * @return the direcciones
     */
    public ERClientesDireccionesTbl[] getDirecciones() {
        return direcciones;
    }

    /**
     * @param direcciones the direcciones to set
     */
    public void setDirecciones(ERClientesDireccionesTbl[] direcciones) {
        this.direcciones = direcciones;
    }

}
