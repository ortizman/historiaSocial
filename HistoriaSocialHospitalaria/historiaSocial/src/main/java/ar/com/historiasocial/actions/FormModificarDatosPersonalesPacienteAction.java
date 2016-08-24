package ar.com.historiasocial.actions;

import java.util.Date;
import java.util.Map;

import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Domicilio;
import ar.com.historiasocial.entities.Paciente;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FormModificarDatosPersonalesPacienteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long				idPaciente;
	private long				idD;
	private long				idV;
	private String				apellidos;
	private String				nombres;
	private String				documento;
	private Date				fechaNacimiento;
	private PacienteDAO			pacienteDAO;

	public long getIdPaciente(){
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente){
		this.idPaciente = idPaciente;
	}

	public void setIdD(long l){
		this.idD = l;
	}

	public long getIdD(){
		return idD;
	}

	public void setIdV(int idV){
		this.idV = idV;
	}

	public long getIdV(){
		return idV;
	}

	public String getApellidos(){
		return apellidos;
	}

	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}

	public String getNombres(){
		return nombres;
	}

	public void setNombres(String nombres){
		this.nombres = nombres;
	}

	public String getDocumento(){
		return documento;
	}

	public void setDocumento(String documento){
		this.documento = documento;
	}

	public Date getFechaNacimiento(){
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String execute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Paciente p = pacienteDAO.paciente(this.getIdPaciente());
		this.setNombres(p.getNombres());
		this.setApellidos(p.getApellidos());
		this.setDocumento(p.getDocumento());
		this.setFechaNacimiento(p.getFechaNacimiento());
		Domicilio d = p.getDomicilio();
		this.setIdD(d.getId());
		session.put("nombre", pacienteDAO.paciente(this.getIdPaciente()).getNombres());
		return SUCCESS;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

}
