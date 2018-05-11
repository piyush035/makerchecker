/**
 * 
 */
package com.hotel.base.common.domain.postalcodes.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.hotel.base.common.domain.states.bean.StatesBean;
import com.rennover.hotel.common.valueobject.DomainObject;

/**
 * @author Prince
 *
 */
public class PostalCodesDto extends DomainObject {
	
	private int id;

	/** State Bean. */
	@OneToOne
	@JoinColumn(name = "stateId")
	private StatesBean state;

	/** State Name. */
	@Column(name = "postalCode", nullable = false)
	private int postalCode;

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
	 * @return the state
	 */
	public StatesBean getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(StatesBean state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

}
