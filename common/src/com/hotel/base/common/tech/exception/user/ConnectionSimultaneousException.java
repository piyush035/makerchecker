package com.hotel.base.common.tech.exception.user;


/**
 * this is a business exception and not IncoherenceException   It is thrown by
 * the Realm so it must be a runtime exception
 * 
 * @author Piyush
 */
public class ConnectionSimultaneousException extends ConnexionException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8409143056376045997L;

	public ConnectionSimultaneousException() {
		super("Attempted simultaneous connection.");
	}
}
