package ar.com.historiasocial.actions;

import java.util.List;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.InstitucionDAO;
import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.InstitutionResponsible;
import ar.com.historiasocial.entities.InstitutionType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InstitucionAction extends ActionSupport implements ModelDriven<Institucion> {

	private static final long					serialVersionUID	= 4276180980347448309L;
	private InstitucionDAO						institucionDAO;
	private HistoriaSocialDAO					historiaSocialDAO;
	private GenericDAO<InstitutionType>			tipoInstitucionDAO;
	private GenericDAO<InstitutionResponsible>	responsableDAO;

	private Institucion							institucion			= new Institucion();
	private String								mensaje;
	private List<InstitutionType>				tiposInstituciones;
	private List<InstitutionResponsible>		responsablesInstituciones;
	private String								action				= "";
	
	private static final Logger	LOGGER	= Logger.getLogger(InstitucionAction.class);

	public String creacionInstitucion() throws HSControllerException{
		this.setInstitucion(new Institucion());
		this.setAction("salvarInstitucion.action");
		tiposInstituciones = getTipoInstitucionDAO().retrieveAll();
		responsablesInstituciones = getResponsableDAO().retrieveAll();
		return SUCCESS;
	}

	public String visualizarInstitucion() throws HSControllerException{
		Long institucionId = this.getInstitucion().getId();
		Institucion p = getInstitucionDAO().retrieveById(institucionId);
		tiposInstituciones = getTipoInstitucionDAO().retrieveAll();
		responsablesInstituciones = getResponsableDAO().retrieveAll();
		if (p == null) { return ERROR; }

		this.setAction("view");

		this.setInstitucion(p);
		return SUCCESS;
	}

	public String editarInstitucion() throws HSControllerException{
		Long institucionId = this.getInstitucion().getId();
		Institucion p = getInstitucionDAO().retrieveById(institucionId);
		tiposInstituciones = getTipoInstitucionDAO().retrieveAll();
		responsablesInstituciones = getResponsableDAO().retrieveAll();
		if (p == null) { return ERROR; }
		this.setInstitucion(p);
		this.setAction("modificarInstitucion.action");
		return SUCCESS;
	}

	/*
	 * 
	 * Falla cuando no esta conectado a internet, porque no puede obtener las
	 * coordenadas para guardar el Location del Institucion Corregir poniendo
	 * las coordenadas por defecco.
	 */
	public String salvarInstitucion() throws HSControllerException{
		try {
			this.getInstitucion().setId(null);
			getInstitucionDAO().save(getInstitucion());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}

		return SUCCESS;
	}

	public String modificarInstitucion() throws HSControllerException{
		getInstitucionDAO().saveOrUpdate(getInstitucion());
		return SUCCESS;
	}

	public String eliminarInstitucion() throws HSControllerException{
		getInstitucionDAO().delete(getInstitucion().getId());
		return SUCCESS;
	}

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
	public String getAction(){
		return action;
	}

	/**
	 * @param oper
	 *            the oper to set
	 */
	public void setAction(String oper){
		this.action = oper;
	}

	/**
	 * @return the tiposInstituciones
	 */
	public List<InstitutionType> getTiposInstituciones(){
		return tiposInstituciones;
	}

	/**
	 * @param tiposInstituciones
	 *            the tiposInstituciones to set
	 */
	public void setTiposInstituciones(List<InstitutionType> tiposInstituciones){
		this.tiposInstituciones = tiposInstituciones;
	}

	/**
	 * @return the responsablesInstituciones
	 */
	public List<InstitutionResponsible> getResponsablesInstituciones(){
		return responsablesInstituciones;
	}

	/**
	 * @param responsablesInstituciones
	 *            the responsablesInstituciones to set
	 */
	public void setResponsablesInstituciones(List<InstitutionResponsible> reponsablesInstituciones){
		this.responsablesInstituciones = reponsablesInstituciones;
	}

	public Institucion getInstitucion(){
		return institucion;
	}

	public void setInstitucion(Institucion institucion){
		this.institucion = institucion;
	}

	@Override
	public Institucion getModel(){
		return getInstitucion();
	}

	public InstitucionDAO getinstitucionDAO(){
		return getInstitucionDAO();
	}

	public void setinstitucionDAO(InstitucionDAO institucionDAO){
		this.setInstitucionDAO(institucionDAO);
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	/**
	 * @return the tipoInstitucionDAO
	 */
	public GenericDAO<InstitutionType> getTipoInstitucionDAO(){
		return tipoInstitucionDAO;
	}

	/**
	 * @param tipoInstitucionDAO
	 *            the tipoInstitucionDAO to set
	 */
	public void setTipoInstitucionDAO(GenericDAO<InstitutionType> tipoInstitucionDAO){
		this.tipoInstitucionDAO = tipoInstitucionDAO;
	}

	/**
	 * @return the responsableDAO
	 */
	public GenericDAO<InstitutionResponsible> getResponsableDAO(){
		return responsableDAO;
	}

	/**
	 * @param responsableDAO
	 *            the responsableDAO to set
	 */
	public void setResponsableDAO(GenericDAO<InstitutionResponsible> responsableDAO){
		this.responsableDAO = responsableDAO;
	}

	/**
	 * @return the institucionDAO
	 */
	InstitucionDAO getInstitucionDAO(){
		return institucionDAO;
	}

	/**
	 * @param institucionDAO
	 *            the institucionDAO to set
	 */
	void setInstitucionDAO(InstitucionDAO institucionDAO){
		this.institucionDAO = institucionDAO;
	}

}
