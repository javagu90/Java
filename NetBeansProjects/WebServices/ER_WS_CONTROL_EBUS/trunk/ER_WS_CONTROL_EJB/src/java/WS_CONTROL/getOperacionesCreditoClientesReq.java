/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.ClienteComisiones;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getOperacionesCreditoClientesReq implements Serializable{
private long  sesionId;
private long clienteId;
private List<ClienteComisiones> clienteComisiones;
private String noCuenta = "";
private String referencia = "";
private String creditoActivo;
private float limiteCredito;
private String aplicaRetencion;

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
    public long getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the clienteComisiones
     */
    public List<ClienteComisiones> getClienteComisiones() {
        return clienteComisiones;
    }

    /**
     * @param clienteComisiones the clienteComisiones to set
     */
    public void setClienteComisiones(List<ClienteComisiones> clienteComisiones) {
        this.clienteComisiones = clienteComisiones;
    }

    /**
     * @return the noCuenta
     */
    public String getNoCuenta() {
        return noCuenta;
    }

    /**
     * @param noCuenta the noCuenta to set
     */
    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
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
     * @return the limiteCredito
     */
    public float getLimiteCredito() {
        return limiteCredito;
    }

    /**
     * @param limiteCredito the limiteCredito to set
     */
    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    /**
     * @return the creditoActivo
     */
    public String getCreditoActivo() {
        return creditoActivo;
    }

    /**
     * @param creditoActivo the creditoActivo to set
     */
    public void setCreditoActivo(String creditoActivo) {
        this.creditoActivo = creditoActivo;
    }

    /**
     * @return the aplicaRetencion
     */
    public String getAplicaRetencion() {
        return aplicaRetencion;
    }

    /**
     * @param aplicaRetencion the aplicaRetencion to set
     */
    public void setAplicaRetencion(String aplicaRetencion) {
        this.aplicaRetencion = aplicaRetencion;
    }



}
