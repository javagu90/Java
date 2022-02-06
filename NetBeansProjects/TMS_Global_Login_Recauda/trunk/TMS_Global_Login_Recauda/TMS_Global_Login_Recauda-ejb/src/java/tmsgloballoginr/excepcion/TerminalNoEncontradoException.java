/*
 * TerminalNoEncontradoException.java
 *
 * Created on 22 de mayo de 2007, 06:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsgloballoginr.excepcion;

/**
 *
 * @author imunoz
 */
public class TerminalNoEncontradoException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>TerminalNoEncontradoException</code> without detail message.
     */
    public TerminalNoEncontradoException() {
    }
    
    
    /**
     * Constructs an instance of <code>TerminalNoEncontradoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public TerminalNoEncontradoException(String msg) {
        super(msg);
    }
}
