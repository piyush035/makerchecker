package com.hotel.service.userinformation.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.base.common.tech.exception.user.ConnectionSimultaneousException;
import com.hotel.base.common.tech.exception.user.DesactivationException;
import com.hotel.base.common.tech.exception.user.LoginIncorrectException;
import com.hotel.base.common.tech.exception.user.PasswordIncorrectException;
import com.hotel.business.userinformation.UserLoginBusinessManager;
import com.hotel.service.userinformation.FacadeUserLogin;


/**
 * @author Piyush
 */
@Remote(FacadeUserLogin.class)
@Stateless(name = "facadeUserLogin")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeUserLoginImpl implements FacadeUserLogin {
	
	/** */
	@Autowired
	private UserLoginBusinessManager userLoginBusinessManager;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean authenticate(UserLoginDto userLoginDto) {
		return userLoginBusinessManager.login(userLoginDto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void authenticate(String login, String password, int nbConnections) throws LoginIncorrectException,
			PasswordIncorrectException, DesactivationException, ConnectionSimultaneousException {
		UserLoginDto userLoginDto = new UserLoginDto();
		userLoginDto.setUserName(login);
		userLoginDto.setPassword(password);
		userLoginBusinessManager.authenticate(userLoginDto);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean checkUserAvailability(String userName) {
		return userLoginBusinessManager.checkUserAvailability(userName);
	}
}
