package com.java.ee.maker.checker.service;

import com.java.ee.maker.checker.common.bean.User;

/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Register.
	 *
	 * @param user the user
	 */
	void register(User user);

	/**
	 * Validate user.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the user
	 */
	User validateUser(String userName, String password);
}
