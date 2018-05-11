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
package com.rennover.hotel.common.helper;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;


/**
 * Resource path example: java/lang/Object.class
 * Resource URL example: file:/D:/Dev/jdk14/jre/lib/rt.jar!/java/lang/Object.class
 *
 * @author dattias
 */
public abstract class ResourceHelper
{
	private static PackageFilter s_packageFilter = new PackageFilter();

    /**
     * Returns the resource path associated to a resource local to a class.
     *
     * @param localClass class local to the resource (i.e. same package)
     * @param resourceName the name of the resource in its package (not the path)
     * @return the resource path
     */
    public static String getLocalResourcePath(Class localClass, String resourceName)
	{
		return localClass.getPackage().getName().replace('.', '/') + '/' + resourceName;
	}

    /**
	 * Returns an InputStream attached to the resource. Returns null if not
	 * found.
	 * 
	 * @param resourcePath
	 *            the resource path in the classpath
	 * @return a input stream attached to the resource
	 */
    public static InputStream getResourceAsStream(String resourcePath)
	{
		Logger.log(ResourceHelper.class, "Loading of " + resourcePath);

		return ResourceHelper.class.getClassLoader().getResourceAsStream(resourcePath);
	}

    /**
	 * Returns an input stream attached to the resource. Returns null if not
	 * found. The resource will be found in the package of the first class
	 * parameter.
	 * 
	 * @param localClass
	 *            class local to the resource (i.e. same package)
	 * @param resourceName
	 *            name of the resource
	 * @return an input stream attached to the resource
	 */
    public static InputStream getResourceAsStream(Class localClass, String resourceName)
	{
		return getResourceAsStream(getLocalResourcePath(localClass, resourceName));
	}

    /**
	 * Returns the URL of the resource. Returns null if not found.
	 * 
	 * @param resourcePath
	 *            the resource path in the classpath
	 * @return the URL of the resource
	 */
    public static URL getResourceUrl(String resourcePath)
	{
		return ClassLoader.getSystemResource(resourcePath);
	}

    /**
     * Returns the URL of the resource in the class loading
     * context of the context class.
     * Returns null if not found.
     *
     * @param context the class loading context
     * @param resourcePath the resource path in the classpath
     * @return the URL of the resource
     */
    public static URL getResourceUrl(Class context, String resourcePath)
	{
		return context.getClassLoader().getResource(resourcePath);
	}

    /**
     * Returns all the root paths to the classpath directories.
     * No classpath jar path is returned.
     *
     * @return a list of classpath root paths
     */
    public static List getClasspathRootPathList()
	{
		try
		{
			List list = new ArrayList();
			Enumeration enum1 = ClassLoader.getSystemResources("");

			while (enum1.hasMoreElements())
			{
				URL url = (URL) enum1.nextElement();
				list.add(url.getPath());
			}

			return list;
		} catch (IOException e)
		{
			throw new TechnicalException(e);
		}
	}

    /**
	 * ATTENTION, CA NE MARCHE PAS DANS LES JARS
	 */
    public static List getResourcePathList(String searchPackage, String suffix)
	{
		List result = new ArrayList();
		FilenameFilter resourceFilter = new SuffixFilenameFilter(suffix);

		try
		{
			Enumeration enum1 = ClassLoader.getSystemResources(searchPackage);

			while (enum1.hasMoreElements())
			{
				URL url = (URL) enum1.nextElement();
				File root = new File(url.getPath());
				searchInPackage(result, root, resourceFilter);
			}

			return result;
		} catch (IOException e)
		{
			throw new TechnicalException(e);
		}
	}

	private static void searchInPackage(List result, File root, FilenameFilter filenameFilter)
	{
		File[] resources = root.listFiles(filenameFilter);

		if (resources != null)
		{
			for (int i = 0; i < resources.length; ++i)
			{
				result.add(resources[i].getAbsolutePath());
			}
		}

		File[] packages = root.listFiles(s_packageFilter);

		if (packages != null)
		{
			for (int i = 0; i < packages.length; ++i)
			{
				searchInPackage(result, packages[i], filenameFilter);
			}
		}
	}
}

class PackageFilter implements FilenameFilter
{
	public boolean accept(File dir, String name)
	{
		return name.indexOf(".") == -1;
	}
}

class SuffixFilenameFilter implements FilenameFilter
{
	private String m_suffix;

	SuffixFilenameFilter(String suffix)
	{
		m_suffix = suffix;
	}

	public boolean accept(File file, String filename)
	{
		return filename.endsWith(m_suffix);
	}
}
