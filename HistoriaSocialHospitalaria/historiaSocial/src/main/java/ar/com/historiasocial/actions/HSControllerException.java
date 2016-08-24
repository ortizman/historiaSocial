/**
 * Manuel Ortiz - ortizman@gmail.com
 */
package ar.com.historiasocial.actions;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class HSControllerException extends Exception {

	/**
	 * Serial AutoGenerate
	 */
	private static final long	serialVersionUID	= -3463553975372444146L;

	/**
	 * Constructor por defecto
	 */
	public HSControllerException() {

	}

	/**
	 * 
	 * @param message
	 */
	public HSControllerException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public HSControllerException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HSControllerException(String message, Throwable cause) {
		super(message, cause);
	}

}
