package Proyecto.Prueba.BCI.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(name = "Number")
	private long Number;
	
	@Column(name = "CityCode")
	private int CityCode;
	
	@Column(name = "CountryCode")
	private int CountryCode;

	public long getNumber() {
		return Number;
	}

	public void setNumber(long number) {
		Number = number;
	}

	public int getCityCode() {
		return CityCode;
	}

	public void setCityCode(int cityCode) {
		CityCode = cityCode;
	}

	public int getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(int countryCode) {
		CountryCode = countryCode;
	}

}
