package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.InstitucionDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.CondicionHabitacional;
import ar.com.historiasocial.entities.Domicilio;
import ar.com.historiasocial.entities.HistoriaSocial;
import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.TipoPropiedad;
import ar.com.historiasocial.entities.Vivienda;
import ar.com.historiasocial.util.PacienteUtils;

import com.opensymphony.xwork2.ActionSupport;

public class PacienteAction extends ActionSupport {

	/**
	 * Serial generado automaticamente
	 */
	private static final long					serialVersionUID			= 5381661846802129041L;

	private Paciente							paciente					= new Paciente();

	private PacienteDAO							pacienteDAO;
	private GenericDAO<Domicilio>				domicilioDAO;
	private GenericDAO<Vivienda>				viviendaDAO;
	private GenericDAO<CondicionHabitacional>	habitacionalDAO;
	private GenericDAO<TipoPropiedad>			tipoPropiedadDAO;
	private InstitucionDAO						institucionDAO;
	private HistoriaSocialDAO					historiaSocialDAO;
	private String								oper						= "";
	private String								submitAction				= "";

	private List<CondicionHabitacional>			condicionesHabitacionales	= new ArrayList<CondicionHabitacional>();
	private List<TipoPropiedad>					tipoDePropiedades			= new ArrayList<TipoPropiedad>();
	private List<Institucion>					instituciones				= new ArrayList<Institucion>();

	private static final Logger	LOGGER	= Logger.getLogger(PacienteAction.class);
	
	@SkipValidation
	public String creacionPaciente() throws HSControllerException{
		this.setPaciente(new Paciente());

		this.setOper("create");
		this.setSubmitAction("salvarPaciente.action");

		this.setTipoDePropiedades(getTipoPropiedadDAO().retrieveAll());
		this.setCondicionesHabitacionales(getHabitacionalDAO().retrieveAll());
		this.setInstituciones(institucionDAO.retrieveAll());

		return SUCCESS;
	}

	@SkipValidation
	public String visualizarPaciente() throws HSControllerException{
		Long pacienteId = this.getPaciente().getId();
		Paciente p = pacienteDAO.retrieveById(pacienteId);
		if (p == null) { return ERROR; }

		this.setOper("view");
		this.setSubmitAction("#");

		this.setPaciente(p);
		return SUCCESS;
	}

	@SkipValidation
	public String editarPaciente() throws HSControllerException{
		Long pacienteId = this.getPaciente().getId();
		Paciente p = pacienteDAO.retrieveById(pacienteId);
		if (p == null) { return ERROR; }
		this.setPaciente(p);

		this.setTipoDePropiedades(getTipoPropiedadDAO().retrieveAll());
		this.setCondicionesHabitacionales(getHabitacionalDAO().retrieveAll());
		this.setInstituciones(institucionDAO.retrieveAll());

		this.setOper("edit");
		this.setSubmitAction("modificarPaciente.action");
		return SUCCESS;
	}

	/*
	 * 
	 * Falla cuando no esta conectado a internet, porque no puede obtener las
	 * coordenadas para guardar el Location del paciente Corregir poniendo las
	 * coordenadas por defecto.
	 */
	public String salvarPaciente() throws HSControllerException{
		try {
			this.getPaciente().setHabilitado(true);
			this.getPaciente().setId(null);
			HistoriaSocial historiaSocial = getPaciente().getHistoriaSocial();

			historiaSocialDAO.save(historiaSocial);
			pacienteDAO.save(getPaciente());
			historiaSocial.setPaciente(getPaciente());
			historiaSocialDAO.saveOrUpdate(historiaSocial);
		} catch (RuntimeException e) {
			LOGGER.error("Se produjo un error al intentar ejecutar la operación", e);
			return ERROR;
		}

		return SUCCESS;
	}

	public String modificarPaciente() throws HSControllerException{

		Paciente p = pacienteDAO.retrieveById(this.getPaciente().getId());
		new PacienteUtils().copyProperties(p, this.getPaciente());

		// HistoriaSocial historiaSocial = p.getHistoriaSocial();
		// historiaSocialDAO.saveOrUpdate(historiaSocial);
		p.setHabilitado(true);
		pacienteDAO.saveOrUpdate(p);

		return SUCCESS;
	}

	// @Override
	// public Paciente getModel() {
	// return this.getPaciente();
	// }

	public Paciente getPaciente(){
		return paciente;
	}

	public void setPaciente(Paciente paciente){
		this.paciente = paciente;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	public String getOper(){
		return oper;
	}

	public void setOper(String oper){
		this.oper = oper;
	}

	public String getSubmitAction(){
		return submitAction;
	}

	public void setSubmitAction(String submitAction){
		this.submitAction = submitAction;
	}

	public InstitucionDAO getInstitucionDAO(){
		return institucionDAO;
	}

	public void setInstitucionDAO(InstitucionDAO institucionDAO){
		this.institucionDAO = institucionDAO;
	}

	public List<CondicionHabitacional> getCondicionesHabitacionales(){
		return condicionesHabitacionales;
	}

	public void setCondicionesHabitacionales(List<CondicionHabitacional> condicionesHabitacionales){
		this.condicionesHabitacionales = condicionesHabitacionales;
	}

	public List<TipoPropiedad> getTipoDePropiedades(){
		return tipoDePropiedades;
	}

	public void setTipoDePropiedades(List<TipoPropiedad> tipoDePropiedades){
		this.tipoDePropiedades = tipoDePropiedades;
	}

	public List<Institucion> getInstituciones(){
		return instituciones;
	}

	public void setInstituciones(List<Institucion> instituciones){
		this.instituciones = instituciones;
	}

	/**
	 * @return the domicilioDAO
	 */
	public GenericDAO<Domicilio> getDomicilioDAO(){
		return domicilioDAO;
	}

	/**
	 * @param domicilioDAO
	 *            the domicilioDAO to set
	 */
	public void setDomicilioDAO(GenericDAO<Domicilio> domicilioDAO){
		this.domicilioDAO = domicilioDAO;
	}

	/**
	 * @return the viviendaDAO
	 */
	public GenericDAO<Vivienda> getViviendaDAO(){
		return viviendaDAO;
	}

	/**
	 * @param viviendaDAO
	 *            the viviendaDAO to set
	 */
	public void setViviendaDAO(GenericDAO<Vivienda> viviendaDAO){
		this.viviendaDAO = viviendaDAO;
	}

	/**
	 * @return the habitacionalDAO
	 */
	public GenericDAO<CondicionHabitacional> getHabitacionalDAO(){
		return habitacionalDAO;
	}

	/**
	 * @param habitacionalDAO
	 *            the habitacionalDAO to set
	 */
	public void setHabitacionalDAO(GenericDAO<CondicionHabitacional> habitacionalDAO){
		this.habitacionalDAO = habitacionalDAO;
	}

	/**
	 * @return the tipoPropiedadDAO
	 */
	public GenericDAO<TipoPropiedad> getTipoPropiedadDAO(){
		return tipoPropiedadDAO;
	}

	/**
	 * @param tipoPropiedadDAO
	 *            the tipoPropiedadDAO to set
	 */
	public void setTipoPropiedadDAO(GenericDAO<TipoPropiedad> tipoPropiedadDAO){
		this.tipoPropiedadDAO = tipoPropiedadDAO;
	}

}
