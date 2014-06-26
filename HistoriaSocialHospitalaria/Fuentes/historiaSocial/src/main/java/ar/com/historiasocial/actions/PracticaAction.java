package ar.com.historiasocial.actions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.HistoriaSocial;
import ar.com.historiasocial.entities.NoExisteTratamientoActivoException;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Practica;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.TipoDePractica;
import ar.com.historiasocial.entities.TipoDeProblematica;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class PracticaAction extends ActionSupport implements ModelDriven<Practica> {
	/**
	 * 
	 */
	private static final long				serialVersionUID	= -4887525475222408599L;

	private Practica						practica;
	private Long							idPractica;
	private Long							idPaciente;
	private List<Practica>					practicas			= new ArrayList<Practica>();
	private HistoriaSocialDAO				historiaSocialDAO;
	private PacienteDAO						pacienteDAO;
	private PracticaDAO						practicaDAO;
	private ProfesionalDAO					profesionalDAO;
	private GenericDAO<TipoDeProblematica>	tipoDeProblematicaDAO;
	private GenericDAO<TipoDePractica>		tipoDePracticaDAO;
	private List<Profesional>			profesionales;
	private List<TipoDeProblematica>	problematicas;
	private List<TipoDePractica>		tipoDePracticas;
	private List<Paciente>				pacientes;
	private String							mensaje;
	private String							oper				= "";

	private static final Logger				LOGGER				= Logger.getLogger(PracticaAction.class);

	public String creacionPractica() throws HSControllerException{
		Practica practica = new Practica();
		practica.setFechaCarga(new Date(System.currentTimeMillis()));
		this.setPractica(practica);
		this.setOper("create");
		profesionales = getProfesionalDAO().retrieveAll();
		pacientes = getPacienteDAO().retrieveAll();
		tipoDePracticas = getTipoDePracticaDAO().retrieveAll();
		problematicas = getTipoDeProblematicaDAO().retrieveAll();
		return SUCCESS;
	}

	public String visualizarPractica() throws HSControllerException{
		Long practicaId = this.getIdPractica();
		Practica p = getPracticaDAO().retrieveById(practicaId);
		profesionales = getProfesionalDAO().retrieveAll();
		pacientes = getPacienteDAO().retrieveAll();
		tipoDePracticas = getTipoDePracticaDAO().retrieveAll();
		problematicas = getTipoDeProblematicaDAO().retrieveAll();
		if (p == null) { return ERROR; }

		this.setOper("view");

		this.setPractica(p);
		return SUCCESS;
	}

	public String editarPractica() throws HSControllerException{
		Long practicaId = this.getIdPractica();
		Practica p = getPracticaDAO().retrieveById(practicaId);
		profesionales = getProfesionalDAO().retrieveAll();
		pacientes = getPacienteDAO().retrieveAll();
		tipoDePracticas = getTipoDePracticaDAO().retrieveAll();
		problematicas = getTipoDeProblematicaDAO().retrieveAll();
		if (p == null) { return ERROR; }
		this.setPractica(p);
		this.setOper("edit");
		return SUCCESS;
	}

	public String salvarPractica() throws HSControllerException{

		this.getPractica().setHabilitado(true);
		this.getPractica().setId(null);
		this.setIdPaciente(null);

		Long id = Long.valueOf(ServletActionContext.getRequest().getParameter("idPaciente"));
		Paciente paciente = getPacienteDAO().paciente(id);

		if (paciente == null) {
			LOGGER.error("Error al intentar guardar la practica, el paciente no pude ser recuperado. ID de paciente: " + id);
		}

		HistoriaSocial historiaSocial = paciente.getHistoriaSocial();
		if (historiaSocial == null) {
			LOGGER.error("Error al intentar guardar la practica, la historia social del Paciente es nula");
		}

		try {

			Practica practica = this.getPracticaDAO().save(getPractica());
			historiaSocial.agregarPractica(practica);

			this.getHistoriaSocialDAO().saveOrUpdate(historiaSocial);

		} catch (NoExisteTratamientoActivoException e) {
			LOGGER.error("Error al intentar persistir la practica", e);
			this.setIdPaciente(id);
		} catch (Exception e) {
			LOGGER.error("Error al intentar persistir la practica", e);
			return ERROR;
		}

		profesionales = getProfesionalDAO().retrieveAll();
		pacientes = getPacienteDAO().retrieveAll();
		tipoDePracticas = getTipoDePracticaDAO().retrieveAll();
		problematicas = getTipoDeProblematicaDAO().retrieveAll();
		Practica p = new Practica();
		p.setProfesional(this.getPractica().getProfesional());
		p.setFechaCarga(new Date(System.currentTimeMillis()));
		this.setPractica(p);
		this.setOper("create");
		return SUCCESS;
	}

	public String modificarPractica() throws HSControllerException{
		this.getPractica().setHabilitado(true);
		getPracticaDAO().saveOrUpdate(getPractica());

		return SUCCESS;
	}

	public String eliminarPractica() throws HSControllerException{
		getPracticaDAO().delete(getIdPractica());
		return SUCCESS;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje(){
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje){
		this.mensaje = mensaje;
	}

	/**
	 * @return the oper
	 */
	public String getOper(){
		return oper;
	}

	/**
	 * @param oper
	 *            the oper to set
	 */
	public void setOper(String oper){
		this.oper = oper;
	}

	@Override
	public Practica getModel(){
		return getPractica();
	}

	/**
	 * @return the practicas
	 */
	public List<Practica> getPracticas(){
		return practicas;
	}

	/**
	 * @param practicas
	 *            the practicas to set
	 */
	public void setPracticas(List<Practica> practicas){
		this.practicas = practicas;
	}

	/**
	 * @return the profesionales
	 */
	public List<Profesional> getProfesionales(){
		return profesionales;
	}

	/**
	 * @param profesionales
	 *            the profesionales to set
	 */
	public void setProfesionales(List<Profesional> profesionales){
		this.profesionales = profesionales;
	}

	/**
	 * @return the problematicas
	 */
	public List<TipoDeProblematica> getProblematicas(){
		return problematicas;
	}

	/**
	 * @param problematicas
	 *            the problematicas to set
	 */
	public void setProblematicas(List<TipoDeProblematica> problematicas){
		this.problematicas = problematicas;
	}

	/**
	 * @return the tipoDePracticas
	 */
	public List<TipoDePractica> getTipoDePracticas(){
		return tipoDePracticas;
	}

	/**
	 * @param tipoDePracticas
	 *            the tipoDePracticas to set
	 */
	public void setTipoDePracticas(List<TipoDePractica> tipoDePracticas){
		this.tipoDePracticas = tipoDePracticas;
	}

	/**
	 * @return the pacientes
	 */
	public List<Paciente> getPacientes(){
		return pacientes;
	}

	/**
	 * @param pacientes
	 *            the pacientes to set
	 */
	public void setPacientes(List<Paciente> pacientes){
		this.pacientes = pacientes;
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	public Long getIdPractica(){
		return idPractica;
	}

	public void setIdPractica(Long idPractica){
		this.idPractica = idPractica;
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

	public Practica getPractica(){
		return practica;
	}

	public void setPractica(Practica practica){
		this.practica = practica;
	}

	public Long getIdPaciente(){
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente){
		this.idPaciente = idPaciente;
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
