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
public class getOperacionContratoReq implements Serializable{

    private Long SesionId;
    private String Cliente_ID;
    private Contrato Contrato;
     private String TipoOperacion;

    /**
     * @return the SesionId
     */
    public Long getSesionId() {
        return SesionId;
    }

    /**
     * @param SesionId the SesionId to set
     */
    public void setSesionId(Long SesionId) {
        this.SesionId = SesionId;
    }

    /**
     * @return the Cliente_ID
     */
    public String getCliente_ID() {
        return Cliente_ID;
    }

    /**
     * @param Cliente_ID the Cliente_ID to set
     */
    public void setCliente_ID(String Cliente_ID) {
        this.Cliente_ID = Cliente_ID;
    }

    /**
     * @return the Contrato
     */
    public Contrato getContrato() {
        return Contrato;
    }

    /**
     * @param Contrato the Contrato to set
     */
    public void setContrato(Contrato Contrato) {
        this.Contrato = Contrato;
    }

    /**
     * @return the TipoOperacion
     */
    public String getTipoOperacion() {
        return TipoOperacion;
    }

    /**
     * @param TipoOperacion the TipoOperacion to set
     */
    public void setTipoOperacion(String TipoOperacion) {
        this.TipoOperacion = TipoOperacion;
    }




}
