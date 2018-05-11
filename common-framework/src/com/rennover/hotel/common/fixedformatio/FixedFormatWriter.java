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
package com.rennover.hotel.common.fixedformatio;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * @author Gkarachi
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FixedFormatWriter extends PrintWriter
{
	public static final char END_OF_LINE = '\n';

	public static final char DEFAULT_PADDING_CHAR = ' ';

	public static final PaddingTypeEnum DEFAULT_PADDING_TYPE = PaddingTypeEnum.LEFT_PADDING;

	// does not make sense here ; but some require it
	public static final char SEPERATOR_TO_USE = '\0';

	public FixedFormatWriter(Writer pOut)
	{
		super(pOut, true);
	}

	public void printline(Object o)
	{
		print(o);
		println();
	}

	public void println()
	{
		print(END_OF_LINE);
	}
}
