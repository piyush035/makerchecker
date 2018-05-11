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
package com.rennover.hotel.common.version;


public class ApplicationVersion
{/*
	private static final String CLASS_VERSION_LABEL = "CLASS_VERSION";
	private static boolean isInitialized = false;

	*//**
	* Liste des classes correspondant au patch
	*//*
	private static final Class[] patch = {  };

	*//**
	* Renvoie la description de tous les param�tres syst�mes de la plate-forme.
	* @return les param�tres syst�mes de Java avec leur valeurs sous forme d'une chaine de caract�re.
	*//*
	private static String getSystemProperties()
	{
		StringBuffer returnValue = new StringBuffer("Param�tres syst�mes" + System.getProperty("line.separator"));
		Properties propSystem = System.getProperties();
		for (Enumeration enum = propSystem.keys(); enum.hasMoreElements();)
		{
			String key = enum.nextElement().toString();
			String value = propSystem.get(key).toString();
			returnValue.append("Propriete : ");
			returnValue.append(key);
			returnValue.append("\tValeur : ");
			returnValue.append(value);
			returnValue.append(System.getProperty("line.separator"));
		}

		return returnValue.toString();
	}

	*//**
	* Renvoie la description de tous les param�tres Weblogic de la plate-forme.
	* TODO : � impl�menter
	* @return les param�tres syst�mes de Java avec leur valeurs sous forme d'une chaine de caract�re.
	*//*
	private static String getWeblogicProperties()
	{
		StringBuffer returnValue = new StringBuffer();

		return returnValue.toString();
	}

	private static String getNetworkProperties()
	{
		StringBuffer returnValue = new StringBuffer();
		try
		{
			InetAddress localAdress = InetAddress.getLocalHost();
			returnValue.append("Adresse Principale Locale : " + localAdress + "\n");
		}
		catch (UnknownHostException exc)
		{
			returnValue.append("Impossible de r�cup�rer l'adresse reseau locale.");
		}
		try
		{
			InetAddress[] allAdresses = InetAddress.getAllByName("localhost");
			for (int cpt = 0; cpt < allAdresses.length; cpt++)
			{
				returnValue.append("Adresse " + cpt + "  : " + allAdresses[cpt] + "\n");
			}
		}
		catch (UnknownHostException exc)
		{
			returnValue.append("Impossible de r�cup�rer les adresses reseaux locales.");
		}

		return returnValue.toString();
	}

	public static void init()
	{
		// Ecriture de l'ent�te du fichier
		writeToVersionFile("------------Description de l'application livree-------------\n");

		// Ecriture de la version de l'application
		writeToVersionFile("Version de l'application : " + getNSIVersion() + "\n");

		// Ecriture de la plate-forme
		writeToVersionFile("Plate-forme : " + getNSIPlateforme() + "\n");

		// Ecriture de la description des classes patch�es
		writeToVersionFile("Patchs d�ploy�s");
		for (int i = 0; i < patch.length; i++)
		{
			try
			{
				Field theField = patch[i].getDeclaredField(CLASS_VERSION_LABEL);
				writeToVersionFile(patch[i].toString() + " " + theField.toString());
			}
			catch (NoSuchFieldException exc)
			{
				writeToVersionFile(patch[i].toString() + ". Pas d'information de version ddisponible");
			}
		}
		writeToVersionFile("Proprietes Systeme");
		writeToVersionFile(getSystemProperties());
		writeToVersionFile("Proprietes Weblogic");
		writeToVersionFile(getWeblogicProperties());
		writeToVersionFile("Proprietes R�seaux");
		writeToVersionFile(getNetworkProperties());
	}

	private static void writeToVersionFile(String message)
	{
		Category appVersionCat = Category.getInstance(ApplicationVersion.class);
		if (!isInitialized)
		{
			isInitialized = true;
			try
			{
				Appender appApplicationVersion = new FileAppender(new SimpleLayout(), "version.txt", false);
				appVersionCat.addAppender(appApplicationVersion);
			}
			catch (IOException exc)
			{
				Logger.error(ApplicationVersion.class, "Impossible de mettre en place le fichier de version de l'application et de la plate-forme.");
			}
		}
		appVersionCat.info(message);
	}

	*//**
	* Permet de r�cup�rer la version du NSI d�ploy�.
	*//*
	public static String getNSIVersion()
	{
		return PropertiesManager.getProperty("nsicm.common.version");
	}

	*//**
	* Permet de r�cup�rer le nom de la plate-forme sur laquelle l'application est d�ploy�e.
	*//*
	public static String getNSIPlateforme()
	{
		return PropertiesManager.getProperty("nsicm.common.platform");
	}
*/}
