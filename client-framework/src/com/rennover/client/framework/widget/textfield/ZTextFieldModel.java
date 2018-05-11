
package com.rennover.client.framework.widget.textfield;

import javax.swing.text.BadLocationException;

import com.rennover.hotel.common.log.Logger;

/**
 * Extension simple de {@link javax.swing.text.PlainDocument} qui ajoute des
 * méthodes pour faciliter le changement de sa valeur String.
 * 
 * @audit dattias 13/09/02
 */
public class ZTextFieldModel extends LimitedDocument
{
	/**
	 * Retourne la chaîne de caractères affichée dans le TextField. S'il est
	 * impossible d'obtenir cette String, une erreur est loggée.
	 */
	public String getText()
	{
		String value = "";
		try
		{
			value = getText(0, getLength());
		} catch (BadLocationException e)
		{
			Logger.error(this, e);
			// @tochange lever une runtime exception
		}
		return value;
	}

	/**
	 * Place une chaîne de caractères dans le TextField correspondant. S'il est
	 * impossible de positionner cette String, une erreur est loggée.
	 */
	public void setText(String text)
	{
		try
		{
			remove(0, getLength());
			insertString(0, text, null);
		} catch (BadLocationException exc)
		{
			Logger.error(this, exc);
			// @tochange lever une runtime exception
		}
	}
}