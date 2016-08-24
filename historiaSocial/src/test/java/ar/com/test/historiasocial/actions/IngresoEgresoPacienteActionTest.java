package ar.com.test.historiasocial.actions;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.historiasocial.actions.IngresoEgresoPacienteAction;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ar/com/historiasocial/beans-jpa-test.xml", "classpath:ar/com/historiasocial/spring/spring-ctx-action.xml" })
public class IngresoEgresoPacienteActionTest extends StrutsSpringJUnit4TestCase<IngresoEgresoPacienteAction> {

	@Override
	@Before
	public void setUp() throws Exception{
		super.setUp();
	}

	@Override
	@After
	public void tearDown() throws Exception{
		super.tearDown();
	}

	@Test
	public void testRegistrarIngreso() throws Exception  {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link ar.com.historiasocial.actions.IngresoEgresoPacienteAction#editarIngreso()}
	 * .
	 */
	@Test
	public void testEditarIngreso(){
//		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link ar.com.historiasocial.actions.IngresoEgresoPacienteAction#saveIngreso()}
	 * .
	 */
	@Test
	public void testSaveIngreso(){
//		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link ar.com.historiasocial.actions.IngresoEgresoPacienteAction#registrarAlta()}
	 * .
	 * @throws ServletException 
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testRegistrarAlta() throws UnsupportedEncodingException, ServletException{
		request.setParameter("idPaciente", "1");
		ActionProxy proxy = getActionProxy("/registrarAlta.action");  
        try{  
            String result = proxy.getInvocation().invoke();  
            Assert.assertEquals("La accion no termino correctamente", ActionSupport.SUCCESS, result);
        } catch(Exception e){  
            e.printStackTrace();  
        } 
	}

	/**
	 * Test method for
	 * {@link ar.com.historiasocial.actions.IngresoEgresoPacienteAction#saveAlta()}
	 * .
	 * @throws ServletException 
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testSaveAlta() throws UnsupportedEncodingException, ServletException{
		
//		fail("Not yet implemented");
	}

}