/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ErUsuariosTbl;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brojas
 */
public class getUsuariosResp  implements Serializable {

    private List<ErUsuariosTbl> usuarios ;
    private boolean operacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the promociones  
     */
    public List<ErUsuariosTbl> getUsuarios() {
        return usuarios;
    }

    /**
     * @param promociones the promociones to set
     */
    public void setUsuarios(List<ErUsuariosTbl> promociones) {
        this.usuarios = promociones;
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
