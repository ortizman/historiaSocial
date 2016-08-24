package ar.com.historiasocial.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) //FIXME: El NONSTRICT_READ_WRITE es solo de prueba, en ambiente productivo debería ir READ_WRITE
@Table(name="historiaSocial")
public class HistoriaSocial implements ar.com.historiasocial.dao.Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(HistoriaSocial.class);
	
	@Id @GeneratedValue
	private Long id;
	@Column(columnDefinition="text")
	private String motivoIntervencionSocial;
	@Column(columnDefinition="date")
	private Date fechaInicio;
	@Column(columnDefinition="date")
	private Date fechaIngreso;
	@Column(columnDefinition="date")
	private Date fechaICServicioSocial;
	@Column(columnDefinition="text")
	private String otrosProfesinalesInterv;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="profesional_fk")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Profesional profesionalResponsable;
	
	@ManyToMany
	@JoinTable(name = "historiaSocial_profesionales", joinColumns = { @JoinColumn(name = "historiaSocial_Id") }, inverseJoinColumns = { @JoinColumn(name = "profesional_Id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Profesional> historialProfesionales = new ArrayList<Profesional>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Institucion institucion;
	
	@ManyToMany
	@JoinTable(name = "historiaSocial_instituciones", joinColumns = { @JoinColumn(name = "historiaSocial_Id") }, inverseJoinColumns = { @JoinColumn(name = "institucion_Id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Institucion> instituciones = new ArrayList<Institucion>();
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "tratamiento_historico_id")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Tratamiento> tratamientosHistoricos = new ArrayList<Tratamiento>();
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
	private Tratamiento tratamientoActual;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
	private Tratamiento tratamientoAmbulatorio = new Tratamiento("Practicas que se realizan de forma Ambulatoria", this);
	
	@OneToOne(mappedBy="historiaSocial")
	private Paciente paciente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMotivoIntervencionSocial() {
		return motivoIntervencionSocial;
	}
	public void setMotivoIntervencionSocial(String motivoIntervencionSocial) {
		this.motivoIntervencionSocial = motivoIntervencionSocial;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Profesional getProfesionalResponsable() {
		return profesionalResponsable;
	}
	public void setProfesionalResponsable(Profesional profesionalResponsable) {
		this.profesionalResponsable = profesionalResponsable;
	}
	public List<Profesional> getHistorialProfesionales() {
		return historialProfesionales;
	}
	public void setHistorialProfesionales(List<Profesional> historialProfesionales) {
		this.historialProfesionales = historialProfesionales;
	}
	public Institucion getInstitucion() {
		return institucion;
	}
	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	public List<Institucion> getInstituciones() {
		return instituciones;
	}
	public void setInstituciones(List<Institucion> instituciones) {
		this.instituciones = instituciones;
	}

	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		paciente.setHistoriaSocial(this);
	}
	
	public void agregarPractica(Practica p) throws NoExisteTratamientoActivoException{

		if(this.getTratamientoActual() != null){
			this.getTratamientoActual().getPracticas().add(p);
			p.setTratamiento(this.getTratamientoActual());
		}
		else {
			//Si no tiene tratamiento actual,significa que es una practica ambulatoria. 
			LOGGER.warn(" ********** Antención: La practica se registrara como ambulante en el paciente *********");
			this.getTratamientoAmbulatorio().getPracticas().add(p);
			p.setTratamiento(this.getTratamientoAmbulatorio());
			
		}
	}
	
	public void registrarIngresoPaciente(String motivo, Diagnostico diagnostico) {
		if(this.getTratamientoActual() != null){
			this.registrarAltaPaciente();
		}
		
		this.setTratamientoActual(new Tratamiento(motivo, this, diagnostico));
	}
	
	public void registrarIngresoPaciente(String motivo, Diagnostico diagnostico, Date fechaIngreso2, Date fechaServicioSocial, Date fechaInternacion, List<Profesional> profList) {
		if(this.getTratamientoActual() != null){
			this.registrarAltaPaciente();
		}
		
		this.setTratamientoActual(new Tratamiento(motivo, diagnostico, fechaIngreso2, fechaServicioSocial, fechaInternacion, profList, this));
	}
	
	public void registrarAltaPaciente() {
		this.getTratamientoActual().setFechaAltaEjecutada(new Date()); //Seteo la fecha de alta del paciente
		this.getTratamientosHistoricos().add(this.getTratamientoActual()); //Registro el tratamiento en el historico
		this.setTratamientoActual(null); // Elimino el tratamiento actual del paciente.
	}
	
	public void registrarAltaPaciente(Tratamiento t) {
		this.getTratamientoActual().setFechaAltaEjecutada(t.getFechaAltaEjecutada()); //Seteo la fecha de alta del paciente
		this.getTratamientoActual().setFechaAltaIniciada(t.getFechaAltaIniciada());
		this.getTratamientoActual().setCondicionesParaEgreso(t.getCondicionesParaEgreso());
		this.getTratamientoActual().setDetalleDiagnosticoAlta(t.getDetalleDiagnosticoAlta());
		this.getTratamientoActual().setDiagnosticoTabuladoAlta(t.getDiagnosticoTabuladoAlta());
		this.getTratamientoActual().setInstitucionResponsableSeguimiento(t.getInstitucionResponsableSeguimiento());
		this.getTratamientoActual().setMotivosModifFechaAlta(t.getMotivosModifFechaAlta());
		this.getTratamientoActual().setResponsableAdultoAlta(t.getResponsableAdultoAlta());
		this.getTratamientoActual().setProfesionalesResponsablesSeguimiento(t.getProfesionalesResponsablesSeguimiento());
		
		this.getTratamientosHistoricos().add(this.getTratamientoActual()); //Registro el tratamiento en el historico
		this.setTratamientoActual(null); // Elimino el tratamiento actual del paciente.
	}
	
	public void cambiarInstitucion(Institucion i){
		instituciones.add(institucion);
		this.setInstitucion(i);
	}
	
	public void cambiarProfesional(Profesional p){
		historialProfesionales.add(profesionalResponsable);
		profesionalResponsable.cambiarAHistorialHS(this);
		this.setProfesionalResponsable(p);
		p.agregarHistoriaSocial(this);		
	}
	
	public boolean existeDependenciaConInstitucion(Institucion i){
		boolean relacion= false;
		if(this.getInstitucion().getNombre().equals(i.getNombre())){
			relacion= true;
		}
		else{
			if(this.getInstituciones().size() > 0){
				Iterator<Institucion> it= this.getInstituciones().iterator();
				while(it.hasNext() && !relacion){
					Institucion ins= it.next();
					if(ins.getNombre().equals(i.getNombre())){
						relacion= true;
					}
				}
			}
		}
		return relacion;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaICServicioSocial() {
		return fechaICServicioSocial;
	}
	public void setFechaICServicioSocial(Date fechaICServicioSocial) {
		this.fechaICServicioSocial = fechaICServicioSocial;
	}
	public String getOtrosProfesinalesInterv() {
		return otrosProfesinalesInterv;
	}
	public void setOtrosProfesinalesInterv(String otrosProfesinalesInterv) {
		this.otrosProfesinalesInterv = otrosProfesinalesInterv;
	}
	public List<Tratamiento> getTratamientosHistoricos() {
		return tratamientosHistoricos;
	}
	public void setTratamientosHistoricos(List<Tratamiento> tratamientosHistoricos) {
		this.tratamientosHistoricos = tratamientosHistoricos;
	}
	public Tratamiento getTratamientoActual() {
		return tratamientoActual;
	}
	public void setTratamientoActual(Tratamiento tratamientoActual) {
		this.tratamientoActual = tratamientoActual;
	}
	public Tratamiento getTratamientoAmbulatorio() {
		return tratamientoAmbulatorio;
	}
	public void setTratamientoAmbulatorio(Tratamiento tratamientoAmbulatorio) {
		this.tratamientoAmbulatorio = tratamientoAmbulatorio;
	}
	
}
