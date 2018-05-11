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
package com.rennover.hotel.common.helper;

import java.util.StringTokenizer;

import com.rennover.hotel.common.valueobject.PropertyHelper;

/**
 * @author dattias
 */
public abstract class StringHelper
{
	private static final String[] COMMON_STRINGS = new String[] { "  ", "saint ", "sous " };

	private static final String[] REPLACEMENT_FOR_COMMON_STRINGS = new String[] { " ", "st ", "ss " };

	public static String toUpperCaseNoAccent(String value)
	{
		if (value == null)
		{
			return null;
		}

		return toUpperCase(toNoAccent(value));
	}

	public static String toUpperCase(String value)
	{
		if (value == null)
		{
			return null;
		}

		return value.toUpperCase();
	}

	/**
	 * Remove all spaces from the string passed as a parameter
	 * 
	 * @param value
	 *            string to trim
	 * @return the value without any spaces character
	 */
	public static String removeAllSpaces(String str)
	{
		int i = str.indexOf(' ');

		if (i == -1)
		{
			return str;
		}

		StringTokenizer tokens = new StringTokenizer(str, " ");

		StringBuffer buffer = new StringBuffer();

		while (tokens.hasMoreTokens())
		{
			buffer.append(tokens.nextToken());
		}

		return buffer.toString();
	}

	/**
	 * Remove the unnecessary space characters from the string passed as a
	 * parameter.
	 * 
	 * Example: " aa aaa " --> "aa aaa"
	 * 
	 * Returns null if the parameter is null or if the trimed string is empty.
	 * 
	 * @param value
	 *            string to trim
	 * @return the value without any unnecessary space character
	 */
	public static String trimSpaces(String value)
	{
		if (value == null)
		{
			return null;
		}

		int index = 0;
		char current;
		boolean triming = true;
		boolean appending = false;
		StringBuffer buffer = new StringBuffer(value.length());

		while (index < value.length())
		{
			current = value.charAt(index);

			if (current == ' ')
			{
				triming = true;
			} else
			{
				if (triming && appending)
				{
					buffer.append(' ');
				}

				buffer.append(current);
				triming = false;
				appending = true;
			}

			++index;
		}

		if (buffer.length() == 0)
		{
			return null;
		}

		return buffer.toString();
	}

	public static String toSimpleString(String str)
	{
		if (null == str)
		{
			return str;
		}

		String newStr = str;
		newStr = newStr.toLowerCase(); // minuscules
		newStr = toNoAccent(newStr);
		newStr = removeNonAlphaCharacters(newStr); // remplacement par des
													// espaces
		newStr = removeCommonPortions(new StringBuffer(newStr)).toString(); // cf.
																			// tableau
																			// COMMON_STRINGS
		newStr = newStr.trim(); // retrait espaces de debut & fin

		return newStr;
	}

	public static String toNoAccent(String value)
	{
		if (value == null)
		{
			return null;
		}

		char[] chars = value.toCharArray();

		for (int i = 0; i < chars.length; i++)
		{
			chars[i] = toNoAccent(chars[i]);
		}

		return new String(chars);
	}

	private static char toNoAccent(char oldChar)
	{
		char newChar = Character.toLowerCase(oldChar);

		if ((newChar == 'é') || (newChar == 'è') || (newChar == 'ê') || (newChar == 'ë'))
		{
			newChar = 'e';
		} else if ((newChar == 'á') || (newChar == 'à') || (newChar == 'â') || (newChar == 'ä') || (newChar == 'å'))
		{
			newChar = 'a';
		} else if ((newChar == 'í') || (newChar == 'ì') || (newChar == 'î') || (newChar == 'ï'))
		{
			newChar = 'i';
		} else if ((newChar == 'ó') || (newChar == 'ò') || (newChar == 'ô') || (newChar == 'ö'))
		{
			newChar = 'o';
		} else if ((newChar == 'ú') || (newChar == 'ù') || (newChar == 'û') || (newChar == 'ü'))
		{
			newChar = 'u';
		} else if (newChar == 'ÿ')
		{
			newChar = 'y';
		} else if (newChar == 'ç')
		{
			newChar = 'c';
		} else if (newChar == 'ñ')
		{
			newChar = 'n';
		}

		if (Character.isUpperCase(oldChar))
		{
			return Character.toUpperCase(newChar);
		}

		return newChar;
	}

	private static String removeNonAlphaCharacters(String str)
	{
		char[] chars = str.toCharArray();

		for (int i = 0; i < chars.length; i++)
		{
			char oldChar = chars[i];
			char newChar = oldChar;

			// ajouter ici toutes les regles d'elimination des accents
			if ((oldChar == '-') || (oldChar == '/') || (oldChar == '\\') || (oldChar == '&') || (oldChar == '_')
			        || (oldChar == '+') || (oldChar == '=') || (oldChar == ':') || (oldChar == '.') || (oldChar == ',')
			        || (oldChar == '\'') || (oldChar == '*') || (oldChar == '|') || (oldChar == '(')
			        || (oldChar == ')'))
			{
				newChar = ' ';
			}

			chars[i] = newChar;
		}

		return new String(chars);
	}

	private static StringBuffer removeCommonPortions(StringBuffer str)
	{
		StringBuffer result = str;

		for (int i = 0; i < COMMON_STRINGS.length; i++)
		{
			for (int j = 0; j < (str.length() - COMMON_STRINGS[i].length() + 1); j++)
			{
				String str1 = COMMON_STRINGS[i];
				String str2 = str.substring(j, j + COMMON_STRINGS[i].length());

				if (str2.equals(str1))
				{
					StringBuffer newStr = new StringBuffer(str.substring(0, j));
					newStr.append(REPLACEMENT_FOR_COMMON_STRINGS[i]);
					newStr.append(str.substring(j + str1.length(), str.length()));
					result = removeCommonPortions(newStr);

					return result;
				}
			}
		}

		return result;
	}

	public static String removeSuffix(String value, String suffix)
	{
		int index = value.indexOf(suffix);

		return (index < 0) ? value : value.substring(0, index);
	}

	public static String removePrefix(String value, String prefix)
	{
		return value.startsWith(prefix) ? value.substring(prefix.length()) : value;
	}

	/**
	 * récupère un token à un index particulier. A utiliser avec précaution ; ne
	 * pas utiliser cette méthode dans une boucle qui fait varier l'index (dans
	 * ce cas utiliser directement un tokenizer et récupérer les différentes
	 * valeurs avec la méthode next())
	 * 
	 * @param index
	 *            zero-based index
	 */
	public static String getToken(String givenName, String separator, int index)
	{
		StringTokenizer tokenizer = new StringTokenizer(givenName, separator);
		int i = 0;

		while (i < index)
		{
			if (tokenizer.hasMoreTokens())
			{
				tokenizer.nextToken();
				i++;
			} else
			{
				return null;
			}
		}

		if (tokenizer.hasMoreTokens())
		{
			return tokenizer.nextToken();
		}

		return null;
	}

	public static String replaceAll(String str, String replaceWhat, String withWhat)
	{
		if (!PropertyHelper.isNull(str))
		{
			int foundAtIndex = str.indexOf(replaceWhat);

			while (foundAtIndex >= 0)
			{
				str = str.substring(0, foundAtIndex) + withWhat + str.substring(foundAtIndex + replaceWhat.length());

				if (foundAtIndex != str.indexOf(replaceWhat))
				{
					foundAtIndex = str.indexOf(replaceWhat);
				} else
				{
					foundAtIndex = str.indexOf(replaceWhat, foundAtIndex + 1);
				}
			}
		}

		return str;
	}

	public static String[] split(String str, String delimiter)
	{
		if (str != null)
		{
			StringTokenizer tokenizer = new StringTokenizer(str, delimiter);
			String[] tokens = new String[tokenizer.countTokens()];
			int i = 0;

			while (tokenizer.hasMoreElements())
			{
				tokens[i++] = tokenizer.nextToken();
			}

			return tokens;
		}

		return null;
	}

	/**
	 * 
	 * @param str
	 * @param delimiter
	 * @param considerSpaceAsToken -
	 *            StringTokenizer by default doesnot consider empty string as a
	 *            token. pass considerSpaceAsToken = true for empty string to be
	 *            considered as a token
	 * @return
	 */
	public static String[] split(String str, String delimiter, boolean considerSpaceAsToken)
	{
		if (!PropertyHelper.isNull(str))
		{
			if (considerSpaceAsToken)
			{
				if (str.startsWith(delimiter))
				{
					str = " " + delimiter;
				}

				if (str.endsWith(delimiter))
				{
					str = str + " ";
				}

				str = replaceAll(str, delimiter + delimiter, delimiter + " " + delimiter);
			}

			return split(str, delimiter);
		}

		return null;
	}

	/**
	 * Use to convert telphone and fax number in French Format.
	 * 
	 * @param str
	 * @return
	 */
	public static String formatTelFax(String str)
	{
		String formattedStr = new String();

		if (!PropertyHelper.isNull(str))
		{
			str = str.trim();

			if (str.length() != 0)
			{
				int rem = str.length() % 2;

				if (rem != 0)
				{
					str = str + " ";
				}

				int i = 0;

				while (i < str.length())
				{
					formattedStr = formattedStr + str.substring(i, i + 2) + " ";
					i = i + 2;
				}
			}
		}

		return trimSpaces(formattedStr);
	}
}
