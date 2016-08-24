package ar.com.historiasocial.bootstrap;

import java.sql.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.StringValueResolver;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.HistoriaSocialDAO;
import ar.com.historiasocial.dao.InstitucionDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.dao.ProfesionalDAO;
import ar.com.historiasocial.entities.CondicionHabitacional;
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
import ar.com.historiasocial.entities.TipoPropiedad;
import ar.com.historiasocial.entities.Vivienda;

/**
 * Application Lifecycle Listener implementation class Cargador
 * 
 */
@WebListener
public class Cargador implements ApplicationListener<ContextRefreshedEvent>, EmbeddedValueResolverAware {

	private PacienteDAO							pacienteDAO;
	private HistoriaSocialDAO					historiaSocialDAO;
	private InstitucionDAO						institucionDAO;
	private GenericDAO<Persona>					personaDAO;
	private PracticaDAO							practicaDAO;
	private GenericDAO<Servicio>				servicioDAO;
	private GenericDAO<TipoDePractica>			tipoDePracticaDAO;
	private GenericDAO<TipoDeProblematica>		tipoDeProblematicaDAO;
	private GenericDAO<InstitutionType>			institucionTypeDAO;
	private GenericDAO<InstitutionResponsible>	responsableDAO;
	private ProfesionalDAO						profesionalDAO;
	private GenericDAO<Diagnostico>				diagnosticoDAO;
	private GenericDAO<Especialidad>			especialidadDAO;
	private GenericDAO<TipoPropiedad>			tipoPropiedadDAO;
	private GenericDAO<CondicionHabitacional>	condicionHabitacionalDAO;
	private GenericDAO<ObraSocial>				obraSocialDAO;

	private String								environment;

	private StringValueResolver					resolver;
	
	private final Logger LOGGER = Logger.getLogger(this.getClass());

	/**
	 * Default constructor.
	 */
	public Cargador() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent ctx){

		LOGGER.debug(" \n ********************************************************************** \n ********************************************************************** \n ******************* HS en Environment:  "
						+ this.getEnvironment()
						+ "  ******************* \n ********************************************************************** \n **********************************************************************");
		if (profesionalDAO.rowCount().intValue() == 0 && this.getEnvironment().equalsIgnoreCase("dev")) {

			Diagnostico diagnostico1 = new Diagnostico("Diagnostico 1");
			Diagnostico diagnostico2 = new Diagnostico("Diagnostico 2");
			Diagnostico diagnostico3 = new Diagnostico("Diagnostico 3");

			getDiagnosticoDAO().save(diagnostico1);
			getDiagnosticoDAO().save(diagnostico2);
			getDiagnosticoDAO().save(diagnostico3);

			TipoPropiedad tipoPropiedad1 = new TipoPropiedad("de material");
			TipoPropiedad tipoPropiedad2 = new TipoPropiedad("de chapa");
			TipoPropiedad tipoPropiedad3 = new TipoPropiedad("de adobe");

			getTipoPropiedadDAO().save(tipoPropiedad1);
			getTipoPropiedadDAO().save(tipoPropiedad2);
			getTipoPropiedadDAO().save(tipoPropiedad3);

			CondicionHabitacional condicionHabitacional1 = new CondicionHabitacional("buena");
			CondicionHabitacional condicionHabitacional2 = new CondicionHabitacional("regular");
			CondicionHabitacional condicionHabitacional3 = new CondicionHabitacional("mala");

			getCondicionHabitacionalDAO().save(condicionHabitacional1);
			getCondicionHabitacionalDAO().save(condicionHabitacional2);
			getCondicionHabitacionalDAO().save(condicionHabitacional3);

			Domicilio d = new Domicilio();
			d.setCalle("10");
			d.setNumero("1398");
			d.setPiso("1");
			d.setDepartamento("1");
			// domicilioDAO.save(d);

			Domicilio d1 = new Domicilio();
			d1.setCalle("5");
			d1.setNumero("456");
			d1.setPiso("4");
			d1.setDepartamento("A");
			// domicilioDAO.save(d1);

			Domicilio d2 = new Domicilio();
			d2.setCalle("8");
			d2.setNumero("975");
			d2.setPiso("7");
			d2.setDepartamento("C");
			// domicilioDAO.save(d2);

			Vivienda v = new Vivienda();
			v.setEstado("true");
			v.setPropiedad("Casa");
			v.setTipo("Casa");
			// viviendaDAO.save(v);

			Vivienda v1 = new Vivienda();
			v1.setEstado("true");
			v1.setPropiedad("Departamento");
			v1.setTipo("Departamento");
			// viviendaDAO.save(v1);

			Vivienda v2 = new Vivienda();
			v2.setEstado("true");
			v2.setPropiedad("Casa");
			v2.setTipo("Casa");
			// viviendaDAO.save(v2);

			HistoriaClinica hc = new HistoriaClinica();
			hc.setDiagnostico("Conjuntivitis");
			hc.setNumero(2);
			hc.setSala("2");
			// historiaClinicaDAO.save(hc);

			HistoriaClinica hc1 = new HistoriaClinica();
			hc1.setDiagnostico("Gripe");
			hc1.setNumero(6);
			hc1.setSala("8");
			// historiaClinicaDAO.save(hc1);

			HistoriaClinica hc2 = new HistoriaClinica();
			hc2.setDiagnostico("Alergia");
			hc2.setNumero(1);
			hc2.setSala("3");
			// historiaClinicaDAO.save(hc2);

			Paciente p = new Paciente();
			p.setNombres("Juan");
			p.setApellidos("Fernandez");
			p.setDocumento("93939339");
			Date fechaNacimiento = Date.valueOf("1985-04-13");
			Date fechaIngreo = Date.valueOf("2013-04-13");

			p.setFechaNacimiento(fechaNacimiento);
			p.setHabilitado(true);
			p.agregarHistoriaClinica(hc);
			p.setDomicilio(d);
			p.setVivienda(v);
			p.setTipoDePropiedad(tipoPropiedad1);
			p.setCondicionesHabitacionales(condicionHabitacional1);
			pacienteDAO.save(p);

			Paciente p2 = new Paciente();
			p2.setNombres("Martin");
			p2.setApellidos("Gutierrez");
			p2.setDocumento("30980789");
			Date fechaNacimiento2 = Date.valueOf("1984-07-21");
			p2.setFechaNacimiento(fechaNacimiento2);
			p2.setHabilitado(true);
			p2.agregarHistoriaClinica(hc1);
			p2.setDomicilio(d1);
			p2.setVivienda(v1);
			p2.setTipoDePropiedad(tipoPropiedad2);
			p2.setCondicionesHabitacionales(condicionHabitacional2);
			p2.setCentroSaludReferencia(null);
			pacienteDAO.save(p2);

			ObraSocial obraSocial = new ObraSocial("OSDE", "Organizacion de servicios directos empresarios", "210");
			ObraSocial obraSocial1 = new ObraSocial("OSDE", "Organizacion de servicios directos empresarios", "310");
			ObraSocial obraSocial2 = new ObraSocial("OSDE", "Organizacion de servicios directos empresarios", "410");
			ObraSocial obraSocial3 = new ObraSocial("OSDE", "Organizacion de servicios directos empresarios", "510");
			ObraSocial obraSocial4 = new ObraSocial("Galeno", "Galeno", "210");
			ObraSocial obraSocial5 = new ObraSocial("IOMA", "IOMA", "");

			getObraSocialDAO().save(obraSocial);
			getObraSocialDAO().save(obraSocial1);
			getObraSocialDAO().save(obraSocial2);
			getObraSocialDAO().save(obraSocial3);
			getObraSocialDAO().save(obraSocial4);
			getObraSocialDAO().save(obraSocial5);

			Conviviente c1 = new Conviviente();
			c1.setApellido("Seferina");
			c1.setNombres("Rosa Maria");
			c1.setNacionalidad("Argentina");
			c1.setVinculo("Prima");
			c1.setObraSocial(obraSocial);
			c1.setEdad(38);
			c1.setIngresos("5000");
			c1.setEstadoCivil("Soltera");
			getPersonaDAO().save(c1);

			Paciente p3 = new Paciente();
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
			p3.setTipoDePropiedad(tipoPropiedad1);
			p3.setCondicionesHabitacionales(condicionHabitacional1);
			p3.setCentroSaludReferencia(null);
			pacienteDAO.save(p3);

			Especialidad es = new Especialidad();
			es.setDescripcion("Clinico");
			especialidadDAO.save(es);

			Especialidad es1 = new Especialidad();
			es1.setDescripcion("Endocrinologo");
			especialidadDAO.save(es1);

			Especialidad es2 = new Especialidad();
			es2.setDescripcion("Pediatria");
			especialidadDAO.save(es2);

			InstitutionType typeInst = getInstitucionTypeDAO().save(new InstitutionType("Tipo por defecto"));
			InstitutionResponsible responsible = getResponsableDAO().save(
					new InstitutionResponsible("Ricardo", "Fontana", "Medico", "riqui at gmail.com", "98391238"));

			Institucion i = new Institucion();
			i.setNombre("Hospital Espaniol");
			i.setDetail("El espaniol");
			i.setEmail("espaniol at hospital.com");
			i.setType(typeInst);
			i.setResponsible(responsible);
			i.setLocation(new Location("Buenos Aires", "", "La Plata", "7", "540", null, null, null, i));
			institucionDAO.save(i);

			Institucion i2 = new Institucion();
			i2.setNombre("Hospital Italiano");
			i2.setDetail("El Italiano");
			i2.setEmail("italiano at hospital.com");
			i2.setType(typeInst);
			i2.setResponsible(responsible);
			i2.setLocation(new Location("Buenos Aires", "", "La Plata", "2", "340", null, null, null, i2));
			institucionDAO.save(i2);

			Institucion i3 = new Institucion();
			i3.setDetail("El San Martin");
			i3.setEmail("sanmartin at hospital.com");
			i3.setNombre("Hospital San Martin");
			i3.setType(typeInst);
			i3.setResponsible(responsible);
			i3.setLocation(new Location("Buenos Aires", "", "La Plata", "14", "640", null, null, null, i3));
			institucionDAO.save(i3);

			Persona per = new Persona();
			per.setApellido("Lopez");
			per.setNombres("Pedro");
			per.setRazonSocial("SRL");
			getPersonaDAO().save(per);

			TipoDePractica tpra = new TipoDePractica();
			tpra.setCodigo("02 Interconsulta");
			tpra.setDescripcion("Prestacion social cuyo tiempo de realizacion se considera de 30 minutos. Implica la comunicacion que se establece entre profesionales de la misma o de otra disciplina para consultar sobre determinada situacion");
			tpra.setDuracion("3:30");
			getTipoDePracticaDAO().save(tpra);

			TipoDePractica tpra2 = new TipoDePractica();
			tpra2.setCodigo("03 Prestacion Social Minima");
			tpra2.setDescripcion("Es aquella prestacion social cuyo tiempo promedio para su realizacion es de 10 minutos. Incluye las siguientes actividades: orientacion, resenia social, tramitacion de apoyo asistencial (como pasaje oficiales, hospedaje, autorizaciones telefonicas, etc.)");
			tpra2.setDuracion("2:10");
			getTipoDePracticaDAO().save(tpra2);

			TipoDePractica tpra3 = new TipoDePractica();
			tpra3.setCodigo("04 Prestacion Social Intermedia");
			tpra3.setDescripcion("Es aquella prestacion social cuyo tiempo promedio para su realizacion es de 25 minutos.");
			tpra3.setDuracion("3");
			getTipoDePracticaDAO().save(tpra3);

			TipoDeProblematica tpro = new TipoDeProblematica();
			tpro.setCodigo("01 Violencia Familiar");
			tpro.setDescripcion("El paciente es agredido por su familia");
			getTipoDeProblematicaDAO().save(tpro);

			TipoDeProblematica tpro2 = new TipoDeProblematica();
			tpro2.setCodigo("02 Desnutricion");
			tpro2.setDescripcion("El paciente sufre problemas de alimentacion");
			getTipoDeProblematicaDAO().save(tpro2);

			TipoDeProblematica tpro3 = new TipoDeProblematica();
			tpro3.setCodigo("03 Situacion de Calle");
			tpro3.setDescripcion("El paciente se encuentra sin una vivienda adecuada");
			getTipoDeProblematicaDAO().save(tpro3);

			Servicio s = new Servicio();
			s.setNombreServicio("Dermatologia");
			getServicioDAO().save(s);
			// servicioDAO.save(s);

			Servicio s2 = new Servicio();
			s2.setNombreServicio("Fonoaudiologia");
			getServicioDAO().save(s2);
			s2.setNombreServicio("Fonoaudiologia");
			// servicioDAO.save(s2);

			Servicio s3 = new Servicio();
			s3.setNombreServicio("Neurologia");
			getServicioDAO().save(s3);
			s3.setNombreServicio("Neurologia");
			// servicioDAO.save(s3);

			Servicio s4 = new Servicio();
			s4.setNombreServicio("Psicologia");
			getServicioDAO().save(s4);
			s4.setNombreServicio("Psicologia");
			// servicioDAO.save(s4);

			Servicio s5 = new Servicio();
			s5.setNombreServicio("Psicopedagogia");
			getServicioDAO().save(s5);
			s5.setNombreServicio("Psicopedagogia");
			// servicioDAO.save(s5);

			Servicio s6 = new Servicio();
			s6.setNombreServicio("Odontopediatria");
			getServicioDAO().save(s6);
			s6.setNombreServicio("Odontopediatria");
			// servicioDAO.save(s6);

			Servicio s7 = new Servicio();
			s7.setNombreServicio("Radiologia");
			getServicioDAO().save(s7);
			s7.setNombreServicio("Radiologia");
			// servicioDAO.save(s7);

			Servicio s8 = new Servicio();
			s8.setNombreServicio("Alimentacion y Dietoterapia");
			// servicioDAO.save(s8);

			Servicio s9 = new Servicio();
			s9.setNombreServicio("Laboratorio");
			// servicioDAO.save(s9);

			Servicio s10 = new Servicio();
			s10.setNombreServicio("Enfermeria");
			// servicioDAO.save(s10);

			Servicio s11 = new Servicio();
			s11.setNombreServicio("Farmacia");
			// servicioDAO.save(s11);

			Practica pra = new Practica();
			pra.setDetalle("Practia 1");
			pra.setFechaCarga(Date.valueOf("2009-05-12"));
			pra.setFechaPractica(Date.valueOf("2009-05-15"));
			pra.setTipoPractica(tpra);
			pra.setTipoProblematica(tpro);
			pra.setHabilitado(true);

			Practica pra2 = new Practica();
			pra2.setDetalle("Practica 2");
			pra2.setFechaCarga(Date.valueOf("2010-07-30"));
			pra2.setFechaPractica(Date.valueOf("2010-08-30"));
			pra2.setTipoPractica(tpra2);
			pra2.setTipoProblematica(tpro2);
			pra2.setHabilitado(true);

			Practica pra3 = new Practica();
			pra3.setDetalle("Practica 3");
			pra3.setFechaCarga(Date.valueOf("2010-08-28"));
			pra3.setFechaPractica(Date.valueOf("2010-10-04"));
			pra3.setTipoPractica(tpra3);
			pra3.setTipoProblematica(tpro3);
			pra3.setHabilitado(true);

			practicaDAO.save(pra);
			practicaDAO.save(pra2);
			practicaDAO.save(pra3);

			Profesional prof = new Profesional();
			prof.setApellido("Badia");
			prof.agregarEspecialidad(es);
			prof.setMatricula("20927384");
			prof.setNombre("Federico");
			prof.setPass("fede");
			prof.agregarPractica(pra);
			prof.setServicio(s);
			prof.setUser("fede");
			prof.setEsDirector(true);
			prof.setHabilitado(true);
			profesionalDAO.save(prof);

			Profesional prof2 = new Profesional();
			prof2.setApellido("Vega");
			prof2.agregarEspecialidad(es1);
			prof2.setMatricula("29836178");
			prof2.setNombre("Hugo");
			prof2.setPass("hugo");
			prof2.agregarPractica(pra2);
			prof2.setServicio(s2);
			prof2.setUser("hugo");
			prof2.setEsDirector(true);
			prof2.setHabilitado(true);
			profesionalDAO.save(prof2);

			Profesional prof3 = new Profesional();
			prof3.setApellido("Hernandez");
			prof3.agregarEspecialidad(es2);
			prof3.setMatricula("24987456");
			prof3.setNombre("David");
			prof3.setPass("david");
			prof3.agregarPractica(pra3);
			prof3.setServicio(s3);
			prof3.setUser("david");
			prof3.setEsDirector(false);
			prof3.setHabilitado(true);
			profesionalDAO.save(prof3);

			HistoriaSocial hs = new HistoriaSocial();
			hs.setFechaInicio(Date.valueOf("2012-09-10"));
			hs.setInstitucion(i);
			hs.setMotivoIntervencionSocial("Violencia familiar");
			hs.registrarIngresoPaciente("Tratamiento 1", diagnostico1);
			try {
				hs.agregarPractica(pra);

				// violo en encapsulamiento, lo hago para fijar la fecha y
				// testearlo.
				hs.getTratamientoActual().setFechaIngreso(Date.valueOf("2014-02-05"));
				hs.getTratamientoAmbulatorio().setFechaIngreso(Date.valueOf("2014-02-05"));
			} catch (NoExisteTratamientoActivoException e) {
				e.printStackTrace();
			}
			hs.setPaciente(p);
			hs.setProfesionalResponsable(prof);
			historiaSocialDAO.save(hs);
			pacienteDAO.saveOrUpdate(p);

			HistoriaSocial hs1 = new HistoriaSocial();
			hs1.setFechaInicio(Date.valueOf("2007-03-09"));
			hs1.setInstitucion(i2);
			hs1.setMotivoIntervencionSocial("Desnutricion");
			hs1.registrarIngresoPaciente("Tratamiento 2", diagnostico2);
			hs1.setMotivoIntervencionSocial("Desnutricion");
			try {
				hs1.agregarPractica(pra2);
				// violo en encapsulamiento, lo hago para fijar la fecha y
				// testearlo.
				hs1.getTratamientoActual().setFechaIngreso(Date.valueOf("2014-02-05"));
				hs1.getTratamientoAmbulatorio().setFechaIngreso(Date.valueOf("2014-02-05"));
			} catch (NoExisteTratamientoActivoException e) {
				e.printStackTrace();
			}
			hs1.setPaciente(p2);
			hs1.setProfesionalResponsable(prof2);
			historiaSocialDAO.save(hs1);
			pacienteDAO.saveOrUpdate(p2);

			HistoriaSocial hs3 = new HistoriaSocial();
			hs3.setFechaInicio(Date.valueOf("2011-07-22"));
			hs3.setInstitucion(i3);
			hs3.setMotivoIntervencionSocial("Situacion de calle");
			hs3.registrarIngresoPaciente("Tratamiento 3", diagnostico3);
			hs3.setMotivoIntervencionSocial("Situacion de calle");
			try {
				hs3.agregarPractica(pra3);
				// violo en encapsulamiento, lo hago para fijar la fecha y
				// testearlo.
				hs3.getTratamientoActual().setFechaIngreso(Date.valueOf("2014-02-05"));
				hs3.getTratamientoAmbulatorio().setFechaIngreso(Date.valueOf("2014-02-05"));
			} catch (NoExisteTratamientoActivoException e) {
				e.printStackTrace();
			}
			hs3.setPaciente(p3);
			hs3.setProfesionalResponsable(prof3);
			historiaSocialDAO.save(hs3);
			pacienteDAO.saveOrUpdate(p3);

			practicaDAO.saveOrUpdate(pra);
			practicaDAO.saveOrUpdate(pra2);
			practicaDAO.saveOrUpdate(pra3);
		} else {
			boolean prodOrTest = this.getEnvironment().equalsIgnoreCase("prod") || this.getEnvironment().equalsIgnoreCase("test");

			if (profesionalDAO.rowCount().intValue() == 0 && prodOrTest) {
				Profesional admin = new Profesional();
				String adminUser = this.getResolver().resolveStringValue("${hs.admin.user}");
				
				LOGGER.debug(" \n **************************************************** \n ************************** Se genero el usuario administrador: " + adminUser + "************************** \n **************************************************** \n");
				
				admin.setUser(adminUser);
				admin.setPass(this.getResolver().resolveStringValue("${hs.admin.pass}"));
				admin.setNombre("Administrador");
				admin.setApellido("Administrador");
				admin.setEsDirector(true);
				admin.setHabilitado(true);

				profesionalDAO.save(admin);
			}

		}

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0){

	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	public void setHistoriaSocialDAO(HistoriaSocialDAO historiaSocialDAO){
		this.historiaSocialDAO = historiaSocialDAO;
	}

	public void setInstitucionDAO(InstitucionDAO institucionDAO){
		this.institucionDAO = institucionDAO;
	}

	public void setPracticaDAO(PracticaDAO practicaDAO){
		this.practicaDAO = practicaDAO;
	}

	public void setProfesionalDAO(ProfesionalDAO profesionalDAO){
		this.profesionalDAO = profesionalDAO;
	}

	/**
	 * @return the condicionHabitacionalDAO
	 */
	public GenericDAO<CondicionHabitacional> getCondicionHabitacionalDAO(){
		return condicionHabitacionalDAO;
	}

	/**
	 * @param condicionHabitacionalDAO
	 *            the condicionHabitacionalDAO to set
	 */
	public void setCondicionHabitacionalDAO(GenericDAO<CondicionHabitacional> condicionHabitacionalDAO){
		this.condicionHabitacionalDAO = condicionHabitacionalDAO;
	}

	/**
	 * @param servicioDAO
	 *            the servicioDAO to set
	 */
	public void setServicioDAO(GenericDAO<Servicio> servicioDAO){
		this.servicioDAO = servicioDAO;
	}

	/**
	 * @param tipoDePracticaDAO
	 *            the tipoDePracticaDAO to set
	 */
	public void setTipoDePracticaDAO(GenericDAO<TipoDePractica> tipoDePracticaDAO){
		this.tipoDePracticaDAO = tipoDePracticaDAO;
	}

	/**
	 * @param tipoDeProblematicaDAO
	 *            the tipoDeProblematicaDAO to set
	 */
	public void setTipoDeProblematicaDAO(GenericDAO<TipoDeProblematica> tipoDeProblematicaDAO){
		this.tipoDeProblematicaDAO = tipoDeProblematicaDAO;
	}

	/**
	 * @param institucionTypeDAO
	 *            the institucionTypeDAO to set
	 */
	public void setInstitucionTypeDAO(GenericDAO<InstitutionType> institucionTypeDAO){
		this.institucionTypeDAO = institucionTypeDAO;
	}

	/**
	 * @param responsableDAO
	 *            the responsableDAO to set
	 */
	public void setResponsableDAO(GenericDAO<InstitutionResponsible> responsableDAO){
		this.responsableDAO = responsableDAO;
	}

	/**
	 * @param diagnosticoDAO
	 *            the diagnosticoDAO to set
	 */
	public void setDiagnosticoDAO(GenericDAO<Diagnostico> diagnosticoDAO){
		this.diagnosticoDAO = diagnosticoDAO;
	}

	/**
	 * @param tipoPropiedadDAO
	 *            the tipoPropiedadDAO to set
	 */
	public void setTipoPropiedadDAO(GenericDAO<TipoPropiedad> tipoPropiedadDAO){
		this.tipoPropiedadDAO = tipoPropiedadDAO;
	}

	/**
	 * @param obraSocialDAO
	 *            the obraSocialDAO to set
	 */
	public void setObraSocialDAO(GenericDAO<ObraSocial> obraSocialDAO){
		this.obraSocialDAO = obraSocialDAO;
	}

	/**
	 * @return the pacienteDAO
	 */
	public PacienteDAO getPacienteDAO(){
		return this.pacienteDAO;
	}

	/**
	 * @return the historiaSocialDAO
	 */
	public HistoriaSocialDAO getHistoriaSocialDAO(){
		return this.historiaSocialDAO;
	}

	/**
	 * @return the institucionDAO
	 */
	public InstitucionDAO getInstitucionDAO(){
		return this.institucionDAO;
	}

	/**
	 * @return the practicaDAO
	 */
	public PracticaDAO getPracticaDAO(){
		return this.practicaDAO;
	}

	/**
	 * @return the servicioDAO
	 */
	public GenericDAO<Servicio> getServicioDAO(){
		return this.servicioDAO;
	}

	/**
	 * @return the tipoDePracticaDAO
	 */
	public GenericDAO<TipoDePractica> getTipoDePracticaDAO(){
		return this.tipoDePracticaDAO;
	}

	/**
	 * @return the tipoDeProblematicaDAO
	 */
	public GenericDAO<TipoDeProblematica> getTipoDeProblematicaDAO(){
		return this.tipoDeProblematicaDAO;
	}

	/**
	 * @return the institucionTypeDAO
	 */
	public GenericDAO<InstitutionType> getInstitucionTypeDAO(){
		return this.institucionTypeDAO;
	}

	/**
	 * @return the responsableDAO
	 */
	public GenericDAO<InstitutionResponsible> getResponsableDAO(){
		return this.responsableDAO;
	}

	/**
	 * @return the profesionalDAO
	 */
	public ProfesionalDAO getProfesionalDAO(){
		return this.profesionalDAO;
	}

	/**
	 * @return the diagnosticoDAO
	 */
	public GenericDAO<Diagnostico> getDiagnosticoDAO(){
		return this.diagnosticoDAO;
	}

	/**
	 * @return the tipoPropiedadDAO
	 */
	public GenericDAO<TipoPropiedad> getTipoPropiedadDAO(){
		return this.tipoPropiedadDAO;
	}

	/**
	 * @return the obraSocialDAO
	 */
	public GenericDAO<ObraSocial> getObraSocialDAO(){
		return this.obraSocialDAO;
	}

	/**
	 * @return the personaDAO
	 */
	public GenericDAO<Persona> getPersonaDAO(){
		return personaDAO;
	}

	/**
	 * @param personaDAO
	 *            the personaDAO to set
	 */
	public void setPersonaDAO(GenericDAO<Persona> personaDAO){
		this.personaDAO = personaDAO;
	}

	/**
	 * @return the environment
	 */
	public String getEnvironment(){
		return environment;
	}

	/**
	 * @param environment
	 *            the environment to set
	 */
	public void setEnvironment(String prueba){
		this.environment = prueba;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver svr){
		this.setResolver(svr);

	}

	/**
	 * @return the resolver
	 */
	public StringValueResolver getResolver(){
		return resolver;
	}

	/**
	 * @param resolver
	 *            the resolver to set
	 */
	public void setResolver(StringValueResolver resolver){
		this.resolver = resolver;
	}

	/**
	 * @return the especialidadDAO
	 */
	public GenericDAO<Especialidad> getEspecialidadDAO(){
		return especialidadDAO;
	}

	/**
	 * @param especialidadDAO the especialidadDAO to set
	 */
	public void setEspecialidadDAO(GenericDAO<Especialidad> especialidadDAO){
		this.especialidadDAO = especialidadDAO;
	}

}
