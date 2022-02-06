/*
 * Terminal.java
 *
 * Created on 8 de febrero de 2010, 07:48 PM
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
public class Terminal {
    private String nombre = "";
    private String dbLink = "";
    private String esquema = "";
    private long id=-1;
    

    /** Creates a new instance of Terminal */
    public Terminal() {
    }

    
    public Terminal(Vector v) {
        this.setId(Long.valueOf(v.get(0).toString()));
        this.setNombre(v.get(1).toString());
        this.setEsquema(v.get(2).toString());
        this.setDbLink(v.get(3).toString());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDbLink() {
        return dbLink;
    }

    public void setDbLink(String dbLink) {
        this.dbLink = dbLink;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
       return this.nombre = nombre;
    }    
    
}
