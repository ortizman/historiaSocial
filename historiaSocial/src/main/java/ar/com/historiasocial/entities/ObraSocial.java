/**
 * @author Manuel Ortiz
 */
package ar.com.historiasocial.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Manuel Ortiz
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="obra_social")
public class ObraSocial implements ar.com.historiasocial.dao.Entity {

	/**
	 * Serial Generado
	 */
	private static final long serialVersionUID = -8065526689720951855L;

	@Id @GeneratedValue
	private Long id;
	private String codigoObraSocial;
	private String nombreObraSocial;
	private String plan;
	
	
	
	/**
	 * @param codigoObraSocial
	 * @param nombreObraSocial
	 * @param plan
	 */
	public ObraSocial(String codigoObraSocial, String nombreObraSocial,
			String plan) {
		super();
		this.codigoObraSocial = codigoObraSocial;
		this.nombreObraSocial = nombreObraSocial;
		this.plan = plan;
	}
	
	public ObraSocial() {
		
	}

	/* (non-Javadoc)
	 * @see ar.com.historiasocial.dao.Entity#setId(java.lang.Object)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see ar.com.historiasocial.dao.Entity#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	public String getCodigoObraSocial() {
		return codigoObraSocial;
	}

	public void setCodigoObraSocial(String codigoObraSocial) {
		this.codigoObraSocial = codigoObraSocial;
	}

	public String getNombreObraSocial() {
		return nombreObraSocial;
	}

	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

}
