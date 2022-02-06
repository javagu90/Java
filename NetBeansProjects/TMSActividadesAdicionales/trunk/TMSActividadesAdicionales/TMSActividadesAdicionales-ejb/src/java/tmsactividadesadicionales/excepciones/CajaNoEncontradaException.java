/*
 * CajaNoEncontradaException.java
 *
 * Created on 18 de octubre de 2007, 01:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.excepciones;

/**
 *
 * @author vgonzalez
 */
public class CajaNoEncontradaException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>CajaNoEncontradaException</code> without detail message.
     */
    public CajaNoEncontradaException() {
    }
    
    
    /**
     * Constructs an instance of <code>CajaNoEncontradaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CajaNoEncontradaException(String msg) {
        super(msg);
    }
}
