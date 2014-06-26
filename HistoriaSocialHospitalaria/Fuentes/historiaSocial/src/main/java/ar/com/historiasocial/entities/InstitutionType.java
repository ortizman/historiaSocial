package ar.com.historiasocial.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * La Plata - Buenos Aires - Argentina 
 * @author "Manuel Ortiz" 
 * Date: 24/02/2013 - 18:14:01 
 * Package: ar.com.historiasocial.entities - File Name: InstitutionType.java
 */

@Entity 
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="institution_type")
public class InstitutionType implements ar.com.historiasocial.dao.Entity{
	
	private static final long serialVersionUID = -9124967730231198191L;; 
	
	@Id @GeneratedValue  
	private Long id;
	private String name;
	
	@OneToMany (mappedBy="type", fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Institucion> institutions;

	public InstitutionType() {
		super();
	}
	
	public InstitutionType(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Institucion> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Set<Institucion> institutions) {
		this.institutions = institutions;
	}
	
	public String toString(){
		
		return this.name;
		
	}

}
