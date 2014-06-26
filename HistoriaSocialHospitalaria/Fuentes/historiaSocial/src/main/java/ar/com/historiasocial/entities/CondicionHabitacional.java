package ar.com.historiasocial.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "condicion_habitacional")
public class CondicionHabitacional implements
		ar.com.historiasocial.dao.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String condicion;

	public CondicionHabitacional() {
	}
	
	/**
	 * Instantiates a new condicion habitacional.
	 *
	 * @param condHabit the cond habit
	 */
	public CondicionHabitacional(String condHabit) {
		this.setCondicion(condHabit);
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
