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

public class DummyConfiguration
{
	private String m_driver;

	private String m_url;

	private String m_userName;

	private String m_password;

	public DummyConfiguration(String driver, String url, String userName, String password)
	{
		this.m_driver = driver;
		this.m_url = url;
		this.m_userName = userName;
		this.m_password = password;
	}

    /**
	 * Returns the driver.
	 * 
	 * @return String
	 */
    public String getDriver()
	{
		return m_driver;
	}

    /**
     * Returns the login.
     * @return String
     */
    public String getUserName()
	{
		return m_userName;
	}

    /**
     * Returns the password.
     * @return String
     */
    public String getPassword()
	{
		return m_password;
	}

    /**
     * Returns the url.
     * @return String
     */
    public String getUrl()
	{
		return m_url;
	}

    /**
     * Sets the driver.
     * @param driver The driver to set
     */
    public void setDriver(String driver)
	{
		this.m_driver = driver;
	}

    /**
	 * Sets the login.
	 * 
	 * @param login
	 *            The login to set
	 */
    public void setUserName(String userName)
	{
		this.m_userName = userName;
	}

    /**
     * Sets the password.
     * @param password The password to set
     */
    public void setPassword(String password)
	{
		this.m_password = password;
	}

    /**
     * Sets the url.
     * @param url The url to set
     */
    public void setUrl(String url)
	{
		this.m_url = url;
	}
}
