package ar.com.historiasocial.dao;

import java.util.List;

import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Profesional;

public interface ProfesionalDAO extends GenericDAO<Profesional>{
	public Profesional existe(String usuario, String pass);
	public Profesional profesional(long id);
	public Profesional profesionalByUser(String p);
	public boolean existe(String usuario);
	public boolean existeExcluyendoId(String usuario, long id);
	public List<Profesional> profesionales();
	public List<Profesional> profesionales(String usuario, Paginador paginador);
	public List<Profesional> profesionalesIncluyendoEliminados();
	public List<Profesional> profesionales(String user, Paginador paginador, String column, String order);
	public List<Profesional> profesionales(String user, Paginador paginador,
			String searchField, String searchString, String searchOper);
}
