package ar.com.historiasocial.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="practica")
public class Practica implements ar.com.historiasocial.dao.Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	@Column(columnDefinition="text")
	private String detalle;
	@Column(columnDefinition="date")
	private Date fechaPractica;
	@Column(columnDefinition="date")
	private Date fechaCarga;
	@Column(columnDefinition="boolean")
	private Boolean habilitado;
	@Column(columnDefinition="text")
	private String localidad;
	
	@ManyToOne(fetch= FetchType.EAGER)
	private TipoDePractica tipoPractica;
	@ManyToOne(fetch= FetchType.EAGER)
	private TipoDeProblematica tipoProblematica;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="profesionalPractica_fk")
	private Profesional profesional;
	
//	@ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="historiaSocial_fk")
//	private HistoriaSocial historiaSocial;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratamiento_fk")
	private Tratamiento tratamiento;
	
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public Date getFechaPractica() {
		return fechaPractica;
	}
	public void setFechaPractica(Date fechaPractica) {
		this.fechaPractica = fechaPractica;
	}
	public Date getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public TipoDePractica getTipoPractica() {
		return tipoPractica;
	}
	public void setTipoPractica(TipoDePractica tipoPractica) {
		this.tipoPractica = tipoPractica;
	}
	public TipoDeProblematica getTipoProblematica() {
		return tipoProblematica;
	}
	public void setTipoProblematica(TipoDeProblematica tipoProblematica) {
		this.tipoProblematica = tipoProblematica;
	}
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	
	public HistoriaSocial getHistoriaSocial() {
		return this.getTratamiento().getHistoriaSocial();
	}
	
//	public void setHistoriaSocial(HistoriaSocial historiaSocial) {
//		this.historiaSocial = historiaSocial;
//	}
	public void setHabilitado(Boolean h){
		this.habilitado=h;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public boolean isHabilitado(){
		return this.habilitado;
	}
	public void eliminar(){
		this.setHabilitado(false);
	}
	public SimpleDateFormat getFechaPracticaFormateada() {
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));
        formateador.format(fechaPractica);
		return formateador;
	}
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	
}
