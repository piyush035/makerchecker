package com.hotel.business.userinformation.impl;

import com.hotel.base.common.domain.user.bean.UserLoginBean;
import com.hotel.base.common.domain.user.dto.UserContext;
import com.hotel.base.common.domain.user.dto.UserInfoGraph;
import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.base.common.facade.RequestContextManager;
import com.hotel.base.common.helper.PropertyHelper;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.common.tech.exception.user.ConnectionSimultaneousException;
import com.hotel.base.common.tech.exception.user.DesactivationException;
import com.hotel.base.common.tech.exception.user.LoginIncorrectException;
import com.hotel.base.common.tech.exception.user.PasswordIncorrectException;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.userinformation.UserLoginBusiness;
import com.hotel.business.userinformation.UserLoginBusinessManager;
import com.hotel.user.dao.UserLoginDao;

/**
 * @author Piyush
 */
public class UserLoginBusinessManagerImpl extends AbstractDefaultBusinessManager implements UserLoginBusinessManager {
	
	/** DAO for User Login */
	private UserLoginDao userLoginDao;
	
	/** Business. */
	private UserLoginBusiness userLoginBusiness;
	
	@Override
	protected DefaultDao getDefaultDao() {
		return userLoginDao;
	}
	
	/**
	 * @param DefaultDao the DefaultDao to set
	 */
	public void setDefaultDao(final UserLoginDao userLoginaDao) {
		this.userLoginDao = userLoginaDao;
	}
	
	/**
	 * @return the userLoginBusiness
	 */
	public UserLoginBusiness getUserLoginBusiness() {
		return userLoginBusiness;
	}
	
	/**
	 * @param userLoginBusiness the userLoginBusiness to set
	 */
	public void setUserLoginBusiness(UserLoginBusiness userLoginBusiness) {
		this.userLoginBusiness = userLoginBusiness;
	}
	
	public boolean login(UserLoginDto userLoginDto) {
		UserLoginBean user = this.userLoginDao.findUserByName(userLoginDto.getUserName());
		if (!PropertyHelper.isNull(user) && PropertyHelper.equals(user.getPassword(), userLoginDto.getPassword())) { return true; }
		return false;
	}
	
	public void authenticate(UserLoginDto userLoginDto) throws LoginIncorrectException, PasswordIncorrectException,
			DesactivationException, ConnectionSimultaneousException {
		UserLoginBean user = this.userLoginDao.findUserByName(userLoginDto.getUserName());
		
		if (PropertyHelper.isNull(user)) {
			throw new LoginIncorrectException();
		} else if (!PropertyHelper.equals(user.getPassword(), userLoginDto.getPassword())) {
			throw new PasswordIncorrectException();
		} else if (user.getStatus().equals("C")) {
			throw new DesactivationException();
		} else if (user.getStatus().equals("L")) { throw new ConnectionSimultaneousException(); }
		UserContext userContext = new UserContext();
		UserInfoGraph graph = new UserInfoGraph();
		userLoginDto = user.getUserLoginDto();
		graph.setUserLoginDto(userLoginDto);
		userContext.setUser(graph);
		RequestContextManager.setUserContext(userContext);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkUserAvailability(String userName) {
		return this.userLoginDao.findUserByName(userName) == null ? true : false;
	}
}