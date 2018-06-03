/**
 * 
 */
package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Diagnostico;
import ar.com.historiasocial.entities.HistoriaSocial;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.Tratamiento;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class IngresoEgresoPacienteAction extends ActionSupport implements ModelDriven<Tratamiento> {

	private Tratamiento				tratamiento			= new Tratamiento();
	private Long					idPaciente;
	private List<Long>				profesionalesValues;
	private String					profesionales;
	private ProfesionalDAO			profesionalDAO;
	private PacienteDAO				pacienteDAO;
	private GenericDAO<Diagnostico>	diagnosticoDAO;
	private HistoriaSocialDAO		historiaSocialDAO;

	private final static String		ERROR_NO_INGRESADO	= "NoFueIngresado";

	private List<Diagnostico>		diagnosticos;
	private String					actionExecute;

	/**
	 * Serial ID
	 */
	private static final long		serialVersionUID	= -7734187885180069048L;

	public String registrarIngreso() throws HSControllerException{
		List<Profesional> profesionales = profesionalDAO.retrieveAll();
		setDiagnosticos(getDiagnosticoDAO().retrieveAll());

		ServletActionContext.getContext().getSession().put("profesionales", profesionales);

		Paciente paciente = pacienteDAO.paciente(idPaciente);
		if (paciente.tieneTratamientoActivo()) {
			Tratamiento tratamientoActual = paciente.getHistoriaSocial().getTratamientoActual();
			this.setTratamiento(tratamientoActual);
			this.setProfesionalesValues(unmarshalProfesionales(tratamientoActual.getProfesionals()));

			this.setActionExecute("editarIngreso.action");
		} else {
			this.setActionExecute("saveIngreso.action");
		}

		return SUCCESS;
	}

	public String editarIngreso() throws HSControllerException{
		Paciente paciente = pacienteDAO.paciente(idPaciente);
		HistoriaSocial historiaSocial = paciente.getHistoriaSocial();

		historiaSocial.setTratamientoActual(actualizarTratamiento(historiaSocial.getTratamientoActual()));
		this.getHistoriaSocialDAO().saveOrUpdate(historiaSocial);
		return SUCCESS;
	}

	private Tratamiento actualizarTratamiento(Tratamiento tratamientoActual){
		List<Profesional> profList = marshalProfesionales(profesionales);
		tratamientoActual.setProfesionals(profList);
		tratamientoActual.setDetalleDiagnosticoIngreso(getTratamiento().getDetalleDiagnosticoIngreso());
		tratamientoActual.setDiagnosticoTabuladoAlIngreso(getTratamiento().getDiagnosticoTabuladoAlIngreso());
		tratamientoActual.setFechaIngreso(getTratamiento().getFechaIngreso());
		tratamientoActual.setFechaServicioSocial(getTratamiento().getFechaServicioSocial());
		tratamientoActual.setFechaInternacion(getTratamiento().getFechaInternacion());

		return tratamientoActual;
	}

	public String saveIngreso(){
		Paciente paciente = pacienteDAO.paciente(idPaciente);
		if (paciente == null) { return ERROR; }

		HistoriaSocial historiaSocial = paciente.getHistoriaSocial();
		if (historiaSocial == null) { return ERROR; }

		List<Profesional> profList = marshalProfesionales(profesionales);

		historiaSocial.registrarIngresoPaciente(tratamiento.getDetalleDiagnosticoIngreso(), tratamiento.getDiagnosticoTabuladoAlIngreso(),
				tratamiento.getFechaIngreso(), tratamiento.getFechaServicioSocial(), tratamiento.getFechaInternacion(), profList);

		historiaSocialDAO.saveOrUpdate(historiaSocial);

		this.setDiagnosticos(new ArrayList<Diagnostico>());

		return SUCCESS;
	}

	/**
	 * Prepara el formulario para el alta
	 * 
	 * @return SUCCESS
	 * @throws HSControllerException
	 */
	public String registrarAlta() throws HSControllerException{
		Paciente paciente = pacienteDAO.paciente(idPaciente);

		if (paciente.tieneTratamientoActivo()) {
			Tratamiento tratamientoActual = paciente.getHistoriaSocial().getTratamientoActual();
			setDiagnosticos(getDiagnosticoDAO().retrieveAll());
			this.setTratamiento(tratamientoActual);
		} else {
			return ERROR_NO_INGRESADO;
		}

		return SUCCESS;
	}

	public String saveAlta(){

		Paciente paciente = pacienteDAO.paciente(idPaciente);
		if (paciente == null) { return ERROR; }

		HistoriaSocial historiaSocial = paciente.getHistoriaSocial();
		if (historiaSocial == null) { return ERROR; }

		historiaSocial.registrarAltaPaciente(this.getTratamiento());

		historiaSocialDAO.saveOrUpdate(historiaSocial);

		return SUCCESS;
	}

	private List<Profesional> marshalProfesionales(String profesionales){
		List<Profesional> profs = new ArrayList<Profesional>();
		String[] splitProf = profesionales.split(",");
		for (String profId : splitProf) {
			Profesional prof = new Profesional();
			prof.setId(Long.valueOf(profId));
			profs.add(prof);
		}

		return profs;
	}

	private List<Long> unmarshalProfesionales(List<Profesional> profesionales){
		List<Long> profs = new ArrayList<Long>();

		for (Profesional pro : profesionales) {
			profs.add(pro.getId());
		}

		return profs;
	}

	@Override
	public Tratamiento getModel(){
		return tratamiento;
	}

	public ProfesionalDAO getProfesionalDAO(){
		return profesionalDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO){
		this.profesionalDAO = profesionalDAO;
	}

	public Long getIdPaciente(){
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente){
		this.idPaciente = idPaciente;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	public Tratamiento getTratamiento(){
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento){
		this.tratamiento = tratamiento;
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public List<Long> getProfesionalesValues(){
		return profesionalesValues;
	}

	public void setProfesionalesValues(List<Long> profesionales){
		this.profesionalesValues = profesionales;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	public void setProfesionales(String profesionales){
		this.profesionales = profesionales;
	}

	public String getProfesionales(){
		return profesionales;
	}

	public String getActionExecute(){
		return actionExecute;
	}

	public void setActionExecute(String actionExecute){
		this.actionExecute = actionExecute;
	}

	public List<Diagnostico> getDiagnosticos(){
		return diagnosticos;
	}

	public void setDiagnosticos(List<Diagnostico> diagnosticos){
		this.diagnosticos = diagnosticos;
	}

	/**
	 * @return the diagnosticoDAO
	 */
	private GenericDAO<Diagnostico> getDiagnosticoDAO() {
		return diagnosticoDAO;
	}

	/**
	 * @param diagnosticoDAO the diagnosticoDAO to set
	 */
	public void setDiagnosticoDAO(GenericDAO<Diagnostico> diagnosticoDAO) {
		this.diagnosticoDAO = diagnosticoDAO;
	}
}
