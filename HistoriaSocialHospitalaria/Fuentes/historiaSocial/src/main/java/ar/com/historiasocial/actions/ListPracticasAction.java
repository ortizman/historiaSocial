package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Practica;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.TipoDePractica;
import ar.com.historiasocial.entities.TipoDeProblematica;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
@InterceptorRefs({ @InterceptorRef("userStack") })
public class ListPracticasAction extends ListJQGridAction {

	private static final long				serialVersionUID	= 1L;
	private List<Practica>					practicas			= new ArrayList<Practica>();
	private Integer							idPaciente;

	private HistoriaSocialDAO				historiaSocialDAO;
	private PacienteDAO						pacienteDAO;
	private PracticaDAO						practicaDAO;
	private ProfesionalDAO					profesionalDAO;
	private GenericDAO<TipoDeProblematica>	tipoDeProblematicaDAO;
	private GenericDAO<TipoDePractica>		tipoDePracticaDAO;

	// Representa el ID de paciente, si no es null, se filtra la busqueda para
	// este paciente.
	private int								cantPags;
	private Integer							pagActual;
	private List<Profesional>				profesionales;
	private List<TipoDeProblematica>		problematicas;
	private List<TipoDePractica>			tipoDePracticas;
	private List<Paciente>					pacientes;

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

	public List<Practica> getPracticas(){
		return practicas;
	}

	public void setPracticas(List<Practica> practicas){
		this.practicas = practicas;
	}

	public Integer getIdPaciente(){
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente){
		this.idPaciente = idPaciente;
	}

	@Override
	@Actions({ @Action(value = "/datosTablaPracticas", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false", 
			"includeProperties",
			"page, total, record, sord, sidx, rows, searchField, searchString, searchOper, filters, ^practicas\\[\\d+\\]\\.id , ^practicas\\[\\d+\\]\\.tratamiento\\.historiaSocial\\.id, ^practicas\\[\\d+\\]\\.detalle, ^practicas\\[\\d+\\]\\.diagnostico, ^practicas\\[\\d+\\]\\.fechaPractica, ^practicas\\[\\d+\\]\\.fechaCarga, ^practicas\\[\\d+\\]\\.tipoPractica\\.id, ^practicas\\[\\d+\\]\\.tipoPractica\\.codigo, ^practicas\\[\\d+\\]\\.tipoProblematica\\.id, ^practicas\\[\\d+\\]\\.tipoProblematica\\.codigo, ^practicas\\[\\d+\\]\\.profesional\\.id, ^practicas\\[\\d+\\]\\.profesional\\.nombreCompleto, ^practicas\\[\\d+\\]\\.tratamiento\\.historiaSocial\\.id, ^practicas\\[\\d+\\]\\.tratamiento\\.historiaSocial\\.paciente\\.nombreCompleto, ^practicas\\[\\d+\\]\\.localidad" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());

		List<Practica> ps;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			ps = practicaDAO.retrieveAll(paginador, this.getSidx(), this.getSord(), idPaciente);
		} else {
			ps = practicaDAO.retrieveAll(paginador, idPaciente);
		}

		this.setPracticas(ps);
		this.setCantPags(paginador.cantidadDePaginas());
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}

	@Actions({ @Action(value = "/fillSelectProf", results = { @Result(name = "success", location = "/pages/practica/fillSelectProf.jsp") }) })
	public String fillSelectProf(){

		profesionales = profesionalDAO.retrieveAll();

		return SUCCESS;
	}

	@Actions({ @Action(value = "/fillSelectPaciente", results = { @Result(name = "success", location = "/pages/practica/fillSelectPaciente.jsp") }) })
	public String fillSelectPaciente(){

		pacientes = pacienteDAO.pacientesConTratamientoAbierto();

		return SUCCESS;
	}

	@Actions({ @Action(value = "/fillSelectProbl", results = { @Result(name = "success", location = "/pages/practica/fillSelectProbl.jsp") }) })
	public String fillSelectProbl(){

		problematicas = getTipoDeProblematicaDAO().retrieveAll();

		return SUCCESS;
	}

	@Actions({ @Action(value = "/fillSelectCodPrac", results = { @Result(name = "success", location = "/pages/practica/fillSelectCodPrac.jsp") }) })
	public String fillSelectCodPrac(){

		tipoDePracticas = getTipoDePracticaDAO().retrieveAll();

		return SUCCESS;
	}

	public List<Profesional> getProfesionales(){
		return profesionales;
	}

	public void setProfesionales(List<Profesional> profesionales){
		this.profesionales = profesionales;
	}

	public List<TipoDeProblematica> getProblematicas(){
		return problematicas;
	}

	public void setProblematicas(List<TipoDeProblematica> problematicas){
		this.problematicas = problematicas;
	}

	public List<TipoDePractica> getTipoDePracticas(){
		return tipoDePracticas;
	}

	public void setTipoDePracticas(List<TipoDePractica> tipoDePracticas){
		this.tipoDePracticas = tipoDePracticas;
	}

	public List<Paciente> getPacientes(){
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes){
		this.pacientes = pacientes;
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	public PracticaDAO getPracticaDAO(){
		return practicaDAO;
	}

	public void setPracticaDAO(PracticaDAO practicaDAO){
		this.practicaDAO = practicaDAO;
	}

	public ProfesionalDAO getProfesionalDAO(){
		return profesionalDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO){
		this.profesionalDAO = profesionalDAO;
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

	/**
	 * @return the tipoDePracticaDAO
	 */
	public GenericDAO<TipoDePractica> getTipoDePracticaDAO(){
		return tipoDePracticaDAO;
	}

	/**
	 * @param tipoDePracticaDAO
	 *            the tipoDePracticaDAO to set
	 */
	public void setTipoDePracticaDAO(GenericDAO<TipoDePractica> tipoDePracticaDAO){
		this.tipoDePracticaDAO = tipoDePracticaDAO;
	}

}
