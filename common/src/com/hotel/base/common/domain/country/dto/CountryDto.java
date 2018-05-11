package com.hotel.base.common.domain.country.dto;

import com.rennover.hotel.common.valueobject.DomainObject;

public class CountryDto extends DomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6480693244133315622L;
	
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
	
	/** Name of Country. */
	private int countryCode;
	
	public static final String COUNTRY_CODE = "countryCode";
	
	/**
	 * @return the countryCode
	 */
	public int getCountryCode() {
		return countryCode;
	}
	
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(int newValue) {
		setCountryCode0(newValue);
	}
	
	private final void setCountryCode0(int newValue) {
		int oldValue = countryCode;
		if (!equals(oldValue, newValue)) {
			countryCode = newValue;
			setDirty(true);
			firePropertyChange(COUNTRY_CODE, oldValue, newValue);
		}
	}
	
	/** Name of Country. */
	private String countryName;
	
	public static final String COUNTRY_NAME = "countryName";
	
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return nullToEmpty(countryName);
	}
	
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryName(String newValue) {
		setCountryName0(newValue);
	}
	
	private final void setCountryName0(String newValue) {
		String oldValue = countryName;
		if (!equals(oldValue, newValue)) {
			countryName = newValue;
			setDirty(true);
			firePropertyChange(COUNTRY_NAME, oldValue, newValue);
		}
	}
	
	public String toString() {
		return this.countryName;
	}
}