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
package com.rennover.hotel.common.server;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author dattias
 */
public class DummyDataSource implements DataSource
{

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection(String arg0, String arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}/*
	private boolean m_autoCommit;

	private DummyConfiguration m_config;

	private PrintWriter m_logWriter;

	public DummyDataSource(DummyConfiguration config, boolean autoCommit)
	{
		m_config = config;
		m_logWriter = new PrintWriter(System.out);
		m_autoCommit = autoCommit;

		try
		{
			Class.forName(m_config.getDriver()).newInstance();
		} catch (Exception e)
		{
			throw new TechnicalException(e);
		}
	}

	public Connection getConnection() throws SQLException
	{
		return getConnection(m_config.getUserName(), m_config.getPassword());
	}

	public Connection getConnection(String userName, String password) throws SQLException
	{
		Connection connection = DriverManager.getConnection(m_config.getUrl(), userName, password);
		connection.setAutoCommit(m_autoCommit);
		return connection;
	}

	public int getLoginTimeout() throws SQLException
	{
		return 0;
	}

	public PrintWriter getLogWriter() throws SQLException
	{
		return m_logWriter;
	}

	public void setLoginTimeout(int seconds) throws SQLException
	{
	}

	public void setLogWriter(PrintWriter out) throws SQLException
	{
	}
*/}
