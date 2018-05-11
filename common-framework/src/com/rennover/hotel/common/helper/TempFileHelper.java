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
import java.io.FileFilter;
import java.io.IOException;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;


/**
 * ...
 *
 * @author dattias
 */
public abstract class TempFileHelper
{
	private static File s_tempDirectory;

	private static int s_expirationTime = 7; // default = 7 days

	private static final String FILE_NAME_SUFFIX = ".tmp";

	private static final String GIVEN_NAME_SUFFIX = ".nsicm.";

	private static final String DEFAULT_GIVEN_NAME = "temp";

    /**
     * File filter that checks NSICM temp files.
     */
    private static FileFilter s_nsicmTempFileFilter = new FileFilter()
	{
		public boolean accept(File file)
		{
			return TempFileHelper.isNsicmTempFile(file);
		}
	};

	public static File createTempFile()
	{
		return createTempFile(DEFAULT_GIVEN_NAME);
	}

	public static File createTempFile(String givenName)
	{
		return createTempFile(givenName, FILE_NAME_SUFFIX);
	}

	private static File createTempFile(String givenName, String suffix)
	{
		if ((givenName == null) || (givenName.length() == 0))
		{
			throw new IllegalArgumentException("Given name null or empty");
		}

		try
		{
			init();
			deleteExpiredTempFiles();

			return File.createTempFile(givenName + GIVEN_NAME_SUFFIX, suffix, s_tempDirectory);
		} catch (IOException e)
		{
			throw new TechnicalException("Create temp file error", e);
		}
	}

    /**
	 * added by murali - ME 905 this method creates a temporary file, which in
	 * turn creates destination file with file name as "givenName"
	 */
    public static File createTemporaryFile(String givenName)
	{
		if ((givenName == null) || (givenName.length() == 0))
		{
			throw new IllegalArgumentException("Given name null or empty");
		}

		init();
		deleteExpiredTempFiles();

		File file = new File(s_tempDirectory, givenName);

		return file;
	}

	public static File createTempNSIZipFile(String givenName)
	{
		return createTempFile(givenName, FileHelper.NSI_ZIP_SUFFIX);
	}

	public static File createTempNSIZipFile()
	{
		return createTempFile(DEFAULT_GIVEN_NAME, FileHelper.NSI_ZIP_SUFFIX);
	}

	public static File[] listTempFiles()
	{
		init();
		deleteExpiredTempFiles();

		return s_tempDirectory.listFiles(s_nsicmTempFileFilter);
	}

	public static String getTempFileGivenName(File file)
	{
		String fileName = file.getName();
		int index = fileName.indexOf(GIVEN_NAME_SUFFIX);

		if (index < 0)
		{
			return fileName;
		}

		return fileName.substring(0, index);
	}

	public static void init(String directoryPath, String expirationTime)
	{
		if (directoryPath != null)
		{
			File file = new File(directoryPath);

			if (file.isDirectory())
			{
				s_tempDirectory = file;
			}
		} else
		{
			try
			{
				File file = File.createTempFile("nsicm.test.", ".tmp");
				s_tempDirectory = file.getParentFile();
			} catch (IOException e)
			{
				throw new TechnicalException("Create temp file error", e);
			}
		}

		if (expirationTime != null)
		{
			s_expirationTime = Integer.parseInt(expirationTime);
		}
	}

	public static long getExpirationDate(long creationDate)
	{
		return creationDate + (s_expirationTime * 1000 * 60 * 60 * 24);
	}

	private static void deleteExpiredTempFiles()
	{
		long expiredDate = System.currentTimeMillis() - (s_expirationTime * 1000 * 60 * 60 * 24);
		File[] files = s_tempDirectory.listFiles(s_nsicmTempFileFilter);

		for (int i = 0; i < files.length; i++)
		{
			if (files[i].lastModified() < expiredDate)
			{
				Logger.debugValue(TempFileHelper.class, "Automatic delete of temp file ", files[i].getAbsolutePath());

				if (!files[i].delete())
				{
					throw new TechnicalException("Delete of temp file error for unknown reason");
				}
			}
		}
	}

	private static void init()
	{
		if (s_tempDirectory == null)
		{
			init(null, null);
		}
	}

	private static boolean isNsicmTempFile(File file)
	{
		return file.getName().indexOf(GIVEN_NAME_SUFFIX) >= 0;
	}

	public static String getFileName(String name)
	{
		if (s_tempDirectory == null)
		{
			init(null, null);
		}

		return s_tempDirectory + "/" + name + GIVEN_NAME_SUFFIX + FILE_NAME_SUFFIX;
	}
}
