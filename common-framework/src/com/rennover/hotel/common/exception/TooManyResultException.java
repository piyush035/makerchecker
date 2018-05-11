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

public class TooManyResultException extends UserShowableException
{
	private QueryInfo m_query;

	public TooManyResultException()
	{
		this(null);
	}

	public TooManyResultException(QueryInfo query)
	{
		super("Le résultat de cette recherche est trop volumineux.\nVeuillez affiner vos critères de recherche.", true);
		m_query = query;
	}
}
