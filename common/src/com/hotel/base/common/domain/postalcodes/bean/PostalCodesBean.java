/**
 * 
 */
package com.hotel.base.common.domain.postalcodes.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.common.domain.postalcodes.dto.PostalCodesDto;
import com.hotel.base.common.domain.states.bean.StatesBean;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Prince
 * 
 */
@Entity
@Table(name = "postalcodes")
public class PostalCodesBean implements DefaultBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3382604925389831362L;

	/** Primary Key. */
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	/** State Bean. */
	@OneToOne
	@JoinColumn(name = "stateId")
	private StatesBean state;

	/** State Name. */
	@Column(name = "postalCode", nullable = false)
	private int postalCode;

	private Object stateId;

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

	/**
	 * 
	 * @return
	 */
	public PostalCodesDto getPostalCodesDto() {
		PostalCodesDto postalCodeDto = new PostalCodesDto();
		postalCodeDto.setId(this.id);
		postalCodeDto.setPostalCode(this.postalCode);
		postalCodeDto.setState(this.state);
		return postalCodeDto;

	}

}
