package ar.com.historiasocial.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="location")
public class Location implements ar.com.historiasocial.dao.Entity {

	private static final long serialVersionUID = 4606588792017110842L;
	@Id @GeneratedValue  
	private Long id;
	private String province = "";
	private String district = "";
	private String city = "";
	private String street = "";
	private String number = "";
	private Double latitude;
	private Double longitude;
	
    @OneToOne(mappedBy="location", fetch=FetchType.EAGER)
    private Paciente pacient;
     
	@OneToOne(mappedBy = "location", fetch=FetchType.EAGER)
    private Institucion institution;
    
    public Location() {
    	super();
    }

	public Location(String province, String district, String city,
			String street, String number, Double latitude, Double longitude,
			Paciente pacient, Institucion institution) {
		super();
		this.setProvince(province);
		this.setDistrict(district);
		this.setCity(city);
		this.setStreet(street);
		this.setNumber(number);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setPacient(pacient);
		this.setInstitution(institution);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	
	public String getCoordinates ()
	{
		return this.getLatitude().toString() + ", " + this.getLongitude().toString();
	}

	public Paciente getPacient() {
		return pacient;
	}

	public void setPacient(Paciente pacient) {
		this.pacient = pacient;
	}

	public Institucion getInstitution() {
		return institution;
	}

	public void setInstitution(Institucion institution) {
		this.institution = institution;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
		result = prime * result + ((this.district == null) ? 0 : this.district.hashCode());
		result = prime * result + ((this.latitude == null) ? 0 : this.latitude.hashCode());
		result = prime * result + ((this.longitude == null) ? 0 : this.longitude.hashCode());
		result = prime * result + ((this.number == null) ? 0 : this.number.hashCode());
		result = prime * result + ((this.province == null) ? 0 : this.province.hashCode());
		result = prime * result + ((this.street == null) ? 0 : this.street.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Location other = (Location) obj;
		if (this.city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!this.city.equals(other.city)) {
			return false;
		}
		if (this.latitude == null) {
			if (other.latitude != null) {
				return false;
			}
		} else if (!this.latitude.equals(other.latitude)) {
			return false;
		}
		if (this.longitude == null) {
			if (other.longitude != null) {
				return false;
			}
		} else if (!this.longitude.equals(other.longitude)) {
			return false;
		}
		if (this.number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!this.number.equals(other.number)) {
			return false;
		}
		if (this.province == null) {
			if (other.province != null) {
				return false;
			}
		} else if (!this.province.equals(other.province)) {
			return false;
		}
		if (this.street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!this.street.equals(other.street)) {
			return false;
		}
		return true;
	}
	
			
}
