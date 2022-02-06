/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tmspcweb.clases;

/**
 *
 * @author vgonzalez
 */
public class reservarAsientosCDIResponse {
    private String reservacionIda = "";
    private String reservacionRegreso = "";
    private String nombrePasajeros="";
    private String tipoPasajeros="";
    private String asientosIda="";
    private String asientosRegreso="";
    private String precio="";
    private boolean success;
    private String errorMsg="";


    public reservarAsientosCDIResponse(){

    }

    /**
     * @return the reservacionIda
     */
    public String getReservacionIda() {
        return reservacionIda;
    }

    /**
     * @param reservacionIda the reservacionIda to set
     */
    public void setReservacionIda(String reservacionIda) {
        this.reservacionIda = reservacionIda;
    }

    /**
     * @return the reservacionRegreso
     */
    public String getReservacionRegreso() {
        return reservacionRegreso;
    }

    /**
     * @param reservacionRegreso the reservacionRegreso to set
     */
    public void setReservacionRegreso(String reservacionRegreso) {
        this.reservacionRegreso = reservacionRegreso;
    }

    /**
     * @return the nombrePasajeros
     */
    public String getNombrePasajeros() {
        return nombrePasajeros;
    }

    /**
     * @param nombrePasajeros the nombrePasajeros to set
     */
    public void setNombrePasajeros(String nombrePasajeros) {
        this.nombrePasajeros = nombrePasajeros;
    }

    /**
     * @return the tipoPasajeros
     */
    public String getTipoPasajeros() {
        return tipoPasajeros;
    }

    /**
     * @param tipoPasajeros the tipoPasajeros to set
     */
    public void setTipoPasajeros(String tipoPasajeros) {
        this.tipoPasajeros = tipoPasajeros;
    }

    /**
     * @return the asientosIda
     */
    public String getAsientosIda() {
        return asientosIda;
    }

    /**
     * @param asientosIda the asientosIda to set
     */
    public void setAsientosIda(String asientosIda) {
        this.asientosIda = asientosIda;
    }

    /**
     * @return the asientosRegreso
     */
    public String getAsientosRegreso() {
        return asientosRegreso;
    }

    /**
     * @param asientosRegreso the asientosRegreso to set
     */
    public void setAsientosRegreso(String asientosRegreso) {
        this.asientosRegreso = asientosRegreso;
    }

    /**
     * @return the precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
}
