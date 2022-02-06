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
public class getUsuariosReq implements Serializable {
   private Long SesionId;
    private String UsuarioNombre;
    private String usuarioNumero;

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
     * @return the UsuarioNombre
     */
    public String getUsuarioNombre() {
        return UsuarioNombre;
    }

    /**
     * @param UsuarioNombre the UsuarioNombre to set
     */
    public void setUsuarioNombre(String UsuarioNombre) {
        this.UsuarioNombre = UsuarioNombre;
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

}
