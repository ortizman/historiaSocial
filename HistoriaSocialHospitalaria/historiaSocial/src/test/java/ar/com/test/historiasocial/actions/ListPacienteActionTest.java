package ar.com.test.historiasocial.actions;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ar/com/historiasocial/beans-jpa-test.xml", "classpath:ar/com/historiasocial/spring/spring-ctx-action.xml" })
public class ListPacienteActionTest extends ListEntityActionTest {

	@Override
	protected String getExpected(){
		return "{\"pacientes\":[{\"apellidos\":\"Fernandez\",\"documento\":\"93939339\",\"domicilio\":{\"id\":1},\"fechaInicioServSocial\":null,\"fechaNacimiento\":\"1985-04-13T00:00:00\",\"historiaSocial\":{\"id\":4,\"tratamientoAmbulatorio\":{\"fechaIngreso\":\"2014-02-05T00:00:00\"}},\"id\":1,\"nombres\":\"Juan\",\"vivienda\":{\"id\":1}},{\"apellidos\":\"Gomez\",\"documento\":\"45673410\",\"domicilio\":{\"id\":3},\"fechaInicioServSocial\":null,\"fechaNacimiento\":\"1987-10-07T00:00:00\",\"historiaSocial\":{\"id\":6,\"tratamientoAmbulatorio\":{\"fechaIngreso\":\"2014-02-05T00:00:00\"}},\"id\":3,\"nombres\":\"Maria\",\"vivienda\":{\"id\":3}},{\"apellidos\":\"Gutierrez\",\"documento\":\"30980789\",\"domicilio\":{\"id\":2},\"fechaInicioServSocial\":null,\"fechaNacimiento\":\"1984-07-21T00:00:00\",\"historiaSocial\":{\"id\":5,\"tratamientoAmbulatorio\":{\"fechaIngreso\":\"2014-02-05T00:00:00\"}},\"id\":2,\"nombres\":\"Martin\",\"vivienda\":{\"id\":2}}]}";
	}

	/**
	 * @return retorna el resultado de la ejecucion de un action
	 * @throws ServletException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	protected String executeAction() throws ServletException, UnsupportedEncodingException{
		return executeAction("/datosTablaPacientes.action");
	}

}
