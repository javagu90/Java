/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tmspcweb.clases;

/**
 *
 * @author vgonzalez
 */
public class reservarAsientosCDIRequest {
   private String origenIda="";
    private String origenRegreso = "";
    private String numeroAsientos = "";
    private String nombrePasajero = "";
    private String fechaIda = "";
    private String fechaRegreso = "";

    public reservarAsientosCDIRequest()
    {

    }

    /**
     * @return the origenIda
     */
    public String getOrigenIda() {
        return origenIda;
    }

    /**
     * @param origenIda the origenIda to set
     */
    public void setOrigenIda(String origenIda) {
        this.origenIda = origenIda;
    }

    /**
     * @return the origenRegreso
     */
    public String getOrigenRegreso() {
        return origenRegreso;
    }

    /**
     * @param origenRegreso the origenRegreso to set
     */
    public void setOrigenRegreso(String origenRegreso) {
        this.origenRegreso = origenRegreso;
    }

    /**
     * @return the numeroAsientos
     */
    public String getNumeroAsientos() {
        return numeroAsientos;
    }

    /**
     * @param numeroAsientos the numeroAsientos to set
     */
    public void setNumeroAsientos(String numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    /**
     * @return the nombrePasajero
     */
    public String getNombrePasajero() {
        return nombrePasajero;
    }

    /**
     * @param nombrePasajero the nombrePasajero to set
     */
    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    /**
     * @return the fechaIda
     */
    public String getFechaIda() {
        return fechaIda;
    }

    /**
     * @param fechaIda the fechaIda to set
     */
    public void setFechaIda(String fechaIda) {
        this.fechaIda = fechaIda;
    }

    /**
     * @return the fechaRegreso
     */
    public String getFechaRegreso() {
        return fechaRegreso;
    }

    /**
     * @param fechaRegreso the fechaRegreso to set
     */
    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

}
