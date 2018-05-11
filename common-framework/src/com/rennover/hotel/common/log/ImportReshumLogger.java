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

import java.io.IOException;
import java.util.Calendar;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


/**
 * @author Gkarachi
 *
 */
public class ImportReshumLogger extends ImportFichierLogger
{
	private ImportReshumLogger()
	{
	}

    /* (non-Javadoc)
     * @see com.agefospme.nsicm.common.log.ImportFichierLogger#initLogFiles()
     */
    public static void initLogFiles()
	{
		try
		{
			Logger importReshumLogger = Logger.getLogger("reshumLogger");
			importReshumLogger.removeAllAppenders();

			FileAppender importReshumAppender = new FileAppender();

			Calendar calendar = Calendar.getInstance();

			//création du reshum .log
			StringBuffer reshumFileName = new StringBuffer("RESHUM");
			reshumFileName.append("-");
			reshumFileName.append(calendar.get(Calendar.YEAR));
			reshumFileName.append("-");
			reshumFileName.append(calendar.get(Calendar.MONTH) + 1);
			reshumFileName.append("-");
			reshumFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			reshumFileName.append(".");
			reshumFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			reshumFileName.append("-");
			reshumFileName.append(calendar.get(Calendar.MINUTE));
			reshumFileName.append(".exx");

			importReshumAppender.setFile(reshumFileName.toString(), true, false, 8 * 1024);
			importReshumAppender.setName("importReshum");
			addLayout(importReshumAppender);
			importReshumLogger.setLevel(Level.INFO);
			importReshumLogger.addAppender(importReshumAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class, "Création du reshum RESHUM<date>.exx");

			//-----------------------------
			Logger importDAELogger = Logger.getLogger("importDAELogger");
			importDAELogger.removeAllAppenders();

			FileAppender importDAEAppender = new FileAppender();

			//création du fichier .log
			StringBuffer importFileName = new StringBuffer("RESHACTI");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importDAEAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importDAEAppender.setName("importDAE");
			addLayout(importDAEAppender);
			importDAELogger.setLevel(Level.INFO);
			importDAELogger.addAppender(importDAEAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHACTI<date>.exx");

			//-----------------------------
			Logger importStagaireLogger = Logger.getLogger("importStagLogger");
			importStagaireLogger.removeAllAppenders();

			FileAppender importStagAppender = new FileAppender();

			//création du fichier .log
			importFileName = new StringBuffer("RESHSTAG");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importStagAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importStagAppender.setName("importStagaire");
			addLayout(importStagAppender);
			importDAELogger.setLevel(Level.INFO);
			importDAELogger.addAppender(importStagAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHSTAG<date>.exx");

			//-----------------------------
			Logger importIndividuLogger = Logger.getLogger("importIndividuLogger");
			importIndividuLogger.removeAllAppenders();

			FileAppender importIndAppender = new FileAppender();

			//création du fichier .log
			importFileName = new StringBuffer("RESHINDI");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importIndAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importIndAppender.setName("importIndividu");
			addLayout(importIndAppender);
			importDAELogger.setLevel(Level.INFO);
			importDAELogger.addAppender(importIndAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHINDI<date>.exx");

			//---------------------------------------------------------
			Logger importDAEPLogger = Logger.getLogger("importDAEPLogger");
			importDAEPLogger.removeAllAppenders();

			FileAppender importDAEPAppender = new FileAppender();

			//création du fichier .log
			importFileName = new StringBuffer("RESHACTP");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importDAEPAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importDAEPAppender.setName("importDAEP");
			addLayout(importDAEPAppender);
			importDAEPLogger.setLevel(Level.INFO);
			importDAEPLogger.addAppender(importDAEPAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHACTP<date>.exx");

			//--------------------------------------------
			Logger importStagProjLogger = Logger.getLogger("importStagProjLogger");
			importDAEPLogger.removeAllAppenders();

			FileAppender importStagProjAppender = new FileAppender();

			//création du fichier .log
			importFileName = new StringBuffer("RESHSTAP");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importStagProjAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importStagProjAppender.setName("importStagProj");
			addLayout(importStagProjAppender);
			importStagProjLogger.setLevel(Level.INFO);
			importStagProjLogger.addAppender(importStagProjAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHSTAP<date>.exx");

			//----------------------------------
			Logger importCOUPLogger = Logger.getLogger("importCOUPLogger");
			importCOUPLogger.removeAllAppenders();

			FileAppender importCOUPAppender = new FileAppender();

			//création du fichier .log
			importFileName = new StringBuffer("RESHCOUP");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importCOUPAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importCOUPAppender.setName("importCOUP");
			addLayout(importCOUPAppender);
			importCOUPLogger.setLevel(Level.INFO);
			importCOUPLogger.addAppender(importCOUPAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHCOUP<date>.exx");

			//----------------------------------------------------						
			Logger importATTPLogger = Logger.getLogger("importATTPLogger");
			importATTPLogger.removeAllAppenders();

			FileAppender importATTPAppender = new FileAppender();

			//création du fichier .log
			importFileName = new StringBuffer("RESHATTP");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importATTPAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importATTPAppender.setName("importATTP");
			addLayout(importATTPAppender);
			importATTPLogger.setLevel(Level.INFO);
			importATTPLogger.addAppender(importATTPAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHATTP<date>.exx");

			//----------------------------------------------------						
			Logger importCOUTLogger = Logger.getLogger("importCOUTLogger");
			importCOUTLogger.removeAllAppenders();

			FileAppender importCOUTAppender = new FileAppender();

			//création du fichier .log
			importFileName = new StringBuffer("RESHCOUT");
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.YEAR));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MONTH) + 1);
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.DAY_OF_MONTH));
			importFileName.append(".");
			importFileName.append(calendar.get(Calendar.HOUR_OF_DAY));
			importFileName.append("-");
			importFileName.append(calendar.get(Calendar.MINUTE));
			importFileName.append(".exx");

			importCOUTAppender.setFile(importFileName.toString(), true, false, 8 * 1024);
			importCOUTAppender.setName("importATTP");
			addLayout(importCOUTAppender);
			importCOUTLogger.setLevel(Level.INFO);
			importCOUTLogger.addAppender(importCOUTAppender);

			com.rennover.hotel.common.log.Logger.log(ImportReshumLogger.class,
			        "Création du fichier RESHCOUT<date>.exx");
		} catch (IOException e)
		{
			com.rennover.hotel.common.log.Logger.error(ImportReshumLogger.class,
			        "Erreur lors de la création des fichiers de log pour l'import des données Reshum");
		}
	}

	public static void logRESHUM(String message)
	{
		Logger.getLogger("reshumLogger").info(message);
	}

	public static void errRESHUM(String message)
	{
		Logger.getLogger("reshumLogger").error(message);
	}

	public static void logACTI(String message)
	{
		Logger.getLogger("importDAELogger").error(message);
	}

	public static void logACTP(String message)
	{
		Logger.getLogger("importDAEPLogger").error(message);
	}

	public static void logSTAG(String message)
	{
		Logger.getLogger("importStagLogger").error(message);
	}

	public static void logIND(String message)
	{
		Logger.getLogger("importIndividuLogger").error(message);
	}

	public static void logSTAGP(String message)
	{
		Logger.getLogger("importStagProjLogger").error(message);
	}

	public static void logCOUP(String message)
	{
		Logger.getLogger("importCOUPLogger").error(message);
	}

	public static void logCOUT(String message)
	{
		Logger.getLogger("importCOUTLogger").error(message);
	}

	public static void logATTP(String message)
	{
		Logger.getLogger("importATTPLogger").error(message);
	}

	public void log(String message)
	{
		Logger.getLogger("importDAELogger").error(message);
	}

	public void log(Throwable throwable)
	{
	}

	public void logBad(String str)
	{
	}

	protected static void addLayout(FileAppender appender)
	{
		String conversionPattern = "--------------";
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern(conversionPattern);
		appender.setLayout(layout);
	}
}
