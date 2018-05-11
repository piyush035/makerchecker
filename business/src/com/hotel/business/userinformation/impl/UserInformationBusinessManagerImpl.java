package com.hotel.business.userinformation.impl;

import com.hotel.base.common.domain.user.dto.UserContext;
import com.hotel.base.common.facade.RequestContextManager;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.userinformation.UserInformationBusiness;
import com.hotel.business.userinformation.UserInformationBusinessManager;
import com.hotel.user.dao.UserInformationDao;
/**
 * 
 * @author Piyush
 *
 */
public class UserInformationBusinessManagerImpl extends AbstractDefaultBusinessManager
		implements UserInformationBusinessManager {

	/** */
	private UserInformationDao userInformationDao;
	
	private UserInformationBusiness userInformationBusiness;

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
	
	/**
	 * @return the userInformationBusiness
	 */
	public UserInformationBusiness getUserInformationBusiness() {
		return userInformationBusiness;
	}

	/**
	 * @param userInformationBusiness the userInformationBusiness to set
	 */
	public void setUserInformationBusiness(
			UserInformationBusiness userInformationBusiness) {
		this.userInformationBusiness = userInformationBusiness;
	}

	@Override
	public UserContext connectUser() {
		UserContext userContext = RequestContextManager.getUserContext();
		if(null == userContext || (null != userContext && null == userContext.getUserLogin())){
			
		}
		return userContext;
	}	
}