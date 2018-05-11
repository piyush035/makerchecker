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
package com.rennover.hotel.common.csv;

import java.io.PrintWriter;
import java.io.Writer;


/**
 * ...
 *
 * @author dattias
 *
 * TODO (dattias, le 13 oct. 2003) : gérer un point-virgule qui marche avec Excel.
 * TODO (dattias, le 13 oct. 2003) : utiliser des double cotes pour encapsuler les cellules.
 * TODO (dattias, le 13 oct. 2003) : ajouter une méthode printStartOfCell()
 *
 */
public class CsvWriter extends PrintWriter implements CsvConstants
{
	public CsvWriter(Writer pOut)
	{
		super(pOut, true);
	}

	public void printEndOfCell()
	{
		print(END_OF_CELL);
	}

	public void printEndOfLine()
	{
		println();
	}

	public void println()
	{
		print(END_OF_LINE);
	}
}
