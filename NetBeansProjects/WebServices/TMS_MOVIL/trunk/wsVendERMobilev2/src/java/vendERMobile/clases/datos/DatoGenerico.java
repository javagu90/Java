/*
 * DatoGenerico.java
 *
 * Created on 14 de septiembre de 2009, 06:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.datos;

import java.util.Vector;

/**
 *
 * @author asolis
 */
public class DatoGenerico {
    int id;
    String clave;
    String nombre;
    /** Creates a new instance of DatoGenerico */
    public DatoGenerico() {
    }
    
    public DatoGenerico(String id, String clave, String nombre) {
        setId(Integer.parseInt(id));
        setClave(clave);
        setNombre(nombre);
    }
    
    
    public DatoGenerico(Vector dato) {
        setId(Integer.parseInt(dato.get(0).toString()));
        setClave(dato.get(1).toString());
        setNombre(dato.get(2).toString());
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getClave(){
        return this.clave;
    }
    public void setClave(String clave){
        this.clave = clave;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
