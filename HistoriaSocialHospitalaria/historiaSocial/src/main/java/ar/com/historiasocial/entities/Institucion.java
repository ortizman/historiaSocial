package ar.com.historiasocial.entities;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="institucion")
public class Institucion implements ar.com.historiasocial.dao.Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	private String nombre;
	private String email;
	private String detail;
	private String telefono;
	private String calleX;
	private String calleY;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinColumn(name="location_id")
	private Location location;
	
	@ManyToOne(cascade = {CascadeType.DETACH}, fetch=FetchType.EAGER) 
	@JoinColumn(name = "responsible_id", nullable = true)
	private InstitutionResponsible responsible;
		
	@ManyToOne (cascade = {CascadeType.DETACH}, fetch=FetchType.EAGER) 
	@JoinColumn(name = "institution_type_id", nullable = true)
	private InstitutionType type;
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * @return the responsible
	 */
	public InstitutionResponsible getResponsible() {
		return responsible;
	}
	/**
	 * @param responsible the responsible to set
	 */
	public void setResponsible(InstitutionResponsible responsible) {
		this.responsible = responsible;
	}
	/**
	 * @return the type
	 */
	public InstitutionType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(InstitutionType type) {
		this.type = type;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the calleX
	 */
	public String getCalleX() {
		return calleX;
	}
	/**
	 * @param calleX the calleX to set
	 */
	public void setCalleX(String calleX) {
		this.calleX = calleX;
	}
	/**
	 * @return the calleY
	 */
	public String getCalleY() {
		return calleY;
	}
	/**
	 * @param calleY the calleY to set
	 */	
	public void setCalleY(String calleY) {
		this.calleY = calleY;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.calleX == null) ? 0 : this.calleX.hashCode());
		result = prime * result + ((this.calleY == null) ? 0 : this.calleY.hashCode());
		result = prime * result + ((this.detail == null) ? 0 : this.detail.hashCode());
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result + ((this.location == null) ? 0 : this.location.hashCode());
		result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
		result = prime * result + ((this.telefono == null) ? 0 : this.telefono.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		Institucion other = (Institucion) obj;
		if (this.calleX == null) {
			if (other.calleX != null) { return false; }
		} else if (!this.calleX.equals(other.calleX)) { return false; }
		if (this.calleY == null) {
			if (other.calleY != null) { return false; }
		} else if (!this.calleY.equals(other.calleY)) { return false; }
		if (this.detail == null) {
			if (other.detail != null) { return false; }
		} else if (!this.detail.equals(other.detail)) { return false; }
		if (this.email == null) {
			if (other.email != null) { return false; }
		} else if (!this.email.equals(other.email)) { return false; }
		if (this.location == null) {
			if (other.location != null) { return false; }
		} else if (!this.location.equals(other.location)) { return false; }
		if (this.nombre == null) {
			if (other.nombre != null) { return false; }
		} else if (!this.nombre.equals(other.nombre)) { return false; }
		if (this.telefono == null) {
			if (other.telefono != null) { return false; }
		} else if (!this.telefono.equals(other.telefono)) { return false; }
		return true;
	}
	
	

}
