/**
 * @author Manuel Ortiz
 */
package ar.com.historiasocial.util;

import ar.com.historiasocial.entities.Domicilio;
import ar.com.historiasocial.entities.Location;
import ar.com.historiasocial.entities.Paciente;

/**
 * @author Manuel Ortiz
 */
public class PacienteUtils {

	/**
	 * @param p
	 * @param paciente
	 */
	public void copyProperties(Paciente dest, Paciente orig) {
		
		dest.setAccesibilidad(orig.getAccesibilidad());
		dest.setApellidos(orig.getApellidos());
		dest.setBeneficionPlanesSubsidios(orig.getBeneficionPlanesSubsidios());
		dest.setCelular(orig.getCelular());
		dest.setCentroSaludReferencia(orig.getCentroSaludReferencia());
		dest.setCondicionesHabitacionales(orig.getCondicionesHabitacionales());
		dest.setDocumento(orig.getDocumento());
		dest.setFechaFinServicio(orig.getFechaFinServicio());
		dest.setFechaInicioServSocial(orig.getFechaInicioServSocial());
		dest.setFechaNacimiento(orig.getFechaNacimiento());
		dest.setLugarDeNacimiento(orig.getLugarDeNacimiento());
		dest.setNombres(orig.getNombres());
		dest.setOtrasInstitucionesIntervinientes(orig.getOtrasInstitucionesIntervinientes());
		dest.setSexo(orig.getSexo());
		dest.setTelefono(orig.getTelefono());
		dest.setTipoDePropiedad(orig.getTipoDePropiedad());
		
		Domicilio domicilio = dest.getDomicilio();
		
		domicilio.setBarrio(orig.getDomicilio().getBarrio());
		domicilio.setCalle(orig.getDomicilio().getCalle());
		domicilio.setCalleX(orig.getDomicilio().getCalleX());
		domicilio.setCalleY(orig.getDomicilio().getCalleY());
		domicilio.setDepartamento(orig.getDomicilio().getDepartamento());
		domicilio.setNumero(orig.getDomicilio().getNumero());
		domicilio.setPiso(orig.getDomicilio().getPiso());
		
		Location location = dest.getLocation();
		
		location.setCity(orig.getLocation().getCity());
		location.setDistrict(orig.getLocation().getDistrict());
		location.setNumber(orig.getLocation().getNumber());
		location.setProvince(orig.getLocation().getProvince());
		location.setStreet(orig.getLocation().getStreet());
		
	}

}
