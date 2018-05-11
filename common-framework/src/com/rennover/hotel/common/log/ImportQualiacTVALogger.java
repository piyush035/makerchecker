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

/**
 * @author narendrabr
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImportQualiacTVALogger extends ImportFichierLogger
{
	private static ImportQualiacTVALogger s_importQualiacLogger = null;
		/* (non-Javadoc)
	/* (non-Javadoc)
	 * @see com.agefospme.nsicm.common.log.ImportFichierLogger#initLogFiles()
	 */
	protected void initLogFiles()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.agefospme.nsicm.common.log.ImportFichierLogger#logBad(java.lang.String)
	 */
	public void logBad(String message)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.agefospme.nsicm.common.log.ImportFichierLogger#log(java.lang.Throwable)
	 */
	public void log(Throwable throwable)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.agefospme.nsicm.common.log.ImportFichierLogger#log(java.lang.String)
	 */
	public void log(String message)
	{
		// TODO Auto-generated method stub

	}

	public static ImportFichierLogger getInstance()
	{

		if (null == s_importQualiacLogger)
		{
			s_importQualiacLogger = new ImportQualiacTVALogger();
			s_importQualiacLogger.initLogFiles();
		}

		return s_importQualiacLogger;
	}

}
