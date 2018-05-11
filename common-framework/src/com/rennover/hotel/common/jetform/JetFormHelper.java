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
package com.rennover.hotel.common.jetform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.helper.FtpHelper;
import com.rennover.hotel.common.helper.FtpWriter;
import com.rennover.hotel.common.helper.StreamHelper;

/**
 * 
 * @author dattias
 */
public class JetFormHelper
{
	public static final String PDF_TARGET_TYPE = "pdf";

	public static final String DEFAULT_JOB_NAME = "DEFAULT_JOB";

	public static final String DEFAULT_FORM_NAME = "bouchon";

	public static final String MAIL_JOB_NAME = "MAIL_JOB";

	public static final String PREVIEW_JOB_NAME = "PREVIEW";

	private static long s_lastJobId = System.currentTimeMillis();

	public static String getProcessName()
	{
		/**
		 * @todo (dattias, 16 janv. 2004): replace this temp impl.
		 * we need to get a real unique process name.
		 */
		return getHostName();
	}

	public static String getHostName()
	{
		try
		{
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e)
		{
			throw new TechnicalException("Get host name error", e);
		}
	}

	private static synchronized String getNextJobId()
	{
		long date = System.currentTimeMillis();
		if (s_lastJobId == date)
		{
			++s_lastJobId;
		} else
		{
			s_lastJobId = date;
		}
		return Long.toString(s_lastJobId);
	}

	public static String getNewJetFormJobName()
	{
		StringBuffer buffer = new StringBuffer(40);
		// commented and added By Amit on 17th July 2006 to fix the defect#7732
		buffer.append(getProcessName()).append('-').append(getNextJobId());
		return buffer.toString();

	}

	public static void upload(File file, final String ftpHost, final String ftpLogin, final String ftpPassword)
	        throws FileNotFoundException, IOException
	{
		String fileName = JetFormHelper.getNewJetFormJobName();
		final FtpWriter ftpWriter = new FtpWriter(ftpHost, ftpLogin, ftpPassword, fileName);
		FileReader fileReader = new FileReader(file);
		StreamHelper.write(fileReader, ftpWriter);
		fileReader.close();
		ftpWriter.close();

/**
 * added By Amit on 17th July 2006 to fix the defect#7732 Problem : when Jetform
 * sees a .dat file, it processes it immediately In case of big files, the
 * process begins before the end of the transfer (FTP)
 * 
 * Solution : Don't use the .dat file extension until the transfer is complete
 * (rename the file at the end of the transfer)
 */
		FtpHelper.renameFile(fileName, ftpHost, ftpLogin, ftpPassword);
	}

}
