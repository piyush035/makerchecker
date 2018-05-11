package com.hotel.business.userinformation.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.userinformation.UserLoginBusiness;
import com.hotel.user.dao.UserLoginDao;

/**
 * 
 * @author Piyush
 * 
 */
public class UserLoginBusinessImpl extends AbstractDefaultBusiness implements
		UserLoginBusiness {

	/** DAO for User Login */
	private UserLoginDao userLoginDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return userLoginDao;
	}

	/**
	 * @param userLoginaDao
	 *            the userLoginDao to set
	 */
	public void setDefaultDao(final UserLoginDao userLoginaDao) {
		this.userLoginDao = userLoginaDao;
	}
}
