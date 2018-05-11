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

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

abstract class WrapperException extends RuntimeException
{
	private ExceptionInfo m_info;

	private transient Exception m_wrappedException;

	public WrapperException()
	{
		this(null, null);
	}

	public WrapperException(String message)
	{
		this(message, null);
	}

	public WrapperException(Exception nested)
	{
		this(null, nested);
	}

	public WrapperException(String message, Exception nested)
	{
		super(message);
		m_wrappedException = nested;

		if (nested == null)
		{
			m_info = new ExceptionInfo(this);
		} else
		{
			ExceptionInfo nestedInfo;

			if (nested instanceof WrapperException)
			{
				nestedInfo = ((WrapperException) nested).m_info;
			} else
			{
				nestedInfo = new ExceptionInfo(nested);
			}

			m_info = new ExceptionInfo(this, nestedInfo);
		}
	}

	public Exception getCausedByException()
	{
		return m_wrappedException;
	}

	public void printStackTrace(PrintStream stream)
	{
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream));
		printExceptionInfo(m_info, writer);
		writer.close();
	}

	public void printStackTrace(PrintWriter writer)
	{
		printExceptionInfo(m_info, writer);
	}

	private void printExceptionInfo(ExceptionInfo info, PrintWriter writer)
	{
		writer.print(info.getName());

		if (info.getMessage() != null)
		{
			writer.println(": " + info.getMessage());
		} else
		{
			writer.println();
		}

		if (info.getStackTrace() != null)
		{
			writer.println(info.getStackTrace());
		}

		if (info.getNestedInfo() != null)
		{
			writer.println("------------- nested exception -------------");
			writer.println();
			printExceptionInfo(info.getNestedInfo(), writer);
		}
	}

	public String getMessage()
	{
		if (m_info == null)
		{
			return getInternalMessage();
		}

		return m_info.getFirstMessageInChain();
	}

	String getInternalMessage()
	{
		return super.getMessage();
	}

	void printInternalStackTrace(PrintWriter writer)
	{
		super.printStackTrace(writer);
	}
}
