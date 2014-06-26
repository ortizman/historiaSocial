package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Diagnostico;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class DiagnosticoAction extends ActionSupport implements ModelDriven<Diagnostico> {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 6035561456421237535L;

	private Diagnostico				diagnostico			= new Diagnostico();
	private String					mensaje;
	private String					oper				= "";

	private GenericDAO<Diagnostico>	diagnosticoDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(DiagnosticoAction.class);

	public String creacionDiagnostico() throws HSControllerException{
		this.setDiagnostico(new Diagnostico());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarDiagnostico() throws HSControllerException{
		Long diagbosticoId = this.getDiagnostico().getId();
		Diagnostico diag = getDiagnosticoDAO().retrieveById(diagbosticoId);

		if (diag == null) { return ERROR; }

		this.setDiagnostico(diag);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarDiagnostico() throws HSControllerException{
		try {
			this.getDiagnostico().setId(null);
			getDiagnosticoDAO().save(getDiagnostico());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarDiagnostico() throws HSControllerException{
		getDiagnosticoDAO().saveOrUpdate(getDiagnostico());
		return SUCCESS;
	}

	public String eliminarDiagnostico() throws HSControllerException{
		getDiagnosticoDAO().delete(getDiagnostico().getId());
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
	public Diagnostico getModel(){
		return getDiagnostico();
	}

	public Diagnostico getDiagnostico(){
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico){
		this.diagnostico = diagnostico;
	}

	/**
	 * @return the diagnosticoDAO
	 */
	public GenericDAO<Diagnostico> getDiagnosticoDAO(){
		return diagnosticoDAO;
	}

	/**
	 * @param diagnosticoDAO
	 *            the diagnosticoDAO to set
	 */
	public void setDiagnosticoDAO(GenericDAO<Diagnostico> diagnosticoDAO){
		this.diagnosticoDAO = diagnosticoDAO;
	}

}
