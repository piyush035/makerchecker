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

import com.rennover.hotel.common.valueobject.ObjectId;

/**
 * @todo (dattias, 21 janv. 2004): déplacer cette classe dans le fmk de
 *       persistence et la faire hériter de PersistenceException
 */
public class NotFoundException extends TechnicalException
{
	private ObjectId m_oid;

	public NotFoundException(ObjectId pOid)
	{
		this(pOid, "Object not found, id = " + pOid);
	}

	public NotFoundException(ObjectId pOid, String message)
	{
		super(message);
		this.m_oid = pOid;
	}

	public ObjectId getObjectNotFoundId()
	{
		return m_oid;
	}
}
