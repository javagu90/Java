/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ErPromocionesTbl;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getPromocionesResp implements Serializable{
private List<ErPromocionesTbl> promociones ;
    private boolean operacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the promociones
     */
    public List<ErPromocionesTbl> getPromociones()  {
        return promociones;
    }

    /**
     * @param promociones the promociones to set
     */
    public void setPromociones(List<ErPromocionesTbl> promociones) {
        this.promociones = promociones;
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
