package ar.com.historiasocial.entities;

public class Paginador {
	public static final Integer CANT_PAGE_DEFAULT = 10;
	private int pagActual;
	private int cantPorPag;
	private int totalItems;
	
	/**
	 *
	 * @param cantPorPag
	 * @param pagActual
	 * @param totalItems
	 */
	public Paginador(int cantPorPag, int pagActual){
		this.totalItems = 0;
		this.pagActual = pagActual;
		this.cantPorPag = cantPorPag;	
	}

	
	public Paginador(int pagActual) {
		this.totalItems = 0;
		this.pagActual = pagActual;
		this.cantPorPag = CANT_PAGE_DEFAULT;
	}
	
	public int getPagActual() {
		return pagActual;
	}

	public void setPagActual(int pagActual) {
		this.pagActual = pagActual;
	}

	public int getCantPorPag() {
		return cantPorPag;
	}

	public void setCantPorPag(int cantPorPag) {
		this.cantPorPag = cantPorPag;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int cantidadDePaginas(){
		if((totalItems % this.cantPorPag)>0){
			return (this.totalItems/this.cantPorPag)+1;
		}
		else{
			return this.totalItems/this.cantPorPag;
		}
	}
	
	public int inicio(){
		int inicio = (this.cantPorPag * this.pagActual) - this.cantPorPag;
		if (inicio < 0)
			inicio = 0;
		return inicio;
	}
	
	public int fin(){
		if( this.cantidadDePaginas() == this.pagActual){
			return this.totalItems;
		}
		else{
			return this.inicio() + this.cantPorPag;
		}
	}

	public int getCantidadDePaginas() {
		if((this.getTotalItems() % this.getCantPorPag())>0){
			return (this.getTotalItems()/this.getCantPorPag())+1;
		}
		else{
			return this.getTotalItems()/this.getCantPorPag();
		}
	}
}
