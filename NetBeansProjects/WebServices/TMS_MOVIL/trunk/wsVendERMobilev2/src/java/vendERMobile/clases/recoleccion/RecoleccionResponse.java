/*
 * RecoleccionResponse.java
 *
 * Created on 11 de septiembre de 2009, 09:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.recoleccion;

/**
 *
 * @author asolis
 */
public class RecoleccionResponse {
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    String uid;
    String folioRecoleccion;
    String nombreSupervisor;
    String nombreUsuario;
    
    /** Creates a new instance of RecoleccionResponse */
    public RecoleccionResponse() {
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
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid; 
    }
    
    public String getFolioRecoleccion() {
        return folioRecoleccion;
    }

    public void setFolioRecoleccion(String folioRecoleccion) {
        this.folioRecoleccion = folioRecoleccion; 
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario; 
    }
    
    public String getNombreSupervisor() {
        return nombreSupervisor;
    }

    public void setNombreSupervisor(String nombreSupervisor) {
        this.nombreSupervisor = nombreSupervisor; 
    }
    
    
}
