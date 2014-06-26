package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.PersonaDAO;
import ar.com.historiasocial.entities.ObraSocial;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Persona;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
public class ListPersonasAction extends ListJQGridAction {

	private static final long		serialVersionUID	= 1L;

	private List<Persona>			personas			= new ArrayList<Persona>();
	private PersonaDAO				personaDAO;
	private Long					idPaciente;

	private GenericDAO<ObraSocial>	obraSocialDAO;
	private List<ObraSocial>		obrasSociales;

	@Override
	@Actions({ @Action(value = "/datosTablaPersonas", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^personas\\[\\d+\\]\\.\\w*, ^personas\\[\\d+\\]\\.obraSocial\\.\\w*" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<Persona> pers;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			pers = personaDAO.retrievePaged(paginador, this.getSidx(), this.getSord(), idPaciente);
		} else {
			pers = personaDAO.retrievePaged(paginador, idPaciente);
		}

		this.setPersonas(pers);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}

	public String listGrupoConviviente(){

		this.setObrasSociales(getObraSocialDAO().retrieveAll());

		return SUCCESS;
	}

	public List<Persona> getPersonas(){
		return personas;
	}

	public void setPersonas(List<Persona> personas){
		this.personas = personas;
	}

	public PersonaDAO getPersonaDAO(){
		return personaDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO){
		this.personaDAO = personaDAO;
	}

	public Long getIdPaciente(){
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente){
		this.idPaciente = idPaciente;
	}

	public List<ObraSocial> getObrasSociales(){
		return obrasSociales;
	}

	public void setObrasSociales(List<ObraSocial> obraSocials){
		this.obrasSociales = obraSocials;
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
