/**
 * 
 */
package com.java.ee.maker.checker.persistance;

import com.java.ee.maker.checker.common.bean.User;

/**
 * The Interface UserDao.
 *
 * @author piygupta2
 */
public interface UserDao {

	/**
	 * Register.
	 *
	 * @param user
	 *            the user
	 */
	void register(User user);

	/**
	 * Validate user.
	 *
	 * @param login
	 *            the login
	 * @return the user
	 */
	User login(String userName, String password);
}
