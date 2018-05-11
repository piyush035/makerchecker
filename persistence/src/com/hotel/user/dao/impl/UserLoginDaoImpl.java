package com.hotel.user.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.user.bean.UserLoginBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.user.dao.UserLoginDao;

/**
 * 
 * @author Piyush
 * 
 */
public class UserLoginDaoImpl extends AbstractDefaultDao implements
		UserLoginDao {

	/**
	 * {@inheritDoc}
	 */

	public UserLoginBean findUserByName(String loginName) {
		List<UserLoginBean> usersList = getHibernateTemplate()
				.find("FROM com.hotel.base.common.domain.user.bean.UserLoginBean user where user.userName = '"
						+ loginName + "'");
		if (!usersList.isEmpty()) {
			return usersList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Class getDefaultClassBean() {

		return UserLoginBean.class;
	}

	@Override
	public List<UserLoginBean> getUserLoginAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
