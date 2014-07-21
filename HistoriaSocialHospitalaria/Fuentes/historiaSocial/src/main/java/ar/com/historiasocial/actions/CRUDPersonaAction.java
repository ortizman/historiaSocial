package ar.com.historiasocial.actions;

import java.util.List;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Conviviente;
import ar.com.historiasocial.entities.ObraSocial;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CRUDPersonaAction extends ActionSupport implements ModelDriven<Conviviente> {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	private Conviviente				conviviente			= new Conviviente();
	private String					mensaje;
	private Long					idPaciente;

	private String					oper;

	private PacienteDAO				pacienteDAO;
	private GenericDAO<ObraSocial>	obraSocialDAO;
	private List<ObraSocial>		obrasSociales;

	public String operConviviente(){
		boolean exito = false;
		if (oper.equalsIgnoreCase("edit")) {
			exito = false;
		} else {
			if (oper.equalsIgnoreCase("del")) {
				exito = eliminarConviviente();
			}
		}

		if (!exito) {
			addFieldError("error", "Ha ocurrido un problema");
			return INPUT;
		} else {
			this.setObrasSociales(getObraSocialDAO().retrieveAll());
			return SUCCESS;
		}
	}
	
	/**
	 * Este método se expone con Struts y se invoca directamente.
	 * 
	 * @return
	 */
	public String salvarConviviente(){
		Conviviente p = this.getModel();
		try {
			pacienteDAO.agregarConviviente(p, idPaciente);
			this.setObrasSociales(getObraSocialDAO().retrieveAll());
		} catch (Exception e) {
			return ERROR;
		}

		return SUCCESS;
	}
	
	public String editarConviviente(){
		Conviviente conv = this.getModel();
		try {
			pacienteDAO.editarConviviente(conv);
			this.setObrasSociales(getObraSocialDAO().retrieveAll());
		} catch (Exception e) {
			return ERROR;
		}

		return SUCCESS;
	}

	private Boolean eliminarConviviente(){
		Conviviente p = this.getModel();
		try {
			pacienteDAO.eliminarConviviente(p, idPaciente);
		} catch (Exception e) {
			return false;
		}

		return true;
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

	// public Long getId() {
	// return Long.valueOf(id);
	// }
	// public void setId(String id) {
	// this.id = id;
	// }

	public String getMensaje(){
		return mensaje;
	}

	public void setMensaje(String mensaje){
		this.mensaje = mensaje;
	}

	@Override
	public Conviviente getModel(){
		return this.conviviente;
	}

	// public Conviviente getConviviente() {
	// return conviviente;
	// }
	// public void setConviviente(Conviviente conv) {
	// this.conviviente = conv;
	// }

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	public Long getIdPaciente(){
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente){
		this.idPaciente = idPaciente;
	}

	public void setConviviente(Conviviente conviviente){
		this.conviviente = conviviente;
	}

	public void setObrasSociales(List<ObraSocial> obrasSociales){
		this.obrasSociales = obrasSociales;
	}

	public Conviviente getConviviente(){
		return conviviente;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public List<ObraSocial> getObrasSociales(){
		return obrasSociales;
	}

	/**
	 * @return the obraSocialDAO
	 */
	private GenericDAO<ObraSocial> getObraSocialDAO(){
		return obraSocialDAO;
	}

	/**
	 * @param obraSocialDAO
	 *            the obraSocialDAO to set
	 */
	public void setObraSocialDAO(GenericDAO<ObraSocial> obraSocialDAO){
		this.obraSocialDAO = obraSocialDAO;
	}

}
