package ar.com.historiasocial.entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class GeoCord {
	private Double	latitud;
	private Double	longitud;

	public GeoCord(Double latitud, Double longitud) {
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}

	public Double getLatitud(){
		return latitud;
	}

	public void setLatitud(Double latitud){
		this.latitud = latitud;
	}

	public Double getLongitud(){
		return longitud;
	}

	public void setLongitud(Double longitud){
		this.longitud = longitud;
	}

	/**
	 * Compara por las coordenadas, latitud y longitud. si ambas son iguales, el
	 * resultado es verdadera. De otra forma es falso.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object geoCord){
		boolean ok = false;
		
		if (geoCord instanceof GeoCord) {
			final GeoCord cord = (GeoCord) geoCord;
			ok = (cord.getLatitud().equals(this.getLatitud()) && cord.getLongitud().equals(this.getLongitud()));
		}
		
		return ok;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return new HashCodeBuilder().append(getLatitud()).append(getLongitud()).toHashCode();
	}

	@Override
	public String toString(){
		return "Latitud: " + this.getLatitud() + " Longitud: " + this.getLongitud();
	}
}
