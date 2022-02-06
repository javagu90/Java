/**
 * Fecha de Creacion: 19/01/2011
 */
package tms_excepciones_incidencias.exceptions;

public class ControlException extends Exception {

	/**
	 * Constructor con mensaje de error
	 * 
	 * @param msg
	 *            mensaje de error con la excepcion
	 */
	public ControlException(String msg) {
		super(msg);
	}

	/**
	 * Constructor con mensaje de error y la causa
	 * 
	 * @param msg
	 *            mensaje de error con la excepcion
	 * @param cause
	 *            causa de excepcion
	 */
	public ControlException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
