package com.java.ee.maker.checker.persistance.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.persistance.UserDao;

/**
 * The Class UserDaoImpl.
 *
 * @author piygupta2 The Class UserDaoImpl.
 */
public class UserDaoImpl implements UserDao {
	
	/** The datasource. */
	@Autowired
	DataSource datasource;
	
	/** The jdbc template. */
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.java.ee.maker.checker.persistance.UserDao#register(com.java.ee.maker.checker.common.bean.User)
	 */
	@Override
	public void register(User user) {
		String sql = "insert into users values(?,?,?,?,?,?,?)";
	    jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
	    user.getLastname(), user.getEmail(),  user.getPhone() });
	    }
	    public User validateUser(Login login) {
	    String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
	    + "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
	    }
		
	}
	
	/* (non-Javadoc)
	 * @see com.java.ee.maker.checker.persistance.UserDao#login(java.lang.String, java.lang.String)
	 */
	@Override
	public com.java.ee.maker.checker.common.bean.User login(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
