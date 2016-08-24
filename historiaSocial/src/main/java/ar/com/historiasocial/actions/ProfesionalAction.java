package ar.com.historiasocial.actions;

import java.util.List;

import org.apache.log4j.Logger;

//NUEVO
import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.Servicio;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class ProfesionalAction extends ActionSupport implements ModelDriven<Profesional> {

	private static final long		serialVersionUID	= -7286825996081068685L;

	HistoriaSocialDAO				historiaSocialDAO;
	private Profesional				profesional			= new Profesional();
	private String					mensaje;
	private List<Servicio>			servicios;
	private String					oper				= "";

	ProfesionalDAO					profesionalDAO;
	private GenericDAO<Servicio>	servicioDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(ProfesionalAction.class);

	public String creacionProfesional() throws HSControllerException{
		this.setProfesional(new Profesional());
		this.setOper("create");
		servicios = getServicioDAO().retrieveAll();
		return SUCCESS;
	}

	// public String visualizarProfesional() throws HSControllerException {
	// Long ProfesionalId = this.getProfesional().getId();
	// Profesional p = profesionalDAO.retrieveById(ProfesionalId);
	// servicios = (List<Servicio>) servicioDAO.retrieveAll();
	// if (p == null) {
	// return ERROR;
	// }
	//
	// this.setOper("view");
	//
	// this.setProfesional(p);
	// return SUCCESS;
	// }

	public String editarProfesional() throws HSControllerException{
		Long profesionalId = this.getProfesional().getId();
		Profesional p = profesionalDAO.retrieveById(profesionalId);
		servicios = getServicioDAO().retrieveAll();
		if (p == null) { return ERROR; }
		this.setProfesional(p);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarProfesional() throws HSControllerException{
		try {
			this.getProfesional().setId(null);
			profesionalDAO.save(getProfesional());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}

		return SUCCESS;
	}

	public String modificarProfesional() throws HSControllerException{
		profesionalDAO.saveOrUpdate(getProfesional());
		// historiaSocial.setInstitucion(getInstitucion());
		// historiaSocialDAO.saveOrUpdate(historiaSocial);
		return SUCCESS;
	}

	public String eliminarProfesional() throws HSControllerException{
		profesionalDAO.delete(getProfesional().getId());
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

	/**
	 * @return the servicios
	 */
	public List<Servicio> getServicios(){
		return servicios;
	}

	/**
	 * @param servicios
	 *            the servicios to set
	 */
	public void setServicios(List<Servicio> servicios){
		this.servicios = servicios;
	}

	public Profesional getProfesional(){
		return profesional;
	}

	public void setProfesional(Profesional profesional){
		this.profesional = profesional;
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	@Override
	public Profesional getModel(){
		return getProfesional();
	}

	public ProfesionalDAO getProfesionalDAO(){
		return profesionalDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO){
		this.profesionalDAO = profesionalDAO;
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
