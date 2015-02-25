package ar.com.historiasocial.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.Entity;
import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.exceptions.HSDataAccessRuntimeException;

public class GenericDAOHibernateJPA<T extends Entity> implements
		GenericDAO<T> { 

	protected Class<T> persistentClass;

	private EntityManager entityManager;
	
	protected final Logger LOGGER = Logger.getLogger(this.getClass());

	@PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public GenericDAOHibernateJPA() {
		super();
	}

	public GenericDAOHibernateJPA(Class<T> persistentClass) {
		this();
		this.setPersistentClass(persistentClass);
	}

	/**
	 * No hace nada. Esta para dar compativilidad a la implementacion anterior.
	 * @param class1
	 * @param entityManager2
	 */
	public GenericDAOHibernateJPA(Class<T> clazz,
			EntityManager entityManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public T save(T entity) {
		 entity.setId(null);
		 this.getEntityManager().persist(entity);
		 return entity;
	}

	@Override
	public T saveOrUpdate(T entity) {
		return this.getEntityManager().merge(entity);
	}

	private void delete(T entity) {
		this.getEntityManager().remove(entity);
	}	
	
	@Override
	public T delete(long id) {

		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null) {
			this.delete(entity);
		}

		return entity;
	}

	@Override
	public T retrieveById(Serializable id) {
		return this.getEntityManager().find(getPersistentClass(), id);
	}

	@Override
	public List<T> retrieveAll() {
		CriteriaQuery<T> q = getEntityManager().getCriteriaBuilder().createQuery(getPersistentClass());
		Root<T> c = q.from(getPersistentClass());
		q.select(c);
		TypedQuery<T> query = this.getEntityManager().createQuery(q);
		query.setFirstResult(0);
		query.setMaxResults(1000); //Se limita a 10 mil la cantidad de resultados

		query.setHint("org.hibernate.cacheable", true);
		
		List<T> list = query.getResultList();
	
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> retrievePaged(Paginador paginador, String column, String order) {
		List<T> resultado = new ArrayList<T>();
		try {
			paginador.setTotalItems(this.rowCount().intValue());
			Query consulta = getEntityManager().createQuery("select entity from "+ persistentClass.getSimpleName() +" entity order by entity." + column + " "
							+ order);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			
			consulta.setHint("org.hibernate.cacheable", true);
			
			resultado = (consulta.getResultList());
		} catch (Exception e) {
			LOGGER.error("Ocurrion un error al intentar obtener las entidades paginadas para " + persistentClass.getSimpleName(), e);
		}

		return resultado;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> retrievePaged(Paginador paginador) {
		try {
			paginador.setTotalItems(this.rowCount().intValue());
			Query consulta = getEntityManager().createQuery(
					"select entity from "+ persistentClass.getSimpleName() +" entity");
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			
			consulta.setHint("org.hibernate.cacheable", true);
			
			return consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Ocurrion un error al intentar obtener las entidades paginadas para " + persistentClass.getSimpleName(), e);
			throw new HSDataAccessRuntimeException("Ocurrion un error al intentar obtener las entidades paginadas para " + persistentClass.getSimpleName(), e);
		}
	}
	
	@Override
	public Long rowCount(Predicate... predicados){
		CriteriaQuery<Long> cq = this.getEntityManager().getCriteriaBuilder().createQuery(Long.class);
		cq.select(this.getEntityManager().getCriteriaBuilder().count(cq.from(this.getPersistentClass())));
		cq.where(predicados);
		TypedQuery<Long> query = this.getEntityManager().createQuery(cq);
		
		query.setHint("org.hibernate.cacheable", true);
		
		Long total = query.getSingleResult();
		
		return total;
	}
	@Override
	public Boolean exist(Serializable id) {
		T obj = this.retrieveById(id);

		return obj != null;
	}


	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

}
