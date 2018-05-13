/**
 * 
 */
package com.java.ee.maker.checker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.ee.maker.checker.common.bean.Login;
import com.java.ee.maker.checker.common.bean.Transaction;
import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.common.constant.TransactionStatus;
import com.java.ee.maker.checker.service.TransactionService;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionController.
 *
 * @author piygupta2
 */
@Controller
public class TransactionController {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	/** The transaction service. */
	@Autowired
	public TransactionService transactionService;

	/**
	 * Creates the transaction.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/createTransaction", method = RequestMethod.GET)
	public ModelAndView createTransaction(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("createTransaction");
		mav.addObject("transaction", new Transaction());
		return mav;
	}

	/**
	 * Creates the transaction process.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param transaction
	 *            the transaction
	 * @return the model and view
	 */
	@RequestMapping(value = "/createTransactionProcess", method = RequestMethod.POST)
	public ModelAndView createTransactionProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("transaction") Transaction transaction) {
		logger.info("" + transaction);
		User user = (User) request.getSession().getAttribute("user");
		transaction.setUserId(user.getId());
		transactionService.createTransaction(transaction);
		return new ModelAndView("welcome", "firstname", transaction.getName());
	}

	/**
	 * Creates the transaction process.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/viewAllTransaction", method = RequestMethod.GET)
	public ModelAndView viewAllTransaction(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		if (null == user) {
			mav = new ModelAndView("login");
			mav.addObject("login", new Login());
		} else {
			mav = new ModelAndView("viewalltransaction");
			mav.addObject("alltransaction", transactionService.getAllTransaction(user));
		}
		return mav;
	}

	/**
	 * Approve transaction.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the model and view
	 */
	@RequestMapping(value = "/approveTransaction", method = RequestMethod.GET)
	public ModelAndView approveTransaction(HttpServletRequest request, HttpServletResponse response,
			@RequestAttribute(name = "id") int id) {
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		if (null == user) {
			mav = new ModelAndView("login");
			mav.addObject("login", new Login());
		} else {
			mav = new ModelAndView("approvetransaction");
			Transaction transaction = transactionService.getTransaction(id);
			mav.addObject("transaction", transaction);
		}
		return mav;
	}

	/**
	 * Reject transaction.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/rejectTransaction", method = RequestMethod.GET)
	public ModelAndView rejectTransaction(HttpServletRequest request, HttpServletResponse response,
			@RequestAttribute(name = "id") int id) {
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		if (null == user) {
			mav = new ModelAndView("login");
			mav.addObject("login", new Login());
		} else {
			mav = new ModelAndView("rejecttransaction");
			Transaction transaction = transactionService.getTransaction(id);
			mav.addObject("transaction", transaction);
		}
		return mav;
	}

	/**
	 * Approve transaction process.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param transaction
	 *            the transaction
	 * @return the model and view
	 */
	@RequestMapping(value = "/approveTransactionProcess", method = RequestMethod.POST)
	public ModelAndView approveTransactionProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestAttribute(name = "id") int id, @RequestAttribute(name = "apprejnote") String apprejnote) {
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		if (null == user) {
			mav = new ModelAndView("login");
			mav.addObject("login", new Login());
		} else {
			mav = new ModelAndView("success");
			Transaction transaction = new Transaction();
			transaction.setId(id);
			transaction.setStatus(TransactionStatus.ACCEPTED.getCode());
			transaction.setApproverId(user.getId());
			transaction.setApprejnote(apprejnote);
			boolean flag = transactionService.update(transaction);
			if (flag) {
				mav.addObject("message", "approved");
			} else {
				mav.addObject("message", "failed");
			}
		}
		return mav;
	}

	/**
	 * Reject transaction process.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param transaction
	 *            the transaction
	 * @return the model and view
	 */
	@RequestMapping(value = "/rejectTransactionProcess", method = RequestMethod.POST)
	public ModelAndView rejectTransactionProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestAttribute(name = "id") int id, @RequestAttribute(name = "apprejnote") String apprejnote) {
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		if (null == user) {
			mav = new ModelAndView("login");
			mav.addObject("login", new Login());
		} else {
			mav = new ModelAndView("success");
			Transaction transaction = new Transaction();
			transaction.setId(id);
			transaction.setStatus(TransactionStatus.REJECTED.getCode());
			transaction.setApproverId(user.getId());
			transaction.setApprejnote(apprejnote);
			boolean flag = transactionService.update(transaction);
			if (flag) {
				mav.addObject("message", "rejected");
			} else {
				mav.addObject("message", "failed");
			}
		}
		return mav;
	}
}
