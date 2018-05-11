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
package com.rennover.hotel.common.log;

import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;

/**
 * @author Gkarachi
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ImportFichierLogger
{

	
	
	
	protected static void addLayout(FileAppender appender)
	{
		String conversionPattern = "--------------------------------------------------------------%n<%d{HH:mm:ss:SSS}> %m%n";
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern(conversionPattern);
		appender.setLayout(layout);
	}

}
	
