package com.hotel.service.userinformation.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.user.dto.UserContext;
import com.hotel.base.common.domain.user.dto.UserInformationDto;
import com.hotel.business.userinformation.UserInformationBusinessManager;
import com.hotel.service.userinformation.FacadeUserInformation;

@Remote(FacadeUserInformation.class)
@Stateless(name = "facadeUserInformation")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeUserInformationImpl implements FacadeUserInformation {

	/** */
	@Autowired
	private UserInformationBusinessManager userInformationBusinessManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UserInformationDto getUserInformation(final long id) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UserContext connectUser() {
		return userInformationBusinessManager.connectUser();
	}
}
