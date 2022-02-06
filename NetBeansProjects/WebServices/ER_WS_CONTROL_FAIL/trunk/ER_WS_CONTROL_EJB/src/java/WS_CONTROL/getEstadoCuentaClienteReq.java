/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getEstadoCuentaClienteReq implements Serializable{
    private long  sesionId;
    private String clienteNombre = "";
    private String clienteRFC = "";
    private String ClienteNoCuenta = "";
    private Date fechaInicial;
    private Date FechaFinal;

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
     * @return the clienteNombre
     */
    public String getClienteNombre() {
        return clienteNombre;
    }

    /**
     * @param clienteNombre the clienteNombre to set
     */
    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    /**
     * @return the clienteRFC
     */
    public String getClienteRFC() {
        return clienteRFC;
    }

    /**
     * @param clienteRFC the clienteRFC to set
     */
    public void setClienteRFC(String clienteRFC) {
        this.clienteRFC = clienteRFC;
    }

    /**
     * @return the ClienteNoCuenta
     */
    public String getClienteNoCuenta() {
        return ClienteNoCuenta;
    }

    /**
     * @param ClienteNoCuenta the ClienteNoCuenta to set
     */
    public void setClienteNoCuenta(String ClienteNoCuenta) {
        this.ClienteNoCuenta = ClienteNoCuenta;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the FechaFinal
     */
    public Date getFechaFinal() {
        return FechaFinal;
    }

    /**
     * @param FechaFinal the FechaFinal to set
     */
    public void setFechaFinal(Date FechaFinal) {
        this.FechaFinal = FechaFinal;
    }

}
