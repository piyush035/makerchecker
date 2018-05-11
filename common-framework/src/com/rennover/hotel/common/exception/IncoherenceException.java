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
 * Formalise un probleme d'execution occasionnee par une erreur de codage
 * ou par une incoherence des donnees (de la base notamment).
 * 
 * @todo (dattias, 21 janv. 2004): renommer cette classe BugException
 */
public class IncoherenceException extends TechnicalException
{
	public IncoherenceException(Exception exc)
	{
		super(exc);
	}

	public IncoherenceException(String msg)
	{
		super(msg);
	}

	public IncoherenceException(String msg, Exception exc)
	{
		super(msg, exc);
	}
}