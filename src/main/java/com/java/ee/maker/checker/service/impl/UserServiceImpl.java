/**
 * 
 */
package com.java.ee.maker.checker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.persistance.UserDao;
import com.java.ee.maker.checker.service.UserService;

/**
 * @author piygupta2
 *
 */
public class UserServiceImpl implements UserService {

	  @Autowired
	  public UserDao userDao;

	  public void register(User user) {
	    userDao.register(user);
	  }

	  public User validateUser(String userName, String password) {
	    return null;//userDao.validateUser(null);
	  }

	}
