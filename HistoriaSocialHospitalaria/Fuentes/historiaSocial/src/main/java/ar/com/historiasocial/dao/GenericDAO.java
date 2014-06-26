package ar.com.historiasocial.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.Predicate;

import ar.com.historiasocial.entities.Paginador;

public interface GenericDAO <T extends Entity>{
	public T save(T o);
	public T saveOrUpdate (T o);
	public T delete (long id);
	public List<T> retrieveAll ();
	public Long rowCount(Predicate... predicados);
	public T retrieveById (Serializable id);
	public Boolean exist(Serializable id);
	public List<T> retrievePaged(Paginador paginador, String column, String order);
	public List<T> retrievePaged(Paginador paginador);
}
