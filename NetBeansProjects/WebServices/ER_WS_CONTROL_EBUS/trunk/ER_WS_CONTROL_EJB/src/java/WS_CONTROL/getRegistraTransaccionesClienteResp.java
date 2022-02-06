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
public class getRegistraTransaccionesClienteResp implements Serializable{
    private float saldoActual;
    private float saldoFavor;
    private boolean operacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

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
     * @return the saldoActual
     */
    public float getSaldoActual() {
        return saldoActual;
    }

    /**
     * @param saldoActual the saldoActual to set
     */
    public void setSaldoActual(float saldoActual) {
        this.saldoActual = saldoActual;
    }

    /**
     * @return the saldoFavor
     */
    public float getSaldoFavor() {
        return saldoFavor;
    }

    /**
     * @param saldoFavor the saldoFavor to set
     */
    public void setSaldoFavor(float saldoFavor) {
        this.saldoFavor = saldoFavor;
    }
}
