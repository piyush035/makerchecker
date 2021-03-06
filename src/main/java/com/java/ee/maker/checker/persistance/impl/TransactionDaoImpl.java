/**
 * 
 */
package com.java.ee.maker.checker.persistance.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.ee.maker.checker.common.bean.Transaction;
import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.persistance.TransactionDao;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionDaoImpl.
 *
 * @author piygupta2
 */
public class TransactionDaoImpl implements TransactionDao {

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
	 * com.java.ee.maker.checker.persistance.TransactionDao#create(com.java.ee.maker
	 * .checker.common.bean.Transaction)
	 */
	@Override
	public boolean create(Transaction transaction) {
		String sql = "insert into transaction (`name`,`accountNumber`,`type`,`status`,`amount`,`comment`,`userid`) values(?,?,?,?,?,?,?)";
		int result = jdbcTemplate.update(sql,
				new Object[] { transaction.getName(), transaction.getAccountNumber(), transaction.getType(),
						transaction.getStatus(), transaction.getAmount(), transaction.getRemark(),
						transaction.getUserId() });
		return result == 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.persistance.TransactionDao#getAllTransaction(com.
	 * java.ee.maker.checker.common.bean.User, int)
	 */
	@Override
	public List<Transaction> getAllTransaction(User user, int status) {
		String sql = "select * from transaction where userid <>'" + user.getId() + "' and status ='" + status + "'";
		List<Transaction> transactionsList = jdbcTemplate.query(sql, new TransactionMapper());
		return transactionsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.persistance.TransactionDao#update(com.java.ee.maker
	 * .checker.common.bean.Transaction)
	 */
	@Override
	public boolean update(Transaction transaction) {
		String sql = "UPDATE transaction SET status = ?, apprejnote=?,approverId=? where id=?";
		int result = jdbcTemplate.update(sql, new Object[] { transaction.getStatus(), transaction.getApprejnote(),
				transaction.getApproverId(), transaction.getId() });
		return result == 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.ee.maker.checker.persistance.TransactionDao#getTransaction(int)
	 */
	@Override
	public Transaction getTransaction(int id) {
		String sql = "select * from transaction where id ='" + id + "'";
		List<Transaction> transactionsList = jdbcTemplate.query(sql, new TransactionMapper());
		return transactionsList.size() > 0 ? transactionsList.get(0) : null;
	}

	/* (non-Javadoc)
	 * @see com.java.ee.maker.checker.persistance.TransactionDao#getAllTransactionOfUser(com.java.ee.maker.checker.common.bean.User, int)
	 */
	@Override
	public List<Transaction> getAllTransactionOfUser(User user, int code) {
		String sql = "select * from transaction where userid ='" + user.getId() + "' and status <>'" + code + "'";
		List<Transaction> transactionsList = jdbcTemplate.query(sql, new TransactionMapper());
		return transactionsList;
	}

}

class TransactionMapper implements RowMapper<Transaction> {
	public Transaction mapRow(ResultSet rs, int arg1) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setId(rs.getInt("id"));
		transaction.setName(rs.getString("name"));
		transaction.setAccountNumber(rs.getInt("accountNumber"));
		transaction.setType(rs.getInt("type"));
		transaction.setStatus(rs.getInt("status"));
		transaction.setAmount(rs.getBigDecimal("amount"));
		transaction.setRemark(rs.getString("comment"));
		transaction.setApprejnote(rs.getString("apprejnote"));
		transaction.setUserId(rs.getInt("userid"));
		transaction.setApproverId(rs.getInt("approverId"));
		return transaction;
	}
}
