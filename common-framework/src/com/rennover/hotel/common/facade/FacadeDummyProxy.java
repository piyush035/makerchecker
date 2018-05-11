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


public abstract class FacadeDummyProxy extends FacadeProxy
{
	public FacadeDummyProxy(FacadeLocator facadeLocator)
	{
		//super(facadeLocator);
	}

/*	*//**
	 * Initialise le contexte de requète.
	 *//*
	protected final void initRequestContext()
	{
		String userLogin = getFacadeLocator().getUserLogin();
		DummyRequestContext context = new DummyRequestContext(userLogin);
		RequestContextManager.setRequestContext(context);
	}

	*//**
	 * Indique que la transaction doit faire un rollback en fin de requète.
	 *//*
	protected final void setRollbackOnly()
	{
		RequestContextManager.getRequestContext().setRollbackOnly();
	}

	*//**
	 * Nettoie le contexte de requète.
	 *//*
	protected final void commitRollbackRequest()
	{
		RequestContextManager.commitRollbackRequest();
	}

	protected final Object clone(Object value)
	{
		return Serializer.clone(value);
	}

	protected final byte clone(byte value)
	{
		return value;
	}

	protected final short clone(short value)
	{
		return value;
	}

	protected final int clone(int value)
	{
		return value;
	}

	protected final long clone(long value)
	{
		return value;
	}

	protected final float clone(float value)
	{
		return value;
	}

	protected final double clone(double value)
	{
		return value;
	}

	protected final boolean clone(boolean value)
	{
		return value;
	}

	protected final char clone(char value)
	{
		return value;
	}
*/}