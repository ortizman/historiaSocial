package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.CondicionHabitacional;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz
 * 
 */
public class CondicionHabitacionalAction extends ActionSupport implements ModelDriven<CondicionHabitacional> {

	/**
	 * 
	 */
	private static final long					serialVersionUID		= 6035561456421237535L;

	private CondicionHabitacional				condicionHabitacional	= new CondicionHabitacional();
	private String								mensaje;
	private String								oper					= "";

	private GenericDAO<CondicionHabitacional>	condicionHabitacionalDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(CondicionHabitacionalAction.class);

	public String creacionCondicionHabitacional() throws HSControllerException{
		this.setCondicionHabitacional(new CondicionHabitacional());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarCondicionHabitacional() throws HSControllerException{
		Long diagbosticoId = this.getCondicionHabitacional().getId();
		CondicionHabitacional condHabit = getCondicionHabitacionalDAO().retrieveById(diagbosticoId);

		if (condHabit == null) { return ERROR; }

		this.setCondicionHabitacional(condHabit);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarCondicionHabitacional() throws HSControllerException{
		try {
			this.getCondicionHabitacional().setId(null);
			getCondicionHabitacionalDAO().save(getCondicionHabitacional());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarCondicionHabitacional() throws HSControllerException{
		getCondicionHabitacionalDAO().saveOrUpdate(getCondicionHabitacional());
		return SUCCESS;
	}

	public String eliminarCondicionHabitacional() throws HSControllerException{
		getCondicionHabitacionalDAO().delete(getCondicionHabitacional().getId());
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
	public CondicionHabitacional getModel(){
		return getCondicionHabitacional();
	}

	public CondicionHabitacional getCondicionHabitacional(){
		return condicionHabitacional;
	}

	public void setCondicionHabitacional(CondicionHabitacional condicionHabitacional){
		this.condicionHabitacional = condicionHabitacional;
	}

	/**
	 * @param condicionHabitacionalDAO
	 *            the condicionHabitacionalDAO to set
	 */
	public void setCondicionHabitacionalDAO(GenericDAO<CondicionHabitacional> condicionHabitacionalDAO){
		this.condicionHabitacionalDAO = condicionHabitacionalDAO;
	}

	/**
	 * @return the condicionHabitacionalDAO
	 */
	public GenericDAO<CondicionHabitacional> getCondicionHabitacionalDAO(){
		return condicionHabitacionalDAO;
	}

}
