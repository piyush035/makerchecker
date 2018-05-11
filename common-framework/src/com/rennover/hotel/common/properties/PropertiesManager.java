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
package com.rennover.hotel.common.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.helper.ResourceHelper;
import com.rennover.hotel.common.log.Logger;


/**
 *
 * @author Piyush
 */
public class PropertiesManager
{
	private static Properties s_properties = new Properties();

	public static int size()
	{
		return s_properties.size();
	}

	public static void use(String resourcePath)
	{
		Properties properties = filter(loadResourceAsProperties(resourcePath));
		s_properties.putAll(properties);
	}

	public static void use(Class localClass, String resourceName)
	{
		Properties properties = filter(loadResourceAsProperties(localClass, resourceName));
		s_properties.putAll(properties);
	}

	public static Properties load(String resourcePath)
	{
		return filter(loadResourceAsProperties(resourcePath));
	}

	public static Properties load(Class localClass, String resourceName)
	{
		return filter(loadResourceAsProperties(localClass, resourceName));
	}

    /*
	 * @param key @param defaultValue @return
	 * 
	 * @see java.util.Properties#getProperty(java.lang.String, java.lang.String)
	 */
    public static String getProperty(String key, String defaultValue)
	{
		return s_properties.getProperty(key, defaultValue);
	}

    /*
	 * @param key @return
	 * 
	 * @see java.util.Properties#getProperty(java.lang.String)
	 */
	public static String getProperty(String key)
	{
		return s_properties.getProperty(key);
	}

    /*
	 * @return
	 * 
	 * @see java.util.Properties#propertyNames()
	 */
    public static Enumeration propertyNames()
	{
		return s_properties.propertyNames();
	}

    /*
     * @param key
     * @param value
     * @return
     * @see java.util.Properties#setProperty(java.lang.String, java.lang.String)
     */
    public static synchronized Object setProperty(String key, String value)
	{
		return s_properties.setProperty(key, value);
	}

    /**
     * Returns the properties contained by a resource.
     *
     * @param resourcePath the resource path in the classpath
     * @return a Properties object
     */
    private static Properties loadResourceAsProperties(String resourcePath)
	{
		InputStream stream = ResourceHelper.getResourceAsStream(resourcePath);

		return loadResourceAsProperties(resourcePath, stream);
	}

    /**
	 * Returns the properties contained by a resource in the class loading
	 * context of the context class.
	 * 
	 * @param context
	 *            the class loading context
	 * @param resourcePath
	 *            the resource path in the classpath
	 * @return a Properties object
	 */
    private static Properties loadResourceAsProperties(Class localClass, String resourceName)
	{
		InputStream stream = ResourceHelper.getResourceAsStream(localClass, resourceName);

		return loadResourceAsProperties(resourceName, stream);
	}

	private static Properties loadResourceAsProperties(String resourcePath, InputStream stream)
	{
		if (stream == null)
		{
			throw new TechnicalException("Resource not found: " + resourcePath);
		}

		try
		{
			Logger.debug(ResourceHelper.class, "Reading ", resourcePath, "...");

			Properties properties = new Properties();
			properties.load(stream);

			return properties;
		} catch (IOException exc)
		{
			throw new TechnicalException("Error reading properties resource " + resourcePath, exc);
		}
	}

    /**
	 * @param properties
	 * @return
	 */
    private static Properties filter(Properties properties)
	{
		Iterator iterator = properties.entrySet().iterator();

		while (iterator.hasNext())
		{
			Map.Entry entry = (Map.Entry) iterator.next();
			String value = filter((String) entry.getValue());

			if (value != null)
			{
				entry.setValue(value);
			}
		}

		return properties;
	}

	private static String filter(String value)
	{
		int fromIndex = 0;
		StringBuffer buffer = new StringBuffer(value.length());

		while (true)
		{
			int firstAtIndex = value.indexOf('@', fromIndex);

			if (firstAtIndex == -1)
			{
				return buffer.append(value.substring(fromIndex)).toString();
			}

			buffer.append(value.substring(fromIndex, firstAtIndex));

			int secondAtIndex = value.indexOf('@', firstAtIndex + 1);

			if (secondAtIndex == -1)
			{
				return buffer.append(value.substring(firstAtIndex)).toString();
			}

			String property = getProperty(value.substring(firstAtIndex + 1, secondAtIndex));

			if (property == null)
			{
				buffer.append(value.substring(firstAtIndex, secondAtIndex));
				fromIndex = secondAtIndex;
			} else
			{
				buffer.append(property);
				fromIndex = secondAtIndex + 1;
			}
		}
	}
	
	/**
	 * Added for properties externalisation.
	 * Get all the propeties
	 * @param properties
	 */
	public static Properties getProperties()
	{
		return s_properties;
	}

    /**
	 * Added for properties externalisation
	 * Add properties to alredy added properties.
	 * @param properties
	 */
	public  static void add(Properties properties)
	{
		s_properties.putAll(filter(properties));
	}
}
