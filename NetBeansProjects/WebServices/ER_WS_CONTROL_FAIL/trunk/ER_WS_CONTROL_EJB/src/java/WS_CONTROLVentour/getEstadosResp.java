/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROLVentour;

import WS_CONTROL.Ventour.entidades.Estado;  
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brojas
 */
public class getEstadosResp  implements Serializable{
    private List<Estado> Estados ;
    private boolean OperacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the Unidades
     */
    public List<Estado> getEstados() {
        return Estados;
    }  

    /**
     * @param Unidades the Unidades to set
     */
    public void setEstados(List<Estado> Estados) {
        this.Estados = Estados;
    }

    /**
     * @return the OperacionExitosa
     */
    public boolean isOperacionExitosa() {
        return OperacionExitosa;
    }

    /**
     * @param OperacionExitosa the OperacionExitosa to set
     */
    public void setOperacionExitosa(boolean OperacionExitosa) {
        this.OperacionExitosa = OperacionExitosa;
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
