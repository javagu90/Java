/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class TramoRuta implements  Serializable{

    private String Origen;
    private String Destino;

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
