/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMSS;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class getRutasItinerariosReq  implements Serializable{

    private Long SesionId;
    private String Servicio;
    private String Origen;
    private String Destino;

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
     * @return the Servicio
     */
    public String getServicio() {
        return Servicio;
    }

    /**
     * @param Servicio the Servicio to set
     */
    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    /**
     * @return the Origen
     */
    public String getOrigen() {
        return Origen;
    }

    /**
     * @param Origen the Origen to set
     */
    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    /**
     * @return the Destino
     */
    public String getDestino() {
        return Destino;
    }

    /**
     * @param Destino the Destino to set
     */
    public void setDestino(String Destino) {
        this.Destino = Destino;
    }



}
