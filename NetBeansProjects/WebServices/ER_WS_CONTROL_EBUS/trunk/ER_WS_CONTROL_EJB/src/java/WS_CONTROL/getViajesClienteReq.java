/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author vgonzalez
 */
public class getViajesClienteReq implements Serializable{
    private BigInteger sesionId;
    private BigInteger clienteId;
    private BigInteger numInicialTransacciones;
    private BigInteger numFinalTransacciones;
    private String TipoConsulta="I";

    public getViajesClienteReq(){

    }

    /**
     * @return the sesionId
     */
    public BigInteger getSesionId() {
        return sesionId;
    }

    /**
     * @param sesionId the sesionId to set
     */
    public void setSesionId(BigInteger sesionId) {
        this.sesionId = sesionId;
    }

    /**
     * @return the clienteId
     */
    public BigInteger getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the numInicialTransacciones
     */
    public BigInteger getNumInicialTransacciones() {
        return numInicialTransacciones;
    }

    /**
     * @param numInicialTransacciones the numInicialTransacciones to set
     */
    public void setNumInicialTransacciones(BigInteger numInicialTransacciones) {
        this.numInicialTransacciones = numInicialTransacciones;
    }

    /**
     * @return the numFinalTransacciones
     */
    public BigInteger getNumFinalTransacciones() {
        return numFinalTransacciones;
    }

    /**
     * @param numFinalTransacciones the numFinalTransacciones to set
     */
    public void setNumFinalTransacciones(BigInteger numFinalTransacciones) {
        this.numFinalTransacciones = numFinalTransacciones;
    }

    /**
     * @return the TipoConsulta
     */
    public String getTipoConsulta() {
        return TipoConsulta;
    }

    /**
     * @param TipoConsulta the TipoConsulta to set
     */
    public void setTipoConsulta(String TipoConsulta) {
        this.TipoConsulta = TipoConsulta;
    }

}
