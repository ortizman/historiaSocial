package ar.com.historiasocial.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.com.historiasocial.dao.InstitucionDAO;
import ar.com.historiasocial.entities.GeoCord;
import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.Location;
import ar.com.historiasocial.util.GeocodingService;

public class InstitucionDAOHibernateJPA extends GenericDAOHibernateJPA<Institucion> implements InstitucionDAO {
	
	public InstitucionDAOHibernateJPA(){
		super(Institucion.class);
		
	}
	
	public InstitucionDAOHibernateJPA(EntityManager entityManager) {
		super(Institucion.class, entityManager);
	}

	@Override
	public Institucion save(Institucion i){
		Location location = i.getLocation();
		
		if(location != null){
			setGeocodingLocationInstitucion(location);
		}
		
		return super.save(i);
	}
	
	

	private void setGeocodingLocationInstitucion(Location location) {
		GeoCord geoCord = GeocodingService.getGeocodingForAddress(location.getStreet(), location.getNumber(), location.getCity(), location.getProvince());
		location.setLatitude(geoCord.getLatitud());
		location.setLongitude(geoCord.getLongitud());
	}

	@Override
	public Institucion findInstitucionByName(String n) {
		Institucion i= null;
		try{
			Query consulta= getEntityManager().createQuery("select i from Institucion i where i.nombre=?");
			consulta.setParameter(1, n);
			Institucion reg= (Institucion)consulta.getSingleResult();
			if(reg != null){
				i= reg;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la capa de acceso a datos: ", e);
		}
		return i;
	}

	@Override
	public Institucion saveOrUpdate(Institucion i) {
			this.setGeocodingLocationInstitucion(i.getLocation());
			return super.saveOrUpdate(i);
	}

	@Override
	public boolean existeIncluyendoId(String nombre, long id) {
		boolean exito= false;
		try{
			Query consulta= getEntityManager().createQuery("select i from Institucion i where i.nombre=? and i.id<>?");
			consulta.setParameter(1, nombre);
			consulta.setParameter(2, id);
			Institucion reg= (Institucion)consulta.getSingleResult();
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
	public List<Institucion> findInstitutionsNearTo(Location location,
			Double delta) {
		Query q = getEntityManager().createQuery("Select i " +
				"from Institucion i " +
				"where ((i.location.latitude  between :latitude_min  and :latitude_max) " +
				"and   ( i.location.longitude between :longitude_min and :longitude_max))")
				.setParameter("latitude_min",  location.getLatitude()  - delta)
				.setParameter("latitude_max",  location.getLatitude()  + delta)
				.setParameter("longitude_min", location.getLongitude() - delta)
				.setParameter("longitude_max", location.getLongitude() + delta);
		return q.getResultList();
	}
}
