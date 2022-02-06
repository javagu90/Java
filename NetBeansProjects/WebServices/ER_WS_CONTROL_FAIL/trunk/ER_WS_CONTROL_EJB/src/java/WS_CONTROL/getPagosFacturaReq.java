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
public class getPagosFacturaReq implements Serializable{
private long  sesionId;
private long  facturaId;

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
     * @return the facturaId
     */
    public long getFacturaId() {
        return facturaId;
    }

    /**
     * @param facturaId the facturaId to set
     */
    public void setFacturaId(long facturaId) {
        this.facturaId = facturaId;
    }

}
