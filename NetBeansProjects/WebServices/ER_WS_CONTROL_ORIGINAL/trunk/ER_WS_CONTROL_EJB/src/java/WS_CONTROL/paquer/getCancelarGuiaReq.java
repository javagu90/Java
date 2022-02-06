/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.paquer;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getCancelarGuiaReq implements Serializable{
private long  sesionId;
private String numeroGuia;

    /**
     * @return the sesionId
     */
    public long getSesionId() {
        return sesionId;
    }

    /**
     * @param sesionId the sesionId to set
     */
    public void setSesionId(long sesionId) {
        this.sesionId = sesionId;
    }

    /**
     * @return the numeroGuia
     */
    public String getNumeroGuia() {
        return numeroGuia;
    }

    /**
     * @param numeroGuia the numeroGuia to set
     */
    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

}
