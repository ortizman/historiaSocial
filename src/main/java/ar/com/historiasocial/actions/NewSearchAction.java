package ar.com.historiasocial.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.historiasocial.dao.PacienteDAO;
import ar.com.historiasocial.entities.Paciente;
import ar.com.historiasocial.entities.Paginador;
import ar.com.historiasocial.util.DistanceToCoordMap;

import com.opensymphony.xwork2.ActionSupport;

public class NewSearchAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private List<Paciente>		pacients			= new ArrayList<Paciente>();
	private Map<Double, String>	map;
	private PacienteDAO			pacienteDAO;

	@Override
	public String execute(){
		map = DistanceToCoordMap.getMap(); 

		this.setPacients(this.retrieveAll(getPacienteDAO()));
		return SUCCESS;
	}

	private List<Paciente> retrieveAll(PacienteDAO dao){
		Paginador paginador = new Paginador(50, 0);
		List<Paciente> pacs = dao.pacientes(paginador);

		return pacs;
	}

	public List<Paciente> getPacients(){
		return pacients;
	}

	public void setPacients(List<Paciente> pacients){
		this.pacients = pacients;
	}

	public Map<Double, String> getMap(){
		return map;
	}

	public void setMap(Map<Double, String> map){
		this.map = map;
	}

	public PacienteDAO getPacienteDAO(){
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO){
		this.pacienteDAO = pacienteDAO;
	}

}
