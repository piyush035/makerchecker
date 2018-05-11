package com.rennover.client.framework.widget.textfield;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Title: Letter or digit document Description: Document used to limit the text
 * length and to verify the text is only made of letter, digit or underscore
 * chars. Copyright: Copyright (c) 2002 Company: Valtech
 * 
 * @author Laurent DELVAUX
 * @version 1.0
 */
public class LetterOrDigitDocument extends LimitedDocument
{
	/**
	 * Try to add the String str to this document. If str length is less than (
	 * getLimitedLength() - current document length ) and if str is exclusively
	 * made of Letters, Digits or "_", str is added to this document, otherwise
	 * nothing happen.
	 */
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
	{
		// if null do nothing
		if (str == null)
		{
			return;
		}
		// if str isn't made exclusively with Letter or Digit, do nothing
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			if (!Character.isLetterOrDigit(chars[i]) && !(chars[i] == '_'))
			{
				return;
			}
		}

		super.insertString(offs, str, a);
	}
}