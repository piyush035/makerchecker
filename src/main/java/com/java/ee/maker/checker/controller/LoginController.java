package com.java.ee.maker.checker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * The Class LoginController.
 */
@Controller
public class LoginController {

	/** The user service. */
	@Autowired
	UserService userService;

	/**
	 * Show login.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = null;
		if (null == user) {
			mav = new ModelAndView("login");
			mav.addObject("login", new Login());
		} else {
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		}
		return mav;
	}

	/**
	 * Login process.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param login
	 *            the login
	 * @return the model and view
	 */
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView mav = null;
		User user = userService.validateUser(login);
		if (null != user) {
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong!!");
		}
		request.getSession().setAttribute("user", user);
		return mav;
	}
}