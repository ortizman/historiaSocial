/**
 * 
 */
package ar.com.test.historiasocial;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.Paciente;


/**
 * @author Manuel Ortiz
 *
 */
@Transactional
public class PacienteTest extends ApplicationTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
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
	public void testAgregarPaciente() {
		
		Paciente paciente = createPacienteCompleto();
		
		//Al momento de crear, ya tiene una historia social abierta.
		assertThat("El paciente se debe crear con una hitoria social vacia", paciente.getHistoriaSocial(), is(notNullValue()));
		
		//No debe tener tratamientos abiertos, hasta que no se realice un ingreso
		assertThat(paciente.getHistoriaSocial().getTratamientoActual(), is(nullValue()));
		
		//No tiene tratamientos historicos
		assertThat(paciente.getHistoriaSocial().getTratamientosHistoricos().size(), equalTo(0));
		
		//No tiene practicas asociadas.
		Assert.assertEquals("Un paciente recien creado, no tiene instituciones", 0, paciente.getHistoriaSocial().getInstituciones().size());
	}

	
	@Test
	@Rollback(true)
	public void testEliminarPaciente() throws Exception{
		Paciente pacienteCompleto = createPacienteCompleto();
		
		pacienteDAO.delete(pacienteCompleto.getId());
		
		Paciente pacienteAux = pacienteDAO.retrieveById(pacienteCompleto.getId());
		
		assertThat("El paciente leido de la base debe ser nulo", pacienteAux, is(nullValue()));
		
	}
	
	@Test
	@Rollback(true)
	public void testModificarPaciente() throws Exception{
		Paciente pacienteCompleto = createPacienteCompleto();
		Institucion institucion = createInstitucion(null, createInstResp());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy", new Locale("es", "ES"));
		
		long id = pacienteCompleto.getId();
		String nombres = "Pepe Lui";
		String apellidos = "Dividivo";
		String celular = "011191817161";
		String telefono = "02218818187";
		Date fechaNacimiento = new Date();
		String beneficionPlanesSubsidios = "No tiene beneficios";
		String documento = "30987456";
		
		pacienteCompleto.setNombres(nombres);
		pacienteCompleto.setApellidos(apellidos);
		pacienteCompleto.setCelular(celular);
		pacienteCompleto.setTelefono(telefono);
		pacienteCompleto.setFechaNacimiento(fechaNacimiento);
		pacienteCompleto.setDocumento(documento);
		pacienteCompleto.setBeneficionPlanesSubsidios(beneficionPlanesSubsidios);
		pacienteCompleto.setCentroSaludReferencia(institucion);
		
		pacienteDAO.saveOrUpdate(pacienteCompleto);
		
		Paciente pacienteAux = pacienteDAO.retrieveById(id);
		
		assertThat(pacienteAux.getNombres(), equalTo(nombres));
		assertThat(pacienteAux.getApellidos(), equalTo(apellidos));
		assertThat(pacienteAux.getCelular(), equalTo(celular));
		assertThat(pacienteAux.getTelefono(), equalTo(telefono));
		assertThat(simpleDateFormat.format(pacienteAux.getFechaNacimiento()), equalTo(simpleDateFormat.format(fechaNacimiento)));
		assertThat(pacienteAux.getBeneficionPlanesSubsidios(), equalTo(beneficionPlanesSubsidios));
		assertThat(pacienteAux.getDocumento(), equalTo(documento));
		assertThat(pacienteAux.getCentroSaludReferencia(), equalTo(institucion));
		
	}
	
}
