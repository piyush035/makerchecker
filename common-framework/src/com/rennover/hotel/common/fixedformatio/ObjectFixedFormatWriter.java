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

import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;

import com.rennover.hotel.common.helper.ReflectionHelper;
import com.rennover.hotel.common.validation.ValidationRules;
import com.rennover.hotel.common.validation.ValidationService;


/**
 * @author Gkarachi
 *
 */
public class ObjectFixedFormatWriter extends FixedFormatWriter
{
	public ObjectFixedFormatWriter(Writer pOut)
	{
		super(pOut);
	}

    /**
	 * The method prints, using the Writer passed in constructor, the String
	 * containing concatenation of fixed format conversion of the properties,
	 * from object, specified in the propertyList which is passed in
	 * constructor.
	 * 
	 * @limitation - The method assumes that all the fields in the argument
	 *             Object are String type
	 * @param
	 *             <p>
	 *             FixedFormatObject object - instance of class that extends
	 *             PersistemtDomainObject and implements FixedFormattable, this
	 *             class should define the length for each and every field in
	 *             the metadata.
	 *             </p>
	 *             <p>
	 *             List propertyList - Contains the list of properties, of
	 *             object, that should be taken into consideration while
	 *             generating fixedFormat line from object. The properties are
	 *             added to list in the order which they should be put in the
	 *             fixedformat line.
	 *             </p>
	 *             <p>
	 *             PaddingTypeEnum paddingType - left or right padding in case
	 *             the length of property value is less than the max size (fixed
	 *             length) defined in metadata.
	 */
    public void printFixedFormat(FixedLengthFormattable object) throws FixedFormatViolationException
	{
		printline(getFixedFormatLine(object));
	}

    /**
	 * The method returns the String containing concatenation of fixed format
	 * conversion of the properties, from object, specified in the propertyList.
	 * 
	 * @limitation - The method assumes that all the fields in the argument
	 *             Object are String type
	 * @param
	 *             <p>
	 *             FixedFormatObject object - instance of class that extends
	 *             PersistemtDomainObject and implements FixedFormattable, this
	 *             class should define the length for each and every field in
	 *             the metadata.
	 *             </p>
	 *             <p>
	 *             List propertyList - Contains the list of properties, of
	 *             object, that should be taken into consideration while
	 *             generating fixedFormat line from object. The properties are
	 *             added to list in the order which they should be put in the
	 *             fixedformat line.
	 *             </p>
	 *             <p>
	 *             PaddingTypeEnum paddingType - left or right padding in case
	 *             the length of property value is less than the max size (fixed
	 *             length) defined in metadata.
	 */
    public static String getFixedFormatLine(FixedLengthFormattable object) throws FixedFormatViolationException
	{
		char seperatorToUse = object.getSeperatorToUse();

		StringBuffer fixedFormatLine = new StringBuffer();
		boolean firstField = true;

		for (Iterator iter = object.getPropertyNameList().iterator(); iter.hasNext();)
		{
			String propertyName = (String) iter.next();
			String propertyValue = (String) ReflectionHelper.getProperty(object, propertyName);

			if (!firstField)
			{
				fixedFormatLine.append(seperatorToUse);
			}

			fixedFormatLine.append(getFixedFormatStr(propertyName, propertyValue, object.getClass()));
			firstField = false;
		}

		return fixedFormatLine.toString();
	}

	public static String getFixedFormatStr(String propertyName, String propertyValue, Class objectClass)
	        throws FixedFormatViolationException
	{
		ValidationRules rules = ValidationService.getPropertyValidationRules(objectClass, propertyName);

		if ((rules == null) || (rules.getSizeMax() == null))
		{
			throw new FixedFormatViolationException("No fixed length specified for the fixed format field "
			        + propertyName + " in class " + objectClass);
		}

		int fixedFormatSize = rules.getSizeMax().intValue();

		propertyValue = (propertyValue == null) ? "" : propertyValue.trim();

		int propertyValueLength = propertyValue.length();

		// Commented by nitinbr to fix Defect #4697
		if (propertyValueLength > fixedFormatSize)
		{
			return propertyValue.substring(0, fixedFormatSize);
		}

		return propertyValue;
	}

    /**
	 * Returns the formatted file name for the generated qualiac file.
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @param objectClass
	 * @return
	 * @throws FixedFormatViolationException
	 */

    // added to fix defects 4733,4775,4801. This method was added
    // since certain portions of the original method, getFixedFormatStr()
    // had to be removed due to defect 4697 ---- nitinbr
    public static String getFormatedFileName(String propertyName, String propertyValue, Class objectClass,
	        PaddingTypeEnum paddingType, char paddingChar) throws FixedFormatViolationException
	{
		ValidationRules rules = ValidationService.getPropertyValidationRules(objectClass, propertyName);

		if ((rules == null) || (rules.getSizeMax() == null))
		{
			throw new FixedFormatViolationException("No fixed length specified for the fixed format field "
			        + propertyName + " in class " + objectClass);
		}

		int fixedFormatSize = rules.getSizeMax().intValue();

		propertyValue = (propertyValue == null) ? "" : propertyValue.trim();

		int propertyValueLength = propertyValue.length();

		if (propertyValueLength < fixedFormatSize)
		{
			StringBuffer tmpBuffer = new StringBuffer();
			char[] charArrayForPadding = new char[fixedFormatSize - propertyValueLength];
			Arrays.fill(charArrayForPadding, paddingChar);

			if (PaddingTypeEnum.LEFT_PADDING.equals(paddingType))
			{
				tmpBuffer.append(charArrayForPadding);
				tmpBuffer.append(propertyValue);
			} else
			{
				tmpBuffer.append(propertyValue);
				tmpBuffer.append(charArrayForPadding);
			}

			return tmpBuffer.toString();
		}

		if (propertyValueLength > fixedFormatSize)
		{
			return propertyValue.substring(0, fixedFormatSize);
		}

		return propertyValue;
	}
}
