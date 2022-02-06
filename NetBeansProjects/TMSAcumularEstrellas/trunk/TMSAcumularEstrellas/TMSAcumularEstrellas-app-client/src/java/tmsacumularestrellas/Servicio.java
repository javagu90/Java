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
public class Servicio {
    private long servicioId = -1;
    private String nombre = "";
    
    /** Creates a new instance of Servicio */
    public Servicio() {
    }

    public Servicio(Vector v) {
        this.setServicioId(Long.valueOf(v.get(0).toString()));
        this.setNombre(v.get(1).toString());
    }

    public long getServicioId() {
        return servicioId;
    }

    public void setServicioId(long servicioId) {
        this.servicioId = servicioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString() {
        return nombre;
    }
    
    
}
