/**
 * 
 */
package com.hotel.base.common.domain.city.dto;

import com.hotel.base.common.domain.postalcodes.dto.PostalCodesDto;
import com.rennover.hotel.common.valueobject.DomainObject;

/**
 * @author Piyush
 */
public class CityDto extends DomainObject {
	
	/**
	 * 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2610613293557204869L;
	/** Primary Key. */
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
	
	/** State Bean. */
	private PostalCodesDto postalCode;
	
	private String cityName;
	public static final String CITY_NAME = "cityName";
	
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return nullToEmpty(cityName);
	}
	
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String newValue) {
		setCityName0(newValue);
	}
	
	private final void setCityName0(String newValue) {
		String oldValue = cityName;
		if (!equals(oldValue, newValue)) {
			cityName = newValue;
			setDirty(true);
			firePropertyChange(CITY_NAME, oldValue, newValue);
		}
	}
	
	/**
	 * @return the postalCode
	 */
	public PostalCodesDto getPostalCode() {
		return postalCode;
	}
	
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(PostalCodesDto postalCode) {
		this.postalCode = postalCode;
	}
	
}
