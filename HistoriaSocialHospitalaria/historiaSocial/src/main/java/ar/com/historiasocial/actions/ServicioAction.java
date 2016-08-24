package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.entities.Servicio;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class ServicioAction extends ActionSupport implements ModelDriven<Servicio> {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -5754163382530314972L;

	private HistoriaSocialDAO		historiaSocialDAO;
	private Servicio				servicio			= new Servicio();
	private String					mensaje;
	private String					oper				= "";

	private static final Logger	LOGGER	= Logger.getLogger(ServicioAction.class);
	
	private GenericDAO<Servicio>	servicioDAO;

	public String creacionServicio() throws HSControllerException{
		this.setServicio(new Servicio());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarServicio() throws HSControllerException{
		Long servicioId = this.getServicio().getId();
		Servicio s = getServicioDAO().retrieveById(servicioId);
		if (s == null) { return ERROR; }
		this.setServicio(s);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarServicio() throws HSControllerException{
		try {
			this.getServicio().setId(null);
			getServicioDAO().save(getServicio());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarServicio() throws HSControllerException{
		getServicioDAO().saveOrUpdate(getServicio());
		return SUCCESS;
	}

	public String eliminarServicio() throws HSControllerException{
		getServicioDAO().delete(getServicio().getId());
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

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	@Override
	public Servicio getModel(){
		return getServicio();
	}

	/**
	 * @return the servicio
	 */
	public Servicio getServicio(){
		return servicio;
	}

	/**
	 * @param servicio
	 *            the servicio to set
	 */
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}

	/**
	 * @return the servicioDAO
	 */
	public GenericDAO<Servicio> getServicioDAO(){
		return servicioDAO;
	}

	/**
	 * @param servicioDAO
	 *            the servicioDAO to set
	 */
	public void setServicioDAO(GenericDAO<Servicio> servicioDAO){
		this.servicioDAO = servicioDAO;
	}

}
