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
package com.rennover.hotel.common.validation;

import java.util.Date;

import com.rennover.hotel.common.helper.DateHelper;

/**
 * ...
 * 
 * @author dattias
 */
public class DateInferieureDateCouranteValidator extends PropertyValidator
{
	public void validate(String propertyName, Object value, PropertyProblemCollection problems)
	{
		if (value instanceof Date)
		{
			Date currentDate = DateHelper.getTodayDateNoTime();

			if (currentDate.before((Date) value))
			{
				problems.addPropertyProblemWithMessage("Cette date doit être inférieure ou égale à la date du jour",
				        propertyName, getClass(), false);
			}
		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, value));
		}
	}
}