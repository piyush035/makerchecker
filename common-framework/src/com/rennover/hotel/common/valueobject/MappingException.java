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

import com.rennover.hotel.common.exception.TechnicalException;

public class MappingException extends TechnicalException
{
	public MappingException(String message, Exception nested)
	{
		super(message, nested);
	}

	public MappingException(String message)
	{
		super(message);
	}
}