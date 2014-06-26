package ar.com.historiasocial.dao;

import java.util.List;

import ar.com.historiasocial.entities.Conviviente;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Paginador;

public interface PacienteDAO extends GenericDAO<Paciente>{
	public List<Paciente> pacientes(Paginador paginador);
	public boolean existe(String dni);
	public boolean existeIncluyendoId(String dni, long id);
	public Paciente paciente(long l);
	public void agregarConviviente(Conviviente p, Long idPaciente);
	public void eliminarConviviente(Conviviente p, Long idPaciente);
	public List<Paciente> pacientesConTratamientoAbierto();
	
	/**
	 * @param paginador
	 * @param sidx
	 * @param sord
	 * @param searchField
	 * @param searchOper
	 * @param searchString
	 */
	public List<Paciente> pacientes(Paginador paginador, String sidx, String sord, String searchField, String searchOper, String searchString);
	/**
	 * @param term
	 * @return
	 */
	public List<Paciente> search(String term); 
}
