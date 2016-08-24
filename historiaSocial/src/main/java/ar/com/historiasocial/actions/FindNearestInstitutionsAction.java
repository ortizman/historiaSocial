package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import ar.com.historiasocial.dao.InstitucionDAO;
import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Institucion;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.util.DistanceToCoordMap;

import com.opensymphony.xwork2.ActionSupport;

public class FindNearestInstitutionsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5081676824153983183L;
	private HttpServletRequest	request;
	@SuppressWarnings("unused")
	private HttpServletResponse	response;
	private List<Institucion>	institutions		= new ArrayList<Institucion>();
	private Paciente			pacient;
	private Map<Double, String>	map;

	private PacienteDAO			pacienteDAO;
	private InstitucionDAO		institucionDAO;

	public String execute() throws HSControllerException{

		Long pacientId = Long.parseLong(request.getParameter("id"));
		Double delta = Double.parseDouble(request.getParameter("delta"));

		this.pacient = pacienteDAO.retrieveById(pacientId);
		this.institutions = institucionDAO.findInstitutionsNearTo(pacient.getLocation(), delta);
		this.map = DistanceToCoordMap.getMap();
		return SUCCESS;
	}

	public Map<Double, String> getMap(){
		return map;
	}

	public void setMap(Map<Double, String> map){
		this.map = map;
	}

	public List<Institucion> getInstitutions(){
		return institutions;
	}

	public void setInstitutions(List<Institucion> institutions){
		this.institutions = institutions;
	}

	public Paciente getPacient(){
		return this.pacient;
	}

	public void setPacient(Paciente p){
		this.pacient = p;
	}

	@Override
	public void setServletResponse(HttpServletResponse response){
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

	public InstitucionDAO getInstitucionDAO(){
		return institucionDAO;
	}

	public void setInstitucionDAO(InstitucionDAO institucionDAO){
		this.institucionDAO = institucionDAO;
	}

}
