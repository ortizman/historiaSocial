package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.entities.InstitutionResponsible;
import ar.com.historiasocial.entities.Paginador;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
@InterceptorRefs({
    @InterceptorRef("userStack")
})
public class ListResponsableInstitucionAction extends ListJQGridAction {

	private static final long					serialVersionUID		= 8257214904282934689L;
	private String								pageTitle				= "Responsables de Instituciones";
	private List<InstitutionResponsible>		institutionResponsibles	= new ArrayList<InstitutionResponsible>();

	private GenericDAO<InstitutionResponsible>	responsableDAO;

	@Override
	@Actions({ @Action(value = "/datosTablaResponsableInstituciones", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^institutionResponsibles\\[\\d+\\]\\.\\w*" }) }) })
	public String execute() throws HSControllerException{
		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<InstitutionResponsible> resp;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			resp = getResponsableDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			resp = getResponsableDAO().retrievePaged(paginador);
		}

		this.setInstitutionResponsibles(resp);
		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}

	public String getPageTitle(){
		return pageTitle;
	}

	public void setPageTitle(String pageTitle){
		this.pageTitle = pageTitle;
	}

	public List<InstitutionResponsible> getInstitutionResponsibles(){
		return institutionResponsibles;
	}

	public void setInstitutionResponsibles(List<InstitutionResponsible> institutionResponsibles){
		this.institutionResponsibles = institutionResponsibles;
	}

	/**
	 * @return the responsableDAO
	 */
	public GenericDAO<InstitutionResponsible> getResponsableDAO(){
		return responsableDAO;
	}

	/**
	 * @param responsableDAO
	 *            the responsableDAO to set
	 */
	public void setResponsableDAO(GenericDAO<InstitutionResponsible> responsableDAO){
		this.responsableDAO = responsableDAO;
	}

}
