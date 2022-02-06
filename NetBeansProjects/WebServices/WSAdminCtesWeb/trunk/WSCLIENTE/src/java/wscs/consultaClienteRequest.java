/*
 * clienteValidoRequest.java
 *
 * Created on 23 de septiembre de 2008, 01:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wscs;


/**
 *
 * @author vgonzalez
 */


public class consultaClienteRequest {
    private String uid = "";
    private String email = "";
    private String contrasenia = "";

    /** Creates a new instance of clienteValidoRequest */
    public consultaClienteRequest() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
