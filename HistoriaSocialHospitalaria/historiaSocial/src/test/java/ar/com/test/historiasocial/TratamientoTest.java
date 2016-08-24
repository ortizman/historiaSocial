/**
 * 
 */
package ar.com.test.historiasocial;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.com.historiasocial.entities.Conviviente;
import ar.com.historiasocial.entities.Diagnostico;
import ar.com.historiasocial.entities.Domicilio;
import ar.com.historiasocial.entities.Especialidad;
import ar.com.historiasocial.entities.HistoriaClinica;
import ar.com.historiasocial.entities.HistoriaSocial;
import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.InstitutionResponsible;
import ar.com.historiasocial.entities.InstitutionType;
import ar.com.historiasocial.entities.NoExisteTratamientoActivoException;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Practica;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.Servicio;
import ar.com.historiasocial.entities.TipoDePractica;
import ar.com.historiasocial.entities.TipoDeProblematica;
import ar.com.historiasocial.entities.Vivienda;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 */
public class TratamientoTest extends ApplicationTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		clearDatabase();
		init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.clearDatabase();
	}
	
	@Test
	@Rollback(true)
	//FIXME: sub-dividir en varios test.
	public void testTratamiento() throws Exception {
		
		List<Practica> practicasAll = practicaDAO.retrieveAll();
		
		assertThat(practicasAll.size(), equalTo(3));
		
		Paciente paciente = pacienteDAO.retrieveById(2L);
		HistoriaSocial historiaSocial = paciente.getHistoriaSocial();
		
		assertThat(historiaSocial.getTratamientoActual(), notNullValue());
		
		historiaSocial.registrarAltaPaciente();
		assertThat(historiaSocial.getTratamientoActual(), nullValue());
		assertThat(historiaSocial.getTratamientosHistoricos().size(), equalTo(1));
		
		historiaSocial.registrarIngresoPaciente("ingreso de prueba", new Diagnostico("un diagnostico"));
		assertThat(historiaSocial.getTratamientoActual(), notNullValue());
		
		Practica practica = createPractica(createTipoPractica(), createTipoProblematica());

		historiaSocial.agregarPractica(practica);
		assertThat(historiaSocial.getTratamientoActual().getPracticas().size(), equalTo(1));
		
		Practica practica2 = createPractica(createTipoPractica(), createTipoProblematica());
		Practica practica3 = createPractica(createTipoPractica(), createTipoProblematica());
		historiaSocial.agregarPractica(practica2);
		historiaSocial.agregarPractica(practica3);
		assertThat(historiaSocial.getTratamientoActual().getPracticas().size(), equalTo(3));
		
		
		historiaSocial.registrarAltaPaciente();
		assertThat(historiaSocial.getTratamientoActual(), nullValue());
		assertThat(historiaSocial.getTratamientosHistoricos().size(), equalTo(2));
		assertThat(historiaSocial.getTratamientosHistoricos().get(1).getPracticas().size(), equalTo(3));
		
		
	}
	

	/**
	 * Inicializa la base de datos HSQL para las pruebas
	 * 
	 * @throws NoExisteTratamientoActivoException 
	 */
	private void init() throws NoExisteTratamientoActivoException {
		
		Domicilio d = this.createDomicilio();
		Domicilio d1 = this.createDomicilio();
		Domicilio d2 = this.createDomicilio();

		Vivienda v = this.createVivienda();
		Vivienda v1 = this.createVivienda();
		Vivienda v2 = this.createVivienda();

		HistoriaClinica hc = this.createHisClinica();
		HistoriaClinica hc1 = this.createHisClinica();
		HistoriaClinica hc2 = this.createHisClinica();

		Paciente p = this.createPaciente(1L, d, v, hc);
		Paciente p2 = this.createPaciente(2L, d1, v1, hc1);
		Conviviente c1 = this.createConviviente();
		Paciente p3 = this.createPacienteConConviviente(3L, d2, v2, hc2, c1);

		Especialidad es = this.createEspecialidad();
		Especialidad es1 = this.createEspecialidad();
		Especialidad es2 = this.createEspecialidad();

		InstitutionType typeInst = this.createTypeInstitucion();
		InstitutionResponsible responsible = this.createInstResp();
		
		Institucion i = this.createInstitucion(typeInst, responsible);
		Institucion i2 = this.createInstitucion(typeInst, responsible);
		Institucion i3 = this.createInstitucion(typeInst, responsible);

		this.createPersona();

		TipoDePractica tpra = this.createTipoPractica();
		TipoDePractica tpra2 = this.createTipoPractica();
		TipoDePractica tpra3 = this.createTipoPractica();

		TipoDeProblematica tpro = this.createTipoProblematica();
		TipoDeProblematica tpro2 = this.createTipoProblematica();
		TipoDeProblematica tpro3 = this.createTipoProblematica();

		Servicio s = this.createServicio();
		Servicio s2 = this.createServicio();
		Servicio s3 = this.createServicio();


		Practica pra = this.createPractica(tpra, tpro);
		Practica pra2 = this.createPractica(tpra2, tpro2);
		Practica pra3 = this.createPractica(tpra3, tpro3);
		
		Profesional prof = this.createProfecional(es, s, pra);
		Profesional prof2 = this.createProfecional(es1, s2, pra2);
		Profesional prof3 = this.createProfecional(es2, s3, pra3);

		HistoriaSocial hs1 = this.createHistoriaSocial(p, i, pra, prof);
		HistoriaSocial hs2 = this.createHistoriaSocial(p2, i2, pra2, prof2);
		HistoriaSocial hs3 = this.createHistoriaSocial(p3, i3, pra3, prof3);
		
		practicaDAO.saveOrUpdate(pra);
		practicaDAO.saveOrUpdate(pra2);
		practicaDAO.saveOrUpdate(pra3);
		
		assertThat(hs1, notNullValue());
		assertThat(hs2, notNullValue());
		assertThat(hs3, notNullValue());
		
		assertThat(hs1.getPaciente(), equalTo(p));
		assertThat(hs2.getPaciente(), equalTo(p2));
	}

}
