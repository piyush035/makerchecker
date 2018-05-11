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
package com.rennover.client.framework.application;

import com.rennover.client.framework.mvc.Controller;

/**
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments.
 * @author Piyush
 * 
 */
public class Application extends Controller
{
	private static Application s_application;
	/**
	 * 
	 */
	public Application()
	{
		super(null);
		setApplication(this);
	}

	/**
	 * @return
	 */
	public static Application getApplication()
	{
		return s_application;
	}

	/**
	 * @param application
	 */
	public static void setApplication(Application application)
	{
		s_application = application;
	}
}