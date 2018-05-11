package com.hotel.base.common.tech.exception.user;

import javax.security.auth.login.LoginException;

/**
 * Should not be a runtime exception not Rollbacker transaction.
 * @author Piyush
 */
public class ConnexionException extends LoginException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 796621832303714943L;
	/**
	 * 
	 */
	public ConnexionException() {
		super("Unknown problem due to the connection");
	}
	/**
	 * 
	 * @param msg
	 */
	public ConnexionException(String msg) {
		super(msg);
	}
}