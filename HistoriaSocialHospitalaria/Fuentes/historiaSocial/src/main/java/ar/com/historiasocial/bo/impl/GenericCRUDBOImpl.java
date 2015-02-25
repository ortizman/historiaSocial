package ar.com.historiasocial.bo.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.transaction.annotation.Transactional;

import ar.com.historiasocial.bo.GenericCRUDBO;
import ar.com.historiasocial.dao.Entity;
import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Paginador;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 *         Bussines Object generico. Por defecto, delega todas las operaciones
 *         al DAO Generico. Todos sus metodos son transaccionales.
 */
@Transactional
public class GenericCRUDBOImpl<T extends Entity> implements GenericCRUDBO<T> {

	/**
	 * DAO generico
	 */
	private GenericDAO<T> genericDAO;

	@Override
	public T save(T entity) {
		return genericDAO.save(entity);
	}

	@Override
	public T saveOrUpdate(T entity) {
		return genericDAO.saveOrUpdate(entity);
	}

	@Override
	public T delete(long id) {
		return genericDAO.delete(id);
	}

	@Override
	public List<T> retrieveAll() {
		return genericDAO.retrieveAll();
	}

	@Override
	public Long rowCount(Predicate... predicados) {
		return genericDAO.rowCount(predicados);
	}

	@Override
	public T retrieveById(Serializable id) {
		return genericDAO.retrieveById(id);
	}

	@Override
	public Boolean exist(Serializable id) {
		return genericDAO.exist(id);
	}

	@Override
	public List<T> retrievePaged(Paginador paginador, String column,
			String order) {
		return genericDAO.retrievePaged(paginador, column, order);
	}

	@Override
	public List<T> retrievePaged(Paginador paginador) {
		return genericDAO.retrievePaged(paginador);
	}

	public GenericDAO<T> getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(GenericDAO<T> genericDAO) {
		this.genericDAO = genericDAO;
	}
	
}
