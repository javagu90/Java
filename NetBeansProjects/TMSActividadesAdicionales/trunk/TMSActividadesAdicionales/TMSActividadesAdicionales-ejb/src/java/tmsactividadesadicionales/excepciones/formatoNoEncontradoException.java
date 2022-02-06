/*
 * formatoNoEncontradoException.java
 *
 * Created on 18 de octubre de 2007, 08:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.excepciones;

/**
 *
 * @author vgonzalez
 */
public class formatoNoEncontradoException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>formatoNoEncontradoException</code> without detail message.
     */
    public formatoNoEncontradoException() {
    }
    
    
    /**
     * Constructs an instance of <code>formatoNoEncontradoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public formatoNoEncontradoException(String msg) {
        super(msg);
    }
}
