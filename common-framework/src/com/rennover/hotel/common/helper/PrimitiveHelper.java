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

/**
 * ...
 * 
 * @author dattias
 * 
 * TODO (dattias, le 20 ao�t 2003) : Renommer cette classe en MathHelper.
 */
public class PrimitiveHelper
{
	public static int compare(long l1, long l2)
	{
		if (l1 > l2)
		{
			return 1;
		} else if (l1 < l2)
		{
			return -1;
		} else
		{
			return 0;
		}
	}

	/**
	 * Remplace Math.pow(double,double) pour �viter les impr�cisions du double.
	 * Attention, les exposant ngatifs sont interdits.
	 * 
	 * @param value
	 * @param expo
	 * @return
	 */
	public static long pow(long value, long expo)
	{
		if (expo < 0)
		{
			throw new IllegalArgumentException("Exposant n�gatif : " + expo);
		}

		if (expo == 0)
		{
			return 1;
		}

		long result = value;
		long absValue = Math.abs(value);

		while (--expo > 0)
		{
			result = result * absValue;
		}

		return result;
	}
}