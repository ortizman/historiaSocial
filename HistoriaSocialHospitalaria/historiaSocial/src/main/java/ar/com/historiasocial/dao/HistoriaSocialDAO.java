package ar.com.historiasocial.dao;

import ar.com.historiasocial.entities.HistoriaSocial;

public interface HistoriaSocialDAO extends GenericDAO<HistoriaSocial>{
	public HistoriaSocial getHistoriaSocialByPacienteId(long id);
}
