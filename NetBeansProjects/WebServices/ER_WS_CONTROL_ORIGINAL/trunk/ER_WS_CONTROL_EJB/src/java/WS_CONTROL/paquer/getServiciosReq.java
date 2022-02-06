/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.paquer;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getServiciosReq  implements Serializable{
    private long  sesionId;
    private String clasificacion="";//MOSTRADOR, MENSAJERIA LOCAL, GUIAS PREPAGADAS, CARGA

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
     * @return the clasificacion
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}
