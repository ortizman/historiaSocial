/**
 * @author Manuel Ortiz
 */
package ar.com.historiasocial.util;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Paciente;
import ar.com.test.historiasocial.ApplicationTestCase;

/**
 * @author Manuel Ortiz
 */
@Ignore
public class UseCacheTest extends ApplicationTestCase {


	@Autowired
	PacienteDAO pacienteDAO;

	Long pacienteId;

	@Before
	public void setUp() {
			Paciente paciente = createPacienteCompleto();

			pacienteId = paciente.getId();
			assertNotNull(
					"El autowire del DAO del paciente no esta funcionando",
					pacienteDAO);
	}

	@Transactional
	@Test
	public void testGet1() throws Exception {
		System.out.println("\n **************** \n retrieve 1 \n***********");
		long inicio = System.nanoTime();
		assertNotNull(pacienteDAO.retrieveById(pacienteId));
		long fin = System.nanoTime() - inicio;
		System.out.println("El tiempo del primero get: " + fin / 1000 / 1000 + "ms" );
		System.out.println("\n **************** \n fin retrieve 1 \n***********");
		
		Thread.sleep(500);
		
		System.out.println("\n **************** \n retrieve 1 \n***********");
		inicio = System.nanoTime();
		assertNotNull(pacienteDAO.retrieveById(pacienteId));
		fin = System.nanoTime() - inicio;
		System.out.println("El tiempo del primero get: " + fin / 1000 / 1000 + "ms" );
		
		System.out.println("\n **************** \n fin retrieve 1 \n***********");

		Thread.sleep(500);
		
		System.out.println("\n **************** \n retrieve 1 \n***********");
		inicio = System.nanoTime();
		assertNotNull(pacienteDAO.retrieveById(pacienteId));
		fin = System.nanoTime() - inicio;
		System.out.println("El tiempo del primero get: " + fin / 1000 / 1000 + "ms" );
		System.out.println("\n **************** \n fin retrieve 1 \n***********");

	}
	


}
