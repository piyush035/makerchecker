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

import com.java.ee.maker.checker.common.bean.Login;
import com.java.ee.maker.checker.common.bean.User;
import com.java.ee.maker.checker.service.UserService;

/**
 * The Class RegistrationController.
 *
 * @author piygupta2 The Class RegistrationController.
 */
@Controller
public class RegistrationController {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	/** The user service. */
	@Autowired
	public UserService userService;

	/**
	 * Show register.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		User user =(User)request.getSession().getAttribute("user");
		ModelAndView mav = null;
		if (null == user) {
			mav = new ModelAndView("register");
			mav.addObject("user", new User());
		} else {
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		}
		return mav;
	}

	/**
	 * Adds the user.
	 *
	 * @param request the request
	 * @param response the response
	 * @param user the user
	 * @return the model and view
	 */
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		logger.info("addUser {}" + user);
		userService.register(user);
		return new ModelAndView("welcome", "firstname", user.getFirstname());
	}
}
