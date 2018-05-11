/**
 * 
 */
package com.hotel.base.common.domain.states.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.base.common.domain.country.bean.CountryBean;
import com.hotel.base.common.domain.states.dto.StatesDto;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Piyush
 * 
 */
@Entity
@Table(name = "states")
public class StatesBean implements DefaultBean {

	/**
	 * 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5849950319901367846L;

	/** Primary Key. */
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	/** Country Bean. */
	@OneToOne
	@JoinColumn(name = "countryId")
	private CountryBean country;

	/** State Name. */
	@Column(name = "stateName", nullable = false)
	private String stateName;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the country
	 */
	public CountryBean getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(CountryBean country) {
		this.country = country;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * 
	 * @return
	 */
	public StatesDto getStatesDto() {
		StatesDto statesDto = new StatesDto();
		statesDto.setId(this.id);
		statesDto.setCountryId(this.country.getId());
		statesDto.setStateName(this.stateName);
		return statesDto;

	}
}
