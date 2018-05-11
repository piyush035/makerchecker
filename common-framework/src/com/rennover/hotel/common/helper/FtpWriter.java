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
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import sun.net.ftp.FtpClient;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;

/**
 * Dans le cas où cette implémentation n'est pas suffisante,
 * revoir la liste des librairies client ftp :
 * http://www.javaworld.com/javaworld/jw-04-2003/jw-0404-ftp.html
 *
 * @author dattias
 */
public class FtpWriter extends Writer
{
	private FtpClient m_ftpClient;

	private Writer m_out;

	/**
	 * @param ftpServer
	 * @param login
	 * @param password
	 * @param filePathName
	 *
	 * @todo (dattias, 6 févr. 2004): nous utilisons actuellement le jdk 1.3 sur
	 * serveur Weblogic, ce qui nous empèche d'utiliser url.openConnection() car
	 * le handler du protocol FTP n'est pas connu. En jdk 1.4, ca marche. Donc
	 * pour l'instant nous utilisons la classe sun.net.ftp.FtpClient. Dès que possible
	 * repasser en implem url.openConnection().
	 */
	public FtpWriter(String ftpServer, String login, String password, String filePathName)
	{
		// Commmented code removed

		try
		{
			Logger.debug(this, "Trying to connect to FTP server ", ftpServer);
			m_ftpClient = new FtpClient();
			m_ftpClient.openServer(ftpServer);
			m_ftpClient.login(login, password);
			m_ftpClient.binary();
			m_out = new BufferedWriter(new OutputStreamWriter(m_ftpClient.put(filePathName)));
			Logger.debug(this, "Connected successfully to FTP server ", ftpServer);
		} catch (IOException e)
		{
			throw new TechnicalException("FTP error", e);
		}
	}

	public void write(char[] cbuf, int off, int len) throws IOException
	{
		m_out.write(cbuf, off, len);
	}

	public void flush() throws IOException
	{
		m_out.flush();
	}

	public void close() throws IOException
	{
		m_out.close();
		m_ftpClient.closeServer();
		Logger.debug(this, "FTP connection closed successfully");
	}

	public static void main(String[] args) throws IOException
	{
		FtpWriter ftpWriter = new FtpWriter("srv02nsiarchi", "jetform", "jetform", "toto.txt");
		ftpWriter.write("toto\n");
		ftpWriter.write("toto\n");
		ftpWriter.write("toto\n");
		ftpWriter.write("toto\n");
		ftpWriter.close();
	}
}
