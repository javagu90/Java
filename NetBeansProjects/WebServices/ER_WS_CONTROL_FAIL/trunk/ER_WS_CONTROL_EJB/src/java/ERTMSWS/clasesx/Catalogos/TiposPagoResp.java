/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clasesx.Catalogos;

import ERTMSWS.clases.TipoPago;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class TiposPagoResp extends ExchangeResp implements Serializable{
    private TipoPago[] TiposPago;

    public TiposPagoResp(){

    }

    /**
     * @return the TiposPago
     */
    public TipoPago[] getTiposPago() {
        return TiposPago;
    }

    /**
     * @param TiposPago the TiposPago to set
     */
    public void setTiposPago(TipoPago[] TiposPago) {
        this.TiposPago = TiposPago;
    }
}
