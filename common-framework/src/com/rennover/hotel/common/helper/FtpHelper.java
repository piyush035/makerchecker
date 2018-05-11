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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import sun.net.ftp.FtpClient;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.properties.PropertiesManager;
import com.rennover.hotel.common.valueobject.PropertyHelper;

/**
 * @author prakashs
 * 
 */

public class FtpHelper
{
	// These properties are defined in default.properties
	public static final String SERVER_HOST_KEY = "nsicm.common.helper.ftp.host";

	public static final String LOGIN_KEY = "nsicm.common.helper.ftp.login";

	public static final String PASSWORD_KEY = "nsicm.common.helper.ftp.password";

	public static final String FTP_SERVER_PATH_KEY = "nsicm.common.helper.ftp.file.path";

	/**
	 * To get the Ftp Server Host
	 * 
	 * @return String
	 */
	public static String getFtpServerHost()
	{
		return PropertiesManager.getProperty(SERVER_HOST_KEY);
	}

	/**
	 * To get the Ftp Server File Path
	 * 
	 * @return String
	 */
	public static String getFtpServerFilePath()
	{
		return PropertiesManager.getProperty(FTP_SERVER_PATH_KEY);
	}

	/**
	 * To upload a file to FTP
	 * 
	 * @param inputfile
	 * @param fileNameOnFtpHost
	 * @return fileNameOnFtpHost String
	 * @throws IOException
	 */
	public static String uploadFile(File inputfile, String fileNameOnFtpHost) throws IOException
	{
		String serverHost = PropertiesManager.getProperty(SERVER_HOST_KEY);
		String serverLogin = PropertiesManager.getProperty(LOGIN_KEY);
		String serverPassword = PropertiesManager.getProperty(PASSWORD_KEY);
		FtpWriter ftpWriter = new FtpWriter(serverHost, serverLogin, serverPassword, fileNameOnFtpHost);
		FileReader fileReader = null;

		try
		{
			fileReader = new FileReader(inputfile);
			StreamHelper.write(fileReader, ftpWriter);
		} finally
		{
			fileReader.close();
			ftpWriter.close();
			fileReader = null;
			ftpWriter = null;
		}

		return fileNameOnFtpHost;
	}

	/**
	 * To get the file from Ftp Server
	 * 
	 * @param fileNameOnFtpHost
	 * @param filePathNameOnLocal
	 * @return downloadedFile File
	 * @throws IOException
	 */
	public static File downloadFile(String fileNameOnFtpHost, String filePathNameOnLocal) throws IOException
	{
		String serverHost = PropertiesManager.getProperty(SERVER_HOST_KEY);
		String serverLogin = PropertiesManager.getProperty(LOGIN_KEY);
		String serverPassword = PropertiesManager.getProperty(PASSWORD_KEY);
		FtpReader ftpReader = new FtpReader(serverHost, serverLogin, serverPassword, fileNameOnFtpHost);
		BufferedWriter bufferedWriter = null;
		File outFile = null;

		try
		{
			outFile = new File(filePathNameOnLocal);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
			StreamHelper.write(ftpReader.getReader(), bufferedWriter);
		} finally
		{
			bufferedWriter.close();
			ftpReader.close();
			bufferedWriter = null;
			ftpReader = null;
		}

		return outFile;
	}

	/**
	 * To create a directory on the Ftp server -ashisht
	 * 
	 * @param dirName
	 * @throws IOException
	 */
	public static void createFolder(final String dirName, final String serverHost, final String serverLogin,
	        final String serverPassword) throws TechnicalException
	{
		if (PropertyHelper.isNull(dirName))
		{
			return;
		}

		new FtpClient()
		{

			{
				create(dirName);
			}

			private void create(String pDirName)
			{
				try
				{
					Logger.debug("createFolderMethod", "Trying to connect to FTP server ", serverHost);
					openServer(serverHost);
					login(serverLogin, serverPassword);
					binary();
					issueCommand("MKD " + pDirName);
					Logger.debug("createFolderMethod", "Connected successfully to FTP server ", serverHost);
				} catch (IOException e)
				{
					throw new TechnicalException("FTP error", e);
				} finally
				{
					try
					{
						closeServer();
					} catch (IOException e)
					{
						throw new TechnicalException("FTP error", e);
					}
				}
			}
		};
	}

	/**
	 * To rename a file on the Ftp server -amit
	 * 
	 * @param fileName
	 * @param serverHost
	 * @param serverLogin
	 * @param serverPassword
	 * @throws IOException
	 * @return void
	 */
	public static void renameFile(final String fileName, final String serverHost, final String serverLogin,
	        final String serverPassword) throws TechnicalException
	{
		new FtpClient()
		{

			{
				renameFile(fileName);
			}

			private void renameFile(String pFileName)
			{
				try
				{
					Logger.debug("rename", "Trying to connect to FTP server ", serverHost);
					openServer(serverHost);
					login(serverLogin, serverPassword);
					binary();

					String file = pFileName + ".dat";
					issueCommand("RNFR " + pFileName);
					issueCommand("RNTO " + file);
					Logger.debug("rename", "Renaming of File is Done successfully to FTP server ", serverHost);
				} catch (IOException e)
				{
					throw new TechnicalException("FTP error", e);
				} finally
				{
					try
					{
						closeServer();
					} catch (IOException e)
					{
						throw new TechnicalException("FTP error", e);
					}
				}
			}
		};
	}

	/**
	 * Added by Gowtham for the defect 7361 To create a file in FTP on the
	 * particular folder (xxxPDF) where xxx is name of the user loged in .
	 * 
	 * @param serverHost
	 * @param serverLogin
	 * @param serverPassword
	 * @param inputfile
	 * @param fileNameOnFtpHost
	 * @return void
	 */
	public static void uploadRejectFile(String serverHost, String serverLogin, String serverPassword, File inputfile,
	        String fileNameOnFtpHost)
	{
		FtpWriter ftpWriter = new FtpWriter(serverHost, serverLogin, serverPassword, fileNameOnFtpHost);
		FileReader fileReader = null;

		try
		{
			fileReader = new FileReader(inputfile);
			StreamHelper.write(fileReader, ftpWriter);
		} catch (Exception e)
		{
		} finally
		{
			try
			{
				fileReader.close();
				ftpWriter.close();
			} catch (Exception e)
			{
			}

			fileReader = null;
			ftpWriter = null;
		}
	}

	/**
	 * Added by Gowtham for the defect 7361 Added by Gowtham to Upload the file
	 * to the concern FTP server based on the siteID of the user
	 * 
	 * @param serverHost
	 * @param serverLogin
	 * @param serverPassword
	 * @param inputfile
	 * @param fileNameOnFtpHost
	 * @return fileNameOnFtpHost String
	 * @throws IOException
	 */
	public static String uploadFichierFile(String serverHost, String serverLogin, String serverPassword,
	        File inputfile, String fileNameOnFtpHost) throws IOException
	{
		FtpWriter ftpWriter = new FtpWriter(serverHost, serverLogin, serverPassword, fileNameOnFtpHost);
		FileReader fileReader = null;

		try
		{
			fileReader = new FileReader(inputfile);
			StreamHelper.write(fileReader, ftpWriter);
		} finally
		{
			fileReader.close();
			ftpWriter.close();
			fileReader = null;
			ftpWriter = null;
		}

		return fileNameOnFtpHost;
	}

	/**
	 * Added by Gowtham for the defect 7361 Added by Gowtham to download the
	 * file from concern FTP server based on the siteID of the user To download
	 * the file from Ftp Server
	 * 
	 * @param serverHost
	 * @param serverLogin
	 * @param serverPassword
	 * @param fileNameOnFtpHost
	 * @param filePathNameOnLocal
	 * @return File
	 * @throws IOException
	 */
	public static File downloadFichierFile(String serverHost, String serverLogin, String serverPassword,
	        String fileNameOnFtpHost, String filePathNameOnLocal) throws IOException
	{
		FtpReader ftpReader = new FtpReader(serverHost, serverLogin, serverPassword, fileNameOnFtpHost);
		BufferedWriter bufferedWriter = null;
		File outFile = null;

		try
		{
			outFile = new File(filePathNameOnLocal);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
			StreamHelper.write(ftpReader.getReader(), bufferedWriter);
		} finally
		{
			bufferedWriter.close();
			ftpReader.close();
			bufferedWriter = null;
			ftpReader = null;
		}

		return outFile;
	}

	/**
	 * @author boopathi.siva Added by Boopathi for the defect 8072 To delete the
	 *         file from concern FTP server
	 * @param serverHost
	 * @param serverLogin
	 * @param serverPassword
	 * @param filePathNameOnLocal
	 * @throws IOException
	 * @throws TechnicalException
	 */
	public static void deleteFichierFile(final String serverHost, final String serverLogin,
	        final String serverPassword, final String filePathNameOnLocal) throws IOException, TechnicalException
	{
		new FtpClient()
		{
			{
				deleteFile(serverHost, serverLogin, serverPassword, filePathNameOnLocal);
			}

			private void deleteFile(String serverHost, String serverLogin, String serverPassword,
			        String filePathNameOnLocal)
			{
				try
				{
					if (!PropertyHelper.isNull(filePathNameOnLocal))
					{
						openServer(serverHost);
						login(serverLogin, serverPassword);
						binary();
						issueCommand("DELE " + filePathNameOnLocal);
					}
				} catch (Exception e)
				{
					Logger.debug("deleteFichierFile", "failed to deleted the file from"
					        + " FTP server, fully qualified file name is ", filePathNameOnLocal);
				}
			}
		};
	}
}