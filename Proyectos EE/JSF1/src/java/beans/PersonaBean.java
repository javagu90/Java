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
  
    public PersonaBean() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void saludar()
    {
        this.mensaje= "Hola "+ this.nombre+" desde Java Server Faces (JSF)";
    }
}
