package ar.com.historiasocial.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * La Plata - Buenos Aires - Argentina 
 * @author "Manuel Ortiz" 
 * Date: 24/02/2013 - 18:16:02 
 * Package: ar.com.historiasocial.entities - File Name: Conviviente.java
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="conviviente")
public class Conviviente extends Persona {

	private static final long serialVersionUID = 2312302287429837810L;
	
	
	@ManyToMany(mappedBy="grupoConviviente")
	private Set<Paciente> pacientes = new HashSet<Paciente>();
	
	private String nro;
	
	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}
}
