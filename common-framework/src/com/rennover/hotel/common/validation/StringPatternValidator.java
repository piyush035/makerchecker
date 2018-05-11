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

//uncomment to switch to jdk 1.4
//import java.util.regex.Pattern;

import com.stevesoft.pat.Regex;

/**
 * Pattern validation using a regex api.
 * 
 * We do not use the jdk 1.4 regex api because the validation fmk
 * can also be used on the server side which s runed in jdk 1.3.
 * 
 * We use a free api downloaded at www.javaregex.com. This library
 * does not behave exactly as the jdk 1.4 but this implementation is.
 * To change to jdk 1.4 just comment the com.stevesoft.pat lines and
 * uncomment the jave.util.regex lines.
 */
public class StringPatternValidator extends PropertyValidator
{
	// pattern constants
	public static final String PATTERN_INTEGER = "[0-9]*";

	public static final String PATTERN_CHAR = "[A-Za-z]*";

	public static final String PATTERN_UPPERCASE_CHAR = "[A-Z]*";

	public static final String PATTERN_LOGIN = "[A-Z0-9]*";

	public static final String PATTERN_NAME = "[^,.:;!?]*";

	public static final String PATTERN_UPPERCASE_NAME = "[^,.:;!?a-z]*";

	public static final String PATTERN_SIRETWILDCARDED = "[0-9*]*";

	// Start_DEFECT_13933
	// Numero pattern changed to: 2-4 alphanumeric (code site) "-" 1-9 numeric
	// (numeric length increased by 2 optional digits)
	public static final String PATTERN_NUMERO = "[A-Z][A-Z]\\d?\\d?[-]\\d\\d?\\d?\\d?\\d?\\d?\\d?\\d?\\d?";

	// End_DEFECT_13933
	public static final String PATTERN_RAISONSOCIALE_SEARCH = "[^\\*,.:;!?][^,.:;!?]*[\\*]*";

	// Added for ME 884 for newly added field:N° déclaration d’activité in
	// UC-R-30
	public static final String PATTERN_NUM_DECLARATION_EXISTANCE_SEARCH = "[^\\*?][^?]*[\\*]*";

	// Ended for ME 884
	// added for UC-G-102
	public static final String PATTERN_NUMERODOSSIER = "[A-Z][A-Z][0-9*]*";

	// added for UC-R-06
	// Commmented code removed
	// to fix 7719
	public static final String PATTERN_NAME_NO_Z = "[A-Za-z0-9]*";

	// added for UC-G-125
	public static final String PATTERN_NUMEROLISTING = "[L][C][0-9*]*";

	// added for UC-R-26
	public static final String PATTERN_RAISON_SOCIALE = ".*[ \t\n\f\r].[ \t\n\f\r].*";

	// added for UCG-164

	public static final String PATTERN_DDF_IDENTIFIANT = "[0-9]*[-]\\d\\d\\d\\d[-]\\d\\d\\d";

	// //added for UC-R-39
	// Commmented code removed
	public static final String PATTERN_INDIVIDU = "[A-Z\\-\\'\\ ]*";

	// Added for UCR-11
	public static final String PATTERN_INDIVIDU_LOGIN = "[A-Z0-9\\-]*";

	// Added for UC-C-48 updation
	public static final String PATTERN_CODE_POSTALE = "[A-Za-z0-9\\-\\ \\*]*";

	// Added for UC-R-60 ME-1431
	public static final String PATTERN_FORMULA_DIF_LIBELLE = "[A-Za-z0-9\\ \\*]*";

	// Added for ME -1219
	public static final String PATTERN_JOUR_MAX_EDITION = "[0-9][0-9][\\][0-9][0-9]";

	// attributes
	private Regex m_regex;

	// uncomment to switch to jdk 1.4
	// private Pattern m_pattern;
	private String m_specificMessage;

	private boolean m_warning;

	public StringPatternValidator(String pattern)
	{
		this(pattern, null);
	}

	public StringPatternValidator(String pattern, String specificMessage)
	{
		this(pattern, specificMessage, false);
	}

	public StringPatternValidator(String pattern, String specificMessage, boolean warning)
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

			if (m_regex.charsMatched() != s.length())
			// uncomment to switch to jdk 1.4
			// Commmented code removed

			{
				if (m_specificMessage == null)
				{
					problems.addPropertyProblem(propertyName, getClass(), m_warning);
				} else
				{
					problems.addPropertyProblemWithMessage(m_specificMessage, propertyName, getClass(), m_warning);
				}
			}
		} else
		{
			throw new IllegalArgumentException(createIllegalTypeMessage(propertyName, value));
		}
	}
}