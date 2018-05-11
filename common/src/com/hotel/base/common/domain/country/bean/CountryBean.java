/**
 * 
 */
package com.hotel.base.common.domain.country.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Piyush
 * 
 */
@Entity
@Table(name = "country", uniqueConstraints = {
		@UniqueConstraint(columnNames = "countryCode"),
		@UniqueConstraint(columnNames = "countryName") })
public class CountryBean implements DefaultBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9190201892210476947L;

	/** Primary Key. */
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	/** Name of Country. */
	@Column(name = "countryCode", nullable = false)
	private int countryCode;

	/** Name of Country. */
	@Column(name = "countryName", nullable = false)
	private String countryName;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public CountryDto getCountryDto(){
		CountryDto countryDto = new CountryDto();
		countryDto.setId(this.id);
		countryDto.setCountryCode(this.countryCode);
		countryDto.setCountryName(this.countryName);
		return countryDto;
	}
	}
