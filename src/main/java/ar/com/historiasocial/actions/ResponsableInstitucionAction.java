package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.entities.InstitutionResponsible;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ResponsableInstitucionAction extends ActionSupport implements ModelDriven<InstitutionResponsible> {

	/**
	 * 
	 */
	private static final long					serialVersionUID		= 7762279508340654479L;

	private HistoriaSocialDAO					historiaSocialDAO;
	// NUEVO
	private InstitutionResponsible				institutionResponsible	= new InstitutionResponsible();
	private String								mensaje;
	private String								oper					= "";

	private GenericDAO<InstitutionResponsible>	responsableDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(ResponsableInstitucionAction.class);

	public String creacionResponsableInstitucion() throws HSControllerException{
		this.setInstitutionResponsible(new InstitutionResponsible());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarResponsableInstitucion() throws HSControllerException{
		Long responsableInstitucionId = this.getInstitutionResponsible().getId();
		InstitutionResponsible ri = getResponsableDAO().retrieveById(responsableInstitucionId);
		if (ri == null) { return ERROR; }
		this.setInstitutionResponsible(ri);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarResponsableInstitucion() throws HSControllerException{
		try {
			this.getInstitutionResponsible().setId(null);
			getResponsableDAO().save(getInstitutionResponsible());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarResponsableInstitucion() throws HSControllerException{
		getResponsableDAO().saveOrUpdate(getInstitutionResponsible());
		return SUCCESS;
	}

	public String eliminarResponsableInstitucion() throws HSControllerException{
		getResponsableDAO().delete(getInstitutionResponsible().getId());
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
	public InstitutionResponsible getModel(){
		return getInstitutionResponsible();
	}

	/**
	 * @return the institutionResponsible
	 */
	public InstitutionResponsible getInstitutionResponsible(){
		return institutionResponsible;
	}

	/**
	 * @param institutionResponsible
	 *            the institutionResponsible to set
	 */
	public void setInstitutionResponsible(InstitutionResponsible institutionResponsible){
		this.institutionResponsible = institutionResponsible;
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

}
