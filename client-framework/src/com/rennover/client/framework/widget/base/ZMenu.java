package com.rennover.client.framework.widget.base;

import java.awt.Color;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ZMenu extends JMenu
{
	public static final Color NOT_AVAILABLE_COLOR = Color.RED.darker().darker();

	public static boolean s_useRedMenu = true;

	/**
	 * Constructor for ZMenu.
	 */
	public ZMenu()
	{
		super();
	}

	/**
	 * Constructor for ZMenu.
	 * 
	 * @param s
	 */
	public ZMenu(String s)
	{
		super(s);
	}

	public ZMenu(String s, int mnemonic)
	{
		super(s);
		setMnemonic(mnemonic);
	}

	public ZMenu(String s, char mnemonic)
	{
		super(s);
		setMnemonic(mnemonic);
	}

	/**
	 * Constructor for ZMenu.
	 * 
	 * @param a
	 */
	public ZMenu(Action a)
	{
		super(a);
	}

	/**
	 * Constructor for ZMenu.
	 * 
	 * @param s
	 * @param b
	 */
	public ZMenu(String s, boolean b)
	{
		super(s, b);
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public ZMenu addSubMenu(String s)
	{
		ZMenu submenu = new ZMenu(s);
		add(submenu);
		return submenu;
	}

	/**
	 * 
	 * @param s
	 * @param mnemonic
	 * @return
	 */
	public ZMenu addSubMenu(String s, int mnemonic)
	{
		ZMenu submenu = new ZMenu(s, mnemonic);
		add(submenu);
		return submenu;
	}

	/**
	 * Utilisation interne Surcharge de createActionComponent pour créer des
	 * ZMenuItems plutôt que des JMenuItems
	 */
	protected JMenuItem createActionComponent(Action a)
	{
		JMenuItem mi = new ZMenuItem((String) a.getValue(Action.NAME), (Icon) a.getValue(Action.SMALL_ICON))
		{
			protected PropertyChangeListener createActionPropertyChangeListener(Action a)
			{
				PropertyChangeListener pcl = createActionChangeListener(this);
				if (pcl == null)
				{
					pcl = super.createActionPropertyChangeListener(a);
				}
				return pcl;
			}
		};
		mi.setHorizontalTextPosition(JButton.TRAILING);
		mi.setVerticalTextPosition(JButton.CENTER);
		mi.setEnabled(a.isEnabled());
		return mi;
	}

	public JMenuItem add(Action a, KeyStroke keyStroke)
	{
		JMenuItem menuItem = super.add(a);
		menuItem.setAccelerator(keyStroke);
		return menuItem;
	}

	public JMenuItem add(String s, Action action)
	{
		return add(s, 0, action);
	}

	public JMenuItem add(String s)
	{
		return add(s, 0, (Action) null);
	}

	public JMenuItem add(String s, int mnemonic)
	{
		return add(s, mnemonic, (Action) null);
	}

	public JMenuItem add(String s, KeyStroke keyStroke)
	{
		return add(s, 0, (Action) null, keyStroke);
	}

	public JMenuItem add(String s, int mnemonic, Action action)
	{
		return add(s, mnemonic, action, (KeyStroke) null);
	}

	public JMenuItem add(String text, int mnemonic, Action action, String tooltip)
	{
		return add(text, mnemonic, action, null, tooltip);
	}

	public JMenuItem add(String s, int mnemonic, KeyStroke keyStroke)
	{
		return add(s, mnemonic, (Action) null, keyStroke, null);
	}

	public JMenuItem add(String text, int mnemonic, Action action, KeyStroke keyStroke)
	{
		return add(text, mnemonic, action, keyStroke, null);
	}

	public JMenuItem add(String s, int mnemonic, Action action, KeyStroke keyStroke, String tooltip)
	{
		JMenuItem menuItem;
		if (action != null)
		{
			menuItem = super.add(action);
			if (s != null)
			{
				menuItem.setText(s);
			}
		} else
		{
			menuItem = add(new ZMenuItem(s));
			if (isUseRedMenu())
			{
				menuItem.setForeground(NOT_AVAILABLE_COLOR);
			}
		}

		if (mnemonic != 0)
		{
			menuItem.setMnemonic(mnemonic);
		}

		if (keyStroke != null)
		{
			menuItem.setAccelerator(keyStroke);
		}

		if (tooltip != null)
		{
			menuItem.setToolTipText(tooltip);
		}

		return menuItem;
	}

	/**
	 * Indique si l'on est en mode menus rouges NB: les menus rouges sont les
	 * menus rattachés à aucune action
	 * 
	 * @return
	 */
	public static boolean isUseRedMenu()
	{
		return s_useRedMenu;
	}

	/**
	 * Active mode menus rouges NB: les menus rouges sont les menus rattachés à
	 * aucune action
	 * 
	 * @param b
	 */
	public static void setUseRedMenu(boolean b)
	{
		s_useRedMenu = b;
	}

}