/**
 * 
 */
package com.hotel.base.common.domain.address.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.base.common.domain.address.dto.AddressDto;
import com.hotel.base.common.domain.addresstype.bean.AddressTypeBean;
import com.hotel.base.common.domain.city.bean.CityBean;
import com.hotel.base.common.domain.postalcodes.bean.PostalCodesBean;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Piyush
 * 
 */
@Entity
@Table(name = "addresses")
public class AddressBean implements DefaultBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6235529370675428602L;

	/** */
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	/** */
	@Column(name = "line1")
	private String line1;

	/** */
	@Column(name = "line2")
	private String line2;

	/** */
	@Column(name = "line3")
	private String line3;

	/** */
	@OneToOne
	@JoinColumn(name = "addresstype")
	private AddressTypeBean addresstype;

	/** */
	@OneToOne
	@JoinColumn(name = "city")
	private CityBean city;

	/** */
	@OneToOne
	@JoinColumn(name = "postalCode")
	private PostalCodesBean postalCode;


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
	 * @return the line1
	 */
	public String getLine1() {
		return line1;
	}

	/**
	 * @param line1
	 *            the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * @return the line2
	 */
	public String getLine2() {
		return line2;
	}

	/**
	 * @param line2
	 *            the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * @return the line3
	 */
	public String getLine3() {
		return line3;
	}

	/**
	 * @param line3
	 *            the line3 to set
	 */
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	/**
	 * @return the addresstype
	 */
	public AddressTypeBean getAddresstype() {
		return addresstype;
	}

	/**
	 * @param addresstype
	 *            the addresstype to set
	 */
	public void setAddresstype(AddressTypeBean addresstype) {
		this.addresstype = addresstype;
	}

	/**
	 * @return the city
	 */
	public CityBean getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(CityBean city) {
		this.city = city;
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
	 * 
	 * @return
	 */
	public AddressDto getAddressDto(){
		AddressDto addressDto = new AddressDto();
		addressDto.setId(this.id);
		addressDto.setLine1(this.line1);
		addressDto.setLine2(this.line2);
		addressDto.setLine3(this.line3);
		addressDto.setAddressTypeDto(this.addresstype.getAddressTypeDto());
		addressDto.setCityDto(this.city.getCityDto());		
		addressDto.setPostalCodesDto(this.postalCode.getPostalCodesDto());		
		return addressDto;
	}
}
