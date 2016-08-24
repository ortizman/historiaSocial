package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.ObraSocial;
import ar.com.historiasocial.entities.Paginador;

/**
 * The Class ListDiagnosticosAction.
 * 
 * @author Manuel Ortiz
 */
@ParentPackage(value = "default")
public class ListObraSocialAction extends ListJQGridAction {

	/** The Constant serialVersionUID. */
	private static final long		serialVersionUID	= 1L;

	/** The obrasSociales. */
	private List<ObraSocial>		obrasSociales		= new ArrayList<ObraSocial>();

	/** The diagnostico dao. */
	private GenericDAO<ObraSocial>	obraSocialDAO;

	// Fin JQuey-Grid

	/**
	 * Sets the diagnostico.
	 * 
	 * @param obrasSociales
	 *            the new diagnostico
	 */
	public void setObrasSociales(List<ObraSocial> obrasSociales){
		this.obrasSociales = obrasSociales;
	}

	/**
	 * Gets the obrasSociales.
	 * 
	 * @return the obrasSociales
	 */
	public List<ObraSocial> getObrasSociales(){
		return obrasSociales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	@Actions({ @Action(value = "/datosTablaObrasSociales", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^obrasSociales\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<ObraSocial> obs;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			obs = getObraSocialDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			obs = getObraSocialDAO().retrievePaged(paginador);
		}

		this.setObrasSociales(obs);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
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
