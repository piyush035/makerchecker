/**
 * 
 */
package com.hotel.base.common.domain.city.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.common.domain.postalcodes.bean.PostalCodesBean;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Prince
 * 
 */
@Entity
@Table(name = "city")
public class CityBean implements DefaultBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9189653863980616629L;

	/** Primary Key. */
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	/** State Bean. */
	@OneToOne
	@JoinColumn(name = "postalCode")
	private PostalCodesBean postalCode;

	@Column(name = "cityName", nullable = false)
	private String cityName;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the postalCode
	 */
	public PostalCodesBean getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(PostalCodesBean postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 
	 * @return
	 */
	public CityDto getCityDto() {
		CityDto cityDto = new CityDto();
		cityDto.setId(this.id);
		cityDto.setPostalCode(this.postalCode.getPostalCodesDto());
		cityDto.setCityName(this.cityName);
		return cityDto;

	}
}