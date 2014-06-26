package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Diagnostico;
import ar.com.historiasocial.entities.Paginador;

/**
 * The Class ListDiagnosticosAction.
 * 
 * @author Manuel Ortiz
 */
@ParentPackage(value = "default")
public class ListDiagnosticosAction extends ListJQGridAction {

	/** The Constant serialVersionUID. */
	private static final long		serialVersionUID	= 1L;

	/** The diagnosticos. */
	private List<Diagnostico>		diagnosticos		= new ArrayList<Diagnostico>();

	/** The diagnostico dao. */
	private GenericDAO<Diagnostico>	diagnosticoDAO;

	// Fin JQuey-Grid

	/**
	 * Sets the diagnostico.
	 * 
	 * @param diagnosticos
	 *            the new diagnostico
	 */
	public void setDiagnosticos(List<Diagnostico> diagnosticos){
		this.diagnosticos = diagnosticos;
	}

	/**
	 * Gets the diagnosticos.
	 * 
	 * @return the diagnosticos
	 */
	public List<Diagnostico> getDiagnosticos(){
		return diagnosticos;
	}

	/**
	 * 
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	@Actions({ @Action(value = "/datosTablaDiagnosticos", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^diagnosticos\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<Diagnostico> diagnosticos;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			diagnosticos = getDiagnosticoDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			diagnosticos = getDiagnosticoDAO().retrievePaged(paginador);
		}

		this.setDiagnosticos(diagnosticos);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
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
