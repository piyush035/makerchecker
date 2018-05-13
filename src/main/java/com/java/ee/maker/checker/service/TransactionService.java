package com.java.ee.maker.checker.service;

import com.java.ee.maker.checker.common.bean.Transaction;

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
}
