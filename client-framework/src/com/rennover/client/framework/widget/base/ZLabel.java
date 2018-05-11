package com.rennover.client.framework.widget.base;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * Un simple héritier de {@link javax.swing.JLabel}, créé au cas où il s'avère
 * nécessaire d'implémenter des fonctionnalités communes à tous les labels
 * utilisés dans l'application.
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
		; // un label n'est pas désactivable
	}

	public void super_setEnabled(boolean b)
	{
		super.setEnabled(b);
	}
}