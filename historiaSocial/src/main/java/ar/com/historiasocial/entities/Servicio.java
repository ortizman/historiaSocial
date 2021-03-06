package ar.com.historiasocial.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="nombreServicio")
public class Servicio implements ar.com.historiasocial.dao.Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	private String nombreServicio;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String servicio) {
		this.nombreServicio = servicio;
	}
	
}
