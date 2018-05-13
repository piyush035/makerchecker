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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.ee.maker.checker.common.bean.Transaction;
import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.service.TransactionService;

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
	 * @param request            the request
	 * @param response            the response
	 * @param transaction the transaction
	 * @return the model and view
	 */
	@RequestMapping(value = "/createTransactionProcess", method = RequestMethod.POST)
	public ModelAndView createTransactionProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("transaction") Transaction transaction) {
		logger.info(""+transaction);
		User user =(User)request.getSession().getAttribute("user");
		transaction.setUserId(user.getId());
		transactionService.createTransaction(transaction);
		return new ModelAndView("welcome", "firstname", transaction.getName());
	}
	
	/**
	 * Creates the transaction process.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/viewAllTransaction", method = RequestMethod.POST)
	public ModelAndView createTransactionProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("viewAllTransaction");
		mav.addObject("transaction", new Transaction());
		return mav;
	}
}
