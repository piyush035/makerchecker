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
package com.rennover.hotel.common.exception;

import java.io.Serializable;
import java.util.List;

public class QueryInfo implements Serializable
{
	private String m_sql;

	private List m_parameters;

	public QueryInfo(String sql, List parameters)
	{
		m_sql = sql;
		m_parameters = parameters;
	}

	public List getParameters()
	{
		return m_parameters;
	}

	public String getSql()
	{
		return m_sql;
	}
}