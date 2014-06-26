package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.TipoDeProblematica;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
public class ListTipoProblematicasAction extends ListJQGridAction {

	private static final long				serialVersionUID	= 1L;
	private List<TipoDeProblematica>		tipoProblematicas	= new ArrayList<TipoDeProblematica>();

	private GenericDAO<TipoDeProblematica>	tipoDeProblematicaDAO;

	public List<TipoDeProblematica> getTipoProblematicas(){
		return tipoProblematicas;
	}

	public void setTipoProblematicas(List<TipoDeProblematica> tipoProblematicas){
		this.tipoProblematicas = tipoProblematicas;
	}

	@Override
	@Actions({ @Action(value = "/datosTablaTipoProblematica", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^tipoProblematicas\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){
		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());

		List<TipoDeProblematica> ps;

		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			ps = getTipoDeProblematicaDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			ps = getTipoDeProblematicaDAO().retrievePaged(paginador);
		}

		this.setTipoProblematicas(ps);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
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
