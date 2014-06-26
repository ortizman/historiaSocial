package ar.com.historiasocial.dto;

import ar.com.historiasocial.entities.Profesional;

public class ProfesionalDTO {
	private long id;
	private String user;
	private String apellido;
	private String nombre;
	private String matricula;
	private String servicio;
	private Boolean esDirector;
	private Boolean habilitado;
	
	public ProfesionalDTO(Profesional profesional) {
		this.setId(profesional.getId());
		this.setApellido(profesional.getApellido());
		this.setEsDirector(profesional.getEsDirector());
		this.setHabilitado(profesional.getHabilitado());
		this.setMatricula(profesional.getMatricula());
		this.setUser(profesional.getUser());
		this.setNombre(profesional.getNombre());
		if(profesional.getServicio() != null){
			this.setServicio(profesional.getServicio().getNombreServicio());
		}
		
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the esDirector
	 */
	public Boolean getEsDirector() {
		return esDirector;
	}

	/**
	 * @param esDirector the esDirector to set
	 */
	public void setEsDirector(Boolean esDirector) {
		this.esDirector = esDirector;
	}

	/**
	 * @return the habilitado
	 */
	public Boolean getHabilitado() {
		return habilitado;
	}

	/**
	 * @param habilitado the habilitado to set
	 */
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
