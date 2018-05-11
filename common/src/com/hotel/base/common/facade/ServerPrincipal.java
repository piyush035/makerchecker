package com.hotel.base.common.facade;

import java.security.Principal;

/**
 * 
 * @author Piyush
 * 
 */
public class ServerPrincipal implements Principal {

	/** */
	private String userLogin;

	public ServerPrincipal(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getName() {
		return userLogin;
	}

	public boolean equals(Object another) {
		if (another instanceof Principal) {
			return userLogin.equals(((Principal) another).getName());
		}

		return false;
	}

	public String toString() {
		return userLogin;
	}

	public int hashCode() {
		return userLogin.hashCode();
	}
}