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
public class getValidaSesionReq implements Serializable{
private long  sesionId;
private String canalVenta;

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
     * @return the canalVenta
     */
    public String getCanalVenta() {
        return canalVenta;
    }

    /**
     * @param canalVenta the canalVenta to set
     */
    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

}
