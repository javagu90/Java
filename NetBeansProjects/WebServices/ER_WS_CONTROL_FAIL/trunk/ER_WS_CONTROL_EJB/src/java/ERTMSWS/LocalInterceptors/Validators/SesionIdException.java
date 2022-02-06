/*
 * SesionIdException.java
 *
 * Created on 21 de junio de 2010, 01:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ERTMSWS.LocalInterceptors.Validators;

/**
 *
 * @author opalafox
 */
public class SesionIdException extends Exception{
    private String mensaje;
    private String motivo;
    /** Creates a new instance of SesionIdException */
    public SesionIdException(String mensaje, String motivo) {
        this.mensaje = mensaje;
        this.motivo = motivo;
        
    }
    public String getmensaje(){
        return mensaje;
    }
    public String getmotivo(){
        return motivo;
    }
    
}
