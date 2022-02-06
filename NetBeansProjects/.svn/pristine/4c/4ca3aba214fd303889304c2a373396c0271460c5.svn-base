/*
 * Servicio.java
 *
 * Created on 9 de febrero de 2010, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsacumularestrellas;

import java.util.Vector;
/**
 *
 * @author vgonzalez
 */
public class tipoPasajero {
    private long tipoId = -1;
    private String nombre = "";
    private String letra = "";
    
    /** Creates a new instance of Servicio */
    public tipoPasajero() {
    }

    public tipoPasajero(Vector v) {
        this.setTipoId(Long.valueOf(v.get(0).toString()));
        this.setNombre(v.get(1).toString());
        this.setLetra(v.get(2).toString());
    }

    public long getTipoId() {
        return tipoId;
    }

    public void setTipoId(long tipoId) {
        this.tipoId = tipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString() {
        return letra+" - "+nombre;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    
    
}
