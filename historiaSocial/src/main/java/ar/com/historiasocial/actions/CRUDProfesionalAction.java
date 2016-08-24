package ar.com.historiasocial.actions;

import java.util.Map;

import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Profesional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CRUDProfesionalAction extends ActionSupport implements ModelDriven<Profesional> {

	private static final long	serialVersionUID	= 1L;

	private Profesional			profesional			= new Profesional();
	private String				id;
	private String				mensaje;
	private String				oper;

	private ProfesionalDAO		profesionalDAO;

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
		getProfesionalDAO().delete(profesional.getId());
		return true;
	}

	private boolean editar(){
		profesional.setHabilitado(true);
		return getProfesionalDAO().saveOrUpdate(profesional) != null;
	}

	private boolean add(){
		profesional.setHabilitado(true);
		return getProfesionalDAO().save(profesional) != null;
	}

	/**
	 * @return the id
	 */
	public String getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id){
		this.id = id;
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
	public Profesional getModel(){
		return this.getProfesional();
	}

	public Profesional getProfesional(){
		return profesional;
	}

	public void setProfesional(Profesional profesional){
		this.profesional = profesional;
	}

	public ProfesionalDAO getProfesionalDAO(){
		return profesionalDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO){
		this.profesionalDAO = profesionalDAO;
	}
}
