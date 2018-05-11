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

import com.stevesoft.pat.Regex;

public class RaisionSocialeValidator extends PropertyValidator
{

	// added for UC-R-26
	public static final String PATTERN_RAISON_SOCIALE = ".*[ \t\n\f\r].[ \t\n\f\r].*";

	// attributes
	private Regex m_regex;

	// uncomment to switch to jdk 1.4
	// private Pattern m_pattern;
	private String m_specificMessage;

	private boolean m_warning;

	public RaisionSocialeValidator()
	{
		this(PATTERN_RAISON_SOCIALE, null);
	}

	public RaisionSocialeValidator(String pattern, String specificMessage)
	{
		this(pattern, specificMessage, false);
	}

	public RaisionSocialeValidator(String pattern, String specificMessage, boolean warning)
	{
		m_regex = new Regex(pattern);
		// uncomment to switch to jdk 1.4
		// Commmented code removed
		m_specificMessage = specificMessage;
		m_warning = warning;
	}

	public void validate(String propertyName, Object value, PropertyProblemCollection problems)
	{
		if (value instanceof String)
		{
			String s = (String) value;

			m_regex.search(s);

			if (m_regex.charsMatched() > 0)
			// uncomment to switch to jdk 1.4
			// Commmented code removed

			{

				problems.addPropertyProblemWithMessage(m_specificMessage, propertyName, getClass(), m_warning);

			}
			System.out.println("trye");
		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, value));
		}
	}
}