/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tms_excepciones_incidencias.exceptions;

/**
 *
 * @author Osvaldo Sanchez
 */
public class ExisteExcepcionIncidenciaException extends Exception{

    private String mensaje;
    /**
     * Contructor con mensaje de error
     * @param mensaje
     */
    public ExisteExcepcionIncidenciaException(String mensaje) {
        this.mensaje=mensaje;
    }
	/**
	 * Constructor con mensaje de error y la causa
	 *
	 * @param msg
	 *            mensaje de error con la excepcion
	 * @param cause
	 *            causa de excepcion
	 */
	public ExisteExcepcionIncidenciaException(String msg, Throwable cause) {
		super(msg, cause);
	}

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

        
}
