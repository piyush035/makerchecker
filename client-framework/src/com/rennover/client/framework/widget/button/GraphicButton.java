
package com.rennover.client.framework.widget.button;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.Icon;

import com.rennover.client.framework.widget.base.ZButton;

/**
 * Un simple héritier de {@link javax.swing.JButton}, créé au cas où il s'avère
 * nécessaire d'implémenter des fonctionnalités communes à tous les boutons
 * utilisés dans l'application.
 * 
 * <p>
 * Note: il n'y a pas de constructeur prenant en paramètre une String, comme
 * dans {@link javax.swing.JButton#JButton(java.lang.String) JButton}, parce
 * qu'on {@link javax.swing.AbstractButton#setAction(javax.swing.Action)
 * utilisera} des {@link javax.swing.Action}s qui positionneront le texte
 * eux-même.
 * </p>
 * 
 * <p>
 * Du coup, il est également inutile d'appeler la méthode {@link
 * javax.swing.AbstractButton#setText(java.lang.String)}.
 * </p>
 */
public class GraphicButton extends ZButton
{
	public GraphicButton()
	{
		init();
	}

	public GraphicButton(Icon icon, Action action)
	{
		this(icon, icon, icon, icon, null, action);
	}

	public GraphicButton(Icon icon, Icon rolloverIcon, String toolTipText, Action action)
	{
		this(icon, icon, rolloverIcon, icon, toolTipText, action);
	}

	public GraphicButton(Icon icon, Icon pressedIcon, Icon rolloverIcon, Icon disableIcon, String toolTipText,
	        Action action)
	{
		super();

		if (action != null)
		{
			setAction(action);
		}

		if (icon != null)
		{
			setIcon(icon);
		}

		if (pressedIcon != null)
		{
			setPressedIcon(pressedIcon);
		}

		if (rolloverIcon != null)
		{
			setRolloverIcon(rolloverIcon);
		}

		if (disableIcon != null)
		{
			setDisabledIcon(disableIcon);
		}

		if (toolTipText != null)
		{
			setToolTipText(toolTipText);
		}

		init();
	}

	private void init()
	{
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setMargin(new Insets(2, 2, 2, 2));

		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke)
			{
				if (ke.getKeyCode() == KeyEvent.VK_ENTER)
				{
					GraphicButton.this.doClick();
				}
			}
		});

		setDefaultCapable(true);
	}

	/**
	 * @deprecate cette méthode ne devrait pas être utilisée, puisque le texte
	 *            doit être positionné par l'Action.
	 */
	public void setText(String text)
	{
		super.setText(text);
	}

	public void setEnabled(boolean b)
	{
		Action action = getAction();
		if (action != null)
		{
			if (action.isEnabled())
			{
				super.setEnabled(b);
			} else if (isEnabled())
			{
				super.setEnabled(false);
			}
		} else
		{
			super.setEnabled(b);
		}
	}
}