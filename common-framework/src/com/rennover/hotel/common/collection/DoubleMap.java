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

import java.io.Serializable;
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
public class DoubleMap implements Serializable
{
	private Map m_mainMap = new HashMap();

	/**
	 * 
	 * @return the first key level
	 */
	public Set getFirstKeySet()
	{
		return m_mainMap.keySet();
	}

	/**
	 */
	public Object put(Object key1, Object value)
	{
		if (value instanceof Map)
		{
			return putMap(key1, (Map) value);
		}

		throw new IllegalArgumentException("value must be a map");
	}

	/**
	 */
	public Map putMap(Object key1, Map map)
	{
		return (Map) m_mainMap.put(key1, map);
	}

	/**
	 * TODO (dattias, le 10 nov. 2003) : renommer en put pour être homogène avec
	 * l'interface Map.
	 */
	public void set(Object key1, Object key2, Object value)
	{
		Map secondLevelMap = (Map) m_mainMap.get(key1);

		if (secondLevelMap == null)
		{
			secondLevelMap = new HashMap();
			m_mainMap.put(key1, secondLevelMap);
		}

		secondLevelMap.put(key2, value);
	}

	/**
	 * @return the value corresponding to key1 and key2
	 * @throws IncoherenceException
	 *             if no value is found
	 */
	public Object get(Object key1, Object key2)
	{
		Map secondLevelMap = getMap(key1);

		if (secondLevelMap == null)
		{
			return null;
		}

		return secondLevelMap.get(key2);
	}

	/**
	 * 
	 * @return all the values
	 */
	public List getAll()
	{
		List result = new LinkedList();
		Iterator eachSubMap = m_mainMap.values().iterator();

		while (eachSubMap.hasNext())
		{
			Map submap = (Map) eachSubMap.next();
			Iterator eachValue = submap.values().iterator();

			while (eachValue.hasNext())
			{
				result.add(eachValue.next());
			}
		}

		return result;
	}

	/**
	 * For compatibility with Map only. Use getMap.
	 */
	public Object get(Object key)
	{
		return m_mainMap.get(key);
	}

	/**
	 * 
	 */
	public Map getMap(Object key)
	{
		return (Map) get(key);
	}

	/**
	 * Remove the whole subset of values corresponding to the provided first
	 * level key
	 */
	public Object remove(Object firstLevelKey)
	{
		return m_mainMap.remove(firstLevelKey);
	}

	/**
	 * @return true if key1 is contained as a first level key
	 */
	public boolean containsKey(Object firstLevelKey)
	{
		return m_mainMap.containsKey(firstLevelKey);
	}
	
	public Map getmainMap()
	{
		return m_mainMap;
	}
}
