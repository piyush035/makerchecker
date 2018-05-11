
package com.rennover.client.framework.widget.button;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.Icon;

import com.rennover.client.framework.widget.base.ZButton;

/**
 * @author tcaboste
 * 
 * Cet objet est un petit bouton que l'on peut placer � c�t� d'un champs. Il
 * n'affiche donc pas le libell� de l'action mais seulement l'ic�ne de l'action.
 * Si aucun ic�ne n'est d�fini, trois petits points s'affichent dans le bouton.
 * Le libell� se retrouve dans le tooltip si celui-ci n'est pas d�fini.
 * 
 */
public class SmallButton extends ZButton
{
	/**
	 * Constructeur simple utilis� en interne
	 */
	private SmallButton()
	{
	}

	public Dimension getPreferredSize()
	{
		return getMinimumSize();
	}

	public Dimension getMaximumSize()
	{
		return getMinimumSize();
	}

	public Dimension getMinimumSize()
	{
		return new Dimension(24, 22);
	}

	/**
	 * Constructeur avec une action
	 * 
	 * @param action
	 *            action to associate with the button
	 */
	public SmallButton(Action action)
	{
		this();
		setAction(action);
	}

	public SmallButton(String title)
	{
		this();
		setText("...");
		setToolTipText(title);
	}

	/**
	 * overriding of the setAction to manage the title and the icon on the small
	 * button
	 * 
	 */
	public void setAction(Action action)
	{
		super.setAction(action);

		Icon icon = (Icon) action.getValue(Action.SMALL_ICON);
		if (icon == null)
		{
			setText("...");
		} else
		{
			setText("");
		}

		String shortDescription = (String) action.getValue(Action.SHORT_DESCRIPTION);
		if (shortDescription == null)
		{
			String name = (String) action.getValue(Action.NAME);
			setToolTipText(name);
		}
	}
}
