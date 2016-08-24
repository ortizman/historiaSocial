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
public class ListObraSocialActionTest extends ListEntityActionTest {

	@Override
	protected String getExpected(){
		return "{\"obrasSociales\":[{\"codigoObraSocial\":\"OSDE\",\"id\":1,\"nombreObraSocial\":\"Organizacion de servicios directos empresarios\",\"plan\":\"210\"},{\"codigoObraSocial\":\"OSDE\",\"id\":2,\"nombreObraSocial\":\"Organizacion de servicios directos empresarios\",\"plan\":\"310\"},{\"codigoObraSocial\":\"OSDE\",\"id\":3,\"nombreObraSocial\":\"Organizacion de servicios directos empresarios\",\"plan\":\"410\"},{\"codigoObraSocial\":\"OSDE\",\"id\":4,\"nombreObraSocial\":\"Organizacion de servicios directos empresarios\",\"plan\":\"510\"},{\"codigoObraSocial\":\"Galeno\",\"id\":5,\"nombreObraSocial\":\"Galeno\",\"plan\":\"210\"},{\"codigoObraSocial\":\"IOMA\",\"id\":6,\"nombreObraSocial\":\"IOMA\",\"plan\":\"\"}]}";
	}
	
	/**
	 * @return retorna el resultado de la ejecucion de un action
	 * @throws ServletException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	protected String executeAction() throws ServletException, UnsupportedEncodingException{
		return executeAction("/datosTablaObrasSociales.action");
	}

}
