/*
 * validaClienteResponse.java
 *
 * Created on 26 de septiembre de 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wscs;

/**
 *
 * @author vgonzalez
 */
public class reestableceContraseniaResponse {
    private String uid = "";
    private String contrasenia = "";
    private boolean success = false;
    private int errorCode = 0;
    private String errorMsg = "";
    
    
    /**
     * Creates a new instance of validaClienteResponse
     */
    public reestableceContraseniaResponse() {
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
    
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
