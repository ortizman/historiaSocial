package ar.com.historiasocial.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="profesional")
public class Profesional implements ar.com.historiasocial.dao.Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	private String user;
	private String pass;
	private String apellido;
	private String nombre;
	private String matricula;
	private Boolean esDirector;
	private Boolean habilitado;
	
	
	@ManyToOne(cascade={CascadeType.DETACH}, fetch=FetchType.EAGER)
    @JoinColumn(name="Servicio_fk")
	private Servicio servicio;
	
	@ManyToMany(cascade=CascadeType.DETACH)
	private List<Especialidad> especialidades = new ArrayList<Especialidad>();
	
	@ManyToMany
	@JoinTable(name = "historiaSocial_profesionales", joinColumns = { @JoinColumn(name = "profesional_Id") }, inverseJoinColumns = { @JoinColumn(name = "historiaSocial_Id") })
	private List<HistoriaSocial> historialHistoriasSociales = new ArrayList<HistoriaSocial>();
	
	@OneToMany(cascade=CascadeType.DETACH)
    @JoinColumn(name="profesional_fk")
	private List<HistoriaSocial> historiaSocial = new ArrayList<HistoriaSocial>();
	
	@OneToMany
    @JoinColumn(name="profesionalPractica_fk")
	private List<Practica> practicas = new ArrayList<Practica>();
	
	@ManyToMany(mappedBy="profesionals")
	private List<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
	
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	public List<HistoriaSocial> getHistorialHistoriasSociales() {
		return historialHistoriasSociales;
	}
	public void setHistorialHistoriasSociales(
			List<HistoriaSocial> historialHistoriasSociales) {
		this.historialHistoriasSociales = historialHistoriasSociales;
	}
	public List<HistoriaSocial> getHistoriaSocial() {
		return historiaSocial;
	}
	public void setHistoriaSocial(List<HistoriaSocial> historiaSocial) {
		this.historiaSocial = historiaSocial;
	}
	public List<Practica> getPracticas() {
		return practicas;
	}
	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public Boolean getEsDirector() {
		return esDirector;
	}
	public void setEsDirector(Boolean esDirector) {
		this.esDirector = esDirector;
	}
	
	public String getNombreCompleto(){
		return this.getApellido() + ", " + this.getNombre();
	}
	
	public void agregarEspecialidad(Especialidad e){
		especialidades.add(e);	
	}
	
	public void agregarColeccionEspecialidades(List<Especialidad> lista){
		especialidades.clear();
		Iterator<Especialidad> it= lista.iterator();
		while(it.hasNext()){
			this.agregarEspecialidad(it.next());
		}
	}
	
	public void agregarPractica(Practica p){
		practicas.add(p);
	}
	
	public void agregarHistoriaSocial(HistoriaSocial hs){
		this.getHistoriaSocial().add(hs);
	}
	
	public void cambiarAHistorialHS(HistoriaSocial hs){
		this.getHistoriaSocial().remove(hs);
		this.getHistorialHistoriasSociales().add(hs);
	}
	
	public boolean tieneDependencias(){
		boolean dependencia= false;
		if(this.getHistoriaSocial().size() > 0){
			dependencia= true;
		}
		if(this.getPracticas().size() > 0){
			dependencia= true;
		}
		return dependencia;
	}
	
	public void eliminar(){
		this.setHabilitado(false);
	}
	
	public void cambiarServicio(Servicio s){
		this.setServicio(s);
	}
	
	public List<Tratamiento> getTratamientos() {
		return tratamientos;
	}
	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.apellido == null) ? 0 : this.apellido.hashCode());
		result = prime * result + ((this.habilitado == null) ? 0 : this.habilitado.hashCode());
		result = prime * result + ((this.matricula == null) ? 0 : this.matricula.hashCode());
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		Profesional other = (Profesional) obj;
		if (this.apellido == null) {
			if (other.apellido != null) { return false; }
		} else if (!this.apellido.equals(other.apellido)) { return false; }
		if (this.habilitado == null) {
			if (other.habilitado != null) { return false; }
		} else if (!this.habilitado.equals(other.habilitado)) { return false; }
		if (this.matricula == null) {
			if (other.matricula != null) { return false; }
		} else if (!this.matricula.equals(other.matricula)) { return false; }
		if (this.nombre == null) {
			if (other.nombre != null) { return false; }
		} else if (!this.nombre.equals(other.nombre)) { return false; }
		return true;
	}

	
}
