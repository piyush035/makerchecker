
package com.rennover.client.framework.valueobject.model;

import java.util.StringTokenizer;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class PropertyPath
{
	public static final String PROPERTY_SEPARATOR = ".";

	private String[] m_propertyPathList;

	private String m_propertyFullPath;

	public PropertyPath(String propertyFullPath)
	{
		setPropertyFullPath(propertyFullPath);
	}

	public PropertyPath(String propertyPath, String property)
	{
		m_propertyFullPath = concate(propertyPath, property);
		String[] propertyPathList = new String[] { propertyPath, property };
		setPropertyPathList(propertyPathList);
	}

	public PropertyPath(String propertyPath1, String propertyPath2, String property)
	{
		String[] propertyPathList = new String[] { propertyPath1, propertyPath2, property };
		setPropertyPathList(propertyPathList);
	}

	public PropertyPath(String propertyPath1, String propertyPath2, String propertyPath3, String property)
	{
		String[] propertyPathList = new String[] { propertyPath1, propertyPath2, propertyPath3, property };
		setPropertyPathList(propertyPathList);
	}

	public PropertyPath(String[] propertyPathList)
	{
		setPropertyPathList(propertyPathList);
	}

	public void setPropertyPathList(String[] propertyPathList)
	{
		m_propertyPathList = propertyPathList;
		m_propertyFullPath = concate(propertyPathList);
	}

	public void setPropertyFullPath(String propertyFullPath)
	{
		m_propertyFullPath = propertyFullPath;
		m_propertyPathList = split(propertyFullPath);
	}

	public String toString()
	{
		return m_propertyFullPath;
	}

	public static String concate(String propertyPath, String property)
	{
		String[] propertyPathList = new String[] { propertyPath, property };
		return concate(propertyPathList);
	}

	public static String concate(String propertyPath1, String propertyPath2, String property)
	{
		String[] propertyPathList = new String[] { propertyPath1, propertyPath2, property };
		return concate(propertyPathList);
	}

	public static String concate(String propertyPath1, String propertyPath2, String propertyPath3, String property)
	{
		String[] propertyPathList = new String[] { propertyPath1, propertyPath2, propertyPath3, property };
		return concate(propertyPathList);
	}

	public static String concate(String[] propertyPathList)
	{
		int propertyCount = propertyPathList.length;
		StringBuffer propertyFullPathBuffer = new StringBuffer(propertyPathList[0]);
		for (int i = 1; i < propertyCount; i++)
		{
			propertyFullPathBuffer.append(PROPERTY_SEPARATOR);
			propertyFullPathBuffer.append(propertyPathList[i]);
		}
		return propertyFullPathBuffer.toString();
	}

	public static String[] split(String propertyFullPath)
	{
		StringTokenizer token = new StringTokenizer(propertyFullPath, PROPERTY_SEPARATOR);
		int tokenCount = token.countTokens();
		String[] propertyPath = new String[tokenCount];
		for (int i = 0; i < tokenCount; i++)
		{
			propertyPath[i] = token.nextToken();
		}
		return propertyPath;
	}

	public static boolean isPropertyPath(String propertyFullPath)
	{
		StringTokenizer token = new StringTokenizer(propertyFullPath, PROPERTY_SEPARATOR);
		return (token.countTokens() > 1);
	}

	public static String getPropertyFullPath(String[] propertyPathList)
	{
		return concate(propertyPathList);
	}

	public static String[] getPropertyPath(String propertyFullPath)
	{
		return split(propertyFullPath);
	}
}