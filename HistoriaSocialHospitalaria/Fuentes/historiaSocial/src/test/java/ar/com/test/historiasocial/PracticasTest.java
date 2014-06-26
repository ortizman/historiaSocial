/**
 * 
 */
package ar.com.test.historiasocial;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.com.historiasocial.entities.Practica;
import ar.com.historiasocial.entities.Profesional;
import ar.com.historiasocial.entities.TipoDePractica;
import ar.com.historiasocial.entities.TipoDeProblematica;

/**
 * @author Manuel Ortiz
 *
 */
@Transactional
public class PracticasTest extends ApplicationTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		init();

	}

	/**
	 * 
	 */
	@Rollback(true)
	private void init() {
		TipoDePractica tpra = this.createTipoPractica();
		TipoDePractica tpra2 = this.createTipoPractica();
		TipoDePractica tpra3 = this.createTipoPractica();

		TipoDeProblematica tpro = this.createTipoProblematica();
		TipoDeProblematica tpro2 = this.createTipoProblematica();
		TipoDeProblematica tpro3 = this.createTipoProblematica();
		
		Practica pra = this.createPractica(tpra, tpro);
		Practica pra2 = this.createPractica(tpra2, tpro2);
		Practica pra3 = this.createPractica(tpra3, tpro3);
		
		practicaDAO.saveOrUpdate(pra);
		practicaDAO.saveOrUpdate(pra2);
		practicaDAO.saveOrUpdate(pra3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.clearDatabase();
	}

	@Test
	public void testPracticas() throws Exception {
		
		List<Practica> practicasAll = practicaDAO.retrieveAll();
		assertThat(practicasAll.size(), equalTo(3));
	}
	
	@Test
	public void testModificarPractica() throws Exception{
		Practica pra = createPracticaCompleta();
		Profesional profesional = createProfecional(createEspecialidad(), createServicio(), null);
		Date fechaCarga = new Date();
		String detalle = "Modifico el detalle";
		
		pra.setDetalle(detalle);
		pra.setProfesional(profesional);
		pra.setFechaCarga(fechaCarga);

		pra = practicaDAO.saveOrUpdate(pra);
		
		assertThat(pra.getDetalle(), equalTo(detalle));
		assertThat(pra.getProfesional(), equalTo(profesional));
		assertThat(pra.getFechaCarga(), equalTo(fechaCarga));
	}

	/**
	 * @return
	 */
	private Practica createPracticaCompleta(){
		TipoDePractica tpra = this.createTipoPractica();
		TipoDeProblematica tpro = this.createTipoProblematica();
		Practica pra = this.createPractica(tpra, tpro);
		return pra;
	}
	
	@Test
	public void testEliminarPractica() throws Exception{
		Practica practicaCompleta = createPracticaCompleta();
		
		long id = practicaCompleta.getId();
		
		practicaDAO.delete(id);
		
		Practica prac = practicaDAO.retrieveById(id);
		
		assertThat(prac, is(nullValue()));
		
		
	}
	
	@Test
	public void testAsignarPracticaAPaciente() throws Exception{
		
	}
	
	@Test
	public void testRecuperarPracticasPorPaciente() throws Exception{
		
	}
	
	@Test
	public void testRecuperarPracticasAmbulantes() throws Exception{
		
	}
	
	@Test
	public void testRecuperarPracticasInternacion() throws Exception{
		
	}

}
