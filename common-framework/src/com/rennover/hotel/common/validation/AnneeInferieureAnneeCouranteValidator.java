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

import java.util.Calendar;
import java.util.Date;

public class AnneeInferieureAnneeCouranteValidator extends PropertyValidator
{
	public void validate(String propertyName, Object value, PropertyProblemCollection problems)
	{
		int annee = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int anneeCourante = calendar.get(Calendar.YEAR);

		if (value instanceof Long)
		{
			annee = ((Long) value).intValue();
		} else if (value instanceof Integer)
		{
			annee = ((Integer) value).intValue();
		} else if (value instanceof Short)
		{
			annee = ((Short) value).intValue();
		} else if (value instanceof Date)
		{
			calendar.setTime((Date) value);
			annee = calendar.get(Calendar.YEAR);
		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, value));
		}

		if (annee > anneeCourante)
		{
			problems.addPropertyProblemWithMessage("Ce champ doit contenir une année inférieure à l'année courante",
			        propertyName, getClass(), false);
		}
	}
}