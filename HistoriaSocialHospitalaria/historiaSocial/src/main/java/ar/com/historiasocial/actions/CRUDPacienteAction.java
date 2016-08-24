package ar.com.historiasocial.actions;

import java.util.Map;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Domicilio;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Vivienda;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CRUDPacienteAction extends ActionSupport implements ModelDriven<Paciente> {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	private String					mensaje;
	private String					oper;

	private Paciente				paciente			= new Paciente();

	private PacienteDAO				pacienteDAO;
	private GenericDAO<Domicilio>	domicilioDAO;
	private GenericDAO<Vivienda>	viviendaDAO;
	private HistoriaSocialDAO		historiaSocialDAO;

	@Override
	public String execute(){
		boolean exito = false;
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (oper.equalsIgnoreCase("add")) {
			exito = add();
		} else {
			if (oper.equalsIgnoreCase("edit")) {
				exito = editar();
			} else {
				if (oper.equalsIgnoreCase("del")) {
					exito = elimniar();
				}
			}
		}

		if (!exito) {
			addFieldError("error", "Ha ocurrido un problema");
			return INPUT;
		} else {
			session.put("error", "abmEspecialidades");
			return SUCCESS;
		}

	}

	private boolean elimniar(){
		Paciente p = pacienteDAO.retrieveById(this.getPaciente().getId());
		p.setHabilitado(false);
		return pacienteDAO.saveOrUpdate(p) != null;
	}

	private boolean editar(){
		this.getDomicilioDAO().saveOrUpdate(this.getPaciente().getDomicilio());
		this.getViviendaDAO().saveOrUpdate(this.getPaciente().getVivienda());
		this.getPaciente().getHistoriaSocial().setPaciente(getPaciente());
		this.getHistoriaSocialDAO().saveOrUpdate(this.getPaciente().getHistoriaSocial());
		this.getPaciente().setHabilitado(true);
		return pacienteDAO.saveOrUpdate(this.getPaciente()) != null;
	}

	private boolean add(){
		this.getPaciente().setHabilitado(true);
		this.getDomicilioDAO().save(this.getPaciente().getDomicilio());
		this.getViviendaDAO().save(this.getPaciente().getVivienda());
		this.getHistoriaSocialDAO().save(this.getPaciente().getHistoriaSocial());
		Paciente pac = this.getPacienteDAO().save(this.getPaciente());
		pac.getHistoriaSocial().setPaciente(pac);

		return this.getHistoriaSocialDAO().save(this.getPaciente().getHistoriaSocial()) != null;

	}

	@Override
	public void validate(){
		if (oper.equalsIgnoreCase("add") && this.getPaciente().getDniApellido().length() == 0) {
			addFieldError("paciente", "Los campos DNI y Apellido son requerido");
		}
		super.validate();
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
	public Paciente getModel(){
		return this.getPaciente();
	}

	public Paciente getPaciente(){
		return paciente;
	}

	public void setPaciente(Paciente paciente){
		this.paciente = paciente;
	}

	public PacienteDAO getPacdj(){
		return pacienteDAO;
	}

	public GenericDAO<Domicilio> getDomicilioDAO(){
		return domicilioDAO;
	}

	public GenericDAO<Vivienda> getViviendaDAO(){
		return viviendaDAO;
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	private PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	public void setDomicilioDAO(GenericDAO<Domicilio> domicilioDAO){
		this.domicilioDAO = domicilioDAO;
	}

	public void setViviendaDAO(GenericDAO<Vivienda> viviendaDAO){
		this.viviendaDAO = viviendaDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}
}
