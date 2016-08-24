package ar.com.historiasocial.actions.interceptors;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * El objetivo de este interceptor es verificar que el usuario este logueado. En
 * caso de que no este logueado, corta la cadena de ejecucion y devulve a la
 * pantalla de login. Este interceptor factoriza el codigo que tienen los
 * actions para verficar que el usuario este logueado
 * 
 * @author Manuel Ortiz - ortizman@gmail.com
 */
public class SecurityUserAccessInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -101083407678556896L;
	private static final Logger	LOGGER				= Logger.getLogger(SecurityUserAccessInterceptor.class);

	/**
	 * Constructor por defecto
	 */
	public SecurityUserAccessInterceptor() {

	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy(){
		LOGGER.debug(" \n **** Destruyendo el interceptor de seguridad **** \n");
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init(){
		LOGGER.debug(" \n **** Inicializando el interceptor de seguridad **** \n");
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception{
		if (invocation.getInvocationContext().getSession().get("user") != null){
			return invocation.invoke();
		} else {
			LOGGER.warn("\n **************************** Se redirecciona a la pagina de login ***************************** \n");
			return "login";
		}
		
		
	}

}
