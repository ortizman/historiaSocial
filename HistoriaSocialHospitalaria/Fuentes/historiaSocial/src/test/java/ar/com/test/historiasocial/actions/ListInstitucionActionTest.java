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
public class ListInstitucionActionTest extends ListEntityActionTest {

	@Override
	protected String getExpected(){
		return "{\"instituciones\":[{\"calleX\":null,\"calleY\":null,\"detail\":\"El espaniol\",\"email\":\"espaniol at hospital.com\",\"id\":1,\"location\":{\"city\":\"La Plata\"},\"nombre\":\"Hospital Espaniol\",\"responsible\":{\"nombreCompleto\":\"Fontana, Ricardo\"},\"telefono\":null,\"type\":{\"name\":\"Tipo por defecto\"}},{\"calleX\":null,\"calleY\":null,\"detail\":\"El Italiano\",\"email\":\"italiano at hospital.com\",\"id\":2,\"location\":{\"city\":\"La Plata\"},\"nombre\":\"Hospital Italiano\",\"responsible\":{\"nombreCompleto\":\"Fontana, Ricardo\"},\"telefono\":null,\"type\":{\"name\":\"Tipo por defecto\"}},{\"calleX\":null,\"calleY\":null,\"detail\":\"El San Martin\",\"email\":\"sanmartin at hospital.com\",\"id\":3,\"location\":{\"city\":\"La Plata\"},\"nombre\":\"Hospital San Martin\",\"responsible\":{\"nombreCompleto\":\"Fontana, Ricardo\"},\"telefono\":null,\"type\":{\"name\":\"Tipo por defecto\"}}]}";
	}

	/**
	 * @return retorna el resultado de la ejecucion de un action
	 * @throws ServletException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	protected String executeAction() throws ServletException, UnsupportedEncodingException{
		return executeAction("/datosTablaInstituciones.action");
	}

}
