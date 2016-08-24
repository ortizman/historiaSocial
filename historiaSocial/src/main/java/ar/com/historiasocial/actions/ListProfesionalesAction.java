package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.dto.ProfesionalDTO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.Servicio;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
public class ListProfesionalesAction extends ListJQGridAction {
	private static final long			serialVersionUID	= 1L;

	private final List<ProfesionalDTO>	profesionales		= new ArrayList<ProfesionalDTO>();

	private ProfesionalDAO				profesionalDAO;
	private GenericDAO<Servicio>		servicioDAO;

	private List<Servicio>				servicios;

	public List<ProfesionalDTO> getProfesionales(){
		return profesionales;
	}

	public void setProfesionales(List<Profesional> profesionales){
		for (Profesional profesional : profesionales) {
			this.profesionales.add(new ProfesionalDTO(profesional));
		}
	}

	@Override
	@Actions({ @Action(value = "/datosTablaProfesionales", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^profesionales\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("user");

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<Profesional> prof;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			prof = getProfesionalDAO().profesionales(user, paginador, this.getSidx(), this.getSord());
		} else if (this.getSearchField() != null && this.getSearchString() != null && this.getSearchOper() != null) {
			prof = getProfesionalDAO().profesionales(user, paginador, this.getSearchField(), this.getSearchString(), this.getSearchOper());
		} else {
			prof = getProfesionalDAO().profesionales(user, paginador);
		}

		this.setProfesionales(prof);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;

	}

	@Actions({ @Action(value = "/fillSelectServ", results = { @Result(name = "success", location = "/pages/profesional/fillSelectServ.jsp") }) })
	public String fillSelectServ(){
		servicios = getServicioDAO().retrieveAll();

		return SUCCESS;
	}

	public List<Servicio> getServicios(){
		return servicios;
	}

	public ProfesionalDAO getProfesionalDAO(){
		return profesionalDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO){
		this.profesionalDAO = profesionalDAO;
	}

	/**
	 * @return the servicioDAO
	 */
	public GenericDAO<Servicio> getServicioDAO(){
		return servicioDAO;
	}

	/**
	 * @param servicioDAO
	 *            the servicioDAO to set
	 */
	public void setServicioDAO(GenericDAO<Servicio> servicioDAO){
		this.servicioDAO = servicioDAO;
	}

}
