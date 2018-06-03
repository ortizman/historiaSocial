package ar.com.historiasocial.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="paciente")
public class Paciente implements ar.com.historiasocial.dao.Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="paciente_id")
	private Long id;
	private String apellidos;
	private String nombres;
	
	@Index(name="idx_dni")
	private String documento;
	
	private String sexo;
	@Column(columnDefinition="date")
	private Date fechaNacimiento;
	@Column(columnDefinition="date")
	private Date fechaInicioServSocial;
	@Column(columnDefinition="date")
	private Date fechaFinServicio;	
	private String telefono;
	private String celular;
	private String lugarDeNacimiento;
	private Boolean habilitado;
	
	/*
	 * Situacion social
	 */
	@Column(columnDefinition="text")
	private String beneficionPlanesSubsidios;
	
	@OneToOne
	private CondicionHabitacional condicionesHabitacionales;
	
	@OneToOne
	private TipoPropiedad tipoDePropiedad;
	
	@OneToOne
	private Institucion centroSaludReferencia;
	
	private String otrasInstitucionesIntervinientes;
	
	private String accesibilidad;
	
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinColumn(name="location_id")
	private Location location;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Domicilio domicilio;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vivienda vivienda;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private HistoriaSocial historiaSocial;
	
	@ManyToMany
	@JoinTable(name="convivientePaciente", 
	joinColumns={@JoinColumn(name="paciente_id")}, 
	inverseJoinColumns={@JoinColumn(name="persona_id")})
	private Set<Conviviente> grupoConviviente = new HashSet<Conviviente>();
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="paciente_fk")
	private List<HistoriaClinica> historiasClinicas = new ArrayList<HistoriaClinica>();
	
	
	public Paciente() {
		super();
		this.setHistoriaSocial(new HistoriaSocial());
	}
	
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public Vivienda getVivienda() {
		return vivienda;
	}
	public void setVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}
	public HistoriaSocial getHistoriaSocial() {
		return historiaSocial;
	}
	public void setHistoriaSocial(HistoriaSocial historiaSocial) {
		this.historiaSocial = historiaSocial;
	}
	public List<HistoriaClinica> getHistoriasClinicas() {
		return historiasClinicas;
	}
	public void setHistoriasClinicas(List<HistoriaClinica> historiasClinicas) {
		this.historiasClinicas = historiasClinicas;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	
	public String getDniApellido(){
		return this.getDocumento() + ", " + this.getApellidos();
	}
	
	public void eliminar(){
		this.setHabilitado(false);
	}
	public void agregarHistoriaClinica(HistoriaClinica hc){
		historiasClinicas.add(hc);
		hc.setPaciente(this);
	}
	public void eliminarHistoriaClinica(HistoriaClinica hc){
		Iterator<HistoriaClinica> it= this.getHistoriasClinicas().iterator();
		HistoriaClinica historiaClinica= null;
		while(it.hasNext()){
			HistoriaClinica hisC= it.next();
			if(hisC.getNumero() == hc.getNumero()){
				historiaClinica= hisC;
			}
		}
		this.getHistoriasClinicas().remove(historiaClinica);
		historiaClinica.eliminarPaciente();
	}
	
	@Override
	public String toString() {
		return getNombres() + "; " + getApellidos();
	}
	
	public void agregarConviviente(Conviviente conviviente) {
		
		this.getGrupoConviviente().add(conviviente);
		conviviente.getPacientes().add(this);
		
	}
	
	public void eliminarConviviente(Conviviente conviviente) {
		this.getGrupoConviviente().remove(conviviente);
		conviviente.getPacientes().remove(this);
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLugarDeNacimiento() {
		return lugarDeNacimiento;
	}
	public void setLugarDeNacimiento(String lugarDeNacimiento) {
		this.lugarDeNacimiento = lugarDeNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Set<Conviviente> getGrupoConviviente() {
		return grupoConviviente;
	}
	public void setGrupoConviviente(Set<Conviviente> grupoConviviente) {
		this.grupoConviviente = grupoConviviente;
	}
	
	public String getNombreCompleto(){
		return this.getApellidos() + ", " + this.getNombres();
	}


	public boolean tieneTratamientoActivo() {
		return this.getHistoriaSocial().getTratamientoActual() != null;
	}


	public Date getFechaFinServicio() {
		return fechaFinServicio;
	}


	public void setFechaFinServicio(Date fechaFinServicio) {
		this.fechaFinServicio = fechaFinServicio;
	}


	public Date getFechaInicioServSocial() {
		return fechaInicioServSocial;
	}


	public void setFechaInicioServSocial(Date fechaInicioServSocial) {
		this.fechaInicioServSocial = fechaInicioServSocial;
	}


	public String getOtrasInstitucionesIntervinientes() {
		return otrasInstitucionesIntervinientes;
	}


	public void setOtrasInstitucionesIntervinientes(
			String otrasInstitucionesIntervinientes) {
		this.otrasInstitucionesIntervinientes = otrasInstitucionesIntervinientes;
	}


	public String getAccesibilidad() {
		return accesibilidad;
	}


	public void setAccesibilidad(String accesibilidad) {
		this.accesibilidad = accesibilidad;
	}


	public Boolean getHabilitado() {
		return habilitado;
	}


	public String getBeneficionPlanesSubsidios() {
		return beneficionPlanesSubsidios;
	}


	public void setBeneficionPlanesSubsidios(String beneficionPlanesSubsidios) {
		this.beneficionPlanesSubsidios = beneficionPlanesSubsidios;
	}


	public CondicionHabitacional getCondicionesHabitacionales() {
		return condicionesHabitacionales;
	}


	public void setCondicionesHabitacionales(
			CondicionHabitacional condicionesHabitacionales) {
		this.condicionesHabitacionales = condicionesHabitacionales;
	}


	public TipoPropiedad getTipoDePropiedad() {
		return tipoDePropiedad;
	}


	public void setTipoDePropiedad(TipoPropiedad tipoDePropiedad) {
		this.tipoDePropiedad = tipoDePropiedad;
	}


	public Institucion getCentroSaludReferencia() {
		return centroSaludReferencia;
	}


	public void setCentroSaludReferencia(Institucion centroSaludReferencia) {
		this.centroSaludReferencia = centroSaludReferencia;
	}
}
