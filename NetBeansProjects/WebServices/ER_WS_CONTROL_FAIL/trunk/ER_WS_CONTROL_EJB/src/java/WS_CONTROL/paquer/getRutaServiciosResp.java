/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.paquer;

import WS_CONTROL.paquer.entidades.RutaServicios;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getRutaServiciosResp implements Serializable{
    private List<RutaServicios> RutasServicios;
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
     * @return the RutasServicios
     */
    public List<RutaServicios> getRutasServicios() {
        return RutasServicios;
    }

    /**
     * @param RutasServicios the RutasServicios to set
     */
    public void setRutasServicios(List<RutaServicios> RutasServicios) {
        this.RutasServicios = RutasServicios;
    }

}
