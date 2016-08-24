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


@Entity 
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="institution_responsible")
public class InstitutionResponsible implements ar.com.historiasocial.dao.Entity{
	
	private static final long serialVersionUID = -6869277954802222050L;

	@Id @GeneratedValue  
	private Long id;
	private String name;
	private String lastname;
	private String profesion;
	private String email;
	private String phoneNumbers;
	
	@OneToMany(mappedBy="responsible", fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Institucion> institutions;

	public InstitutionResponsible() {
		super();
	}
	
	
	public InstitutionResponsible(String name, String lastname,
			String profesion, String email, String phoneNumbers) {
		super();
		this.setName(name);
		this.setLastname(lastname);
		this.setProfesion(profesion);
		this.setEmail(email);
		this.setPhoneNumbers(phoneNumbers);
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Set<Institucion> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Set<Institucion> institutions) {
		this.institutions = institutions;
	}

	public String toString(){
		
		return this.getLastname()+", "+this.getName();
		
	}
	
	public String getNombreCompleto(){
		return this.getLastname() + ", " + this.getName();
	}

}
