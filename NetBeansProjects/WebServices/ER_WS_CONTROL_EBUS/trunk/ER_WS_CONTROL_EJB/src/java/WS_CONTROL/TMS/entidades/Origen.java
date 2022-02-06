/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMS.entidades;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class Origen implements Serializable {
    private String origen="";
    private String descripcion="";
    private Destino[] destinos;

    public Origen(){

    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the destinos
     */
    public Destino[] getDestinos() {
        return destinos;
    }

    /**
     * @param destinos the destinos to set
     */
    public void setDestinos(Destino[] destinos) {
        this.destinos = destinos;
    }


}
