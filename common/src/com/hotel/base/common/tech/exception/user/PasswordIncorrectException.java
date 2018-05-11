package com.hotel.base.common.tech.exception.user;


/**
 * this is a business exception and not IncoherenceException it is thrown by the
 * Realm so it must be a runtime exception.
 * 
 * @author Piyush
 * 
 */
public class PasswordIncorrectException extends ConnexionException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4301236272923409718L;

	/**
	 * 
	 */
	public PasswordIncorrectException() {
		super("Incorrect password");
	}
}
