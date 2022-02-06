/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wsc.excepciones;

/**
 *
 * @author imunoz
 */
public class AgenteNoCreadoException extends Exception {

    /**
     * Creates a new instance of <code>AgenteNoCreadoException</code> without detail message.
     */
    public AgenteNoCreadoException() {
    }


    /**
     * Constructs an instance of <code>AgenteNoCreadoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public AgenteNoCreadoException(String msg) {
        super(msg);
    }
}
