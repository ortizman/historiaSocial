/**
 * Manuel Ortiz - ortizman@gmail.com
 */
package ar.com.historiasocial.dao;

import java.util.List;

import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Persona;
/**
asdasd
 */
/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 */
public interface PersonaDAO extends GenericDAO<Persona>{

	/**
	 * @param paginador
	 * @param idPaciente
	 * @return la lista de personas paginadas, por id de praciente
	 */
	List<Persona> retrievePaged(Paginador paginador, Long idPaciente);

	/**
	 * @param paginador
	 * @param column
	 * @param order
	 * @param idPaciente
	 * @return La lista de personas paginadas, por id de praciente. Ordenadas por column
	 */
	List<Persona> retrievePaged(Paginador paginador, String column, String order, Long idPaciente);

}
