/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getLogoutReq implements Serializable{
   private long  sesionId;
   private String usuarioNumero="";
   private String  usuarioContrasena="";

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
     * @return the usuarioNumero
     */
    public String getUsuarioNumero() {
        return usuarioNumero;
    }

    /**
     * @param usuarioNumero the usuarioNumero to set
     */
    public void setUsuarioNumero(String usuarioNumero) {
        this.usuarioNumero = usuarioNumero;
    }

    /**
     * @return the usuarioContrasena
     */
    public String getUsuarioContrasena() {
        return usuarioContrasena;
    }

    /**
     * @param usuarioContrasena the usuarioContrasena to set
     */
    public void setUsuarioContrasena(String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }

}
