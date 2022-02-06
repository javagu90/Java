/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WS_CONTROL.paquer.entidades;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 *
 * @author galbarran
 */
@Embeddable
public class DatosPersona implements Serializable {

    private String nombre = "";
    private String telefono= "";
    private Direccion direccion = new Direccion();
    private String rfc= "";
    @Transient
    private String email="";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
}
