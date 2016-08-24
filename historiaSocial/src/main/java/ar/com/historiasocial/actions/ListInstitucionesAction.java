/*
 * 
 */
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
import ar.com.historiasocial.dao.InstitucionDAO;
import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.InstitutionResponsible;
import ar.com.historiasocial.entities.InstitutionType;
import ar.com.historiasocial.entities.Paginador;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 * 
 */
@ParentPackage(value = "default")
@InterceptorRefs({ @InterceptorRef("userStack") })
public class ListInstitucionesAction extends ListJQGridAction {

	private static final long					serialVersionUID	= 1L;

	private List<Institucion>					instituciones		= new ArrayList<Institucion>();
	private int									cantPags;
	private Integer								pagActual;

	List<InstitutionType>						tiposInstituciones;

	private InstitucionDAO						institucionDAO;
	private GenericDAO<InstitutionType>			tipoInstitucionDAO;
	private GenericDAO<InstitutionResponsible>	responsableDAO;

	private List<InstitutionResponsible>		reponsablesInstituciones;

	public List<Institucion> getInstituciones(){
		return instituciones;
	}

	public void setInstituciones(List<Institucion> instituciones){
		this.instituciones = instituciones;
	}

	/**
	 * 
	 * 
	 * @param cantPags
	 */
	public void setCantPags(int cantPags){
		this.cantPags = cantPags;
	}

	public int getCantPags(){
		return cantPags;
	}

	public Integer getPagActual(){
		return pagActual;
	}

	public void setPagActual(Integer pagActual){
		this.pagActual = pagActual;
	}

	@Override
	@Actions({ @Action(value = "/datosTablaInstituciones", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false", 
			"includeProperties",
			"page, total, record, sord, sidx, rows, ^instituciones\\[\\d+\\]\\.\\w* , ^instituciones\\[\\d+\\]\\.nombre, ^instituciones\\[\\d+\\]\\.type\\.name, ^instituciones\\[\\d+\\]\\.responsible\\.nombreCompleto, ^instituciones\\[\\d+\\]\\.detail, ^instituciones\\[\\d+\\]\\.email, ^instituciones\\[\\d+\\]\\.telefono, ^instituciones\\[\\d+\\]\\.location\\.city" }) }) })
	public String execute(){

		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}

		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<Institucion> ins;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			ins = institucionDAO.retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			ins = institucionDAO.retrievePaged(paginador);
		}

		this.setInstituciones(ins);
		this.setTotal(paginador.getCantidadDePaginas());
		this.setCantPags(paginador.getCantidadDePaginas());

		return SUCCESS;

	}

	@Actions({ @Action(value = "/fillSelectTypeInst", results = { @Result(name = "success", location = "/pages/institucion/fillSelectTypeInst.jsp") }) })
	public String fillSelectTypeInst(){

		tiposInstituciones = getTipoInstitucionDAO().retrieveAll();

		return SUCCESS;
	}

	@Actions({ @Action(value = "/fillSelectResp", results = { @Result(name = "success", location = "/pages/institucion/fillSelectResp.jsp") }) })
	public String fillSelectResp(){

		setResponsablesInstituciones(getResponsableDAO().retrieveAll());
		return SUCCESS;
	}

	/**
	 * @return the tiposInstituciones
	 */
	public List<InstitutionType> getTiposInstituciones(){
		return tiposInstituciones;
	}

	/**
	 * @param tiposInstituciones
	 *            the tiposInstituciones to set
	 */
	public void setTiposInstituciones(List<InstitutionType> tiposInstituciones){
		this.tiposInstituciones = tiposInstituciones;
	}

	public List<InstitutionResponsible> getResponsablesInstituciones(){
		return reponsablesInstituciones;
	}

	public void setResponsablesInstituciones(List<InstitutionResponsible> reponsablesInstituciones){
		this.reponsablesInstituciones = reponsablesInstituciones;
	}

	public InstitucionDAO getInstitucionDAO(){
		return institucionDAO;
	}

	public void setInstitucionDAO(InstitucionDAO institucionDAO){
		this.institucionDAO = institucionDAO;
	}

	public List<InstitutionResponsible> getReponsablesInstituciones(){
		return reponsablesInstituciones;
	}

	public void setReponsablesInstituciones(List<InstitutionResponsible> reponsablesInstituciones){
		this.reponsablesInstituciones = reponsablesInstituciones;
	}

	/**
	 * @return the tipoInstitucionDAO
	 */
	public GenericDAO<InstitutionType> getTipoInstitucionDAO(){
		return tipoInstitucionDAO;
	}

	/**
	 * @param tipoInstitucionDAO
	 *            the tipoInstitucionDAO to set
	 */
	public void setTipoInstitucionDAO(GenericDAO<InstitutionType> tipoInstitucionDAO){
		this.tipoInstitucionDAO = tipoInstitucionDAO;
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
