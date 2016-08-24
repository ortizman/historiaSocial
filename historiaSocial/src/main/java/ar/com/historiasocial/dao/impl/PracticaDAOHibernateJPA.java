package ar.com.historiasocial.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Practica;

public class PracticaDAOHibernateJPA extends GenericDAOHibernateJPA<Practica> implements PracticaDAO {
	
	
	private static final Logger LOGGER = Logger.getLogger(PracticaDAOHibernateJPA.class);
	
	public PracticaDAOHibernateJPA(){
		super(Practica.class);
		
	}
	
	public PracticaDAOHibernateJPA(EntityManager entityManager) {
		
		super(Practica.class, entityManager);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> retrieveAll(Paginador paginador) {
		List<Practica> resultado= new ArrayList<Practica>();

		try{
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true");
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true order by p.fechaPractica");
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Practica> retrieveAll(Paginador paginador, Integer idPaciente) {
		List<Practica> resultado= new ArrayList<Practica>();
		String conditions = "";
		
		if(idPaciente != null && idPaciente != 0){
			conditions = " and tratamiento.historiaSocial.paciente.id="+idPaciente+" ";
		}
		
		try{
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true "+conditions);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true " + conditions + " order by p.fechaPractica");
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Practica> retrieveAll(Paginador paginador, String column, String order, Integer idPaciente) {
		List<Practica> resultado= new ArrayList<Practica>();
		
		String conditions = "";
		
		if(idPaciente != null && idPaciente != 0){
			conditions = " and tratamiento.historiaSocial.paciente.id="+idPaciente+" ";
		}
		
		
		try{
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true"+conditions);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true"+conditions+" order by p." + column + " "+ order);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> practicasIncluyendoLasDePacientesEliminados() {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			Query consulta= getEntityManager().createQuery("select p from Practica p order by p.fechaPractica");
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@Override
	public Practica retrieveById(long id) {
		Practica exito= null;
		try{
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.id=? and p.habilitado=true");
			consulta.setParameter(1, id);
			Practica reg= (Practica)consulta.getSingleResult();
			if(reg != null){
				exito= reg;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return exito;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> filtroFecha(Date inicio, Date fin, Paginador paginador) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.tratamiento.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p.fechaPractica");
			consultaTotal.setParameter(1, fin);
			consultaTotal.setParameter(2, inicio);
			consultaTotal.setParameter(3, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.tratamiento.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p.fechaPractica");
			consulta.setParameter(1, fin);
			consulta.setParameter(2, inicio);
			consulta.setParameter(3, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> filtroFecha(Date inicio, Date fin, Paginador paginador, String column, String order) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p.fechaPractica");
			consultaTotal.setParameter(1, fin);
			consultaTotal.setParameter(2, inicio);
			consultaTotal.setParameter(3, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p." + column + " "+ order);
			consulta.setParameter(1, fin);
			consulta.setParameter(2, inicio);
			consulta.setParameter(3, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> filtroProfesional(Date inicio, Date fin, long id, Paginador paginador) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.profesional.id = ? and p.tratamiento.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p.fechaPractica");
			consultaTotal.setParameter(1, fin);
			consultaTotal.setParameter(2, inicio);
			consultaTotal.setParameter(3, id);
			consultaTotal.setParameter(4, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.profesional.id = ? and p.tratamiento.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p.fechaPractica");
			consulta.setParameter(1, fin);
			consulta.setParameter(2, inicio);
			consulta.setParameter(3, id);
			consulta.setParameter(4, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> filtroProfesional(Date inicio, Date fin, long id, Paginador paginador, String column, String order) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.profesional.id = ? and p.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p." + column + " "+ order);
			consultaTotal.setParameter(1, fin);
			consultaTotal.setParameter(2, inicio);
			consultaTotal.setParameter(3, id);
			consultaTotal.setParameter(4, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.profesional.id = ? and p.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p." + column + " "+ order);
			consulta.setParameter(1, fin);
			consulta.setParameter(2, inicio);
			consulta.setParameter(3, id);
			consulta.setParameter(4, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> filtroPaciente(Date inicio, Date fin, long id, Paginador paginador) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.tratamiento.historiaSocial.paciente.id = ? and p.tratamiento.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p.fechaPractica");
			consultaTotal.setParameter(1, fin);
			consultaTotal.setParameter(2, inicio);
			consultaTotal.setParameter(3, id);
			consultaTotal.setParameter(4, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.tratamiento.historiaSocial.paciente.id = ? and p.tratamiento.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p.fechaPractica");
			consulta.setParameter(1, fin);
			consulta.setParameter(2, inicio);
			consulta.setParameter(3, id);
			consulta.setParameter(4, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> filtroPaciente(Date inicio, Date fin, long id, Paginador paginador, String column, String order) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.historiaSocial.paciente.id = ? and p.historiaSocial.paciente.habilitado=? and p.habilitado=true order by p." + column + " "+ order);
			consultaTotal.setParameter(1, fin);
			consultaTotal.setParameter(2, inicio);
			consultaTotal.setParameter(3, id);
			consultaTotal.setParameter(4, true);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.fechaPractica <= ? and p.fechaPractica >= ? and p.historiaSocial.paciente.id = ? and p.historiaSocial.paciente.habilitado=? and p.habilitado=true order order by p." + column + " "+ order);
			consulta.setParameter(1, fin);
			consulta.setParameter(2, inicio);
			consulta.setParameter(3, id);
			consulta.setParameter(4, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> practicasDeHistoriaSocial(long l, Paginador paginador) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true and p.tratamiento.historiaSocial.id=? order by p.fechaPractica");
			consultaTotal.setParameter(1, l);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true and p.tratamiento.historiaSocial.id=? order by p.fechaPractica");
			consulta.setParameter(1, l);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Practica> practicasDeHistoriaSocial(long l, Paginador paginador, String column, String order) {
		List<Practica> resultado= new ArrayList<Practica>();
		try{
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true and p.tratamiento.historiaSocial.id=?");
			consultaTotal.setParameter(1, l);
			Long total = (Long)consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select p from Practica p where p.tratamiento.historiaSocial.paciente.habilitado=true and p.habilitado=true and p.tratamiento.historiaSocial.id=? order by p." + column + " "+ order);
			consulta.setParameter(1, l);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return resultado;
	}	
	
	
}
