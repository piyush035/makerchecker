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
package com.rennover.hotel.common.valueobject;

public class RelationEncoreActiveException extends DomainException
{
	//object vers lequel il y a encore des relations actives
	private DomainObject m_domainObject;

	public RelationEncoreActiveException()
	{
		super("Encore au moins une relation active");
	}

	/**
	 * 
	 * @param msg
	 * @param object peut etre null si pas besoin d'etre retourné au client.
	 */
	public RelationEncoreActiveException(String msg, DomainObject object)
	{
		super(msg);

		m_domainObject = object;
	}

	/**
	 * @return
	 */
	public DomainObject getAssociatedObject()
	{
		return m_domainObject;
	}
}