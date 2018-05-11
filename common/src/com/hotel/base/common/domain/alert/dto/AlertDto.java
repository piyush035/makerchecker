/**
 * 
 */
package com.hotel.base.common.domain.alert.dto;

/**
 * 
 */

import com.rennover.hotel.common.valueobject.DomainObject;

/**
 * @author Prince
 * 
 */
public class AlertDto extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1706669994276463750L;

	/**
	 * 
	 */

	public AlertDto() {

	}

	/** */
	private long id;

	public static final String ID = "id";

	public long getId() {
		return id;

	}

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

	private String alertType;
	public static final String ALERT_TYPE = "alertType";

	public String getAlertType() {
		return alertType;

	}

	public void setAlertType(String newValue) {
		setAlertType0(newValue);
	}

	private final void setAlertType0(String newValue) {
		String oldValue = alertType;
		if (!equals(oldValue, newValue)) {
			alertType = newValue;
			setDirty(true);
			firePropertyChange(ALERT_TYPE, oldValue, newValue);
		}
	}


	private String alertName;
	public static final String ALERT_NAME = "alertName";

	public String getAlertName() {
		return alertName;

	}

	public void setAlertName(String newValue) {
		setAlertType0(newValue);
	}

	private final void setAlertName0(String newValue) {
		String oldValue = alertName;
		if (!equals(oldValue, newValue)) {
			alertName = newValue;
			setDirty(true);
			firePropertyChange(ALERT_NAME, oldValue, newValue);
		}
	}

	/*public String toString() {
		return this.alertName;
	}
	public String toString() {
		return this.alertType;
	}*/

}