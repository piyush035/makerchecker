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

import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import com.rennover.hotel.common.helper.ReflectionHelper;

/**
 * ...
 * 
 * @author dattias
 */
public class ObjectCsvWriter extends CsvWriter
{
	private List m_propertyNameList;

	public ObjectCsvWriter(Writer out, List propertyNameList)
	{
		super(out);
		m_propertyNameList = propertyNameList;
	}

	public void printCsvHeader()
	{
		for (Iterator iter = m_propertyNameList.iterator(); iter.hasNext();)
		{
			print(iter.next());
			printEndOfCell();
		}
		printEndOfLine();
	}

	public void printCsv(Object object)
	{
		for (Iterator iter = m_propertyNameList.iterator(); iter.hasNext();)
		{
			String propertyName = (String) iter.next();
			Object propertyValue = ReflectionHelper.getProperty(object, propertyName);
			// FIX FOR DEFECT#4506
			// To Replace true and false by oui & non
			if (propertyValue != null)
			{

				if (propertyValue.toString().equals("true"))
				{
					print("oui");
				} else if (propertyValue.toString().equals("false"))
				{
					print("non");
				} else if (propertyValue.toString().equals(object.getClass().toString()))
				{
					print("");
				} else
				{
					print(propertyValue);
				}
			}
			printEndOfCell();
		}
		printEndOfLine();
	}
}