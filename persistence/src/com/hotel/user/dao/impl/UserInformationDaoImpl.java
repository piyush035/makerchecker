/**
 * 
 */
package com.hotel.user.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.country.bean.CountryBean;
import com.hotel.base.common.domain.user.bean.UserInformationBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.user.dao.UserInformationDao;

/**
 * 
 * @author Prince
 * 
 */
public class UserInformationDaoImpl extends AbstractDefaultDao implements
		UserInformationDao {

	/**
	 * {@inheritDoc}
	 */

	public UserInformationBean findUserInformationByemail(String email) {
		List<UserInformationBean> userinformationList = getHibernateTemplate()
				.find("FROM com.hotel.base.common.domain.userinformation.bean.UserInformationBean userinformation where userinformation.email = '"
						+ email + "'");
		if (!userinformationList.isEmpty()) {
			return userinformationList.get(0);
		} else {
			return null;
		}
	}

	public UserInformationBean findUserInformationByNumber(long mobileNumber) {
		List<UserInformationBean> userinformationList = getHibernateTemplate()
				.find("FROM com.hotel.base.common.domain.userinformation.bean.UserInformationBean userinformation where userinformation.mobileNumber = '"
						+ mobileNumber + "'");
		if (!userinformationList.isEmpty()) {
			return userinformationList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Class getDefaultClassBean() {

		return CountryBean.class;
	}

}
