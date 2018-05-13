/*
 * 
 */
package com.java.ee.maker.checker.service;

import java.util.List;

import com.java.ee.maker.checker.common.bean.Transaction;
import com.java.ee.maker.checker.common.bean.User;

/**
 * The Interface TransactionService.
 *
 * @author piygupta2
 */
public interface TransactionService {

	/**
	 * Creates the transaction.
	 *
	 * @param transactionService
	 *            the transaction service
	 * @return true, if successful
	 */
	boolean createTransaction(Transaction transactionService);

	/**
	 * Gets the transaction.
	 *
	 * @param id
	 *            the id
	 * @return the transaction
	 */
	Transaction getTransaction(int id);

	/**
	 * Gets the all transaction.
	 *
	 * @param user
	 *            the user
	 * @return the all transaction
	 */
	List<Transaction> getAllTransaction(User user);

	/**
	 * Approve transaction.
	 *
	 * @param transaction
	 *            the transaction
	 * @return true, if successful
	 */
	boolean approveTransaction(Transaction transaction);

	/**
	 * Reject transaction.
	 *
	 * @param transaction
	 *            the transaction
	 * @return true, if successful
	 */
	boolean rejectTransaction(Transaction transaction);

	/**
	 * Update.
	 *
	 * @param transaction
	 *            the transaction
	 * @return true, if successful
	 */
	boolean update(Transaction transaction);
}
