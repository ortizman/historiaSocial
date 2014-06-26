package ar.com.historiasocial.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CerrarSesionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public String execute(){
		((org.apache.struts2.dispatcher.SessionMap<String, Object>) ActionContext.getContext().getSession()).invalidate();
		return SUCCESS;
	}
}
