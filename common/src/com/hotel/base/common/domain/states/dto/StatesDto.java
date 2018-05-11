/**
 * 
 */
package com.hotel.base.common.domain.states.dto;

import com.rennover.hotel.common.valueobject.DomainObject;

/**
 * @author Piyush
 */
public class StatesDto extends DomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 432373736233422152L;
	
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
	
	/** Country Bean. */
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
	
	/** State Name. */
	private String stateName;
	
	public static final String STATE_NAME = "stateName";
	
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return nullToEmpty(stateName);
	}
	
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String newValue) {
		setStateName0(newValue);
	}
	
	private final void setStateName0(String newValue) {
		String oldValue = stateName;
		if (!equals(oldValue, newValue)) {
			stateName = newValue;
			setDirty(true);
			firePropertyChange(STATE_NAME, oldValue, newValue);
		}
	}
	public String toString() {
		return this.stateName;
	}
}