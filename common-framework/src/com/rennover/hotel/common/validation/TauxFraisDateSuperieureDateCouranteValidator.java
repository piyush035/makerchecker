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

public class TauxFraisDateSuperieureDateCouranteValidator extends PropertyValidator
{

	private boolean m_superieureOuEgale = false;

	public TauxFraisDateSuperieureDateCouranteValidator()
	{
		this(false);
	}

	public TauxFraisDateSuperieureDateCouranteValidator(boolean superieureOuEgale)
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
					/*
					 * Start: ME-1187 Changed message
					 */
					problems.addPropertyProblemWithMessage(
					        "Veuillez saisir une date d'inactivation postérieure à la date d'effet ou une date null.",
					        propertyName, getClass(), false);
					// End: ME-1187
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
