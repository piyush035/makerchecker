
package com.rennover.client.framework.widget.textfield;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import com.rennover.hotel.common.helper.StringHelper;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class UpperCaseDocument extends LimitedDocument
{
	private boolean m_useUpperCase = true;

	public UpperCaseDocument()
	{
	}

	public UpperCaseDocument(boolean activate)
	{
		setUseUpperCase(activate);
	}

	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
	{
		if (isUseUpperCase())
		{
			str = StringHelper.toUpperCaseNoAccent(str);
		}
		super.insertString(offs, str, a);
	}

	/**
	 * Indique si la saisie en majuscule est active
	 * 
	 * @return
	 */
	public boolean isUseUpperCase()
	{
		return m_useUpperCase;
	}

	/**
	 * Permet d'activer ou de désactiver le mode majuscule
	 * 
	 * @param activate
	 */
	public void setUseUpperCase(boolean activate)
	{
		m_useUpperCase = activate;
	}
}