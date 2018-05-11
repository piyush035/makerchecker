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
package com.rennover.hotel.common.valueobject;

import com.rennover.hotel.common.exception.NonTechnicalException;

/**
 * @author dattias
 *
 */
public class DomainException extends NonTechnicalException
{
	public DomainException()
	{
		super();
	}

	public DomainException(String message)
	{
		super(message);
	}
	
	public DomainException(Throwable cause)
	{
		super(cause);
	}

	public DomainException(String message, Throwable cause)
	{
		super(message, cause);
	}
}