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
public class getRutaServiciosReq implements Serializable{
private long  sesionId;
private String CPOrigen = "";
private String CiudadOrigen = "";
private String CPDestino = "";
private String CiudadDestino = "";
private String clasificacion = "";


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
     * @return the CPOrigen
     */
    public String getCPOrigen() {
        return CPOrigen;
    }

    /**
     * @param CPOrigen the CPOrigen to set
     */
    public void setCPOrigen(String CPOrigen) {
        this.CPOrigen = CPOrigen;
    }

    /**
     * @return the CiudadOrigen
     */
    public String getCiudadOrigen() {
        return CiudadOrigen;
    }

    /**
     * @param CiudadOrigen the CiudadOrigen to set
     */
    public void setCiudadOrigen(String CiudadOrigen) {
        this.CiudadOrigen = CiudadOrigen;
    }

    /**
     * @return the CPDestino
     */
    public String getCPDestino() {
        return CPDestino;
    }

    /**
     * @param CPDestino the CPDestino to set
     */
    public void setCPDestino(String CPDestino) {
        this.CPDestino = CPDestino;
    }

    /**
     * @return the CiudadDestino
     */
    public String getCiudadDestino() {
        return CiudadDestino;
    }

    /**
     * @param CiudadDestino the CiudadDestino to set
     */
    public void setCiudadDestino(String CiudadDestino) {
        this.CiudadDestino = CiudadDestino;
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
