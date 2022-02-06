/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROLVentour;

import WS_CONTROL.Ventour.entidades.Contrato;
import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class getCotizadorReq implements  Serializable{

    private Contrato contrato;
    private String TipoCotizacion ="";   // F = foraneo, L = locak

    /**
     * @return the contrato
     */
    public Contrato getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the TipoCotizacion
     */
    public String getTipoCotizacion() {
        return TipoCotizacion;
    }

    /**
     * @param TipoCotizacion the TipoCotizacion to set
     */
    public void setTipoCotizacion(String TipoCotizacion) {
        this.TipoCotizacion = TipoCotizacion;
    }

}
