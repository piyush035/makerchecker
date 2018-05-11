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
package com.rennover.hotel.common.facade;

/**
 * @author tcaboste
 * 
 * Class event for CallListener
 * 
 */
public class CallEvent
{
	private Throwable m_exception;

	/**
	 * Classic constructor for fireBefore and fireAfter
	 * 
	 * @param threadId
	 *            thread caller's id
	 */
	public CallEvent()
	{
	}

	/**
	 * Constructor for fireOnError
	 * 
	 * @param threadId
	 *            thread caller's id
	 * @param exception
	 *            throwed exception
	 */
	public CallEvent(Throwable exception)
	{
		m_exception = exception;
	}

	/**
	 * @return the throwed exception
	 */
	public Throwable getException()
	{
		return m_exception;
	}

}
