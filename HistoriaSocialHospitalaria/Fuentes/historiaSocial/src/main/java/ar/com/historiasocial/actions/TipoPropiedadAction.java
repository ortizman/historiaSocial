package ar.com.historiasocial.actions;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.TipoPropiedad;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class TipoPropiedadAction extends ActionSupport implements ModelDriven<TipoPropiedad> {

	private static final long			serialVersionUID	= 6035561456421237535L;

	private TipoPropiedad				tipoPropiedad		= new TipoPropiedad();
	private String						mensaje;
	private String						oper				= "";

	private GenericDAO<TipoPropiedad>	tipoPropiedadDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger(TipoPropiedadAction.class);

	public String creacionTipoPropiedad() throws HSControllerException{ 
		this.setTipoPropiedad(new TipoPropiedad());
		this.setOper("create");
		return SUCCESS;
	}

	public String editarTipoPropiedad() throws HSControllerException{
		Long diagbosticoId = this.getTipoPropiedad().getId();
		TipoPropiedad tipoProp = getTipoPropiedadDAO().retrieveById(diagbosticoId);

		if (tipoProp == null) { return ERROR; }

		this.setTipoPropiedad(tipoProp);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarTipoPropiedad() throws HSControllerException{
		try {
			this.getTipoPropiedad().setId(null);
			getTipoPropiedadDAO().save(getTipoPropiedad());
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String modificarTipoPropiedad() throws HSControllerException{
		getTipoPropiedadDAO().saveOrUpdate(getTipoPropiedad());
		return SUCCESS;
	}

	public String eliminarTipoPropiedad() throws HSControllerException{
		getTipoPropiedadDAO().delete(getTipoPropiedad().getId());
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
	public TipoPropiedad getModel(){
		return getTipoPropiedad();
	}

	public TipoPropiedad getTipoPropiedad(){
		return tipoPropiedad;
	}

	public void setTipoPropiedad(TipoPropiedad tipoPropiedad){
		this.tipoPropiedad = tipoPropiedad;
	}

	/**
	 * @return the tipoPropiedadDAO
	 */
	public GenericDAO<TipoPropiedad> getTipoPropiedadDAO(){
		return tipoPropiedadDAO;
	}

	/**
	 * @param tipoPropiedadDAO
	 *            the tipoPropiedadDAO to set
	 */
	public void setTipoPropiedadDAO(GenericDAO<TipoPropiedad> tipoPropiedadDAO){
		this.tipoPropiedadDAO = tipoPropiedadDAO;
	}

}
