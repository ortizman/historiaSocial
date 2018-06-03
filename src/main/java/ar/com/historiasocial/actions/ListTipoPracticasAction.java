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
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.TipoDePractica;

@ParentPackage(value = "default")
@InterceptorRefs({ @InterceptorRef("userStack") })
public class ListTipoPracticasAction extends ListJQGridAction {

	
	
//	private Integer	rows	= 0;
//	private Integer	page	= 0;
//	// Your Total Pages
//	private Integer	total	= 0;
//	// All Record
//	private Integer	record	= 0;
//	
//	private String	sord;
//	private String	sidx;
	
	
	private static final long			serialVersionUID	= 1L;
	private List<TipoDePractica>		tipoPracticas		= new ArrayList<TipoDePractica>();
	private int							cantPags;
	private Integer						pagActual;
	private GenericDAO<TipoDePractica>	tipoDePracticaDAO;

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

	public List<TipoDePractica> getTipoPracticas(){
		return tipoPracticas;
	}

	public void setTipoPracticas(List<TipoDePractica> tipoPracticas){
		this.tipoPracticas = tipoPracticas;
	}

	@Override
	@Actions({ @Action(value = "/datosTablaTipoPracticas", results = { @Result(name = "success", type = "json", params = {"ignoreHierarchy", "false", "includeProperties",
			"page, total, record, sord, sidx, rows, ^tipoPracticas\\[\\d+\\]\\.\\w*" }) }) })
	public String execute(){
		if (this.getRows() == 0) {
			this.setRows(Paginador.CANT_PAGE_DEFAULT);
		}
		Paginador paginador = new Paginador(this.getRows(), this.getPage());
		List<TipoDePractica> ps = null;
		if ((this.getSidx() != null && !this.getSidx().isEmpty()) && (this.getSord() != null) && !this.getSord().isEmpty()) {
			ps = getTipoDePracticaDAO().retrievePaged(paginador, this.getSidx(), this.getSord());
		} else {
			ps = getTipoDePracticaDAO().retrievePaged(paginador);
		}
		this.setTipoPracticas(ps);

		this.setTotal(paginador.getCantidadDePaginas());

		return SUCCESS;
	}
	

	public static long getSerialversionuid(){
		return serialVersionUID;
	}

	/**
	 * @return the tipoDePracticaDAO
	 */
	public GenericDAO<TipoDePractica> getTipoDePracticaDAO(){
		return tipoDePracticaDAO;
	}

	/**
	 * @param tipoDePracticaDAO
	 *            the tipoDePracticaDAO to set
	 */
	public void setTipoDePracticaDAO(GenericDAO<TipoDePractica> tipoDePracticaDAO){
		this.tipoDePracticaDAO = tipoDePracticaDAO;
	}

//	public Integer getRows() {
//		return rows;
//	}
//
//	public void setRows(Integer rows) {
//		this.rows = rows;
//	}

//	public Integer getPage() {
//		return page;
//	}
//
//	public void setPage(Integer page) {
//		this.page = page;
//	}
//
//	public Integer getTotal() {
//		return total;
//	}
//
//	public void setTotal(Integer total) {
//		this.total = total;
//	}
//
//	public Integer getRecord() {
//		return record;
//	}
//
//	public void setRecord(Integer record) {
//		this.record = record;
//	}
//
//	public String getSord() {
//		return sord;
//	}
//
//	public void setSord(String sord) {
//		this.sord = sord;
//	}
//
//	public String getSidx() {
//		return sidx;
//	}
//
//	public void setSidx(String sidx) {
//		this.sidx = sidx;
//	}

}
