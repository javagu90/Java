/*
 * NoBloqueoFoliosException.java
 *
 * Created on 12 de enero de 2008, 11:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.util;

/**
 *
 * @author ocruz
 */
public class NoBloqueoFoliosException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>NoBloqueoFoliosException</code> without detail message.
     */
    public NoBloqueoFoliosException() {
    }
    
    
    /**
     * Constructs an instance of <code>NoBloqueoFoliosException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NoBloqueoFoliosException(String msg) {
        super(msg);
    }
}
