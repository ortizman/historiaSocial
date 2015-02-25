package ar.com.historiasocial.actions;

import java.util.Map;

import ar.com.historiasocial.bo.ProfesionalBO;
import ar.com.historiasocial.entities.Profesional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IniciarSesionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				usuario;
	private String				pass;

	private ProfesionalBO profesionalBO; 

	public String getUsuario(){
		return usuario;
	}

	public void setUsuario(String usuario){
		this.usuario = usuario;
	}

	public String getPass(){
		return pass;
	}

	public void setPass(String pass){
		this.pass = pass;
	}

	@Override
	public void validate(){
		if (this.getUsuario().length() == 0) {
			addFieldError("usuario", "El nombre de usuario no puede estar vacío");

		}
		if (this.getPass().length() == 0) {
			addFieldError("pass", "La contraseña no puede estar vacío");
		}
	}

	@Override
	public String execute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("user");
		if (user == null) {
			Profesional prof = getProfesionalBO().existe(this.getUsuario(), this.getPass());
			if (prof != null) {
				session.put("esDirector", prof.getEsDirector());
				session.put("user", prof.getUser());
				session.put("profesionalLogin", prof);
				return SUCCESS;
			} else {
				addFieldError("usuario", "Datos Incorrectos");
				return INPUT;
			}
		} else {
			return "conectado";
		}
	}

	public ProfesionalBO getProfesionalBO(){
		return profesionalBO;
	}

	public void setProfesionalBO(ProfesionalBO profesionalBO){
		this.profesionalBO = profesionalBO;
	}

}
