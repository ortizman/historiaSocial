package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ar.com.historiasocial.dao.GenericDAO;
import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.entities.Practica;
import ar.com.historiasocial.entities.TipoDePractica;
import ar.com.historiasocial.entities.TipoDeProblematica;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EstadisticaCalcularAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	private String							estadistica;
	private List<String>					resultado			= new ArrayList<String>();
	private Map<String, Integer>			porcentajes			= new HashMap<String, Integer>();

	private GenericDAO<TipoDeProblematica>	tipoDeProblematicaDAO;
	private GenericDAO<TipoDePractica>		tipoDePracticaDAO;
	private PracticaDAO						practicaDAO;

	public Map<String, Integer> getPorcentajes(){
		return porcentajes;
	}

	public String getEstadistica(){
		return estadistica;
	}

	public void setEstadistica(String estadistica){
		this.estadistica = estadistica;
	}

	public void setResultado(List<String> resultado){
		this.resultado = resultado;
	}

	public List<String> getResultado(){
		return resultado;
	}

	@Override
	public String execute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Paginador pag = new Paginador(Integer.MAX_VALUE, 1);

		if (this.getEstadistica().equals("problematica")) {

			List<TipoDeProblematica> problematicas = getTipoDeProblematicaDAO().retrievePaged(pag);
			@SuppressWarnings("unchecked")
			List<Practica> pt = (List<Practica>) session.get("practotales");
			if (pt == null) {
				pt = getPracticaDAO().retrieveAll(pag);
			}
			Integer cantProblematicas = pt.size();

			HashMap<String, Integer> cantProb = new HashMap<String, Integer>();
			Iterator<TipoDeProblematica> it = problematicas.iterator();
			List<String> r = new ArrayList<String>();
			while (it.hasNext()) {
				TipoDeProblematica tp = it.next();
				String cod = tp.getCodigo();
				Integer cant = this.contarProb(tp.getCodigo(), pt);
				Integer porcentaje = (cant * 100) / cantProblematicas;
				cantProb.put(cod, porcentaje);
				r.add(cod + " representa el " + porcentaje.toString() + "% ");

			}
			this.setPorcentajes(cantProb);
			return SUCCESS;
		} else {
			if (this.getEstadistica().equals("practica")) {

				List<TipoDePractica> practicas = getTipoDePracticaDAO().retrievePaged(pag);
				@SuppressWarnings("unchecked")
				List<Practica> pt = (List<Practica>) session.get("practotales");
				if (pt == null) {
					pt = getPracticaDAO().retrieveAll(pag);
				}
				Integer cantPracticas = pt.size();
				HashMap<String, Integer> cantPras = new HashMap<String, Integer>();
				Iterator<TipoDePractica> it = practicas.iterator();
				List<String> r = new ArrayList<String>();
				while (it.hasNext()) {
					TipoDePractica tp = it.next();
					String cod = tp.getCodigo();
					Integer cant = this.contarPras(tp.getCodigo(), pt);
					Integer porcentaje = (cant * 100) / cantPracticas;
					cantPras.put(cod, porcentaje);
					r.add(cod + " representa el " + porcentaje.toString() + "% ");
				}
				this.setPorcentajes(cantPras);
				return SUCCESS;
			}
		}

		return INPUT;
	}

	@Override
	public void validate(){
		if (this.getEstadistica() == null) {
			addFieldError("estadistica", "Seleccione un criterio de c�lculo");

		}

	}

	private Integer contarProb(String cod, List<Practica> pt){
		Integer resul = 0;
		Iterator<Practica> ptotal = pt.iterator();
		while (ptotal.hasNext()) {
			Practica p = ptotal.next();
			if (p.getTipoProblematica().getCodigo().equals(cod)) {
				resul++;
			}
		}
		return resul;
	}

	private Integer contarPras(String cod, List<Practica> pt){
		Integer resul = 0;
		Iterator<Practica> ptotal = pt.iterator();
		while (ptotal.hasNext()) {
			Practica p = ptotal.next();
			if (p.getTipoPractica().getCodigo().equals(cod)) {
				resul++;
			}
		}
		return resul;
	}

	public PracticaDAO getPracticaDAO(){
		return practicaDAO;
	}

	public void setPracticaDAO(PracticaDAO practicaDAO){
		this.practicaDAO = practicaDAO;
	}

	/**
	 * @return the tipoDeProblematicaDAO
	 */
	public GenericDAO<TipoDeProblematica> getTipoDeProblematicaDAO(){
		return tipoDeProblematicaDAO;
	}

	/**
	 * @param tipoDeProblematicaDAO
	 *            the tipoDeProblematicaDAO to set
	 */
	public void setTipoDeProblematicaDAO(GenericDAO<TipoDeProblematica> tipoDeProblematicaDAO){
		this.tipoDeProblematicaDAO = tipoDeProblematicaDAO;
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

	/**
	 * @param porcentajes
	 *            the porcentajes to set
	 */
	public void setPorcentajes(Map<String, Integer> porcentajes){
		this.porcentajes = porcentajes;
	}

}
