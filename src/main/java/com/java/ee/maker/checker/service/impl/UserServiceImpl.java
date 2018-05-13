/**
 * 
 */
package com.java.ee.maker.checker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.ee.maker.checker.common.bean.Login;
import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.persistance.UserDao;
import com.java.ee.maker.checker.service.UserService;

/**
 * The Class UserServiceImpl.
 *
 * @author piygupta2
 */
public class UserServiceImpl implements UserService {

	/** The user dao. */
	@Autowired
	public UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.service.UserService#register(com.java.ee.maker.
	 * checker.common.bean.User)
	 */
	public void register(User user) {
		userDao.register(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.service.UserService#validateUser(com.java.ee.maker.
	 * checker.common.bean.Login)
	 */
	public User validateUser(Login login) {
		final User user = userDao.validateUser(login);
		if (null != user && login.getPassword().equals(user.getPassword())) {
			return user;
		}
		return null;
	}

}
