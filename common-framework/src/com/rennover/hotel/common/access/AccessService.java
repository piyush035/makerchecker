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
package com.rennover.hotel.common.access;

import java.util.List;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AccessService
{
	private static AccessManager s_accessManager = new DefaultAccessManager();

	public static boolean checkAccess(List serviceAccessList)
	{
		return getAccessManager().checkAccess(serviceAccessList);
	}

	public static boolean checkAccessAtOneService(List serviceAccessList)
	{
		return getAccessManager().checkAccessAtOneService(serviceAccessList);
	}

	/**
	 * @return
	 */
	public static AccessManager getAccessManager()
	{
		return s_accessManager;
	}

	/**
	 * @param manager
	 */
	public static void setAccessManager(AccessManager manager)
	{
		s_accessManager = manager;
	}

	static class DefaultAccessManager implements AccessManager
	{
		public boolean checkAccess(List serviceAccessList)
		{
			return true;
		}

		public boolean checkAccessAtOneService(List serviceAccessList)
		{
			return true;
		}
	}
}