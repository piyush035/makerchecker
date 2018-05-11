/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.facade;

import java.security.Principal;

public interface RequestContext
{
	public String IMPOSSIBLE_HORS_J2EE_EXCEPTION = "Impossible hors contexte J2EE";

	public String IMPOSSIBLE_EN_J2EE_EXCEPTION = "Impossible en contexte J2EE";

	public Principal getCallerPrincipal();

	public boolean getRollbackOnly();

//	public UserTransaction getUserTransaction();

	public boolean isCallerInRole(String roleName);

	public void setRollbackOnly();
}