package ar.com.historiasocial.exceptions;
/**
 * Representa un error irrecuperrable desde la capa de DAO
 * 
 * @author Manuel Ortiz - ortizman@gmail.com
 */
public class HSDataAccessRuntimeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3026830614001093873L;

	/**
	 * constructor por defecto 
	 * @param msj
	 */
	public HSDataAccessRuntimeException(String msj) {
		super(msj);
	}
	
	/**
	 * @param t
	 */
	public HSDataAccessRuntimeException(Throwable t) {
		super(t);
	}
	
	/**
	 * @param msj
	 * @param t
	 */
	public HSDataAccessRuntimeException(String msj, Throwable t) {
		super(msj, t);
	}

}
