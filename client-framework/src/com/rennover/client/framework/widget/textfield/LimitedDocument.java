package com.rennover.client.framework.widget.textfield;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Title: Limited PlainDocument Description: Document used to limit the text
 * length entered in a TextField or in a TextArea Copyright: Copyright (c) 2002
 * Company: Valtech
 * 
 * @author Laurent DELVAUX
 * @version 1.1
 */
public class LimitedDocument extends PlainDocument
{
	private int m_length = -1;

	/**
	 * Override this method to modify the max length of the document.
	 * 
	 * @return the max length this document can be
	 */
	public int getLimitedLength()
	{
		return m_length;
	}

	public void setLimitedLength(int length)
	{
		m_length = length;
	}

	/**
	 * Try to add the String str to this document. If str length is less than (
	 * getLimitedLength() - current document length ), str is added to this
	 * document, otherwise nothing happen.
	 */
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
	{
		// if null exit
		if ((str == null))
		{
			return;
		}

		int strLength = str.length();
		int length = this.getLength();
		int limitedLength = this.getLimitedLength();

		// if compute length is less than getLimitedLength() then insert
		// submited string
		if (limitedLength == -1 || (length + strLength) <= limitedLength)
		{
			super.insertString(offs, str, a);
		} else
		{
			// else truncate the String to an aceptable length
			super.insertString(offs, str.substring(0, limitedLength - length), a);
		}
	}
}