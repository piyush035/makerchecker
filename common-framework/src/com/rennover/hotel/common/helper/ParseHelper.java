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
package com.rennover.hotel.common.helper;

import com.rennover.hotel.common.valueobject.SmallDecimal;

/**
 * ...
 * 
 * @author dattias
 */
public class ParseHelper
{
	public static Long parseLong(String number)
	{
		return number == null ? null : Long.valueOf(number);
	}

	public static Integer parseInteger(String number)
	{
		return number == null ? null : Integer.valueOf(number);
	}

	public static SmallDecimal parseSmallDecimal(String number)
	{
		return number == null ? null : new SmallDecimal(number);
	}
}