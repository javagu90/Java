/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.Cliente;
import WS_CONTROL.entidades.ClienteTransaccion;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getEstadoCuentaClienteResp implements Serializable{
private List<ClienteTransaccion> clienteTransacciones;
private Cliente cliente;
private boolean operacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the clienteTransacciones
     */
    public List<ClienteTransaccion> getClienteTransacciones() {
        return clienteTransacciones;
    }

    /**
     * @param clienteTransacciones the clienteTransacciones to set
     */
    public void setClienteTransacciones(List<ClienteTransaccion> clienteTransacciones) {
        this.clienteTransacciones = clienteTransacciones;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the operacionExitosa
     */
    public boolean isOperacionExitosa() {
        return operacionExitosa;
    }

    /**
     * @param operacionExitosa the operacionExitosa to set
     */
    public void setOperacionExitosa(boolean operacionExitosa) {
        this.operacionExitosa = operacionExitosa;
    }

    /**
     * @return the ErrorCode
     */
    public String getErrorCode() {
        return ErrorCode;
    }

    /**
     * @param ErrorCode the ErrorCode to set
     */
    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    /**
     * @return the ErrorMsg
     */
    public String getErrorMsg() {
        return ErrorMsg;
    }

    /**
     * @param ErrorMsg the ErrorMsg to set
     */
    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }
}
