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

public class Assert
{
	/**
	 * Check if the condition is true. If not, throws an
	 * IllegalArgumentException with a "Assert check failed" message.
	 * 
	 * @param condition
	 *            to be checked
	 */
	public static void check(boolean condition)
	{
		if (!condition)
		{
			throw new IllegalArgumentException("Assert check failed");
		}
	}

	/**
	 * Check if the condition is true. If not, throws an
	 * IllegalArgumentException with the exception message argument.
	 * 
	 * @param condition
	 *            to be checked
	 * @param exceptionMessage
	 *            message to use if condition false
	 */
	public static void check(boolean condition, String exceptionMessage)
	{
		if (!condition)
		{
			throw new IllegalArgumentException(exceptionMessage);
		}
	}

	/**
	 * Check if the object argument is not null. If null, throws an
	 * IllegalArgumentException with a "Assert check failed" message.
	 * 
	 * @param object
	 *            to be checked if not null
	 */
	public static void checkNotNull(Object object)
	{
		if (object == null)
		{
			throw new IllegalArgumentException("Assert check failed");
		}
	}

	/**
	 * Check if the object argument is not null. If null, throws an
	 * IllegalArgumentException with the exception message argument.
	 * 
	 * @param object
	 *            to be checked if not null
	 * @param exceptionMessage
	 *            message to use if condition false
	 */
	public static void checkNotNull(Object object, String exceptionMessage)
	{
		if (object == null)
		{
			throw new IllegalArgumentException(exceptionMessage);
		}
	}
}