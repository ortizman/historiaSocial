package ar.com.historiasocial.dao;

import java.util.List;

import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.Location;

public interface InstitucionDAO extends GenericDAO<Institucion>{
	public Institucion findInstitucionByName(String n);
	public boolean existeIncluyendoId(String nombre, long id);
	public List<Institucion> findInstitutionsNearTo(Location location, Double delta);
}
