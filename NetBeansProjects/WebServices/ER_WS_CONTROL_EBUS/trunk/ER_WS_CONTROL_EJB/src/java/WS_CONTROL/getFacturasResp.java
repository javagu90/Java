/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ErFacturasTbl;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brojas
 */
public class getFacturasResp implements Serializable {

   private ErFacturasTbl factura ;
    private boolean operacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;
    private byte[] FacturaPDF ;
    private String FacturaXML ;


 

    /**
     * @return the operacionExitosa
     */
    public boolean isOperacionExitosa() {
        return operacionExitosa;
    }

    /**
     * @param operacionExitosa the operacionExitosa to set
     */
    public void setOperacionExitosa(boolean operacionExitosa) {
        this.operacionExitosa = operacionExitosa;
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

    /**
     * @return the FacturaPDF
     */
    public byte[] getFacturaPDF() {
        return FacturaPDF;
    }

    /**
     * @param FacturaPDF the FacturaPDF to set
     */
    public void setFacturaPDF(byte[] FacturaPDF) {
        this.FacturaPDF = FacturaPDF;
    }

    /**
     * @return the FacturaXML
     */
    public String getFacturaXML() {
        return FacturaXML;
    }

    /**
     * @param FacturaXML the FacturaXML to set
     */
    public void setFacturaXML(String FacturaXML) {
        this.FacturaXML = FacturaXML;
    }

    /**
     * @return the factura
     */
    public ErFacturasTbl getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(ErFacturasTbl factura) {
        this.factura = factura;
    }


}
