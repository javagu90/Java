/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.estrellaroja.TMSFacturaElectronicaWS;

/**
 *
 * @author cpalalia
 */
public class Servicio {
    private int servicioId;
    private String servicioClave;
    private int servicioNumero;
    private String servicioNombre;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getServicioClave() {
        return servicioClave;
    }

    public void setServicioClave(String servicioClave) {
        this.servicioClave = servicioClave;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public int getServicioNumero() {
        return servicioNumero;
    }

    public void setServicioNumero(int servicioNumero) {
        this.servicioNumero = servicioNumero;
    }
    
}
