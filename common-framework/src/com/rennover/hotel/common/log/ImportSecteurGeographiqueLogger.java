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
package com.rennover.hotel.common.log;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.rennover.hotel.common.properties.PropertiesManager;

/**
 * @author omallassi
 *
 * permet de générere le rapport d'exécution des imports secteurs géographiques
 */
public class ImportSecteurGeographiqueLogger
{
	static
	{
		Properties properties = PropertiesManager.load(ImportSecteurGeographiqueLogger.class,
		        "importsecteurgeographiquelogger.server.properties");
		PropertyConfigurator.configure(properties);
	}

	public static void log(String message)
	{
		Logger.getInstance("importsecteurgeographiquelogger").info(message);
	}
}