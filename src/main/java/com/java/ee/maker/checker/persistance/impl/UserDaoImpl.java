package com.java.ee.maker.checker.persistance.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.ee.maker.checker.common.bean.Login;
import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.persistance.UserDao;

// TODO: Auto-generated Javadoc
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.persistance.UserDao#register(com.java.ee.maker.
	 * checker.common.bean.User)
	 */
	@Override
	public void register(User user) {
		String sql = "insert into users (`username`,`password`,`firstname`,`lastname`,`email`,`phone`) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
				user.getLastname(), user.getEmail(), user.getPhone() });
	}

	
	
	/* (non-Javadoc)
	 * @see com.java.ee.maker.checker.persistance.UserDao#validateUser(com.java.ee.maker.checker.common.bean.Login)
	 */
	public User validateUser(Login login) {
		String sql = "select * from users where username='" + login.getUsername() + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

}

class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getInt("phone"));
		user.setId(rs.getInt("id"));
		return user;
	}
}
