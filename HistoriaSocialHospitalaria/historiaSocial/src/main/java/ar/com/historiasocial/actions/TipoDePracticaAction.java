package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.entities.TipoDePractica;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TipoDePracticaAction extends ActionSupport implements ModelDriven<TipoDePractica> {

	/**
	 * 
	 */
	private static final long			serialVersionUID	= -5034604886935224669L;

	private HistoriaSocialDAO			historiaSocialDAO;
	// NUEVO
	private TipoDePractica				tipoDePractica		= new TipoDePractica();
	private String						mensaje;
	private String						oper				= "";

	private GenericDAO<TipoDePractica>	tipoDePracticaDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(TipoDePracticaAction.class);

	public String creacionTipoDePractica() throws HSControllerException{
		this.setTipoDePractica(new TipoDePractica());
		this.setOper("create");
		return SUCCESS;
	}

	public String visualizarTipoDePractica() throws HSControllerException{
		Long tipoDePracticaId = this.getTipoDePractica().getId();
		TipoDePractica tp = getTipoDePracticaDAO().retrieveById(tipoDePracticaId);
		if (tp == null) { return ERROR; }
		this.setOper("view");
		this.setTipoDePractica(tp);
		return SUCCESS;
	}

	public String editarTipoDePractica() throws HSControllerException{
		Long tipoDePracticaId = this.getTipoDePractica().getId();
		TipoDePractica tp = getTipoDePracticaDAO().retrieveById(tipoDePracticaId);
		if (tp == null) { return ERROR; }
		this.setTipoDePractica(tp);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarTipoDePractica() throws HSControllerException{
		try {
			this.getTipoDePractica().setId(null);
			getTipoDePracticaDAO().save(getTipoDePractica());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarTipoDePractica() throws HSControllerException{
		getTipoDePracticaDAO().saveOrUpdate(getTipoDePractica());
		return SUCCESS;
	}

	public String eliminarTipoDePractica() throws HSControllerException{
		getTipoDePracticaDAO().delete(getTipoDePractica().getId());
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
	public TipoDePractica getModel(){
		return getTipoDePractica();
	}

	/**
	 * @return the tipoDePractica
	 */
	public TipoDePractica getTipoDePractica(){
		return tipoDePractica;
	}

	/**
	 * @param tipoDePractica
	 *            the tipoDePractica to set
	 */
	public void setTipoDePractica(TipoDePractica tipoDePractica){
		this.tipoDePractica = tipoDePractica;
	}

	/**
	 * @return the tipoDePracticaDAO
	 */
	public GenericDAO<TipoDePractica> getTipoDePracticaDAO(){
		return tipoDePracticaDAO;
	}

	/**
	 * @param tipoDePracticaDAO
	 *            the tipoDePracticaDAO to set
	 */
	public void setTipoDePracticaDAO(GenericDAO<TipoDePractica> tipoDePracticaDAO){
		this.tipoDePracticaDAO = tipoDePracticaDAO;
	}
}
