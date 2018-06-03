package ar.com.historiasocial.actions.interceptors;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * El objetivo de este interceptor es verificar que el usuario logueado sea
 * director. En caso que no los sea, corta la cadena de ejecucion y devulve a
 * una pantalla de 'acceso denegado'. Este interceptor factoriza el codigo que
 * tienen los actions para verficar que el usuario este logueado
 * 
 * @author Manuel Ortiz - ortizman@gmail.com
 */
public class SecurityDirectorAccessInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -101083407678556896L;
	private static final Logger	LOGGER				= Logger.getLogger(SecurityDirectorAccessInterceptor.class);

	/**
	 * Constructor por defecto
	 */
	public SecurityDirectorAccessInterceptor() {

	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy(){
		LOGGER.debug(" \n **** Destruyendo el interceptor de seguridad por director **** \n");
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init(){
		LOGGER.debug(" \n **** Inicializando el interceptor de seguridad por director**** \n");
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception{
		Object esDir = invocation.getInvocationContext().getSession().get("esDirector");

		boolean access = esDir != null && ((Boolean) esDir);

		if (access) {
			return invocation.invoke();
		} else {
			LOGGER.warn("\n **************************** Acceso Denegado ***************************** \n Usuario: " + invocation.getInvocationContext().getSession().get("user"));
			return "accessdenied";
		}

	}

}
