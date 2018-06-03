package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.entities.TipoDeProblematica;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//NUEVO

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class ProblematicaAction extends ActionSupport implements ModelDriven<TipoDeProblematica> {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= -4045639349368796741L;

	private HistoriaSocialDAO				historiaSocialDAO;
	private TipoDeProblematica				tipoDeProblematica	= new TipoDeProblematica();
	private String							mensaje;
	private String							oper				= "";

	private GenericDAO<TipoDeProblematica>	tipoDeProblematicaDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(ProblematicaAction.class);

	public String creacionProblematica() throws HSControllerException{
		this.setTipoDeProblematica(new TipoDeProblematica());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarProblematica() throws HSControllerException{
		Long problematicaId = this.getTipoDeProblematica().getId();
		TipoDeProblematica p = getTipoDeProblematicaDAO().retrieveById(problematicaId);
		if (p == null) { return ERROR; }
		this.setTipoDeProblematica(p);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarProblematica() throws HSControllerException{
		try {
			this.getTipoDeProblematica().setId(null);
			getTipoDeProblematicaDAO().save(getTipoDeProblematica());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarProblematica() throws HSControllerException{
		getTipoDeProblematicaDAO().saveOrUpdate(getTipoDeProblematica());
		return SUCCESS;
	}

	public String eliminarProblematica() throws HSControllerException{
		getTipoDeProblematicaDAO().delete(getTipoDeProblematica().getId());
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
	public TipoDeProblematica getModel(){
		return getTipoDeProblematica();
	}

	/**
	 * @return the tipoDeProblematica
	 */
	public TipoDeProblematica getTipoDeProblematica(){
		return tipoDeProblematica;
	}

	/**
	 * @param tipoDeProblematica
	 *            the tipoDeProblematica to set
	 */
	public void setTipoDeProblematica(TipoDeProblematica tipoDeProblematica){
		this.tipoDeProblematica = tipoDeProblematica;
	}

	/**
	 * @return the tipoDeProblematicaDAO
	 */
	public GenericDAO<TipoDeProblematica> getTipoDeProblematicaDAO(){
		return tipoDeProblematicaDAO;
	}

	/**
	 * @param tipoDeProblematicaDAO
	 *            the tipoDeProblematicaDAO to set
	 */
	public void setTipoDeProblematicaDAO(GenericDAO<TipoDeProblematica> tipoDeProblematicaDAO){
		this.tipoDeProblematicaDAO = tipoDeProblematicaDAO;
	}

}
