package ar.com.historiasocial.actions.interceptors;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 */
public class JQGridSearchInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -101083407678556896L;
	private static final Logger	LOGGER	= Logger.getLogger(JQGridSearchInterceptor.class);
	
	/**
	 * Constructor por defecto
	 */
	public JQGridSearchInterceptor() {
		
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy(){
		LOGGER.debug(" \n **** Destruyendo el interceptor de busquedas para las Grillas **** \n");
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init(){
		LOGGER.debug(" \n **** Inicializando el interceptor de busquedas para las Grillas **** \n");
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception{
			
		Object search = invocation.getInvocationContext().getParameters().get("_search");
		if(search != null){	
			String[] a = (String[]) search;
			Boolean isSearch = Boolean.valueOf(a[0]);
			if(isSearch){
				String ss = ((String[])invocation.getInvocationContext().getParameters().get("searchString"))[0];
				String searchOper = ((String[])invocation.getInvocationContext().getParameters().get("searchOper"))[0];
				
				if("cn".equals(searchOper)){ //filtro tipo "contiene", sin distinguir mayusculas
					invocation.getInvocationContext().getParameters().put("searchOper", new String[]{"LIKE"});
					invocation.getInvocationContext().getParameters().put("searchString", new String[]{"%"+ss+"%"});
				}
			    else if("bw".equals(searchOper)){ //filtro tipo "comienza con", sin distinguir mayusculas
			    	invocation.getInvocationContext().getParameters().put("searchOper", new String[]{"LIKE"});
			    	invocation.getInvocationContext().getParameters().put("searchString", new String[]{ss+"%"});
			    }
			
			}
		}
		
		return invocation.invoke();
	}

}
