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
public class getClientesReq implements Serializable {
    private Long SesionId;
    private String clienteNombre;
    private String clienteRFC;
    private String ClienteNoCuenta;
    private String email;
    private String contrasena;

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }



}
