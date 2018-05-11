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

/**
 * @author Piyush
 */
public class CommonProperties
{
	/**
	 * Allows you to know if your client is connected to a real server or if it's used in a development environment without a server.
	 * @return true if there is no real server.
	 */
	public static boolean isNoServerMode()
	{
		return System.getProperties().containsKey("no.server.mode");
	}

	/**
	 * Set the application to be used in a mode without a real weblogic server.
	 * This mode is used by the developpers only to accelerate the deployement time.
	 *
	 */
	public static void setNoServerMode()
	{
		System.setProperty("no.server.mode", "true");
	}

	/**
	 * Allows you to know if your code is running on the server or in the client.
	 * @return true if the current code runs on the server.
	 */
	public static boolean isRunningOnServer()
	{
		return System.getProperties().containsKey("weblogic.ProductionModeEnabled");
	}

	/**
	 * Returns the URL of the server cluster. Used by the client to locate its server.
	 * @return the server's cluster adress.
	 */
	public static String getClusterUrl()
	{
		return PropertiesManager.getProperty("nsicm.common.cluster.url");
	}

	public static boolean isApplicationModeDebug()
	{
		return "DEBUG".equals(PropertiesManager.getProperty("nsicm.common.application.mode"));
	}

	public static String getCommonDataSourceName()
	{
		return PropertiesManager.getProperty("nsicm.server.datasource.common.name");
	}
	
	public static String getAutoCommitDataSourceName()
	{
		return PropertiesManager.getProperty("nsicm.server.datasource.autocommit.name");
	}
}