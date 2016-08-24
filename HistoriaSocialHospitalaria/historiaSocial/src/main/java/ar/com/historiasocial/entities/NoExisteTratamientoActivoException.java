package ar.com.historiasocial.entities;

public class NoExisteTratamientoActivoException extends Exception {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 11710582302581571L;
	
	public NoExisteTratamientoActivoException() {
		super();
	}
	
	public NoExisteTratamientoActivoException(String mensaje) {
		super(mensaje);
	}
	
	public NoExisteTratamientoActivoException(Throwable e) {
		super(e);
	}
	
	public NoExisteTratamientoActivoException(String mensaje, Throwable e) {
		super(mensaje, e);
	}

}
