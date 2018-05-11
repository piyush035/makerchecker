/**
 * 
 */
package com.hotel.base.common.domain.address.dto;

import com.hotel.base.common.domain.addresstype.dto.AddressTypeDto;
import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.common.domain.postalcodes.dto.PostalCodesDto;
import com.rennover.hotel.common.valueobject.DomainObject;

/**
 * @author Piyush
 */
public class AddressDto extends DomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4312576651186176816L;
	
	/** */
	private long id;
	
	public static final String ID = "id";
	
	/**
	 * @return the id
	 */
	
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long newValue) {
		setId0(newValue);
	}
	
	private final void setId0(long newValue) {
		long oldValue = id;
		if (!equals(oldValue, newValue)) {
			id = newValue;
			setDirty(true);
			firePropertyChange(ID, oldValue, newValue);
		}
	}
	
	/** */
	private String line1;
	
	public static final String LINE_1 = "line1";
	
	/**
	 * @return the line1
	 */
	public String getLine1() {
		return nullToEmpty(line1);
	}
	
	/**
	 * @param line1 the line1 to set
	 */
	public void setLine1(String newValue) {
		setLine10(newValue);
	}
	
	private final void setLine10(String newValue) {
		String oldValue = line1;
		if (!equals(oldValue, newValue)) {
			line1 = newValue;
			setDirty(true);
			firePropertyChange(LINE_1, oldValue, newValue);
		}
	}
	
	/** */
	private String line2;
	
	public static final String LINE_2 = "line2";
	
	/**
	 * @return the line2
	 */
	public String getLine2() {
		return nullToEmpty(line2);
	}
	
	/**
	 * @param line2 the line2 to set
	 */
	public void setLine2(String newValue) {
		setLine20(newValue);
	}
	
	private final void setLine20(String newValue) {
		String oldValue = line2;
		if (!equals(oldValue, newValue)) {
			line2 = newValue;
			setDirty(true);
			firePropertyChange(LINE_2, oldValue, newValue);
		}
	}
	
	/** */
	private String line3;
	
	public static final String LINE_3 = "line3";
	
	/**
	 * @return the line3
	 */
	public String getLine3() {
		return nullToEmpty(line3);
	}
	
	/**
	 * @param line3 the line3 to set
	 */
	public void setLine3(String newValue) {
		setLine30(newValue);
	}
	
	private final void setLine30(String newValue) {
		String oldValue = line3;
		if (!equals(oldValue, newValue)) {
			line3 = newValue;
			setDirty(true);
			firePropertyChange(LINE_3, oldValue, newValue);
		}
	}
	
	/** */
	private long addressTypeId;
	
	public static final String ADDRESS_TYPE_ID = "addressTypeId";
	
	/**
	 * @return the id
	 */
	
	public long getAddressTypeId() {
		return addressTypeId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setAddressTypeId(long newValue) {
		setAddressTypeId0(newValue);
	}
	
	private final void setAddressTypeId0(long newValue) {
		long oldValue = addressTypeId;
		if (!equals(oldValue, newValue)) {
			addressTypeId = newValue;
			setDirty(true);
			firePropertyChange(ADDRESS_TYPE_ID, oldValue, newValue);
		}
	}
	
	/** */
	private long cityId;
	
	public static final String CITY_ID = "cityId";
	
	/**
	 * @return the id
	 */
	
	public long getCityId() {
		return cityId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setCityId(long newValue) {
		setCityId0(newValue);
	}
	
	private final void setCityId0(long newValue) {
		long oldValue = cityId;
		if (!equals(oldValue, newValue)) {
			cityId = newValue;
			setDirty(true);
			firePropertyChange(CITY_ID, oldValue, newValue);
		}
	}
	
	/** */
	private long postalCodeId;
	
	public static final String POSTAL_CODE_ID = "postalCodeId";
	
	/**
	 * @return the id
	 */
	
	public long getPostalCodeId() {
		return postalCodeId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setPostalCodeId(long newValue) {
		setPostalCodeId0(newValue);
	}
	
	private final void setPostalCodeId0(long newValue) {
		long oldValue = postalCodeId;
		if (!equals(oldValue, newValue)) {
			postalCodeId = newValue;
			setDirty(true);
			firePropertyChange(POSTAL_CODE_ID, oldValue, newValue);
		}
	}
	
	/** */
	private int stateId;
	
	public static final String STATE_ID = "stateId";
	
	/**
	 * @return the id
	 */
	
	public int getStateId() {
		return stateId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setStateId(int newValue) {
		setStateId0(newValue);
	}
	
	private final void setStateId0(int newValue) {
		long oldValue = stateId;
		if (!equals(oldValue, newValue)) {
			stateId = newValue;
			setDirty(true);
			firePropertyChange(STATE_ID, oldValue, newValue);
		}
	}
	
	/** */
	private int countryId;
	
	public static final String COUNTRY_ID = "countryId";
	
	/**
	 * @return the id
	 */
	
	public int getCountryId() {
		return countryId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setCountryId(int newValue) {
		setCountryId0(newValue);
	}
	
	private final void setCountryId0(int newValue) {
		long oldValue = countryId;
		if (!equals(oldValue, newValue)) {
			countryId = newValue;
			setDirty(true);
			firePropertyChange(COUNTRY_ID, oldValue, newValue);
		}
	}
	
	private AddressTypeDto addressTypeDto;
	
	/**
	 * @return the addressTypeDto
	 */
	public AddressTypeDto getAddressTypeDto() {
		return addressTypeDto;
	}
	
	/**
	 * @param addressTypeDto the addressTypeDto to set
	 */
	public void setAddressTypeDto(AddressTypeDto addressTypeDto) {
		this.addressTypeDto = addressTypeDto;
	}
	
	private CityDto cityDto;
	
	private PostalCodesDto postalCodesDto;
	
	/**
	 * @return the cityDto
	 */
	public CityDto getCityDto() {
		return cityDto;
	}
	
	/**
	 * @param cityDto the cityDto to set
	 */
	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}
	
	/**
	 * @return the postalCodesDto
	 */
	public PostalCodesDto getPostalCodesDto() {
		return postalCodesDto;
	}
	
	/**
	 * @param postalCodesDto the postalCodesDto to set
	 */
	public void setPostalCodesDto(PostalCodesDto postalCodesDto) {
		this.postalCodesDto = postalCodesDto;
	}	
}