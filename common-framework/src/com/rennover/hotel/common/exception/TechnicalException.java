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
 * Racine des exceptions techniques.
 * 
 * @author Jerome Pietri
 */
public class TechnicalException extends WrapperException implements ExceptionBaseInterface
{
	public TechnicalException()
	{
		super();
	}

	public TechnicalException(Exception exc)
	{
		super(exc);
	}

	public TechnicalException(String message)
	{
		super(message);
	}

	public TechnicalException(String message, Exception exc)
	{
		super(message, exc);
	}

	public boolean isUserShowable()
	{
		return false;
	}

	public boolean isWarning()
	{
		return false;
	}

	public String getUserShowableMessage()
	{
		return getMessage();
	}

	public Throwable getCause()
	{
		return getCausedByException();
	}
}