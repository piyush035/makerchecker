package com.hotel.base.common.facade;

import java.security.Principal;

/**
 * A DummyRequestContext should not be used in a context EJB. It will be used in
 * tests to simulate a query context by giving the name of the user.
 * 
 * @author Piyush
 */
public class ServerRequestContext implements RequestContext {

	/** */
	private Principal principal;

	/**
	 * 
	 * @param userLogin
	 */
	public ServerRequestContext(String userLogin) {
		principal = new ServerPrincipal(userLogin);
	}

	public Principal getCallerPrincipal() {
		return principal;
	}

	public boolean isCallerInRole(String roleName) {
		throw new IllegalStateException(IMPOSSIBLE_HORS_J2EE_EXCEPTION);
	}
}