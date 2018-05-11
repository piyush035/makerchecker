package com.hotel.business.userinformation;

import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.base.common.tech.exception.user.ConnectionSimultaneousException;
import com.hotel.base.common.tech.exception.user.DesactivationException;
import com.hotel.base.common.tech.exception.user.LoginIncorrectException;
import com.hotel.base.common.tech.exception.user.PasswordIncorrectException;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * 
 * @author Piyush
 * 
 */
public interface UserLoginBusinessManager extends DefaultBusinessManager {

	/** */
	UserLoginBusiness getUserLoginBusiness();

	/**
	 * 
	 * @param userLoginDto
	 * @return
	 */
	boolean login(UserLoginDto userLoginDto);

	/**
	 * 
	 * @param userLoginDto
	 * @return
	 * @throws LoginIncorrectException
	 * @throws PasswordIncorrectException
	 * @throws DesactivationException
	 * @throws ConnectionSimultaneousException
	 */
	public void authenticate(UserLoginDto userLoginDto)
			throws LoginIncorrectException, PasswordIncorrectException,
			DesactivationException, ConnectionSimultaneousException;
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	boolean checkUserAvailability(String userName);
}
