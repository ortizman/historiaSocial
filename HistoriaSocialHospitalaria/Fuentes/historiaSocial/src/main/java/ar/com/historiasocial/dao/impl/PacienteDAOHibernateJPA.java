package ar.com.historiasocial.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.PersonaDAO;
import ar.com.historiasocial.entities.Conviviente;
import ar.com.historiasocial.entities.GeoCord;
import ar.com.historiasocial.entities.Location;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.exceptions.HSDataAccessRuntimeException;
import ar.com.historiasocial.util.GeocodingService;

public class PacienteDAOHibernateJPA extends GenericDAOHibernateJPA<Paciente> implements PacienteDAO {

	final private static String	provincia		= "Buenos Aires";
	final private static String	ciudad			= "La Plata";
	private static int			firstDefault	= 0;
	private static int			sizeResultDefault	= 100;

	private PersonaDAO			personaDAO;

	private static final Logger	LOGGER			= Logger.getLogger(PacienteDAOHibernateJPA.class);

	public PacienteDAOHibernateJPA() {
		super(Paciente.class);
	}

	public PacienteDAOHibernateJPA(EntityManager entityManager) {
		super(Paciente.class, entityManager);
	}

	@Override
	public Paciente save(Paciente persona){

		setGeocodingPacienteLocation(persona);
		return super.save(persona);
	}

	private void setGeocodingPacienteLocation(Paciente persona){
		// FIXME: Por ahora se harcodea el pais, la provincia y la ciudad. Se
		// tendria que parametrizar.
		Location locat = persona.getLocation();
		GeoCord geocord;

		if (locat == null) {
			locat = new Location();
		}

		String inter = "";
		if(persona.getDomicilio() != null){
			if(StringUtils.isNotEmpty(persona.getDomicilio().getCalleX())){
				inter = persona.getDomicilio().getCalleX();
			} else if(StringUtils.isNotEmpty(persona.getDomicilio().getCalleY())){
				inter = persona.getDomicilio().getCalleY();
			}
		}

		if ("".equalsIgnoreCase(locat.getProvince()) || "".equalsIgnoreCase(locat.getCity())) {
			geocord = GeocodingService.getGeocoding(locat.getStreet(), locat.getNumber(), inter, ciudad, provincia);
		}

		geocord = GeocodingService.getGeocoding(locat.getStreet(), locat.getNumber(), inter, locat.getCity(), locat.getProvince());
		
		Location location = new Location(locat.getProvince(), "???", locat.getCity(), locat.getStreet(), locat.getNumber(), geocord.getLatitud(),
				geocord.getLongitud(), persona, null);
		persona.setLocation(location);
	}

	@Override
	public Paciente saveOrUpdate(Paciente entity){
		this.setGeocodingPacienteLocation(entity);
		return super.saveOrUpdate(entity);
	}

	@Override
	public List<Paciente> pacientes(Paginador paginador){
		try {
			Query consultaTotal = getEntityManager().createQuery("select count(*) from Paciente p where p.habilitado=?");
			consultaTotal.setParameter(1, true);
			Long total = (Long) consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());

			Query consulta = getEntityManager().createQuery("select p from Paciente p where p.habilitado=? order by p.apellidos, p.nombres");
			consulta.setParameter(1, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());

			return consulta.getResultList();
		} catch (Exception e) {
			LOGGER.error("Error al recuperar los pacientes", e);
			throw new HSDataAccessRuntimeException("Error al recuperar los pacientes", e);

		}
	}

	private List<Paciente> pacientes(Paginador paginador, String column, String order){

		try {

			Query consultaTotal = getEntityManager().createQuery("select count(*) from Paciente p where p.habilitado=?");
			consultaTotal.setParameter(1, true);
			Long total = (Long) consultaTotal.getSingleResult();
			paginador.setTotalItems(total.intValue());

			Query consulta = getEntityManager().createQuery("select p from Paciente p where p.habilitado=? order by p." + column + " " + order);
			consulta.setParameter(1, true);
			consulta.setFirstResult(paginador.inicio());
			consulta.setMaxResults(paginador.getCantPorPag());

			return consulta.getResultList();

		} catch (Exception e) {
			LOGGER.error("Error al recuperar los pacientes ordenados por la columna: " + column, e);
			throw new HSDataAccessRuntimeException("Error al recuperar los pacientes ordenados por columna", e);
		}
	}

	@Override
	public boolean existe(String dni){
		boolean exito = false;
		try {
			Query consulta = getEntityManager().createQuery("select p from Paciente p where p.documento=?");
			consulta.setParameter(1, dni);
			Paciente reg = (Paciente) consulta.getSingleResult();
			if (reg != null) {
				exito = true;
			}
		} catch (Exception e) {
			LOGGER.error("Error comprobando si existe una paciente con DNI: " + dni, e);
			throw new HSDataAccessRuntimeException("Error comprobando si existe una paciente", e);
		}
		return exito;
	}

	@Override
	public Paciente paciente(long l){
		Paciente exito = null;
		try {
			Query consulta = getEntityManager().createQuery("select p from Paciente p where p.id=? and p.habilitado=?");
			consulta.setParameter(1, l);
			consulta.setParameter(2, true);
			Paciente reg = (Paciente) consulta.getSingleResult();
			if (reg != null) {
				exito = reg;
			}
		} catch (Exception e) {
			LOGGER.error("Error recuperando el paciente con ID: " + l, e);
			throw new HSDataAccessRuntimeException("Error recuperando el paciente", e);
		}
		return exito;
	}

	@Override
	public boolean existeIncluyendoId(String dni, long id){
		boolean exito = false;
		try {
			Query consulta = getEntityManager().createQuery("select p from Paciente p where p.documento=? and p.id<>?");
			consulta.setParameter(1, dni);
			consulta.setParameter(2, id);
			Paciente reg = (Paciente) consulta.getSingleResult();
			if (reg != null) {
				exito = true;
			}
		} catch (Exception e) {
			LOGGER.error("Error recuperando el paciente con DNI: " + dni, e);
			throw new HSDataAccessRuntimeException("Error recuperando el paciente", e);
		}
		return exito;
	}

	@Override
	public void agregarConviviente(Conviviente conviviente, Long idPaciente){
		Paciente pac = this.retrieveById(idPaciente);
		conviviente.setId(null);
		try {
			getPersonaDAO().save(conviviente);
			pac.agregarConviviente(conviviente);
			this.saveOrUpdate(pac);
		} catch (Exception e) {
			LOGGER.error("Error agregando el conviviente: " + conviviente + " para el ID de paciente: " + idPaciente, e);
			throw new HSDataAccessRuntimeException("Error agregando el conviviente", e);
		}

	}

	public PersonaDAO getPersonaDAO(){
		return personaDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO){
		this.personaDAO = personaDAO;
	}

	@Override
	public void eliminarConviviente(Conviviente conviviente, Long idPaciente){
		Paciente pac = this.retrieveById(idPaciente);
		Conviviente con = (Conviviente) personaDAO.retrieveById(conviviente.getId());
		try {
			pac.eliminarConviviente(con);
			this.saveOrUpdate(pac);
		} catch (Exception e) {
			LOGGER.error("Error eliminado el conviviente: " + conviviente + " sobre el paciente con ID: " + idPaciente, e);
			throw new HSDataAccessRuntimeException("Error eliminando el conviviente", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> pacientesConTratamientoAbierto(){

		try {
			Query consulta = getEntityManager()
					.createQuery(
							"select p from Paciente p where p.habilitado=? and p.historiaSocial.tratamientoActual.fechaAlta is null and p.historiaSocial.tratamientoActual.fechaIngreso is not null order by p.apellidos asc");
			consulta.setParameter(1, true);
			consulta.setFirstResult(0);
			consulta.setMaxResults(Integer.MAX_VALUE);
			return consulta.getResultList();

		} catch (Exception e) {
			LOGGER.error("Error recuperando los pacientes con tratamientos abiertos.", e);
			throw new HSDataAccessRuntimeException("Error recuperando los pacientes con tratamientos abiertos", e);
		}
	}

	/**
	 * @see ar.com.historiasocial.dao.PacienteDAO#pacientes(ar.com.historiasocial.entities.Paginador,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public List<Paciente> pacientes(Paginador paginador, String sidx, String sord, String searchField, String searchOper, String searchString){
		List<Paciente> pacientes = null;

		if (!StringUtils.isEmpty(searchField) && !StringUtils.isEmpty(searchOper)) {
			pacientes = this.execQuery(paginador, sidx, sord, searchField, searchOper, searchString);
		} else if (!StringUtils.isEmpty(sidx) && !StringUtils.isEmpty(sord)) {
			pacientes = this.pacientes(paginador, sidx, sord);
		} else {
			pacientes = this.pacientes(paginador);
		}

		return pacientes;

	}

	/**
	 * @param paginador
	 * @param sidx
	 * @param sord
	 * @param searchField
	 * @param searchOper
	 * @param searchString
	 * @return
	 */
	private List<Paciente> execQuery(Paginador paginador, String column, String order, String sf, String so, String texto){

		Query consultaTotal = getEntityManager().createQuery("select count(*) from Paciente p where p.habilitado=:bool and p." + sf + " " + so + " :text");
		consultaTotal.setParameter("bool", true);
		consultaTotal.setParameter("text", texto);
		Long total = (Long) consultaTotal.getSingleResult();
		paginador.setTotalItems(total.intValue());

		Query consulta = getEntityManager().createQuery("select p from Paciente p where p.habilitado=:bool and p." + sf + " " + so + " :text");
		consulta.setParameter("bool", true);
		consulta.setParameter("text", texto);
		consulta.setFirstResult(paginador.inicio());
		consulta.setMaxResults(paginador.getCantPorPag());

		return consulta.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.historiasocial.dao.PacienteDAO#search(java.lang.String)
	 */
	@Override
	public List<Paciente> search(String term){
		term = term + "%";
		Query searchPaciente = getEntityManager().createQuery("select p from Paciente p where p.habilitado=:bool and p.apellidos LIKE :text");
		searchPaciente.setParameter("bool", true);
		searchPaciente.setParameter("text", term);
		searchPaciente.setFirstResult(PacienteDAOHibernateJPA.firstDefault);
		searchPaciente.setMaxResults(PacienteDAOHibernateJPA.sizeResultDefault);
		
		return searchPaciente.getResultList();
	}
}
