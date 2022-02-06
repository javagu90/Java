/*
 * TmsRutasTbl.java
 *
 * Created on 29 de julio de 2009, 01:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_bloqueo.entidad;

import java.util.Vector;


public class TmsRutasTbl {

    private String rutaId;    
    private String rutaNumero;    
    private String rutaNombre;
    private String servicioNombre;
    private String origen;
    private String destino;
    private String rutaNumeroNombre;
    
    /** Creates a new instance of TmsRutasTbl */
    public TmsRutasTbl() {
    }

    
    public TmsRutasTbl(Vector rutas) {
        this.rutaId = rutas.get(0).toString();
        this.rutaNombre = rutas.get(1).toString();
        this.rutaNumero = rutas.get(2).toString();        
        rutaNumeroNombre = rutas.get(3).toString();        
        servicioNombre =  rutas.get(4).toString();  
        origen =rutas.get(5).toString();
        destino = rutas.get(6).toString();        
    }

    /**
     * Gets the rutaId of this TmsRutasTbl.
     * @return the rutaId
     */
    public String getRutaId() {
        return this.rutaId;
    }

    /**
     * Sets the rutaId of this TmsRutasTbl to the specified value.
     * @param rutaId the new rutaId
     */
    public void setRutaId(String rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * Gets the rutaNumero of this TmsRutasTbl.
     * @return the rutaNumero
     */
    public String getRutaNumero() {
        return this.rutaNumero;
    }

    /**
     * Sets the rutaNumero of this TmsRutasTbl to the specified value.
     * @param rutaNumero the new rutaNumero
     */
    public void setRutaNumero(String rutaNumero) {
        this.rutaNumero = rutaNumero;
    }

    /**
     * Gets the rutaNombre of this TmsRutasTbl.
     * @return the rutaNombre
     */
    public String getRutaNombre() {
        return this.rutaNombre;
    }

    /**
     * Sets the rutaNombre of this TmsRutasTbl to the specified value.
     * @param rutaNombre the new rutaNombre
     */
    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }

    public String  getRutaNumeroNombre() {
        return this.rutaNumeroNombre;
    }
    
    public String  getServicioNombre() {
        return this.servicioNombre;
    }
    
    public String  getDestino() {
        return this.destino;
    }
    
    public String  getOrigen() {
        return this.origen;
    }       
    
   public String toString(){
       return rutaNumeroNombre;
   }
    
}
