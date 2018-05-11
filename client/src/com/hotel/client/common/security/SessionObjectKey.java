package com.hotel.client.common.security;

import com.rennover.hotel.common.helper.Wrapper;

/**
 * ...
 * 
 * @author dattias
 */
public class SessionObjectKey
{
	private static int s_keyGenerator = 0;

	private int m_key;

	public SessionObjectKey()
	{
		synchronized (this)
		{
			m_key = s_keyGenerator++;
		}
	}

	public int hashCode()
	{
		return m_key;
	}

	public String toString()
	{
		return Wrapper.toString(m_key);
	}
}
