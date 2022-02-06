/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clasesx.Catalogos;

import ERTMSWS.clases.Terminal;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class TerminalesResp extends ExchangeResp implements Serializable{
   private Terminal[] Terminales;

   public TerminalesResp(){

    }

    /**
     * @return the Terminales
     */
    public Terminal[] getTerminales() {
        return Terminales;
    }

    /**
     * @param Terminales the Terminales to set
     */
    public void setTerminales(Terminal[] Terminales) {
        this.Terminales = Terminales;
    }



}
