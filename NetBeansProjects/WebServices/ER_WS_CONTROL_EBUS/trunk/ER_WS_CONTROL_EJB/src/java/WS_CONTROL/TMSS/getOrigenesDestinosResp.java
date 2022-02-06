/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMSS;

import ERTMSWS.clasesx.ExchangeResp;
import WS_CONTROL.TMS.entidades.Origen;
import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getOrigenesDestinosResp extends ExchangeResp implements Serializable{
    private Origen[] origenes;

 public getOrigenesDestinosResp(){

 }

    /**
     * @return the origenes
     */
    public Origen[] getOrigenes() {
        return origenes;
    }

    /**
     * @param origenes the origenes to set
     */
    public void setOrigenes(Origen[] origenes) {
        this.origenes = origenes;
    }


}
