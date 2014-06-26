package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Practica;
import ar.com.historiasocial.entities.Profesional;

@ParentPackage(value = "default")
public class EstadisticaAction extends ListJQGridAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<Practica>		practicas			= new ArrayList<Practica>();
	private List<Profesional>	profesionales		= new ArrayList<Profesional>();
	private List<Paciente>		pacientes			= new ArrayList<Paciente>();
	private Date				inicio;
	private Date				fin;
	private int					profesional;
	private int					paciente;
	private String				filtro;
	private int					cantPags;
	private Integer				pagActual;


	private PracticaDAO			practicaDAO;
	private ProfesionalDAO		profesionalDAO;
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

	public List<Practica> getPracticas(){
		return practicas;
	}

	public void setPracticas(List<Practica> practicas){
		this.practicas = practicas;
	}

	public List<Profesional> getProfesionales(){
		return profesionales;
	}

	public void setProfesionales(List<Profesional> profesionales){
		this.profesionales = profesionales;
	}

	public List<Paciente> getPacientes(){
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes){
		this.pacientes = pacientes;
	}

	public int getProfesional(){
		return profesional;
	}

	public void setProfesional(int profesional){
		this.profesional = profesional;
	}

	public int getPaciente(){
		return paciente;
	}

	public void setPaciente(int paciente){
		this.paciente = paciente;
	}

	public void setFiltro(String filtro){
		this.filtro = filtro;
	}

	public String getFiltro(){
		return filtro;
	}

	public Date getInicio(){
		return inicio;
	}

	public void setInicio(Date inicio){
		this.inicio = inicio;
	}

	public Date getFin(){
		return fin;
	}

	public void setFin(Date fin){
		this.fin = fin;
	}
	
	@Override
	public String preparar() throws Exception{
		
//		this.setProfesionales(getProfesionalDAO().retrieveAll());
//		this.setPacientes(getPacienteDAO().retrieveAll());
		
		return super.preparar();
	}

	@Override
	@Actions({ @Action(value = "/datosTablaPractica", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false", 
			"includeProperties",
			"paciente, profesional, filtro, inicio, fin, page, total, record, sord, sidx, rows, ^practicas\\[\\d+\\]\\.id , ^practicas\\[\\d+\\]\\.localidad, ^practicas\\[\\d+\\]\\.historiaSocial\\.id,  ^practicas\\[\\d+\\]\\.fechaPractica, ^practicas\\[\\d+\\]\\.fechaCarga, ^practicas\\[\\d+\\]\\.tipoPractica\\.id, ^practicas\\[\\d+\\]\\.tipoPractica\\.codigo, ^practicas\\[\\d+\\]\\.tipoProblematica\\.id, ^practicas\\[\\d+\\]\\.tipoProblematica\\.codigo, ^practicas\\[\\d+\\]\\.profesional\\.id, ^practicas\\[\\d+\\]\\.profesional\\.nombreCompleto, ^practicas\\[\\d+\\]\\.historiaSocial\\.id, ^practicas\\[\\d+\\]\\.historiaSocial\\.paciente\\.nombreCompleto" }) }) })
	public String execute(){
		
		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}
		if (this.getPage() == null) {
			this.setPage(1);
		}
		
		Paginador paginador = new Paginador(this.getRows(), this.getPage());

		List<Practica> ps = null;
		if (this.getFiltro() == null) {
			this.setFiltro("sfiltro");
			if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
				ps = getPracticaDAO().retrieveAll(paginador, this.getSidx(), this.getSord(), paciente);
			} else {
				ps = getPracticaDAO().retrieveAll(paginador);
			}
		} else {
			if (this.getFiltro().equals("fecha")) {
				if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
					ps = (getPracticaDAO().filtroFecha(this.getInicio(), this.getFin(), paginador, this.getSidx(), this.getSord()));
				} else {
					ps = (getPracticaDAO().filtroFecha(this.getInicio(), this.getFin(), paginador));
				}
			} else {
				if (this.getFiltro().equals("fechaprof")) {
					if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
						ps = (getPracticaDAO().filtroProfesional(this.getInicio(), this.getFin(), this.getProfesional(), paginador, this.getSidx(),
								this.getSord()));
					} else {
						ps = (getPracticaDAO().filtroProfesional(this.getInicio(), this.getFin(), this.getProfesional(), paginador));
					}
				} else {
					if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
						ps = (getPracticaDAO().filtroPaciente(this.getInicio(), this.getFin(), this.getPaciente(), paginador, this.getSidx(), this.getSord()));
					} else {
						ps = (getPracticaDAO().filtroPaciente(this.getInicio(), this.getFin(), this.getPaciente(), paginador));
					}

				}
			}
		}

		this.setPracticas(ps);
		this.setCantPags(paginador.cantidadDePaginas());
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;

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

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

}
