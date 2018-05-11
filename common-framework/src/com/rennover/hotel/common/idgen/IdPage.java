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
package com.rennover.hotel.common.idgen;

import java.io.Serializable;

public class IdPage implements Serializable
{
	private long m_firstId;

	private long m_currentId;

	private long m_lastId;

	public IdPage(long firstId, long lastId)
	{
		m_firstId = firstId;
		m_currentId = firstId;
		m_lastId = lastId;
	}

	public boolean hasNext()
	{
		if (m_currentId > m_lastId)
		{
			return false;
		}

		return true;
	}

	public long next()
	{
		if (hasNext())
		{
			m_currentId++;

			return m_currentId - 1;
		}

		throw new IllegalStateException("Page d'id entièrement utilisée");
	}

	public long getFirstId()
	{
		return m_firstId;
	}

	public long getLastId()
	{
		return m_lastId;
	}

	public String toString()
	{
		return "[IdPage, firstId=" + m_firstId + ", lastId=" + m_lastId + ", currentId=" + m_currentId + ']';
	}
}
