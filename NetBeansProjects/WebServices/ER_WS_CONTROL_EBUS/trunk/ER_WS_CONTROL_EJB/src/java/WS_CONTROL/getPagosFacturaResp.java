/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.PagoFactura;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getPagosFacturaResp implements Serializable{
    private List<PagoFactura> pagosFacturas;
    private boolean operacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the pagosFacturas
     */
    public List<PagoFactura> getPagosFacturas() {
        return pagosFacturas;
    }

    /**
     * @param pagosFacturas the pagosFacturas to set
     */
    public void setPagosFacturas(List<PagoFactura> pagosFacturas) {
        this.pagosFacturas = pagosFacturas;
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
