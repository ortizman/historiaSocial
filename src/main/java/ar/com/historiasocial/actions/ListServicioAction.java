package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Servicio;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
public class ListServicioAction extends ListJQGridAction {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	private List<Servicio>			servicios			= new ArrayList<Servicio>();

	private GenericDAO<Servicio>	servicioDAO;

	@Override
	@Actions({ @Action(value = "/datosTablaServicio", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^servicios\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){
		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());

		List<Servicio> servicios;

		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			servicios = getServicioDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			servicios = getServicioDAO().retrievePaged(paginador);
		}

		this.setServicios(servicios);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}

	public List<Servicio> getServicios(){
		return servicios;
	}

	public void setServicios(List<Servicio> servicios){
		this.servicios = servicios;
	}

	/**
	 * @return the servicioDAO
	 */
	public GenericDAO<Servicio> getServicioDAO(){
		return servicioDAO;
	}

	/**
	 * @param servicioDAO
	 *            the servicioDAO to set
	 */
	public void setServicioDAO(GenericDAO<Servicio> servicioDAO){
		this.servicioDAO = servicioDAO;
	}
}
