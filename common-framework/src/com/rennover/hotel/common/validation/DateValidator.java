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
import java.util.GregorianCalendar;

import com.rennover.hotel.common.helper.DateHelper;

/**
 * V�rifie la validit� d'une date
 * @exemple 
 */
public class DateValidator extends PropertyValidator
{
	Date m_dateMin = null;

	Date m_dateMax = null;

	/**
	 */
	public DateValidator()
	{
		// fix for INTERNAL DEFECT --- According to the Ergonomics specified in
		// VSS

		m_dateMin = new GregorianCalendar(1900, 01, 01).getTime();
		m_dateMax = new GregorianCalendar(3000, 01, 01).getTime();
	}

	/**
	 * Valide une r�gle simple sur une propri�t�
	 * 
	 * @param propertyName nom de la propri�t� test�e
	 * @param value valeur de la propri�t� � v�rifier
	 * @param problems collection de probl�mes � compl�ter si n�cessaire 
	 */
	public void validate(String propertyName, Object propertyValue, PropertyProblemCollection problems)
	{

		if (propertyValue instanceof Date)
		{
			Date date = (Date) propertyValue;
			if (DateHelper.INVALID_DATE.equals(propertyValue) || m_dateMin.compareTo(date) > 0
			        || m_dateMax.compareTo(date) <= 0)
			{
				problems.addPropertyProblemWithMessage("La date saisie est invalide ou incompl�te.", propertyName,
				        getClass(), false);
			}

		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, propertyValue));
		}

	}
}