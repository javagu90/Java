/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ERClientesDireccionesTbl;
import WS_CONTROL.entidades.ErClientesTbl;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brojas
 */
public class getOperacionesClienteReq implements Serializable {
   private Long SesionId;
   private ErClientesTbl cliente;
   private String operacion;

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
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the cliente
     */
    public ErClientesTbl getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ErClientesTbl cliente) {
        this.cliente = cliente;
    }
}
