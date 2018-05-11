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

import java.io.IOException;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.facade.NSIZippedData;

/**
 * @author Gkarachi
 * 
 */
public abstract class DataCompressionHelper
{
	public static NSIZippedData compressNSIData(Object object) throws TechnicalException
	{
		if (null != object)
		{
			try
			{
				return new NSIZippedData(Zipper.deflate(Serializer.toByte(object)));
			} catch (IOException e)
			{
				throw new TechnicalException("Error during compression. " + e.getMessage());
			}
		}

		return null;
	}

	public static Object deCompressNSIData(NSIZippedData zippedData) throws TechnicalException
	{
		if (null != zippedData)
		{
			try
			{
				return Serializer.toObject(Zipper.inflate(zippedData.getZippedData()));
			} catch (IOException e)
			{
				throw new TechnicalException("Error during decompression: " + e.getMessage());
			}
		}

		return null;
	}
}
