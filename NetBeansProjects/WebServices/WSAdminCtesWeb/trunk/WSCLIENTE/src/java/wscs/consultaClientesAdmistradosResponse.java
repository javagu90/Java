/*
 * clienteValidoResponse.java
 *
 * Created on 26 de septiembre de 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wscs;

import java.util.Vector;
import wsc.entidades.Cliente;

/**
 *
 * @author vgonzalez
 */
public class consultaClientesAdmistradosResponse {
    private String uid = "";
    private boolean success = false;
    private int errorCode = 0;
    private String errorMsg = "";
    private Vector<Cliente> clientesAdministrados;
    
    /** Creates a new instance of consultaClienteResponse */
    public consultaClientesAdmistradosResponse() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Vector<Cliente>  getClientesAdministrados() {
        return clientesAdministrados;
    }

    public void setClientesAdministrados(Vector<Cliente>  clientes) {
        this.clientesAdministrados = clientes;
    }
    
}
