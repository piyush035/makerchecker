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

public class ObjetNonTrouveException extends DomainException
{
	public ObjetNonTrouveException(Class type)
	{
		super(createMessage(type));
	}

	public ObjetNonTrouveException(ObjectId objectId)
	{
		super(createMessage(objectId));
	}

	private static String createMessage(Class aClass)
	{
		StringBuffer buffer = new StringBuffer("L'objet de type ");
		buffer.append(aClass);
		buffer.append(" n'existe pas dans la base");
		return buffer.toString();
	}

	private static String createMessage(ObjectId objectId)
	{
		StringBuffer buffer = new StringBuffer("L'objet ");
		buffer.append(objectId.getId());
		buffer.append(" de type ");
		buffer.append(objectId.getObjectType());
		buffer.append(" n'existe pas dans la base");
		return buffer.toString();
	}
}