/**
 * @autor Manuel Ortiz - ortizman@gmail.com
 *
 * 19/2/2015 
 */
package ar.com.historiasocial.bo.impl;

import ar.com.historiasocial.bo.ProfesionalBO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Profesional;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 */
public class ProfesionalBOImpl implements ProfesionalBO {
	
	private ProfesionalDAO profesionalDAO;

	@Override
	public Profesional existe(String usuario, String pass) {
		return profesionalDAO.existe(usuario, pass);
	}

	public ProfesionalDAO getProfesionalDAO() {
		return profesionalDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO) {
		this.profesionalDAO = profesionalDAO;
	}
	
}
