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

import java.io.Serializable;

/**
 * @author dattias
 */
class ExceptionInfo implements Serializable
{
	private String m_name;

	private String m_message;

	private String m_stackTrace;

	private ExceptionInfo m_nested;

	ExceptionInfo(Throwable throwable)
	{
		m_name = throwable.getClass().getName();
		m_message = ExceptionHelper.getInternalMessage(throwable);
		m_stackTrace = ExceptionHelper.getInternalStackTraceOnly(throwable);
	}

	ExceptionInfo(Throwable throwable, ExceptionInfo nested)
	{
		this(throwable);
		m_nested = nested;
	}

	String getName()
	{
		return m_name;
	}

	String getMessage()
	{
		return m_message;
	}

	String getFirstMessageInChain()
	{
		if (m_message != null)
		{
			return m_message;
		}

		if (m_nested != null)
		{
			return m_nested.getFirstMessageInChain();
		}

		return null;
	}

	String getStackTrace()
	{
		return m_stackTrace;
	}

	ExceptionInfo getNestedInfo()
	{
		return m_nested;
	}
}