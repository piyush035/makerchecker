package com.hotel.user.dao;

import java.util.List;

import com.hotel.base.common.domain.user.bean.UserLoginBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * 
 * @author Piyush
 * 
 */
public interface UserLoginDao extends DefaultDao {
	/**
	 * To Find User With
	 * 
	 * @param user
	 * @return
	 */
	UserLoginBean findUserByName(String loginName);
	/**
	 * 
	 * @return
	 */
	List<UserLoginBean> getUserLoginAll();
}
