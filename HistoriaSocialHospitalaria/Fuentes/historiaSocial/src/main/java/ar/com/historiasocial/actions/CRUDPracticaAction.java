package ar.com.historiasocial.actions;

import java.util.Map;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.entities.HistoriaSocial;
import ar.com.historiasocial.entities.NoExisteTratamientoActivoException;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Practica;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CRUDPracticaAction extends ActionSupport implements ModelDriven<Practica> {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String				mensaje;
	private String				oper;
	private Long				pacienteId;

	private Practica			practica			= new Practica();

	private PracticaDAO			practicaDAO;
	private HistoriaSocialDAO	historiaSocialDAO;
	private PacienteDAO			pacienteDAO;

	private static final Logger	LOGGER				= Logger.getLogger(CRUDPracticaAction.class);

	@Override
	public void validate(){
		if (this.getPractica().getDetalle() == null) {
			addFieldError("detalle", "no puede ser vacio!");
		}
	}

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
		boolean ok = true;
		try {
			practicaDAO.delete(this.getPractica().getId());
		} catch (Exception e) {
			System.out.println("Error al eliminar la practica: " + e.getMessage());
			ok = false;
		}
		return ok;
	}

	private boolean editar(){
		this.getPractica().setHabilitado(true);
		return practicaDAO.saveOrUpdate(this.getPractica()) != null;
	}

	private boolean add(){
		this.getPractica().setId(null);
		this.getPractica().setHabilitado(true);

		Paciente paciente = pacienteDAO.paciente(this.getPacienteId());

		if (paciente == null) { return false; }

		HistoriaSocial historiaSocial = paciente.getHistoriaSocial();
		if (historiaSocial == null) { return false; }

		Practica practica = this.getPracticaDAO().save(getPractica());
		try {
			historiaSocial.agregarPractica(practica);
		} catch (NoExisteTratamientoActivoException e) {
			LOGGER.error("No se pude agregar la practica a la historia social del paciente");
			return false;
		}

		return this.getHistoriaSocialDAO().saveOrUpdate(historiaSocial) != null;
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

	public Practica getPractica(){
		return practica;
	}

	public void setPractica(Practica practica){
		this.practica = practica;
	}

	@Override
	public Practica getModel(){
		return this.practica;
	}

	public PracticaDAO getPracticaDAO(){
		return practicaDAO;
	}

	public void setPracticaDAO(PracticaDAO practicaDAO){
		this.practicaDAO = practicaDAO;
	}

	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return historiaSocialDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	public Long getPacienteId(){
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId){
		this.pacienteId = pacienteId;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}
}
