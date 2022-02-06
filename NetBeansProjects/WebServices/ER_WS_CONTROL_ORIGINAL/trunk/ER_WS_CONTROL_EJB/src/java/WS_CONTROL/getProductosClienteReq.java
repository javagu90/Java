/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class getProductosClienteReq implements  Serializable {

private long  sesionId;
private String clienteId;
private String referencia;
private String nombreProducto;

    /**
     * @return the sesionId
     */
    public long getSesionId() {
        return sesionId;
    }

    /**
     * @param sesionId the sesionId to set
     */
    public void setSesionId(long sesionId) {
        this.sesionId = sesionId;
    }

    /**
     * @return the clienteId
     */
    public String getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }



}
