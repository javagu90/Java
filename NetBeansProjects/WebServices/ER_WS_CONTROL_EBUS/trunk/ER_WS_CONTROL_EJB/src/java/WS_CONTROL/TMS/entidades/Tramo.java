/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMS.entidades;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class Tramo implements Serializable{
private Long Tramo_Numero;
    private String Origen;
    private String Destino;
    private String OrigenDescripcion;
    private String DestinoDescripcion;
    private Long Tiempo_Desface;
    private float tarifaTramoAdulto;
    /**
     * @return the Tramo_Numero
     */
    public Long getTramo_Numero() {
        return Tramo_Numero;
    }

    /**
     * @param Tramo_Numero the Tramo_Numero to set
     */
    public void setTramo_Numero(Long Tramo_Numero) {
        this.Tramo_Numero = Tramo_Numero;
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

    /**
     * @return the Tiempo_Desface
     */
    public Long getTiempo_Desface() {
        return Tiempo_Desface;
    }

    /**
     * @param Tiempo_Desface the Tiempo_Desface to set
     */
    public void setTiempo_Desface(Long Tiempo_Desface) {
        this.Tiempo_Desface = Tiempo_Desface;
    }


    /**
     * @return the OrigenDescripcion
     */
   // public String getOrigenDescripcion() {
   //     return OrigenDescripcion;
  //  }

    /**
     * @param OrigenDescripcion the OrigenDescripcion to set
     */
  //  public void setOrigenDescripcion(String OrigenDescripcion) {
  //      this.OrigenDescripcion = OrigenDescripcion;
  //  }

    /**
     * @return the DestinoDescripcion
     */
    public String getDestinoDescripcion() {
        return DestinoDescripcion;
    }

    /**
     * @param DestinoDescripcion the DestinoDescripcion to set
     */
    public void setDestinoDescripcion(String DestinoDescripcion) {
        this.DestinoDescripcion = DestinoDescripcion;
    }

   


    /**
     * @return the OrigenDescripcion
     */
    public String getOrigenDescripcion() {
        return OrigenDescripcion;
    }

    /**
     * @param OrigenDescripcion the OrigenDescripcion to set
     */
    public void setOrigenDescripcion(String OrigenDescripcion) {
        this.OrigenDescripcion = OrigenDescripcion;
    }

    /**
     * @return the tarifaTramoAdulto
     */
    public float getTarifaTramoAdulto() {
        return tarifaTramoAdulto;
    }

    /**
     * @param tarifaTramoAdulto the tarifaTramoAdulto to set
     */
    public void setTarifaTramoAdulto(float tarifaTramoAdulto) {
        this.tarifaTramoAdulto = tarifaTramoAdulto;
    }

   
}
