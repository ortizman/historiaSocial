package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.TipoPropiedad;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
public class ListTipoPropiedadAction extends ListJQGridAction {

	/** The Constant serialVersionUID. */
	private static final long			serialVersionUID	= 1L;

	/** The tipoPropiedades. */
	private List<TipoPropiedad>			tipoPropiedades		= new ArrayList<TipoPropiedad>();

	/** The tipo propiedad dao. */
	private GenericDAO<TipoPropiedad>	tipoPropiedadDAO;

	// Fin JQuey-Grid

	/**
	 * Sets the diagnostico.
	 * 
	 * @param tipoPropiedades
	 *            the new diagnostico
	 */
	public void setTipoPropiedades(List<TipoPropiedad> tipoPropiedades){
		this.tipoPropiedades = tipoPropiedades;
	}

	/**
	 * Gets the tipoPropiedades.
	 * 
	 * @return the tipoPropiedades
	 */
	public List<TipoPropiedad> getTipoPropiedades(){
		return tipoPropiedades;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	@Actions({ @Action(value = "/datosTablaTipoPropiedades", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^tipoPropiedades\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<TipoPropiedad> tipoPropiedades;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			tipoPropiedades = getTipoPropiedadDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			tipoPropiedades = getTipoPropiedadDAO().retrievePaged(paginador);
		}

		this.setTipoPropiedades(tipoPropiedades);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
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
