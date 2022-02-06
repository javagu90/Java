/*
 * UsuarioNoEncontradoException.java
 *
 * Created on 22 de mayo de 2007, 06:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

/**
 *
 * @author imunoz
 */
public class UsuarioNoEncontradoException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>UsuarioNoEncontradoException</code> without detail message.
     */
    public UsuarioNoEncontradoException() {
    }
    
    
    /**
     * Constructs an instance of <code>UsuarioNoEncontradoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UsuarioNoEncontradoException(String msg) {
        super(msg);
    }
}
