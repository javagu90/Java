/*
 * Boleto.java
 *
 * Created on 27 de marzo de 2010, 12:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsacumularestrellas;

/**
 *
 * @author vgonzalez
 */
public class Boleto {

    private String folio;
    private String tipoPasajeroLetra;
    private long tipoPasajeroId;
    private float precio;
    private String numeroAsiento;
    private String fechaVenta;
    private String nombrePasajero;
    private String aplica;
    private String abierto;
    private String NoTarAcum; 
    
    /** Creates a new instance of Boleto */
    public Boleto(){
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getTipoPasajeroLetra() {
        return tipoPasajeroLetra;
    }

    public void setTipoPasajeroLetra(String tipoPasajeroLetra) {
        this.tipoPasajeroLetra = tipoPasajeroLetra;
    }

    public long getTipoPasajeroId() {
        return tipoPasajeroId;
    }

    public void setTipoPasajeroId(long tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public String toString(){
        String cadena=   ""+folio+","+tipoPasajeroLetra+","+tipoPasajeroId+","+precio+","+numeroAsiento;
        return cadena;
    }   

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getAplica() {
        return aplica;
    }

    public void setAplica(String aplica) {
        this.aplica = aplica;
    }

    public String getNoTarAcum() {
        return NoTarAcum;
    }

    public void setNoTarAcum(String NoTarAcum) {
        this.NoTarAcum = NoTarAcum;
    }

    public String getAbierto() {
        return abierto;
    }

    public void setAbierto(String abierto) {
        this.abierto = abierto;
    }

}
