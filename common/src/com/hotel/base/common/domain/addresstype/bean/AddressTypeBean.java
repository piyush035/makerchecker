/**
 * 
 */
package com.hotel.base.common.domain.addresstype.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hotel.base.common.domain.addresstype.dto.AddressTypeDto;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Prince
 * 
 */
@Entity
@Table(name = "refaddresstype")
public class AddressTypeBean implements DefaultBean {

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 6138957719101880279L;

	/**
	 * 
	 */

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "addressTypeDesc")
	private String addressTypeDesc;

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

	/**
	 * @return the addressTypeDesc
	 */
	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}

	/**
	 * @param addressTypeDesc
	 *            the addressTypeDesc to set
	 */
	public void setAddressTypeDesc(String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}

	public AddressTypeDto getAddressTypeDto() {
		AddressTypeDto addresstypeDto = new AddressTypeDto();
		addresstypeDto.setId(this.id);
		addresstypeDto.setAddressTypeDesc(this.addressTypeDesc);
		return addresstypeDto;
	}

}
