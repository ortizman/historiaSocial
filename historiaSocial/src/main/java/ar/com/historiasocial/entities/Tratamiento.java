package ar.com.historiasocial.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="tratamiento")
public class Tratamiento implements ar.com.historiasocial.dao.Entity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Motivo de la intervencion/internacion por el cual el paciente entra en tratamiento.
	 */
	@Column(name = "motivo_internacion")
	private String detalleDiagnosticoAlta;
	
	/**
	 * es un tipo de diagnostico general, sirve como agrupador
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Diagnostico diagnosticoTabuladoAlIngreso;
	
	/**
	 * Diagnostico en el momento del Alta
	 */
	@Column(name = "detalle_diag_alta")
	private String detalleDiagnosticoIngreso;

	/**
	 * Es un tipo de diagnostico general, sirve como agrupador
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Diagnostico diagnosticoTabuladoAlta;	
	
	@Column(name = "motivo_modificacion_fechaalta")
	private String motivosModifFechaAlta;

	@Column(name = "responsable_alta")
	private String responsableAdultoAlta;

	@Column(name = "condiciones_egreso")
	private String condicionesParaEgreso;

	private String institucionResponsableSeguimiento;
	
	private String profesionalesResponsablesSeguimiento;
	
	/**
	 * Fecha en la que el paciente ingresa al hospital y comienza un nuevo tratamiento.
	 */
	@Column(columnDefinition="date")
	private Date fechaIngreso;

	/**
	 * Fecha en la que el paciente es internado en el hospital
	 */
	@Column(columnDefinition="date")
	private Date fechaInternacion;
	
	@Column(columnDefinition="date")
	private Date fechaServicioSocial;
	
	/**
	 * Fecha en la que el paciente finaliza el tratamiento, se da de alta el paciente.
	 */
	@Column(columnDefinition="date")
	private Date fechaAltaEjecutada;

	@Column(columnDefinition="date")
	private Date fechaAltaIniciada;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tratamiento")
	private List<Practica> practicas;

	@ManyToMany
	@JoinTable(
		      name="TRATAMIENTO_PROFESIONAL",
		      joinColumns={@JoinColumn(name="TRATAMIENTO_ID", referencedColumnName="ID")},
		      inverseJoinColumns={@JoinColumn(name="PROFESIONAL_ID", referencedColumnName="ID")})
	private List<Profesional> profesionals;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historiaSocial_fk")
	private HistoriaSocial historiaSocial;
	
	private static final long serialVersionUID = -2810101131516651353L;

	/**
	 * Constructor vacio
	 */
	public Tratamiento() {
		super();
		this.setPracticas(new ArrayList<Practica>());
	}
	
	
	/**
	 * Setea el motivo que viene como parametro y
	 * setea la fecha de ingreso con la fecha y hora actual.
	 * Inicializa la lista de practicas con una collecion vacia.
	 * setea la historia social
	 * @param motivo
	 * @param historiaSocial
	 */
	public Tratamiento(String motivo, HistoriaSocial historiaSocial, Diagnostico d) {
		this.setDetalleDiagnosticoIngreso(motivo);
		this.setFechaIngreso(new Date());
		this.setDiagnosticoTabuladoAlIngreso(d);
		this.setPracticas(new ArrayList<Practica>());
		this.setHistoriaSocial(historiaSocial);
	}
	
	
	public Tratamiento(String motivo2, Diagnostico diagnostico, Date fechaIngreso2, Date fechaServicioSocial2, Date fechaInternacion, List<Profesional> profList, HistoriaSocial historiaSocial2) {
		this.setDetalleDiagnosticoIngreso(motivo2);
		this.setDiagnosticoTabuladoAlIngreso(diagnostico);
		this.setFechaIngreso(fechaIngreso2);
		this.setFechaServicioSocial(fechaServicioSocial2);
		this.setFechaInternacion(fechaInternacion);
		this.setHistoriaSocial(historiaSocial2);
		this.setProfesionals(profList);
		
		for (Profesional profesional : profList) {
			profesional.getTratamientos().add(this);
		}
		
		this.setPracticas(new ArrayList<Practica>());
	}


	public Tratamiento(String motivo, HistoriaSocial historiaSocial) {
		this.setDetalleDiagnosticoIngreso(motivo);
		this.setFechaIngreso(new Date());
		this.setPracticas(new ArrayList<Practica>());
		this.setHistoriaSocial(historiaSocial);
	}


	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getDetalleDiagnosticoIngreso() {
		return detalleDiagnosticoIngreso;
	}

	public void setDetalleDiagnosticoIngreso(String motivo) {
		this.detalleDiagnosticoIngreso = motivo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaAltaEjecutada() {
		return fechaAltaEjecutada;
	}

	public void setFechaAltaEjecutada(Date fechaAlta) {
		this.fechaAltaEjecutada = fechaAlta;
	}

	public List<Practica> getPracticas() {
		return practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

	public HistoriaSocial getHistoriaSocial() {
		return historiaSocial;
	}

	public void setHistoriaSocial(HistoriaSocial historiaSocial) {
		this.historiaSocial = historiaSocial;
	}


	public Date getFechaServicioSocial() {
		return fechaServicioSocial;
	}


	public void setFechaServicioSocial(Date fechaServicioSocial) {
		this.fechaServicioSocial = fechaServicioSocial;
	}


	public List<Profesional> getProfesionals() {
		return profesionals;
	}


	public void setProfesionals(List<Profesional> profesionals) {
		this.profesionals = profesionals;
	}


	public Date getFechaInternacion() {
		return fechaInternacion;
	}


	public void setFechaInternacion(Date fechaInternacion) {
		this.fechaInternacion = fechaInternacion;
	}


	public Diagnostico getDiagnosticoTabuladoAlIngreso(){
		return diagnosticoTabuladoAlIngreso;
	}


	public void setDiagnosticoTabuladoAlIngreso(Diagnostico diagnostico){
		this.diagnosticoTabuladoAlIngreso = diagnostico;
	}


	/**
	 * @return the detalleDiagnosticoAlta
	 */
	public String getDetalleDiagnosticoAlta(){
		return detalleDiagnosticoAlta;
	}


	/**
	 * @param detalleDiagnosticoAlta the detalleDiagnosticoAlta to set
	 */
	public void setDetalleDiagnosticoAlta(String detalleDiagnosticoAlta){
		this.detalleDiagnosticoAlta = detalleDiagnosticoAlta;
	}


	/**
	 * @return the diagnosticoTabuladoAlta
	 */
	public Diagnostico getDiagnosticoTabuladoAlta(){
		return diagnosticoTabuladoAlta;
	}


	/**
	 * @param diagnosticoTabuladoAlta the diagnosticoTabuladoAlta to set
	 */
	public void setDiagnosticoTabuladoAlta(Diagnostico diagnosticoTabuladoAlta){
		this.diagnosticoTabuladoAlta = diagnosticoTabuladoAlta;
	}


	/**
	 * @return the motivosModifFechaAlta
	 */
	public String getMotivosModifFechaAlta(){
		return motivosModifFechaAlta;
	}


	/**
	 * @param motivosModifFechaAlta the motivosModifFechaAlta to set
	 */
	public void setMotivosModifFechaAlta(String motivosModifFechaAlta){
		this.motivosModifFechaAlta = motivosModifFechaAlta;
	}


	/**
	 * @return the responsableAlta
	 */
	public String getResponsableAdultoAlta(){
		return responsableAdultoAlta;
	}


	/**
	 * @param responsableAlta the responsableAlta to set
	 */
	public void setResponsableAdultoAlta(String responsableAlta){
		this.responsableAdultoAlta = responsableAlta;
	}


	/**
	 * @return the condicionesParaEgreso
	 */
	public String getCondicionesParaEgreso(){
		return condicionesParaEgreso;
	}


	/**
	 * @param condicionesParaEgreso the condicionesParaEgreso to set
	 */
	public void setCondicionesParaEgreso(String condicionesParaEgreso){
		this.condicionesParaEgreso = condicionesParaEgreso;
	}


	/**
	 * @return the institucionResponsableSeguimiento
	 */
	public String getInstitucionResponsableSeguimiento(){
		return institucionResponsableSeguimiento;
	}


	/**
	 * @param institucionResponsableSeguimiento the institucionResponsableSeguimiento to set
	 */
	public void setInstitucionResponsableSeguimiento(String institucionResponsableSeguimiento){
		this.institucionResponsableSeguimiento = institucionResponsableSeguimiento;
	}


	/**
	 * @return the responsablesSeguimiento
	 */
	public String getProfesionalesResponsablesSeguimiento(){
		return profesionalesResponsablesSeguimiento;
	}


	/**
	 * @param responsablesSeguimiento the responsablesSeguimiento to set
	 */
	public void setProfesionalesResponsablesSeguimiento(String responsablesSeguimiento){
		this.profesionalesResponsablesSeguimiento = responsablesSeguimiento;
	}


	/**
	 * @return the fechaAltaIniciada
	 */
	public Date getFechaAltaIniciada(){
		return fechaAltaIniciada;
	}


	/**
	 * @param fechaAltaIniciada the fechaAltaIniciada to set
	 */
	public void setFechaAltaIniciada(Date fechaAltaIniciada){
		this.fechaAltaIniciada = fechaAltaIniciada;
	}


}
