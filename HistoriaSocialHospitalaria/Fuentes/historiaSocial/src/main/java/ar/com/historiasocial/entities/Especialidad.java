package ar.com.historiasocial.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * La Plata - Buenos Aires - Argentina
 * 
 * @author "Manuel Ortiz" Date: 24/02/2013 - 18:18:25 Package:
 *         ar.com.historiasocial.entities - File Name: Especialidad.java
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "descripcion")
public class Especialidad implements ar.com.historiasocial.dao.Entity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@Id
	@GeneratedValue
	private Long				id;
	private String				descripcion;

	@Override
	public Long getId(){
		return id;
	}

	@Override
	public void setId(Long id){
		this.id = id;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String especialidad){
		this.descripcion = especialidad;
	}

	@Override
	public boolean equals(Object esp){
		boolean ok = false;
		
		if(esp instanceof Especialidad){
			Especialidad esp2 = (Especialidad) esp;
			ok = this.getDescripcion().equals(esp2.getDescripcion());
		}

		return ok;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return new HashCodeBuilder().append(getDescripcion()).toHashCode();
	}
}
