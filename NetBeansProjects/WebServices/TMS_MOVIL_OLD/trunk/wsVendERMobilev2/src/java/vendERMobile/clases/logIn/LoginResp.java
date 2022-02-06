/*
 * LoginResp.java
 *
 * Created on 26 de noviembre de 2008, 07:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.logIn;

import vendERMobile.clases.datos.Sesion;


/**
 *
 * @author ocruz
 */
public class LoginResp {
    
    private String uid;
    private Sesion sesion;
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    private String nombreUsuario;
    /**
     * Creates a new instance of LoginResp
     */
    public LoginResp() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
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
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
