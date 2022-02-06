/*
 * Sesion.java
 *
 * Created on 27 de noviembre de 2008, 09:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.datos;

/**
 *
 * @author ocruz
 */
public class Sesion {       
    private int idCanal;
    private String idSucursal;
    private String idEstacionTrabajo;    
    private String IdSesion;
    private String numUsuario;    
    private String contrasenia;
        
    
    /** Creates a new instance of Sesion */
    public Sesion() {
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getIdEstacionTrabajo() {
        return idEstacionTrabajo;
    }

    public void setIdEstacionTrabajo(String idEstacionTrabajo) {
        this.idEstacionTrabajo = idEstacionTrabajo;
    }

    public String getIdSesion() {
        return IdSesion;
    }

    public void setIdSesion(String IdSesion) {
        this.IdSesion = IdSesion;
    }

    public String getNumUsuario() {
        return numUsuario;
    }

    public void setNumUsuario(String usuario) {
        this.numUsuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
