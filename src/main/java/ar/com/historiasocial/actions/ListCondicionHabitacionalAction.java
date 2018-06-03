package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.CondicionHabitacional;
import ar.com.historiasocial.entities.Paginador;

/**
 * The Class ListCondicionHabitacionalAction.
 * 
 * @author Manuel Ortiz
 */
@ParentPackage(value = "default")
public class ListCondicionHabitacionalAction extends ListJQGridAction {

	/** The Constant serialVersionUID. */
	private static final long					serialVersionUID		= 1L;

	/** The condicionHabitacionales. */
	private List<CondicionHabitacional>			condicionHabitacionales	= new ArrayList<CondicionHabitacional>();

	private GenericDAO<CondicionHabitacional>	condicionHabitacionalDAO;

	/**
	 * Sets the diagnostico.
	 * 
	 * @param condicionHabitacionales
	 *            the new diagnostico
	 */
	public void setCondicionHabitacionales(List<CondicionHabitacional> condicionHabitacionales){
		this.condicionHabitacionales = condicionHabitacionales;
	}

	/**
	 * Gets the condicionHabitacionales.
	 * 
	 * @return the condicionHabitacionales
	 */
	public List<CondicionHabitacional> getCondicionHabitacionales(){
		return condicionHabitacionales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	@Actions({ @Action(value = "/datosTablaCondicionHabitacionales", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^condicionHabitacionales\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<CondicionHabitacional> condicionHabitacionales;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			condicionHabitacionales = getCondicionHabitacionalDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			condicionHabitacionales = getCondicionHabitacionalDAO().retrievePaged(paginador);
		}

		this.setCondicionHabitacionales(condicionHabitacionales);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}

	/**
	 * @return the condicionHabitacionalDAO
	 */
	public GenericDAO<CondicionHabitacional> getCondicionHabitacionalDAO(){
		return condicionHabitacionalDAO;
	}

	/**
	 * @param condicionHabitacionalDAO
	 *            the condicionHabitacionalDAO to set
	 */
	public void setCondicionHabitacionalDAO(GenericDAO<CondicionHabitacional> condicionHabitacionalDAO){
		this.condicionHabitacionalDAO = condicionHabitacionalDAO;
	}

}
