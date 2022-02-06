/*
 * ErrorMessage.java
 *
 * Created on 22 de junio de 2010, 11:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ERTMSWS.LocalInterceptors.Validators;

/**
 *
 * @author opalafox
 */
public class ErrorMessage {
    private int errorCode;
    private String mensaje;
    
    /** Creates a new instance of ErrorMessage */
    public ErrorMessage(int errorCode,String mensaje) {
        this.errorCode = errorCode;
        this.mensaje = mensaje;
    }

    public int geterrorCode() {
        return errorCode;
    }

    public String getmensaje() {
        return mensaje;
    }
    
}
