package com.rennover.client.framework.widget.base;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * Un simple h�ritier de {@link javax.swing.JLabel}, cr�� au cas o� il s'av�re
 * n�cessaire d'impl�menter des fonctionnalit�s communes � tous les labels
 * utilis�s dans l'application.
 */
public class ZLabel extends JLabel
{
	public ZLabel(String text)
	{
		super(text);
	}

	public ZLabel(String text, Icon icon, int horizontalAlignment)
	{
		super(text, icon, horizontalAlignment);
	}

	public ZLabel(String text, int horizontalAlignment)
	{
		super(text, horizontalAlignment);
	}

	public ZLabel(Icon image)
	{
		super(image);
	}

	public ZLabel()
	{
		super();
	}

	public ZLabel(Icon icon, int horizontalAlignment)
	{
		super(icon, horizontalAlignment);
	}

	public void setLabel(String label)
	{
		setText(label + " :");
	}

	public void setEnabled(boolean enabled)
	{
		; // un label n'est pas d�sactivable
	}

	public void super_setEnabled(boolean b)
	{
		super.setEnabled(b);
	}
}