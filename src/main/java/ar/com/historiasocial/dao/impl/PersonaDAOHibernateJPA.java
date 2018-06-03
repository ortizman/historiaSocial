package ar.com.historiasocial.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ar.com.historiasocial.dao.PersonaDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Persona;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 */
public class PersonaDAOHibernateJPA extends GenericDAOHibernateJPA<Persona> implements PersonaDAO{
	
	public PersonaDAOHibernateJPA(){
		super(Persona.class);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> retrievePaged(Paginador paginador, Long idPaciente) {
		List<Persona> resultado= new ArrayList<Persona>();
		
		try{
			paginador.setTotalItems(this.rowCount().intValue());
			
			Query consulta= getEntityManager().createQuery("select c from Conviviente c join c.pacientes pac where pac.id="+idPaciente);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= (List<Persona>)consulta.getResultList();
			
		} catch (Exception e) {
			LOGGER.error("Error de SQL al intentar recuperar las personas", e);
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> retrievePaged(Paginador paginador, String column, String order, Long idPaciente) {
		List<Persona> resultado= new ArrayList<Persona>();
		try{
			
			Query consultaTotal= getEntityManager().createQuery("select count(*) from Persona e order by e." + column + " " + order);
			Long total = (Long)consultaTotal.getSingleResult();
			
			paginador.setTotalItems(total.intValue());
			
			Query consulta= getEntityManager().createQuery("select e from Persona e order by e." + column + " " + order);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());
			resultado= (List<Persona>)consulta.getResultList();
			
		} catch (Exception e) {
			System.out.println("Error de SQL al obtener las personas: "+e.getMessage());
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> search(String term) {
		term = term + "%";
		Query searchPersona = getEntityManager().createQuery("select c from Conviviente c where c.apellido LIKE :text");
		searchPersona.setParameter("text", term);
		searchPersona.setFirstResult(0);
		searchPersona.setMaxResults(50);
		
		return searchPersona.getResultList();
	}
}
