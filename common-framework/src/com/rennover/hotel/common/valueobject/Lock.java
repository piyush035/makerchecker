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

public class Lock implements Serializable
{
	private long lockId;

	public Lock(long id)
	{
		lockId = id;
	}

	public long getId()
	{
		return lockId;
	}
	
	/* 
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if (obj == null || !obj.getClass().equals(Lock.class))
		{
			return false;
		}
		return lockId == ((Lock)obj).lockId;
	}

	/* 
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return (int)(lockId ^ (lockId >>> 32));
	}

	/* 
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "[Lock, lockId=" + lockId + ']';
	}

}