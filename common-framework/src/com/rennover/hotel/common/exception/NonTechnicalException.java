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

/**
 * Super-classe de DomainException et ContractException. La méthode getMessage
 * est redéfinie pour éviter d'avoir la stack trace du serveur.
 * 
 * @author dattias
 */
public abstract class NonTechnicalException extends Exception implements ExceptionBaseInterface
{
	private String m_message;

	private transient Throwable m_cause;

	public NonTechnicalException()
	{
		super();
	}

	public NonTechnicalException(String message)
	{
		this(message, null);
	}

	public NonTechnicalException(Throwable cause)
	{
		this(null, cause);
	}

	public NonTechnicalException(String message, Throwable cause)
	{
		super(message);
		m_message = message;
		m_cause = cause;
	}

	public String getMessage()
	{
		return m_message;
	}

	public String getInternalMessage()
	{
		return super.getMessage();
	}

	public boolean isUserShowable()
	{
		return false;
	}

	public boolean isWarning()
	{
		return false;
	}

	public String getUserShowableMessage()
	{
		return getMessage();
	}

	public Throwable getCause()
	{
		return m_cause;
	}
}