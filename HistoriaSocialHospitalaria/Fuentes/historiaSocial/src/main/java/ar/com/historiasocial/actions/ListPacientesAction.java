package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Paginador;

@ParentPackage(value = "default")
@InterceptorRefs({ @InterceptorRef("mylogging"), @InterceptorRef("userStack") })
public class ListPacientesAction extends ListJQGridAction {
	private static final long	serialVersionUID	= 1L;

	private List<Paciente>		pacientes			= new ArrayList<Paciente>();
	private int					cantPags;
	private Integer				pagActual;
	List<PacienteResponseAutoComplete>	suggestions = new ArrayList<PacienteResponseAutoComplete>();
	private String				query;

	private PacienteDAO			pacienteDAO;

	public void setCantPags(int cantPags){
		this.cantPags = cantPags;
	}

	public int getCantPags(){
		return cantPags;
	}

	public Integer getPagActual(){
		return pagActual;
	}

	public void setPagActual(Integer pagActual){
		this.pagActual = pagActual;
	}

	public List<Paciente> getPacientes(){
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes){
		this.pacientes = pacientes;
	}

	@Actions({ @Action(value = "/datosTablaPacientes", results = { @Result(name = "success", type = "json", params = {
			"ignoreHierarchy",
			"false",
			"includeProperties",
			"page, total, record, sord, sidx, rows, ^pacientes\\[\\d+\\]\\.id , ^pacientes\\[\\d+\\]\\.nombres, ^pacientes\\[\\d+\\]\\.apellidos, ^pacientes\\[\\d+\\]\\.documento, ^pacientes\\[\\d+\\]\\.fechaNacimiento, ^pacientes\\[\\d+\\]\\.fechaInicioServSocial, ^pacientes\\[\\d+\\]\\.domicilio\\.id,  ^pacientes\\[\\d+\\]\\.vivienda\\.id, ^pacientes\\[\\d+\\]\\.historiaSocial\\.id, ^pacientes\\[\\d+\\]\\.historiaSocial\\.tratamientoAmbulatorio\\.fechaIngreso" }) }) })
	public String listPaciente(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());

		this.setPacientes(getPacienteDAO().pacientes(paginador, getSidx(), getSord(), getSearchField(), getSearchOper(), getSearchString()));

		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}

	@Actions({ @Action(value = "/pacienteAutoComplete", results = { @Result(type = "json", name = "success", params={
			"includeProperties",
			"^suggestions\\[\\d+\\]\\.value, ^suggestions\\[\\d+\\]\\.data" 
	}) }) })
	public String autocomplete(){
		
		if(!StringUtils.isEmpty(query)){
			List<Paciente> pacientes = getPacienteDAO().search(query);
			suggestions = new ArrayList<PacienteResponseAutoComplete>();
			for (Paciente pa : pacientes) {
				suggestions.add(new PacienteResponseAutoComplete(pa.getNombreCompleto() + ". DNI: " + pa.getDocumento() , pa.getId().toString()));
			}
		}
		
		
		return SUCCESS;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	/**
	 * @return the suggestions
	 */
	public List<PacienteResponseAutoComplete> getSuggestions(){
		return this.suggestions;
	}

	/**
	 * @param suggestions the suggestions to set
	 */
	public void setSuggestions(List<PacienteResponseAutoComplete> pacientess){
		this.suggestions = pacientess;
	}

	/**
	 * @return the query
	 */
	public String getQuery(){
		return this.query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query){
		this.query = query;
	}
	
	public class PacienteResponseAutoComplete {
	
		private String value;
		private String data;
		
		
		public PacienteResponseAutoComplete(String value, String data) {
			this.value = value;
			this.data = data;
		}
		
		/**
		 * @return the value
		 */
		public String getValue(){
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(String value){
			this.value = value;
		}
		
		/**
		 * @return the data
		 */
		public String getData(){
			return data;
		}
		/**
		 * @param data the data to set
		 */
		public void setData(String data){
			this.data = data;
		}
		
		
	}
}
