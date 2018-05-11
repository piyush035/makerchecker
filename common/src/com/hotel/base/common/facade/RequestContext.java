/**
 * 
 */
package com.hotel.base.common.facade;

import java.security.Principal;

/**
 * 
 * @author Piyush
 * 
 */
public interface RequestContext {
	public String IMPOSSIBLE_HORS_J2EE_EXCEPTION = "Unable out of context J2EE.";

	public String IMPOSSIBLE_EN_J2EE_EXCEPTION = "Impossible J2EE context";

	public Principal getCallerPrincipal();

	public boolean isCallerInRole(String roleName);
}