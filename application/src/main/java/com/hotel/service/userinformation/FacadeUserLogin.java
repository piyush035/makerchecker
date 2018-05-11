package com.hotel.service.userinformation;

import javax.ejb.Remote;

import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.base.common.tech.exception.user.ConnectionSimultaneousException;
import com.hotel.base.common.tech.exception.user.DesactivationException;
import com.hotel.base.common.tech.exception.user.LoginIncorrectException;
import com.hotel.base.common.tech.exception.user.PasswordIncorrectException;

/**
 * 
 * @author Piyush
 * 
 */
@Remote
public interface FacadeUserLogin {
	/**
	 * 
	 * @param userLoginDto
	 * @return
	 */
	boolean authenticate(UserLoginDto userLoginDto);

	/**
	 * 
	 * @param login
	 * @param password
	 * @param nbConnections
	 * @throws LoginIncorrectException
	 * @throws PasswordIncorrectException
	 * @throws DesactivationException
	 * @throws ConnectionSimultaneousException
	 */
	void authenticate(String login, String password, int nbConnections)
			throws LoginIncorrectException, PasswordIncorrectException,
			DesactivationException, ConnectionSimultaneousException;
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	boolean checkUserAvailability(String userName);
}
