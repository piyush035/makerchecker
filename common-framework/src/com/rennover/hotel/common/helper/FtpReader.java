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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import sun.net.ftp.FtpClient;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;

/**
 * @author prakashs
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FtpReader extends Reader
{
	private FtpClient m_ftpClient;

	private Reader m_in;

	/**
	 * @param ftpServer
	 * @param login
	 * @param password
	 * @param filePathName
	 * 
	 */
	public FtpReader(String ftpServer, String login, String password, String filePathName)
	{
		try
		{
			Logger.debug(this, "Trying to connect to FTP server ", ftpServer);
			m_ftpClient = new FtpClient();
			m_ftpClient.openServer(ftpServer);
			m_ftpClient.login(login, password);
			m_ftpClient.binary();
			m_in = new BufferedReader(new InputStreamReader(m_ftpClient.get(filePathName)));
			Logger.debug(this, "Connected successfully to FTP server ", ftpServer);
		} catch (IOException e)
		{
			throw new TechnicalException("FTP error", e);
		}
	}

	public int read(char[] cbuf, int off, int len) throws IOException
	{
		return m_in.read(cbuf, off, len);
	}

	public Reader getReader()
	{
		return m_in;
	}

	public void close() throws IOException
	{
		m_in.close();
		m_ftpClient.closeServer();
		Logger.debug(this, "FTP connection closed successfully");
	}
}