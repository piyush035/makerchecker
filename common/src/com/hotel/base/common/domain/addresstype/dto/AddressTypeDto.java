/**
 * 
 */
package com.hotel.base.common.domain.addresstype.dto;

import com.rennover.hotel.common.valueobject.DomainObject;

/**
 * @author Prince
 * 
 */
public class AddressTypeDto extends DomainObject {

	private int id;

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

}
