package ar.com.test.historiasocial.actions;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.historiasocial.actions.ListCondicionHabitacionalAction;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 *         Clase abstracta para testear los actions de Struts que listan las
 *         entidades del sistema.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ar/com/historiasocial/beans-jpa-test.xml", "classpath:ar/com/historiasocial/spring/spring-ctx-action.xml" })
public abstract class ListEntityActionTest extends StrutsSpringJUnit4TestCase<ListCondicionHabitacionalAction> {

	/**
	 * Constructor por defecto
	 */
	public ListEntityActionTest() {
		super();
	}

	/**
	 * Test method for
	 * {@link ar.com.historiasocial.actions.ListCondicionHabitacionalAction#execute()}
	 * .
	 * 
	 * @throws ServletException
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testExecute() throws Exception{

		request.setParameter("idPaciente", "1");
		HttpSession session = request.getSession(true);
		session.setAttribute("user", "fede");
		session.setAttribute("esDirector", Boolean.TRUE);
		String expected = getExpected();

		String result = executeAction();

		JSONAssert.assertEquals(expected, result, false);
	}

	/**
	 * @return el resultado obtenido luego de ejecutar la accion
	 * @throws ServletException
	 * @throws UnsupportedEncodingException
	 */
	protected abstract String executeAction() throws ServletException, UnsupportedEncodingException;
	
	/**
	 * @return El Resultado esperado después de la ejecución del action
	 */
	protected abstract String getExpected();

}
