package ar.com.historiasocial.dao;

import java.util.Date;
import java.util.List;

import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Practica;

public interface PracticaDAO extends GenericDAO<Practica>{
	public List<Practica> retrieveAll(Paginador paginador);
	public List<Practica> retrieveAll(Paginador paginador, Integer idPaciente);
	public List<Practica> practicasIncluyendoLasDePacientesEliminados();
	public Practica retrieveById(long id);
	public List<Practica> filtroFecha(Date inicio, Date fin, Paginador paginador);
	public List<Practica> filtroProfesional(Date inicio, Date fin, long id, Paginador paginador);
	public List<Practica> filtroPaciente(Date inicio, Date fin, long id, Paginador paginador);
	public List<Practica> practicasDeHistoriaSocial(long hs, Paginador paginador);
	public List<Practica> practicasDeHistoriaSocial(long id,
			Paginador paginador, String column, String order);
	public List<Practica> retrieveAll(Paginador paginador, String sidx,
			String sord, Integer idPaciente);
	List<Practica> filtroFecha(Date inicio, Date fin, Paginador paginador,
			String column, String order);
	List<Practica> filtroPaciente(Date inicio, Date fin, long id,
			Paginador paginador, String column, String order);
	List<Practica> filtroProfesional(Date inicio, Date fin, long id,
			Paginador paginador, String column, String order);
	
}
