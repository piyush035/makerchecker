/**
 * 
 */
package com.java.ee.maker.checker.persistance;

import java.util.List;

import com.java.ee.maker.checker.common.bean.Transaction;
import com.java.ee.maker.checker.common.bean.User;

/**
 * The Interface TransactionDao.
 *
 * @author piygupta2
 */
public interface TransactionDao {

	/**
	 * Creates the.
	 *
	 * @param transaction
	 *            the transaction
	 * @return true, if successful
	 */
	boolean create(Transaction transaction);

	/**
	 * Gets the all transaction.
	 *
	 * @param user
	 *            the user
	 * @param status
	 *            the status
	 * @return the all transaction
	 */
	List<Transaction> getAllTransaction(User user, int status);

	/**
	 * Update.
	 *
	 * @param transaction
	 *            the transaction
	 * @return true, if successful
	 */
	boolean update(Transaction transaction);
	
	/**
	 * Gets the transaction.
	 *
	 * @param id the id
	 * @return the transaction
	 */
	Transaction getTransaction(int id);

	/**
	 * Gets the all transaction of user.
	 *
	 * @param user the user
	 * @param code the code
	 * @return the all transaction of user
	 */
	List<Transaction> getAllTransactionOfUser(User user, int code);
}
