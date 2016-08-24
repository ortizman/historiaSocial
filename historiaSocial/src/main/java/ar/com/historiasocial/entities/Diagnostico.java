package ar.com.historiasocial.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The Class Diagnostico.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="diagnostico")
public class Diagnostico implements ar.com.historiasocial.dao.Entity{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id @GeneratedValue
	private Long id;
	
	/** The diagnost. */
	private String diagnost;
	
	/**
	 * Instantiates a new diagnostico.
	 */
	public Diagnostico() {
	}
	
	/**
	 * Instantiates a new diagnostico.
	 *
	 * @param diagnostico the diagnostico
	 */
	public Diagnostico(String diagnostico) {
		this.diagnost = diagnostico;
	}
	
	/**
	 * Gets the diagnost.
	 *
	 * @return the diagnost
	 */
	public String getDiagnost() {

		return diagnost;
	}
	
	/**
	 * Sets the diagnost.
	 *
	 * @param diagnost the new diagnost
	 */
	public void setDiagnost(String diagnost) {
		this.diagnost = diagnost;
	}

	/* (non-Javadoc)
	 * @see ar.com.historiasocial.dao.Entity#getId()
	 */
	public Long getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.historiasocial.dao.Entity#setId(java.lang.Object)
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
