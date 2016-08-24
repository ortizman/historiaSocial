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
import ar.com.historiasocial.entities.InstitutionType;

@ParentPackage(value = "default")
@InterceptorRefs({
    @InterceptorRef("userStack")
})
public class ListTipoInstitucionAction extends ListJQGridAction {

	private static final long			serialVersionUID	= 8257214904282934689L;
	private String						pageTitle			= "Tipos de Instituciones";
	private List<InstitutionType>		institutionTypes	= new ArrayList<InstitutionType>();

	private GenericDAO<InstitutionType>	institutionTypeDAO;

	@Override
	@Actions({ @Action(value = "/datosTablaTipoInstituciones", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false",  "includeProperties",
			"page, total, record, sord, sidx, rows, ^institutionTypes\\[\\d+\\]\\.\\w*" }) }) })
	public String execute() throws HSControllerException{

		this.setInstitutionTypes(getInstitutionTypeDAO().retrieveAll());
		return SUCCESS; 
	}

	public String getPageTitle(){
		return pageTitle;
	}

	public void setPageTitle(String pageTitle){
		this.pageTitle = pageTitle;
	}

	public List<InstitutionType> getInstitutionTypes(){
		return institutionTypes;
	}

	public void setInstitutionTypes(List<InstitutionType> institutionTypes){
		this.institutionTypes = institutionTypes;
	}

	/**
	 * @return the institutionTypeDAO
	 */
	public GenericDAO<InstitutionType> getInstitutionTypeDAO(){
		return institutionTypeDAO;
	}

	/**
	 * @param institutionTypeDAO
	 *            the institutionTypeDAO to set
	 */
	public void setInstitutionTypeDAO(GenericDAO<InstitutionType> institutionTypeDAO){
		this.institutionTypeDAO = institutionTypeDAO;
	}

}
