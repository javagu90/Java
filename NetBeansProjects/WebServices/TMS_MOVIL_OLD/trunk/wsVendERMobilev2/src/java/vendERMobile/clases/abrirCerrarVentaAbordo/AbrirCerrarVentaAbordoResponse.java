/*
 * AbrirCerrarVentaAbordoResponse.java
 *
 * Created on 22 de septiembre de 2009, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.abrirCerrarVentaAbordo;

/**
 *
 * @author asolis
 */
public class AbrirCerrarVentaAbordoResponse {
    private String uid;
    private boolean success;
    private int errorCode;
    private String errorMsg;
    int idVentaAbierta;
    
    /** Creates a new instance of AbrirCerrarVentaAbordoResponse */
    public AbrirCerrarVentaAbordoResponse() {
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
    
    public int getIdVentaAbierta() {
        return idVentaAbierta;
    }

    public void setIdVentaAbierta(int idVentaAbierta) {
        this.idVentaAbierta = idVentaAbierta;
    }

}
