/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clasesx.Catalogos;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class TiposPagoReq implements Serializable{
    private int SesionId;
    
    public TiposPagoReq(){

    }

    /**
     * @return the SesionId
     */
    public int getSesionId() {
        return SesionId;
    }

    /**
     * @param SesionId the SesionId to set
     */
    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
    }
}
