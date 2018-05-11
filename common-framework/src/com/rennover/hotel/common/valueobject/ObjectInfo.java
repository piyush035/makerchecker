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

import java.io.Serializable;

public class ObjectInfo extends ValueObject implements Deletable, Serializable
{
	private ObjectId m_oid;
	private int m_version;
	private String m_lock;

	public String getLock()
	{
		return m_lock;
	}

	public ObjectId getObjectId()
	{
		return m_oid;
	}

	public int getVersion()
	{
		return m_version;
	}

	public void setLock(String lock)
	{
		m_lock = lock;
	}

	public void setObjectId(ObjectId oid)
	{
		m_oid = oid;
	}

	public void setVersion(int version)
	{
		m_version = version;
	}

	public boolean equals2(ValueObject o)
	{
		ObjectInfo oi = (ObjectInfo) o;
		return m_oid.equals(oi.getObjectId());
	}
}