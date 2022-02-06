/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.paquer;

import WS_CONTROL.paquer.entidades.Servicio;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getServiciosResp implements Serializable{
    private List<Servicio> servicios;
    private boolean operacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the servicios
     */
    public List<Servicio> getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
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
