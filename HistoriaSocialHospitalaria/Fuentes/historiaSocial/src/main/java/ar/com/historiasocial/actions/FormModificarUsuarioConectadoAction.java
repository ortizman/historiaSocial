package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Especialidad;
import ar.com.historiasocial.entities.Profesional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
public class FormModificarUsuarioConectadoAction extends ActionSupport {

	private static final long			serialVersionUID	= 1L;

	private long						id;
	private String						nombre;
	private String						apellido;
	private String						user;
	private String						pass;
	private String						matricula;
	private ProfesionalDAO				profesionalDAO;
	private GenericDAO<Especialidad>	especialidadDAO;

	private List<String>				especialidad		= new ArrayList<String>();
	private List<Especialidad>			especialidades		= new ArrayList<Especialidad>();

	public long getId(){
		return id;
	}

	public void setId(long l){
		this.id = l;
	}

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getApellido(){
		return apellido;
	}

	public void setApellido(String apellido){
		this.apellido = apellido;
	}

	public String getUser(){
		return user;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getPass(){
		return pass;
	}

	public void setPass(String pass){
		this.pass = pass;
	}

	public String getMatricula(){
		return matricula;
	}

	public void setMatricula(String matricula){
		this.matricula = matricula;
	}

	public List<String> getEspecialidad(){
		return especialidad;
	}

	public void setEspecialidad(List<String> especialidad){
		this.especialidad = especialidad;
	}

	public List<Especialidad> getEspecialidades(){
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades){
		this.especialidades = especialidades;
	}

	@Override
	public String execute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("user");

		Profesional p = profesionalDAO.profesionalByUser(user);
		this.setId(p.getId());
		this.setNombre(p.getNombre());
		this.setApellido(p.getApellido());
		this.setUser(p.getUser());
		this.setPass(p.getPass());
		this.setMatricula(p.getMatricula());
		this.setEspecialidades(especialidadDAO.retrieveAll());
		Iterator<Especialidad> it2 = p.getEspecialidades().iterator();
		while (it2.hasNext()) {
			Especialidad e = it2.next();
			this.getEspecialidad().add(e.getDescripcion());
		}
		return SUCCESS;
	}

	public ProfesionalDAO getProfesionalDAO(){
		return profesionalDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO){
		this.profesionalDAO = profesionalDAO;
	}
}
