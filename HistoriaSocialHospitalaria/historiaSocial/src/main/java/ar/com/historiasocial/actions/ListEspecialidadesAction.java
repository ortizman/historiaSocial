package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Especialidad;
import ar.com.historiasocial.entities.Paginador;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
public class ListEspecialidadesAction extends ListJQGridAction {

	private static final long			serialVersionUID	= 1L;

	private List<Especialidad>			especialidades		= new ArrayList<Especialidad>();
	private GenericDAO<Especialidad>	especialidadDAO;

	// Fin JQuey-Grid

	public void setEspecialidades(List<Especialidad> especialidades){
		this.especialidades = especialidades;
	}

	public List<Especialidad> getEspecialidades(){
		return especialidades;
	}

	@Override
	@Actions({ @Action(value = "/datosTablaEspecialidades", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^especialidades\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<Especialidad> es;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			es = getEspecialidadDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			es = getEspecialidadDAO().retrievePaged(paginador);
		}

		this.setEspecialidades(es);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}

	/**
	 * @return the especialidadDAO
	 */
	public GenericDAO<Especialidad> getEspecialidadDAO(){
		return especialidadDAO;
	}

	/**
	 * @param especialidadDAO
	 *            the especialidadDAO to set
	 */
	public void setEspecialidadDAO(GenericDAO<Especialidad> especialidadDAO){
		this.especialidadDAO = especialidadDAO;
	}

}
