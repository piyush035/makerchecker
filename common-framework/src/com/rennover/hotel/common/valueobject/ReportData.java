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
package com.rennover.hotel.common.valueobject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @audit dattias 18/10/02
 */
public abstract class ReportData
{
	/**
 * Constructor for ReportData.
 */
	public ReportData()
	{
		super();
	}

	public static String getDateDuJour()
	{
		Calendar c = GregorianCalendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(d);
	}
}