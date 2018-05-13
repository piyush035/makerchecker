/**
 * 
 */
package com.java.ee.maker.checker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.ee.maker.checker.common.bean.Transaction;
import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.common.constant.TransactionStatus;
import com.java.ee.maker.checker.common.constant.TransactionType;
import com.java.ee.maker.checker.persistance.TransactionDao;
import com.java.ee.maker.checker.service.TransactionService;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionServiceImpl.
 *
 * @author piygupta2
 */
public class TransactionServiceImpl implements TransactionService {

	/** The user dao. */
	@Autowired
	public TransactionDao transactionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.service.TransactionService#createTransaction(com.
	 * java.ee.maker.checker.service.TransactionService)
	 */
	@Override
	public boolean createTransaction(Transaction transaction) {
		transaction.setStatus(TransactionStatus.PENDING.getCode());
		transaction.setType(TransactionType.DEBIT.getCode());
		transactionDao.create(transaction);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.service.TransactionService#getAllTransaction(com.
	 * java.ee.maker.checker.common.bean.User)
	 */
	@Override
	public List<Transaction> getAllTransaction(User user) {
		return transactionDao.getAllTransaction(user, TransactionStatus.PENDING.getCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.ee.maker.checker.service.TransactionService#getTransaction(int)
	 */
	@Override
	public Transaction getTransaction(int id) {
		return transactionDao.getTransaction(id);
	}

	/* (non-Javadoc)
	 * @see com.java.ee.maker.checker.service.TransactionService#update(com.java.ee.maker.checker.common.bean.Transaction)
	 */
	@Override
	public boolean update(Transaction transaction) {
		return transactionDao.update(transaction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.service.TransactionService#update(com.java.ee.maker
	 * .checker.common.bean.Transaction)
	 */
	@Override
	public boolean approveTransaction(Transaction transaction) {
		transactionDao.update(transaction);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.java.ee.maker.checker.service.TransactionService#getAllTransactionOfUser(com.java.ee.maker.checker.common.bean.User)
	 */
	public List<Transaction> getAllTransactionOfUser(User user) {
		return transactionDao.getAllTransactionOfUser(user, TransactionStatus.PENDING.getCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.ee.maker.checker.service.TransactionService#rejectTransaction(com.
	 * java.ee.maker.checker.common.bean.Transaction)
	 */
	@Override
	public boolean rejectTransaction(Transaction transaction) {
		return true;
	}
}