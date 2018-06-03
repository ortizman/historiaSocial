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
@Table(name="nacionalidad")
public class Nacionalidad implements ar.com.historiasocial.dao.Entity {

	/**
	 * Serial generado
	 */
	private static final long serialVersionUID = -5830081578646707229L;

	@Id @GeneratedValue
	private Long id;
	
	private String nombrePais;
	
	
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

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

}
