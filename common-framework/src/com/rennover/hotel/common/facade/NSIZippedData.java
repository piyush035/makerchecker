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
package com.rennover.hotel.common.facade;

import java.io.Serializable;

public class NSIZippedData implements Serializable
{
	byte[] m_zippedData = null;

	public NSIZippedData(byte[] zippedData)
	{
		m_zippedData = zippedData;
	}

	public byte[] getZippedData()
	{
		return m_zippedData;
	}

	public void setZippedData(byte[] zippedData)
	{
		m_zippedData = zippedData;
	}

}