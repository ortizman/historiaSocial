package ar.com.historiasocial.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="tipo_propiedad")
public class TipoPropiedad implements ar.com.historiasocial.dao.Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	private String tipoDePropiedad;
	
	public TipoPropiedad() {
	}
	
	/**
	 * Instantiates a new tipo propiedad.
	 *
	 * @param tipoPropiedad the tipo propiedad
	 */
	public TipoPropiedad(String tipoPropiedad) {
		this.setTipoDePropiedad(tipoPropiedad);
	}
	
	public String getTipoDePropiedad() {
		return tipoDePropiedad;
	}
	public void setTipoDePropiedad(String tipoDePropiedad) {
		this.tipoDePropiedad = tipoDePropiedad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
