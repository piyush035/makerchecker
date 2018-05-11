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
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public abstract class Zipper
{
	private static final int BUF_LEN = 1024;

	public static String unzip(byte[] content) throws IOException
	{
		return new String(inflate(content));
	}

	public static byte[] zip(String content) throws IOException
	{
		return deflate(content.getBytes());
	}

	public static void main(String[] args)
	{
		String str = "1234123412341234";
		try
		{
			byte[] buf = zip(str);
			System.out.println(new String(buf));
			System.out.println(unzip(buf));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static byte[] deflate(byte[] input) throws IOException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(input);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DeflaterOutputStream dos = new DeflaterOutputStream(bos);
		byte[] buffer = new byte[BUF_LEN];
		int bytesRead = bis.read(buffer);
		while (bytesRead > 0)
		{
			dos.write(buffer, 0, bytesRead);
			bytesRead = bis.read(buffer);
		}
		dos.close();
		return bos.toByteArray();
	}

	public static byte[] inflate(byte[] input) throws IOException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(input);
		InflaterInputStream iis = new InflaterInputStream(bis);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUF_LEN];
		int bytesRead = iis.read(buffer);
		while (bytesRead > 0)
		{
			bos.write(buffer, 0, bytesRead);
			bytesRead = iis.read(buffer);
		}
		bos.flush();
		return bos.toByteArray();
	}
}