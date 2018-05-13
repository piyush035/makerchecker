package com.java.ee.maker.checker.service;

import com.java.ee.maker.checker.common.bean.Login;
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
	 * @param login the login
	 * @return the user
	 */
	User validateUser(Login login);
}
