/*
 * DatoGenerico.java
 *
 * Created on 27 de noviembre de 2008, 09:44 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

/**
 *
 * @author ocruz
 */
public class DatoGenerico {    
    private String id;
    private String clave;
    private String nombre;

    /** Creates a new instance of DatoGenerico */
    public DatoGenerico() {
    }
     
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
