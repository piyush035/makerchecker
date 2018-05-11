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

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rennover.hotel.common.helper.StringHelper;
import com.rennover.hotel.common.valueobject.PropertyHelper;
import com.rennover.hotel.common.valueobject.SmallDecimal;


/**
 * Class to read from a csv text file.
 * Stores one line of the file in a String array called cursor.
 * How to use:
 * 1..Call the constructor passing the delimiter string
 * 2..Call the method openCsvFile() passing the file/fileName
 * 3..Successive calls to the method next() read the next line in the file
 *           and the read attributes are set to a String array.
 * 4..methods are available to convert the read attribute to the desired data type.
 * 5..call to next() returns false if End Of File is reached.
 */
public class CsvReader
{
	private boolean m_endOfFileReached;

	private String[] m_cursor;

	private String m_delimiter;

	private String m_nextLine = null;

	private BufferedReader m_reader;

	public CsvReader(String delimiter)
	{
		m_delimiter = delimiter;
	}

    /**
	 * sets a buffered reader to the file passed
	 * 
	 * @param file
	 * @return boolean
	 */
    public boolean openCsvFile(File file) throws FileNotFoundException
	{
		m_reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

		return (m_reader != null) ? true : false;
	}

    /**
	 * sets a buffered reader to the file passed
	 * 
	 * @param fileName
	 * @return boolean
	 */
    public boolean openCsvFile(String fileName) throws FileNotFoundException
	{
		m_reader = new BufferedReader(new FileReader(fileName));

		return (m_reader != null) ? true : false;
	}

    public void closeCsvFile() throws IOException
	{
		m_reader.close();
	}

    /**
     * if next line exists then
         *                 read next line of the file into the cursor.
         *                 return true.
     * else
     *                 return false.
     *
     * @return boolean
     */
    public boolean next() throws IOException
	{
		m_nextLine = m_reader.readLine();

		if (!PropertyHelper.isNull(m_nextLine))
		{
			if (m_nextLine.startsWith(m_delimiter))
			{
				m_nextLine = " " + m_nextLine;
			}

			if (m_nextLine.endsWith(m_delimiter))
			{
				m_nextLine = m_nextLine + " ";
			}

			m_nextLine = StringHelper
			        .replaceAll(m_nextLine, m_delimiter + m_delimiter, m_delimiter + " " + m_delimiter);

			m_cursor = StringHelper.split(m_nextLine, m_delimiter);
			normalizeCursor();

			return true;
		}

		// readLine will return null if EOF reached. So....
		m_endOfFileReached = true;

		return false;
	}

    /**
	 * Returns the current line's attributes in a String array
	 * 
	 * @return String[]
	 */
    public String[] getCursor()
	{
		return m_cursor;
	}

    /**
     * to get string value of the field
     * @param index
     * @return String
     * @throws EOFException
     */
    public String getString(int index) throws EOFException
	{
		if (!m_endOfFileReached)
		{
			if (null == m_cursor[index])
			{
				return null;
			}

			return m_cursor[index];
		}

		throw new EOFException();
	}

    /**
	 * to get int value of the field
	 * 
	 * @param index
	 * @return int
	 * @throws EOFException
	 */
    public Integer getInt(int index) throws EOFException
	{
		if (!m_endOfFileReached)
		{
			if (null == m_cursor[index])
			{
				return null;
			}

			return Integer.valueOf(m_cursor[index]);
		}

		throw new EOFException();
	}

    /**
	 * to get long value of the field
	 * 
	 * @param index
	 * @return Long
	 * @throws EOFException
	 */
    public Long getLong(int index) throws EOFException
	{
		if (!m_endOfFileReached)
		{
			if (null == m_cursor[index])
			{
				return null;
			}

			return Long.valueOf(m_cursor[index]);
		}

		throw new EOFException();
	}

    /**
	 * to get SmallDecimal value of the field
	 * 
	 * @param index
	 * @return SmallDecimal
	 * @throws EOFException
	 */
    public SmallDecimal getSmallDecimal(int index) throws EOFException
	{
		if (!m_endOfFileReached)
		{
			if (null == m_cursor[index])
			{
				return null;
			}

			return SmallDecimal.valueOf(m_cursor[index]);
		}

		throw new EOFException();
	}

    /**
	 * to get the short value of the field
	 * 
	 * @param index
	 * @return Short
	 * @throws EOFException
	 */
    public Short getShort(int index) throws EOFException
	{
		if (!m_endOfFileReached)
		{
			if (null == m_cursor[index])
			{
				return null;
			}

			return Short.valueOf(m_cursor[index]);
		}

		throw new EOFException();
	}

    /**
	 * returns the date and time
	 * 
	 * @param index
	 * @param dateSeparator
	 * @return Date
	 * @throws EOFException
	 */
    public Date getDateTime(int index) throws EOFException, ParseException
	{
		if (!m_endOfFileReached)
		{
			if (null == m_cursor[index])
			{
				return null;
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			return dateFormat.parse(m_cursor[index]);
		}

		throw new EOFException();
	}

    public boolean isEndOfFileReached()
	{
		return m_endOfFileReached;
	}

	public String getWholeLine()
	{
		return m_nextLine;
	}

	private void normalizeCursor()
	{
		for (int i = 0; i < m_cursor.length; i++)
		{
			if (m_cursor[i].equals(""))
			{
				m_cursor[i] = null;
			}
		}
	}
}
