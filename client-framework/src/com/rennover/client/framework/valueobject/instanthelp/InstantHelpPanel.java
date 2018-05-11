package com.rennover.client.framework.valueobject.instanthelp;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JSeparator;

import com.rennover.client.framework.message.MessageFactory;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.widget.panel.StatusBar;

/**
 * Barre d'état utilisable dans les Frame et les Dialog Panel d'aide à la saisie
 * contenant une zone de message
 * 
 * Ce panneau est utilisé en tant que zone d'aide à la saisie avec
 * 
 * 
 * @audit dattias 13/09/02
 */
public class InstantHelpPanel extends StatusBar implements InstantHelpView
{
	/**
	 * Construit un panel composé d'un JLabel et d'un fond jaune.
	 */
	public InstantHelpPanel()
	{
		add(new JSeparator(), BorderLayout.NORTH);
		displayString("Help Zone");
	}

	/**
	 * Implémentation de l'interface InstantHelpView Permet d'afficher un
	 * message d'aide à la saisie
	 */
	public void displayString(String text)
	{
		text = MessageFactory.translate(text);
		super.setText(text);
		setIcon(null);
	}

	public void displayString(int iconStyle, String text)
	{
		text = MessageFactory.translate(text);
		super.setText(text);
		setIcon(getIcon(iconStyle));
	}

	static protected Icon getIcon(int iconStyle)
	{
		switch (iconStyle)
		{
		case WARNING_ICON:
			return IconFactory.getWarningIcon();
		case ERROR_ICON:
			return IconFactory.getErrorIcon();
		default:
			return null;
		}
	}

}
