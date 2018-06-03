package ar.com.test.historiasocial;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.InstitucionDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.PersonaDAO;
import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.Conviviente;
import ar.com.historiasocial.entities.Diagnostico;
import ar.com.historiasocial.entities.Domicilio;
import ar.com.historiasocial.entities.Especialidad;
import ar.com.historiasocial.entities.HistoriaClinica;
import ar.com.historiasocial.entities.HistoriaSocial;
import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.InstitutionResponsible;
import ar.com.historiasocial.entities.InstitutionType;
import ar.com.historiasocial.entities.Location;
import ar.com.historiasocial.entities.NoExisteTratamientoActivoException;
import ar.com.historiasocial.entities.ObraSocial;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Persona;
import ar.com.historiasocial.entities.Practica;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.Servicio;
import ar.com.historiasocial.entities.TipoDePractica;
import ar.com.historiasocial.entities.TipoDeProblematica;
import ar.com.historiasocial.entities.Vivienda;

/**
 * Clase base para todos los test de la aplicación.
 * 
 * Define varios de los daos presentes en la app, asi como también un conjunto
 * de metodos que crean e inicializan entidades del dominio
 * 
 * @author Manuel Ortiz - ortizma@gmail.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ar/com/historiasocial/beans-jpa-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public abstract class ApplicationTestCase {

	@Resource(name = "embeddedDataSource")
	protected DataSource						database;
	@Autowired
	protected PacienteDAO						pacienteDAO;
	@Autowired
	private HistoriaSocialDAO					historiaSocialDAO;
	@Autowired
	private InstitucionDAO						institucionDAO;
	@Autowired
	private PersonaDAO							personaDAO;
	@Autowired
	protected PracticaDAO						practicaDAO;
	@Autowired
	private GenericDAO<Servicio>				servicioDAO;
	@Autowired
	private GenericDAO<Especialidad>				especialidadDAO;
	@Autowired
	private GenericDAO<TipoDePractica>			tipoDePracticaDAO;
	@Autowired
	private GenericDAO<TipoDeProblematica>		tipoDeProblematicaDAO;
	@Autowired
	private GenericDAO<InstitutionType>			institutionTypeDAO;
	@Autowired
	private GenericDAO<InstitutionResponsible>	responsableDAO;
	@Autowired
	private GenericDAO<ObraSocial>				obraSocialDAO;

	@Autowired
	private GenericDAO<Diagnostico>				diagnosticoDAO;

	@Autowired
	private ProfesionalDAO						profesionalDAO;

	public ApplicationTestCase() {
		super();
	}

	/**
	 * @param p
	 * @param i
	 * @param pra
	 * @param prof
	 * @throws NoExisteTratamientoActivoException
	 */
	protected HistoriaSocial createHistoriaSocial(Paciente p, Institucion i, Practica pra, Profesional prof) throws NoExisteTratamientoActivoException{
		HistoriaSocial hs = new HistoriaSocial();
		hs.setFechaInicio(Date.valueOf("2012-09-10"));
		hs.setInstitucion(i);
		hs.setMotivoIntervencionSocial("Violencia familiar");
		Diagnostico diagnostico = new Diagnostico("prueba");
		diagnosticoDAO.save(diagnostico);
		hs.registrarIngresoPaciente("Tratamiento 1", diagnostico);
		hs.agregarPractica(pra);
		hs.setPaciente(p);
		hs.setProfesionalResponsable(prof);
		historiaSocialDAO.save(hs);
		pacienteDAO.saveOrUpdate(p);

		return hs;
	}

	/**
	 * @param es
	 * @param s
	 * @param pra
	 * @return
	 */
	protected Profesional createProfecional(Especialidad es, Servicio s, Practica pra){
		Profesional prof = new Profesional();
		prof.setApellido("Badia");
		prof.agregarEspecialidad(es);
		
		prof.setMatricula("20927384");
		prof.setNombre("Federico");
		prof.setPass("fede");
		if (pra != null) {
			prof.agregarPractica(pra);
		}
		prof.setServicio(s);
		prof.setUser("fede");
		prof.setEsDirector(true);
		prof.setHabilitado(true);
		profesionalDAO.save(prof);
		return prof;
	}

	/**
	 * @param tpra
	 * @param tpro
	 * @return
	 */
	protected Practica createPractica(TipoDePractica tpra, TipoDeProblematica tpro){
		Practica pra = new Practica();
		pra.setDetalle("Practia 1");
		pra.setFechaCarga(Date.valueOf("2009-05-12"));
		pra.setFechaPractica(Date.valueOf("2009-05-15"));
		pra.setTipoPractica(tpra);
		pra.setTipoProblematica(tpro);
		pra.setHabilitado(true);
		practicaDAO.save(pra);

		return pra;
	}

	/**
	 * @return
	 */
	protected Servicio createServicio(){
		Servicio s = new Servicio();
		s.setNombreServicio("Dermatologia");
		servicioDAO.save(s);
		return s;
	}

	/**
	 * @return
	 */
	protected TipoDeProblematica createTipoProblematica(){
		TipoDeProblematica tpro = new TipoDeProblematica();
		tpro.setCodigo("01 Violencia Familiar");
		tpro.setDescripcion("El paciente es agredido por su familia");
		tipoDeProblematicaDAO.save(tpro);
		return tpro;
	}

	/**
	 * @return
	 */
	protected TipoDePractica createTipoPractica(){
		TipoDePractica tpra = new TipoDePractica();
		tpra.setCodigo("02 Interconsulta");
		tpra.setDescripcion("Prestacion social cuyo tiempo de realizacion se considera de 30 minutos. Implica la comunicacion que se establece entre profesionales de la misma o de otra disciplina para consultar sobre determinada situacion");
		tpra.setDuracion("3:30");
		tipoDePracticaDAO.save(tpra);
		return tpra;
	}

	/**
	 * 
	 */
	protected void createPersona(){
		Persona per = new Persona();
		per.setApellido("Lopez");
		per.setNombres("Pedro");
		per.setRazonSocial("SRL");
		personaDAO.save(per);
	}

	/**
	 * @param typeInst
	 * @param responsible
	 * @return
	 */
	protected Institucion createInstitucion(InstitutionType typeInst, InstitutionResponsible responsible){
		Institucion i = new Institucion();
		i.setNombre("Hospital Espaniol");
		i.setDetail("El espaniol");
		i.setEmail("espaniol at hospital.com");
		i.setType(typeInst);
		i.setResponsible(responsible);
		i.setLocation(new Location("Buenos Aires", "", "La Plata", "7", "540", null, null, null, i));
		institucionDAO.save(i);
		return i;
	}

	/**
	 * @return
	 */
	protected InstitutionResponsible createInstResp(){
		InstitutionResponsible responsible = responsableDAO.save(new InstitutionResponsible("Ricardo", "Fontana", "Medico", "riqui at gmail.com", "98391238"));
		return responsible;
	}

	/**
	 * @return
	 */
	protected InstitutionType createTypeInstitucion(){
		InstitutionType typeInst = institutionTypeDAO.save(new InstitutionType("Tipo por defecto"));
		return typeInst;
	}

	/**
	 * @return
	 */
	protected Especialidad createEspecialidad(){
		Especialidad es = new Especialidad();
		es.setDescripcion("Clinico");
		
		especialidadDAO.save(es);
		
		return es;
	}

	/**
	 * @return
	 */
	protected Conviviente createConviviente(){
		Conviviente c1 = new Conviviente();
		c1.setApellido("Seferina");
		c1.setNombres("Rosa Maria");
		c1.setNacionalidad("Argentina");
		c1.setVinculo("Prima");
		ObraSocial obraSocial = new ObraSocial("OSDE", "OSDE descripcion", "310");
		obraSocial = obraSocialDAO.save(obraSocial);
		c1.setObraSocial(obraSocial);
		c1.setEdad(38);
		c1.setIngresos("5000");
		c1.setEstadoCivil("Soltera");
		personaDAO.save(c1);
		return c1;
	}

	/**
	 * @param d2
	 * @param v2
	 * @param hc2
	 * @param c1
	 * @return
	 */
	protected Paciente createPacienteConConviviente(Long id, Domicilio d2, Vivienda v2, HistoriaClinica hc2, Conviviente c1){
		Paciente p3 = new Paciente();
		p3.setId(id);
		p3.setNombres("Maria");
		p3.setApellidos("Gomez");
		p3.setDocumento("45673410");
		Date fechaNacimiento3 = Date.valueOf("1987-10-07");
		p3.setFechaNacimiento(fechaNacimiento3);
		p3.setHabilitado(true);
		p3.agregarHistoriaClinica(hc2);
		p3.setDomicilio(d2);
		p3.setVivienda(v2);
		p3.agregarConviviente(c1);
		pacienteDAO.save(p3);
		return p3;
	}

	/**
	 * Crea un paciente, lo persiste y lo devuelve
	 * 
	 * @param d
	 * @param v
	 * @param hc
	 * @return
	 */
	protected Paciente createPaciente(Long id, Domicilio d, Vivienda v, HistoriaClinica hc){

		Paciente p = new Paciente();
		p.setId(id);
		p.setNombres("Juan");
		p.setApellidos("Fernandez");
		p.setDocumento("93939339");
		Date fechaNacimiento = Date.valueOf("1985-04-13");

		p.setFechaNacimiento(fechaNacimiento);
		p.setHabilitado(true);
		p.agregarHistoriaClinica(hc);
		p.setDomicilio(d);
		p.setVivienda(v);
		pacienteDAO.save(p);
		return p;
	}

	/**
	 * @return
	 */
	protected HistoriaClinica createHisClinica(){
		HistoriaClinica hc = new HistoriaClinica();
		hc.setDiagnostico("Conjuntivitis");
		hc.setNumero(2);
		hc.setSala("2");
		return hc;
	}

	/**
	 * @return una location ubicada en el centro de la plata
	 */
	public Location createLocation(Paciente p, Institucion i){
		return new Location("Buenos aires", "", "buenos aires", "calle 1", "123", -34.921439, -57.954541, p, i);
	}

	/**
	 * @return
	 */
	protected Vivienda createVivienda(){
		Vivienda v = new Vivienda();
		v.setEstado("true");
		v.setPropiedad("Casa");
		v.setTipo("Casa");
		return v;
	}

	/**
	 * @return
	 */
	protected Domicilio createDomicilio(){
		Domicilio d = new Domicilio();
		d.setCalle("10");
		d.setNumero("1398");
		d.setPiso("1");
		d.setDepartamento("1");
		return d;
	}

	public void clearDatabase() throws Exception{
		DataSource ds = database;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			try {
				Statement stmt = connection.createStatement();
				try {
					stmt.execute("SET REFERENTIAL_INTEGRITY FALSE");
					Set<String> tables = new HashSet<String>();
					ResultSet rs = stmt.executeQuery("select TABLE_NAME " + "FROM INFORMATION_SCHEMA.TABLES "
							+ "WHERE table_type='TABLE' and table_schema='PUBLIC'");
					
					
					while(rs.next()){
						if(!rs.getString(1).startsWith("DUAL_")){
							tables.add(rs.getString(1));
						}
					}
					
					rs.close();
					
					for (String table : tables) {
						stmt.executeUpdate("TRUNCATE TABLE " + table);
					}
					stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");

					connection.commit();
				} finally {
					stmt.close();
				}
			} catch (SQLException e) {
				connection.rollback();
				throw new Exception(e);
			}
		} catch (SQLException e) {
			throw new Exception(e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * @return un paciente con domicilio, vivienda, y historia clinica
	 */
	protected Paciente createPacienteCompleto() {
		Domicilio d = this.createDomicilio();
		Vivienda v = this.createVivienda();
		HistoriaClinica hc = this.createHisClinica();
		
		Paciente paciente = this.createPaciente(1L, d, v, hc);
		return paciente;
	}

}
