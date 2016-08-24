package ar.com.historiasocial.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.entities.HistoriaSocial;

public class HistoriaSocialDAOHibernateJPA extends GenericDAOHibernateJPA<HistoriaSocial> implements HistoriaSocialDAO {
	
	public HistoriaSocialDAOHibernateJPA(){
		super(HistoriaSocial.class);
		
	}
	
	public HistoriaSocialDAOHibernateJPA(EntityManager entityManager) {
		super(HistoriaSocial.class, entityManager);
	}
	
	@Override
	public HistoriaSocial getHistoriaSocialByPacienteId(long id) {
		HistoriaSocial exito= null;
		try{
			Query consulta= getEntityManager().createQuery("select hs from HistoriaSocial hs where hs.paciente.id=? and hs.paciente.habilitado=?");
			consulta.setParameter(1, id);
			consulta.setParameter(2, true);
			HistoriaSocial reg= (HistoriaSocial)consulta.getSingleResult();
			if(reg != null){
				exito= reg;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return exito;
	}
}
