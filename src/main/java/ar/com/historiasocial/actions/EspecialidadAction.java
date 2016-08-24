package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Especialidad;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz
 */
public class EspecialidadAction extends ActionSupport implements ModelDriven<Especialidad> {

	/**
	 * 
	 */
	private static final long			serialVersionUID	= 6035561456421237535L;

	private static final Logger	LOGGER	= Logger.getLogger(EspecialidadAction.class);

	private Especialidad				especialidad					= new Especialidad();
	private String						mensaje;
	private String						oper				= "";

	private GenericDAO<Especialidad>	especialidadDAO;

	public String creacionEspecialidad() throws HSControllerException{
		this.setEspecialidad(new Especialidad());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarEspecialidad() throws HSControllerException{
		Long especialidadId = this.getEspecialidad().getId();
		Especialidad e = getEspecialidadDAO().retrieveById(especialidadId);
		if (e == null) { return ERROR; }
		this.setEspecialidad(e);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarEspecialidad() throws HSControllerException{
		try {
			this.getEspecialidad().setId(null);
			getEspecialidadDAO().save(getEspecialidad());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarEspecialidad() throws HSControllerException{
		getEspecialidadDAO().saveOrUpdate(getEspecialidad());
		return SUCCESS;
	}

	public String eliminarEspecialidad() throws HSControllerException{
		getEspecialidadDAO().delete(getEspecialidad().getId());
		return SUCCESS;
	}

	// GETTERS Y SETTERS

	/**
	 * @return the mensaje
	 */
	public String getMensaje(){
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje){
		this.mensaje = mensaje;
	}

	/**
	 * @return the oper
	 */
	public String getOper(){
		return oper;
	}

	/**
	 * @param oper
	 *            the oper to set
	 */
	public void setOper(String oper){
		this.oper = oper;
	}

	@Override
	public Especialidad getModel(){
		return getEspecialidad();
	}

	/**
	 * @return the especialidad
	 */
	public Especialidad getEspecialidad(){
		return especialidad;
	}

	/**
	 * @param especialidad
	 *            the especialidad to set
	 */
	public void setEspecialidad(Especialidad especialidad){
		this.especialidad = especialidad;
	}

	/**
	 * @return the especialidadDAO
	 */
	public GenericDAO<Especialidad> getEspecialidadDAO(){
		return especialidadDAO;
	}

	/**
	 * @param especialidadDAO
	 *            the especialidadDAO to set
	 */
	public void setEspecialidadDAO(GenericDAO<Especialidad> especialidadDAO){
		this.especialidadDAO = especialidadDAO;
	}

}
