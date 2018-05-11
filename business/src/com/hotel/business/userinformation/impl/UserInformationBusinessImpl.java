package com.hotel.business.userinformation.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.userinformation.UserInformationBusiness;
import com.hotel.user.dao.UserInformationDao;
/**
 * 
 * @author Piyush
 *
 */
public class UserInformationBusinessImpl extends AbstractDefaultBusiness
		implements UserInformationBusiness {

	/** */
	private UserInformationDao userInformationDao;

	/**
	 * 
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return userInformationDao;
	}
	
	/**
	 * @param userLoginaDao
	 *            the userLoginDao to set
	 */
	public void setDefaultDao(final UserInformationDao userInformationDao) {
		this.userInformationDao = userInformationDao;
	}
}
