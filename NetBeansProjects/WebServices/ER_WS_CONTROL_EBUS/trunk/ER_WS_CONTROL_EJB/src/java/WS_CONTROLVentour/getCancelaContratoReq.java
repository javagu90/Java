/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROLVentour;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class getCancelaContratoReq implements Serializable {
  private Long SesionId;
    private String Contrato_ID;
    private String ReferenciaContrato;

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
     * @return the Contrato_ID
     */
    public String getContrato_ID() {
        return Contrato_ID;
    }

    /**
     * @param Contrato_ID the Contrato_ID to set
     */
    public void setContrato_ID(String Contrato_ID) {
        this.Contrato_ID = Contrato_ID;
    }

    /**
     * @return the ReferenciaContrato
     */
    public String getReferenciaContrato() {
        return ReferenciaContrato;
    }

    /**
     * @param ReferenciaContrato the ReferenciaContrato to set
     */
    public void setReferenciaContrato(String ReferenciaContrato) {
        this.ReferenciaContrato = ReferenciaContrato;
    }


}
