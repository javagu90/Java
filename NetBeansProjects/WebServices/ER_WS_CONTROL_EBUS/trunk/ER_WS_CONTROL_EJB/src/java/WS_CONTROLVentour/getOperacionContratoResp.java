/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROLVentour;

import WS_CONTROL.Ventour.entidades.Contrato;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author brojas
 */
public class getOperacionContratoResp implements Serializable{
  private Long Contrato_ID;
    private String Cotizacion_Referencia;
    private BigDecimal Importe_Contrato;
   private boolean OperacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the Contrato_ID
     */
    public Long getContrato_ID() {
        return Contrato_ID;
    }

    /**
     * @param Contrato_ID the Contrato_ID to set
     */
    public void setContrato_ID(Long Contrato_ID) {
        this.Contrato_ID = Contrato_ID;
    }

    /**
     * @return the Cotizacion_Referencia
     */
    public String getCotizacion_Referencia() {
        return Cotizacion_Referencia;
    }

    /**
     * @param Cotizacion_Referencia the Cotizacion_Referencia to set
     */
    public void setCotizacion_Referencia(String Cotizacion_Referencia) {
        this.Cotizacion_Referencia = Cotizacion_Referencia;
    }

    /**
     * @return the Importe_Contrato
     */
    public BigDecimal getImporte_Contrato() {
        return Importe_Contrato;
    }

    /**
     * @param Importe_Contrato the Importe_Contrato to set
     */
    public void setImporte_Contrato(BigDecimal Importe_Contrato) {
        this.Importe_Contrato = Importe_Contrato;
    }

    /**
     * @return the OperacionExitosa
     */
    public boolean isOperacionExitosa() {
        return OperacionExitosa;
    }

    /**
     * @param OperacionExitosa the OperacionExitosa to set
     */
    public void setOperacionExitosa(boolean OperacionExitosa) {
        this.OperacionExitosa = OperacionExitosa;
    }

    /**
     * @return the ErrorCode
     */
    public String getErrorCode() {
        return ErrorCode;
    }

    /**
     * @param ErrorCode the ErrorCode to set
     */
    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    /**
     * @return the ErrorMsg
     */
    public String getErrorMsg() {
        return ErrorMsg;
    }

    /**
     * @param ErrorMsg the ErrorMsg to set
     */
    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }



}
