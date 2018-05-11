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
package com.rennover.hotel.common.exception;

/**
 * 
 * @author dattias
 */
public class UserShowableException extends TechnicalException
{
	private boolean m_warning;

	public UserShowableException(String message)
	{
		super(message);
	}

	public UserShowableException(String message, boolean warning)
	{
		super(message);
		m_warning = warning;
	}

	public UserShowableException(String message, Exception exc)
	{
		super(message, exc);
	}

	public UserShowableException(String message, Exception exc, boolean warning)
	{
		super(message, exc);
		m_warning = warning;
	}

	public boolean isUserShowable()
	{
		return true;
	}

	public boolean isWarning()
	{
		return m_warning;
	}
}
