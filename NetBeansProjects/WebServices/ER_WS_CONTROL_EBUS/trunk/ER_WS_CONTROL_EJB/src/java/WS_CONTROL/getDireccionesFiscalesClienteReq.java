/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author vgonzalez
 */
public class getDireccionesFiscalesClienteReq implements Serializable {
    private Long SesionId;
    private Long clienteId;
    private BigDecimal clientesDirFiscalesId=BigDecimal.ZERO;

    public getDireccionesFiscalesClienteReq(){

    }

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
     * @return the clienteId
     */
    public Long getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the clientesDirFiscalesId
     */
    public BigDecimal getClientesDirFiscalesId() {
        return clientesDirFiscalesId;
    }

    /**
     * @param clientesDirFiscalesId the clientesDirFiscalesId to set
     */
    public void setClientesDirFiscalesId(BigDecimal clientesDirFiscalesId) {
        this.clientesDirFiscalesId = clientesDirFiscalesId;
    }

}
