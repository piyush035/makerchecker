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

/**
 * Check a max limit validation rule.
 */
public class RangeMaxValidator extends PropertyValidator
{
	/**
	 * Max limit.
	 */
	private Comparable m_max;

	/**
	 * Wether the limit is strict or not.
	 */
	private boolean m_strict;

	private boolean m_warning;

	private String m_specificMessage;

	public RangeMaxValidator(Comparable max)
	{
		this(max, false);
	}

	public RangeMaxValidator(Comparable max, boolean strict)
	{
		this(max, strict, null, false);
	}

	public RangeMaxValidator(Comparable max, boolean strict, String specificMessage, boolean warning)
	{
		m_max = max;
		m_strict = strict;
		m_specificMessage = specificMessage;
		m_warning = warning;
	}

	public void validate(String propertyName, Object propertyValue, PropertyProblemCollection problems)
	{
		if (propertyValue instanceof Comparable)
		{
			Comparable c = (Comparable) propertyValue;

			if (m_max != null)
			{
				int comp = c.compareTo(m_max);

				if (comp > 0 || (m_strict && comp == 0))
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
		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, propertyValue));
		}
	}
}