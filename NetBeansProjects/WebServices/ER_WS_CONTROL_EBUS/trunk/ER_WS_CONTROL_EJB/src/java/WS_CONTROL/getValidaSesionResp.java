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
public class getValidaSesionResp implements Serializable{
  private long corteId=-1;
  private long usuarioId=-1;
  private long clienteId=-1;
  private boolean operacionExitosa;
  private String ErrorCode;
  private String ErrorMsg;
 
    /**
     * @return the corteId
     */
    public long getCorteId() {
        return corteId;
    }

    /**
     * @param corteId the corteId to set
     */
    public void setCorteId(long corteId) {
        this.corteId = corteId;
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

    /**
     * @return the usuarioId
     */
    public long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
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

}
