/*
 * ValidarBoletoResp.java
 *
 * Created on 9 de agosto de 2010, 10:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ERTMSWS.clasesx.ValidarBoleto;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;

/**
 *
 * @author opalafox
 */

public class ValidarBoletoResp extends ExchangeResp  implements Serializable{
    private ERTMSWS.clases.Asientos Asientos[];
    private ERTMSWS.clases.FoliosBoletos FoliosBoletos[];
    
    
    /** Creates a new instance of ValidarBoletoResp */
    public ValidarBoletoResp() {
    }

    public ERTMSWS.clases.Asientos[] getAsientos() {
        return Asientos;
    }

    public void setAsientos(ERTMSWS.clases.Asientos[] Asientos) {
        this.Asientos = Asientos;
    }

    public ERTMSWS.clases.FoliosBoletos[] getFoliosBoletos() {
        return FoliosBoletos;
    }

    public void setFoliosBoletos(ERTMSWS.clases.FoliosBoletos[] FoliosBoletos) {
        this.FoliosBoletos = FoliosBoletos;
    }
    
}
