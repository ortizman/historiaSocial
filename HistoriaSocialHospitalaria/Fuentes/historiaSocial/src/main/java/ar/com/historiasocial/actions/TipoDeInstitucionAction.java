package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.entities.InstitutionType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class TipoDeInstitucionAction extends ActionSupport implements ModelDriven<InstitutionType> {

	private static final long			serialVersionUID	= 7762279508340654479L;

	private HistoriaSocialDAO			historiaSocialDAO;
	private InstitutionType				institutionType		= new InstitutionType();
	private String						mensaje;
	private String						oper				= "";

	private GenericDAO<InstitutionType>	institutionTypeDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(TipoDeInstitucionAction.class);

	public String creacionTipoDeInstitucion() throws HSControllerException{
		this.setInstitutionType(new InstitutionType());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarTipoDeInstitucion() throws HSControllerException{
		Long tipoDeInstitucionId = this.getInstitutionType().getId();
		InstitutionType ti = getInstitutionTypeDAO().retrieveById(tipoDeInstitucionId);
		if (ti == null) { return ERROR; }
		this.setInstitutionType(ti);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarTipoDeInstitucion() throws HSControllerException{
		try {
			this.getInstitutionType().setId(null);
			getInstitutionTypeDAO().save(getInstitutionType());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarTipoDeInstitucion() throws HSControllerException{
		getInstitutionTypeDAO().saveOrUpdate(getInstitutionType());
		return SUCCESS;
	}

	public String eliminarTipoDeInstitucion() throws HSControllerException{
		getInstitutionTypeDAO().delete(getInstitutionType().getId());
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
	public InstitutionType getModel(){
		return getInstitutionType();
	}

	/**
	 * @return the institutionType
	 */
	public InstitutionType getInstitutionType(){
		return institutionType;
	}

	/**
	 * @param institutionType
	 *            the institutionType to set
	 */
	public void setInstitutionType(InstitutionType institutionType){
		this.institutionType = institutionType;
	}

	/**
	 * @return the institutionTypeDAO
	 */
	public GenericDAO<InstitutionType> getInstitutionTypeDAO(){
		return institutionTypeDAO;
	}

	/**
	 * @param institutionTypeDAO
	 *            the institutionTypeDAO to set
	 */
	public void setInstitutionTypeDAO(GenericDAO<InstitutionType> institutionTypeDAO){
		this.institutionTypeDAO = institutionTypeDAO;
	}

}
