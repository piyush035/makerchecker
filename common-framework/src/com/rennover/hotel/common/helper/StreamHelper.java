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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * ...
 * 
 * @author dattias
 */
public class StreamHelper
{
	public static void write(Reader reader, Writer writer) throws IOException
	{
		int n = 0;
		char[] buffer = new char[1024];
		while (true)
		{
			n = reader.read(buffer);
			if (n == -1)
			{
				return;
			}
			writer.write(buffer, 0, n);
		}
	}

	public static void write(InputStream in, OutputStream out) throws IOException
	{
		int n = 0;
		byte[] buffer = new byte[1024];
		while (true)
		{
			n = in.read(buffer);
			if (n == -1)
			{
				return;
			}
			out.write(buffer, 0, n);
		}
	}
}
