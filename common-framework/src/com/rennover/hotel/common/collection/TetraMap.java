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
package com.rennover.hotel.common.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rennover.hotel.common.exception.IncoherenceException;

/**
 * @author jpietri
 */
public class TetraMap
{
	private TripleMap m_tripleMap = new TripleMap();

	/**
	 * @return the first key level
	 */
	public Set getFirstKeySet()
	{
		return m_tripleMap.getFirstKeySet();
	}

	/**
	 * 
	 */
	public void set(Object key1, Object key2, Object key3, Object key4, Object value)
	{
		Map fourthLevelMap = (Map) m_tripleMap.get(key1, key2, key3);
		if (fourthLevelMap == null)
		{
			fourthLevelMap = new HashMap();
			m_tripleMap.set(key1, key2, key3, fourthLevelMap);
		}
		fourthLevelMap.put(key4, value);
	}

	/**
	 * @return the value corresponding to key1, key2 and key3
	 * @throws IncoherenceException
	 *             if no value is found
	 */
	public Object get(Object key1, Object key2, Object key3, Object key4)
	{
		Map fourthLevelMap = (Map) m_tripleMap.get(key1, key2, key3);
		if (fourthLevelMap == null)
		{
			// Commmented code removed
			return null;
		}
		Object value = fourthLevelMap.get(key4);
		if (value == null)
		{
			// Commmented code removed
			return null;
		}
		return value;
	}

	/**
	 * @return all the values
	 */
	public List getAll()
	{
		List result = new LinkedList();
		Iterator eachSubSubMap = m_tripleMap.getAll().iterator();
		while (eachSubSubMap.hasNext())
		{
			Map subsubmap = (Map) eachSubSubMap.next();
			Iterator eachValue = subsubmap.values().iterator();
			while (eachValue.hasNext())
			{
				result.add(eachValue.next());
			}
		}
		return result;
	}
}
