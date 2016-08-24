package ar.com.historiasocial.actions;

import java.util.Date;
import java.util.Map;

import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Paciente;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * The Class ModificarDatosPersonalesPacienteAction.
 */
public class ModificarDatosPersonalesPacienteAction extends ActionSupport {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The id paciente. */
	private int					idPaciente;

	/** The id d. */
	private int					idD;

	/** The id v. */
	private int					idV;

	/** The apellidos. */
	private String				apellidos;

	/** The nombres. */
	private String				nombres;

	/** The documento. */
	private String				documento;

	/** The fecha nacimiento. */
	private Date				fechaNacimiento;

	private PacienteDAO			pacienteDAO;

	/**
	 * Gets the id paciente.
	 * 
	 * @return the id paciente
	 */
	public int getIdPaciente(){
		return idPaciente;
	}

	/**
	 * Sets the id paciente.
	 * 
	 * @param idPaciente
	 *            the new id paciente
	 */
	public void setIdPaciente(int idPaciente){
		this.idPaciente = idPaciente;
	}

	/**
	 * Gets the id d.
	 * 
	 * @return the id d
	 */
	public int getIdD(){
		return idD;
	}

	/**
	 * Sets the id d.
	 * 
	 * @param idD
	 *            the new id d
	 */
	public void setIdD(int idD){
		this.idD = idD;
	}

	/**
	 * Gets the id v.
	 * 
	 * @return the id v
	 */
	public int getIdV(){
		return idV;
	}

	/**
	 * Sets the id v.
	 * 
	 * @param idV
	 *            the new id v
	 */
	public void setIdV(int idV){
		this.idV = idV;
	}

	/**
	 * Gets the apellidos.
	 * 
	 * @return the apellidos
	 */
	public String getApellidos(){
		return apellidos;
	}

	/**
	 * Sets the apellidos.
	 * 
	 * @param apellidos
	 *            the new apellidos
	 */
	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}

	/**
	 * Gets the nombres.
	 * 
	 * @return the nombres
	 */
	public String getNombres(){
		return nombres;
	}

	/**
	 * Sets the nombres.
	 * 
	 * @param nombres
	 *            the new nombres
	 */
	public void setNombres(String nombres){
		this.nombres = nombres;
	}

	/**
	 * Gets the documento.
	 * 
	 * @return the documento
	 */
	public String getDocumento(){
		return documento;
	}

	/**
	 * Sets the documento.
	 * 
	 * @param documento
	 *            the new documento
	 */
	public void setDocumento(String documento){
		this.documento = documento;
	}

	/**
	 * Gets the fecha nacimiento.
	 * 
	 * @return the fecha nacimiento
	 */
	public Date getFechaNacimiento(){
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 * 
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute(){
		Map<String, Object> session = ActionContext.getContext().getSession();

		Paciente p = getPacienteDAO().paciente(this.getIdPaciente());

		p.setNombres(this.getNombres());
		p.setApellidos(this.getApellidos());
		p.setDocumento(this.getDocumento());
		p.setFechaNacimiento(this.getFechaNacimiento());
		boolean exito3 = getPacienteDAO().saveOrUpdate(p) != null;
		if (exito3) {
			session.put("nombre", p.getNombres());
			session.put("origen", "modificarPaciente?id=" + this.getIdPaciente());
			return SUCCESS;
		} else {
			addFieldError("nombres", "Ha ocurrido un error");
		}
		return INPUT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate(){
		Boolean error = false;
		if (this.getNombres().length() == 0) {
			addFieldError("nombres", "El campo Nombre es requerido");
			error = true;
		}
		if (this.getApellidos().length() == 0) {
			addFieldError("apellidos", "El campo Apellido es requerido");
			error = true;
		}
		if (this.getDocumento().length() == 0) {
			addFieldError("documento", "El campo Numero de Documento es requerido");
			error = true;
		}
		if (this.getFechaNacimiento() == null) {
			addFieldError("fechaNacimiento", "El campo Fecha de Nacimiento es requerido");
			error = true;
		}
		if (!error) {

			boolean existe = getPacienteDAO().existeIncluyendoId(this.getDocumento(), this.getIdPaciente());
			if (existe) {
				addFieldError("user", "Ya existe este DNI");
			}
		}
	}

	/**
	 * @return the pacienteDAO
	 */
	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	/**
	 * @param pacienteDAO
	 *            the pacienteDAO to set
	 */
	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}
}
