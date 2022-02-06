/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ErClientesTbl;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brojas
 */
public class getOperacionesClientesUsuarioReq implements  Serializable {
  private Long SesionId;
  private String usuarioId;
  private List<ErClientesTbl> Clientes;
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
     * @return the usuarioId
     */
    public String getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
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
     * @return the Cliente
     */
    public List<ErClientesTbl> getCliente() {
        return Clientes;
    }

    /**
     * @param Cliente the Cliente to set
     */
    public void setCliente(List<ErClientesTbl> Cliente) {
        this.Clientes = Cliente;
    }




}
