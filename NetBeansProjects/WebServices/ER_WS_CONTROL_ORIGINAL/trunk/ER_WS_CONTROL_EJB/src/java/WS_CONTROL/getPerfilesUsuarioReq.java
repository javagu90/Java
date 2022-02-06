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
public class getPerfilesUsuarioReq implements Serializable{
private long  sesionId;
private long  usuarioId = -1;

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
     * @return the usuarioId
     */
    public long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
