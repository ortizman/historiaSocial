package ar.com.historiasocial.actions;


import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 */
public class ListJQGridAction extends ActionSupport {

	/**
	 * Serial auto-generado
	 */
	private static final long	serialVersionUID	= -4156615219593751558L;
	
	private Integer	rows	= 0;
	private Integer	page	= 0;
	// Your Total Pages
	private Integer	total	= 0;
	// All Record
	private Integer	record	= 0;
	
	private String	sord;
	private String	sidx;
	
	private String	searchField;
	private String	searchString;
	 // he Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String	searchOper;
	private String filters;
	
	/**
	 * Prepara antes de la invocacion al listado. Por defecto no hace nada.
	 * @return SUCCESS
	 * @throws Exception
	 */
	public String preparar() throws Exception{
		return SUCCESS;
	}

	/**
	 * 
	 */
	public ListJQGridAction() {
		super();
	}

	/**
	 * @return the rows
	 */
	public Integer getRows(){
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(Integer rows){
		this.rows = rows;
	}

	/**
	 * @return the page
	 */
	public Integer getPage(){
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Integer page){
		this.page = page;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal(){
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Integer total){
		this.total = total;
	}

	/**
	 * @return the record
	 */
	public Integer getRecord(){
		return record;
	}

	/**
	 * @param record
	 *            the record to set
	 */
	public void setRecord(Integer record){
		this.record = record;
	}

	/**
	 * @return the sord
	 */
	public String getSord(){
		return sord;
	}

	/**
	 * @param sord
	 *            the sord to set
	 */
	public void setSord(String sord){
		this.sord = sord;
	}

	/**
	 * @return the sidx
	 */
	public String getSidx(){
		return sidx;
	}

	/**
	 * @param sidx
	 *            the sidx to set
	 */
	public void setSidx(String sidx){
		this.sidx = sidx;
	}

	/**
	 * @return the searchField
	 */
	public String getSearchField(){
		return searchField;
	}

	/**
	 * @param searchField
	 *            the searchField to set
	 */
	public void setSearchField(String searchField){
		this.searchField = searchField;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString(){
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString){
		this.searchString = searchString;
	}

	/**
	 * @return the searchOper
	 */
	public String getSearchOper(){
		return searchOper;
	}

	/**
	 * @param searchOper
	 *            the searchOper to set
	 */
	public void setSearchOper(String searchOper){
		this.searchOper = searchOper;
	}

	/**
	 * @return the filters
	 */
	public String getFilters(){
		return filters;
	}

	/**
	 * @param filters the filters to set
	 */
	public void setFilters(String filters){
		this.filters = filters;
	}

}
