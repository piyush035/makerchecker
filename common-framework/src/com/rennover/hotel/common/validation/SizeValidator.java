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

import java.util.Collection;
import java.util.Map;

/**
 * Vérifie le respect d'une taille min et max
 */
public abstract class SizeValidator extends PropertyValidator
{
	private String m_specificMessage;

	private boolean m_warning;

	public SizeValidator(String specificMessage, boolean warning)
	{
		m_specificMessage = specificMessage;
		m_warning = warning;
	}

	public void validate(String propertyName, Object value, PropertyProblemCollection problems)
	{
		long size;

		if (value instanceof String)
		{
			size = ((String) value).length();
		} else if (value instanceof Collection)
		{
			size = ((Collection) value).size();
		} else if (value instanceof Map)
		{
			size = ((Map) value).size();
		} else if (value instanceof Number)
		{
			return; // rien pour l'instant
		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, value));
		}

		if (isSizeInvalid(size))
		{
			if (m_specificMessage == null)
			{
				problems.addPropertyProblem(propertyName, getClass(), m_warning);
			} else
			{
				problems.addPropertyProblemWithMessage(m_specificMessage, propertyName, getClass(), m_warning);
			}
		}
	}

	protected abstract boolean isSizeInvalid(long size);
}