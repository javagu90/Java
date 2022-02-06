/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Javy
 */
@ManagedBean 
@RequestScoped
public class PersonaBean {
    
    private String nombre;
    private String mensaje;

    /**
     * Creates a new instance of PersonaBean
     */
    public PersonaBean() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void saludar()
    {
        this.mensaje= "Hola: "+this.nombre+" desde JSF+ Bootstrap"; 
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
