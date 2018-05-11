/**
 * 
 */
package com.hotel.base.common.domain.alert.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hotel.base.common.domain.alert.dto.AlertDto;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Prince
 * 
 */
@Entity
@Table(name = "status")
public class AlertBean implements DefaultBean {

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -3367144331092518049L;

	/** Primary Key. */
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "alertType", nullable = false)
	private String alertType;

	@Column(name = "alertName", nullable = false)
	private String alertName;

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
	 * @return the alertType
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param statusType
	 *            the statusType to set
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return the alertName
	 */
	public String getAlertName() {
		return alertName;
	}

	/**
	 * @param alertName
	 *            the alertName to set
	 */
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public AlertDto getalertDto() {
		AlertDto alertDto = new AlertDto();
		alertDto.setId(this.id);
		alertDto.setAlertType(this.alertType);
		alertDto.setAlertName(this.alertName);
		return alertDto;
	}

}