package ar.com.historiasocial.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.exceptions.HSDataAccessRuntimeException;

public class ProfesionalDAOHibernateJPA extends GenericDAOHibernateJPA<Profesional> implements ProfesionalDAO {
	
	public ProfesionalDAOHibernateJPA(){
		super(Profesional.class);
		
	}
	
	public ProfesionalDAOHibernateJPA(EntityManager entityManager) {
		super(Profesional.class, entityManager);
	}

	@Override
	public Profesional existe(String usuario, String pass){
		Profesional prof = null;
		try{
			
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.user=? and p.pass=? and p.habilitado=?");
			consulta.setParameter(1, usuario);
			consulta.setParameter(2, pass);
			consulta.setParameter(3, true);
			prof= (Profesional)consulta.getSingleResult();
			
		} catch (NoResultException nre){
			LOGGER.error("No existe un usuario con el nombre: " + usuario);
			prof = null;
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos, preguntando por existe del usuario: " + usuario, e);
			throw new HSDataAccessRuntimeException("Error consultando por existe");
		}

		return prof;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Profesional> profesionales() {
		List<Profesional> profesionales= new ArrayList<Profesional>();
		try{
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.habilitado=true order by p.apellido, p.nombre");
			profesionales= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return profesionales;
	}
	@Override
	public boolean existe(String usuario) {
		boolean exito= false;
		try{
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.user=?");
			consulta.setParameter(1, usuario);
			Profesional reg= (Profesional)consulta.getSingleResult();
			if(reg != null){
				exito= true;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return exito;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Profesional> profesionales(String usuario, Paginador paginador) {
		List<Profesional> profesionales= new ArrayList<Profesional>();
		try{
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Profesional p where p.user<>? and p.habilitado=?");
			consultaTotal.setParameter(1, usuario);
			consultaTotal.setParameter(2, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.user<>? and p.habilitado=? order by p.apellido, p.nombre");
			consulta.setParameter(1, usuario);
			consulta.setParameter(2, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			
			profesionales= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return profesionales;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Profesional> profesionales(String usuario, Paginador paginador, String searchColumn, String searchString, String searchOper) {
		List<Profesional> profesionales= new ArrayList<Profesional>();
		try{
			if(searchOper.endsWith("bw")){
				searchString = "'" + searchString + "%'";
			}
			else{
				searchString = "'%" + searchString + "%'";
			}
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Profesional p where p.user<>? and p.habilitado=? and p." + searchColumn + " like "+ searchString);
			consultaTotal.setParameter(1, usuario);
			consultaTotal.setParameter(2, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.user<>? and p.habilitado=? and p." + searchColumn + " like "+ searchString);
			consulta.setParameter(1, usuario);
			consulta.setParameter(2, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			
			profesionales= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return profesionales;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Profesional> profesionales(String usuario, Paginador paginador, String column, String order) {
		List<Profesional> profesionales= new ArrayList<Profesional>();
		try{
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Profesional p where p.user<>? and p.habilitado=?");
			consultaTotal.setParameter(1, usuario);
			consultaTotal.setParameter(2, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.user<>? and p.habilitado=? order by p." + column + " " + order);
			consulta.setParameter(1, usuario);
			consulta.setParameter(2, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			
			profesionales= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return profesionales;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Profesional> profesionalesIncluyendoEliminados() {
		List<Profesional> profesionales= new ArrayList<Profesional>();
		try{
			Query consulta= getEntityManager().createQuery("select p from Profesional p order by p.user");
			profesionales= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return profesionales;
	}
	
	@Override
	public Profesional profesional(long l) {
		Profesional exito= null;
		try{
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.id=? and p.habilitado=?");
			consulta.setParameter(1, l);
			consulta.setParameter(2, true);
			Profesional reg= (Profesional)consulta.getSingleResult();
			if(reg != null){
				exito= reg;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return exito;
	}

	@Override
	public Profesional profesionalByUser(String user) {
		Profesional i= null;
		try{
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.user=? and p.habilitado=?");
			consulta.setParameter(1, user);
			consulta.setParameter(2, true);
			Profesional reg= (Profesional)consulta.getSingleResult();
			if(reg != null){
				i= reg;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return i;
	}
	@Override
	public boolean existeExcluyendoId(String usuario, long id) {
		boolean exito= false;
		try{
			Query consulta= getEntityManager().createQuery("select p from Profesional p where p.user=? and p.id<>?");
			consulta.setParameter(1, usuario);
			consulta.setParameter(2, id);
			Profesional reg= (Profesional)consulta.getSingleResult();
			if(reg != null){
				exito= true;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return exito;
	}

	
}
