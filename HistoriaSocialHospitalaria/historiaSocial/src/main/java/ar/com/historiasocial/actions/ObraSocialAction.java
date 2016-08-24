package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.ObraSocial;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class ObraSocialAction extends ActionSupport implements ModelDriven<ObraSocial> {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 6035561456421237535L;

	private ObraSocial				obraSocial			= new ObraSocial();
	private String					mensaje;
	private String					oper				= "";

	private GenericDAO<ObraSocial>	obraSocialDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(ObraSocialAction.class);

	public String creacionObraSocial() throws HSControllerException{ 
		this.setObraSocial(new ObraSocial());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarObraSocial() throws HSControllerException{
		Long obraSocialId = this.getObraSocial().getId();
		ObraSocial diag = getObraSocialDAO().retrieveById(obraSocialId);

		if (diag == null) { return ERROR; }

		this.setObraSocial(diag);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarObraSocial() throws HSControllerException{
		try {
			this.getObraSocial().setId(null);
			getObraSocialDAO().save(getObraSocial());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarObraSocial() throws HSControllerException{
		getObraSocialDAO().saveOrUpdate(getObraSocial());
		return SUCCESS;
	}

	public String eliminarObraSocial() throws HSControllerException{
		getObraSocialDAO().delete(getObraSocial().getId());
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
	public ObraSocial getModel(){
		return getObraSocial();
	}

	public ObraSocial getObraSocial(){
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial){
		this.obraSocial = obraSocial;
	}

	/**
	 * @return the obraSocialDAO
	 */
	public GenericDAO<ObraSocial> getObraSocialDAO(){
		return obraSocialDAO;
	}

	/**
	 * @param obraSocialDAO
	 *            the obraSocialDAO to set
	 */
	public void setObraSocialDAO(GenericDAO<ObraSocial> obraSocialDAO){
		this.obraSocialDAO = obraSocialDAO;
	}

}
