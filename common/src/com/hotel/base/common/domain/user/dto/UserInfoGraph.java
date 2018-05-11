/**
 * 
 */
package com.hotel.base.common.domain.user.dto;

import com.rennover.hotel.common.valueobject.GraphObject;

/**
 * @author Piyush
 * 
 */
public class UserInfoGraph extends GraphObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2782819867018999856L;

	/** */
	private UserLoginDto userLoginDto;

	/**
	 * @return the userLoginDto
	 */
	public UserLoginDto getUserLoginDto() {
		return userLoginDto;
	}

	/**
	 * @param userLoginDto
	 *            the userLoginDto to set
	 */
	public void setUserLoginDto(UserLoginDto userLoginDto) {
		this.userLoginDto = userLoginDto;
	}

	/**
	 * @return the userInformationDto
	 */
	public UserInformationDto getUserInformationDto() {
		return null;//this.userLoginDto.getUserInformationDto();
	}

	/**
	 * @param userInformationDto
	 *            the userInformationDto to set
	 */
	public void setUserInformationDto(UserInformationDto userInformationDto) {
		//this.userLoginDto.setUserInformationDto(userInformationDto);
	}
}