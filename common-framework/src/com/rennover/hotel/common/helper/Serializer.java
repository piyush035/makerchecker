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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;

/**
 * @author Piyush
 */
public abstract class Serializer
{
	private static long s_fromBegining;

	public static Object clone(Object object)
	{
		return toObject(toByte(object));
	}

	public static byte[] toByte(Object object)
	{
		ObjectOutputStream stream = null;
		try
		{

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			stream = new ObjectOutputStream(buffer);
			stream.writeObject(object);
			stream.flush();
			return buffer.toByteArray();
		} catch (Exception e)
		{
			throw new TechnicalException("Unable to serialize an object", e);
		} finally
		{
			try
			{
				stream.close();
			} catch (Exception e)
			{
				throw new TechnicalException("Unable to close an ObjectOutputStream", e);
			}
		}
	}

	public static Object toObject(byte[] bytes)
	{
		debug(bytes.length);
		ObjectInputStream stream = null;
		try
		{
			ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
			stream = new ObjectInputStream(buffer);
			return stream.readObject();
		} catch (Exception e)
		{
			throw new TechnicalException("Unable to de-serialize an object", e);
		} finally
		{
			try
			{
				stream.close();
			} catch (Exception e)
			{
				throw new TechnicalException("Unable to close a ObjectInputStream", e);
			}
		}
	}

	private static void debug(int size)
	{
		s_fromBegining += size;
		Logger.debugValue(Serializer.class, "Object serialization size", size);
		Logger.debugValue(Serializer.class, "Bytes transfered from begining", s_fromBegining);
	}
}