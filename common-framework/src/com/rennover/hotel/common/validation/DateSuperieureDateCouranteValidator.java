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
 * This validator validates a date if it is greater than the current date
 * (strictly or not) . It does not take into acompte the time part of
 * the date.
 *
 * @author harishp
 */
public class DateSuperieureDateCouranteValidator extends PropertyValidator
{
	/**
	 * false if strict comparison.
	 */
    private boolean m_superieureOuEgale = false;

    /**
     * Strictly greater by default.
     *
     */
    public DateSuperieureDateCouranteValidator()
	{
		this(false);
	}

    public DateSuperieureDateCouranteValidator(boolean superieureOuEgale)
	{
		m_superieureOuEgale = superieureOuEgale;
	}

	public void validate(String propertyName, Object value, PropertyProblemCollection problems)
	{
		if (value instanceof Date)
		{
			if (m_superieureOuEgale)
			{
				Date today = DateHelper.getTodayDateNoTime();

				if (today.after((Date) value))
				{
					//Modified for defect 7861
					problems.addPropertyProblemWithMessage(
					        "Cette date doit être postérieure ou égale à la date du jour.", propertyName, getClass(),
					        false);
				}
			} else
			{
				Date tomorrow = DateHelper.getTomorrowDateNoTime();

				if (tomorrow.after((Date) value))
				{
					problems.addPropertyProblemWithMessage("Cette date doit être supérieure à la date du jour",
					        propertyName, getClass(), false);
				}
			}
		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, value));
		}
	}
}
