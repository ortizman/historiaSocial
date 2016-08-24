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
public class ListEspecialidadActionTest extends ListEntityActionTest {

	@Override
	protected String getExpected(){
		return "{\"especialidades\":[{\"descripcion\":\"Clinico\",\"id\":1},{\"descripcion\":\"Endocrinologo\",\"id\":2},{\"descripcion\":\"Pediatria\",\"id\":3}]}";
	}

	/**
	 * @return retorna el resultado de la ejecucion de un action
	 * @throws ServletException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	protected String executeAction() throws ServletException, UnsupportedEncodingException{
		return executeAction("/datosTablaEspecialidades.action");
	}

}
