
package com.rennover.client.framework.widget.button;

import javax.swing.Action;
import javax.swing.Icon;

import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.icon.IconFactory;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class ButtonFactory
{
	public static final String BOUTONS_DIR = "boutons";

	public static final String ACCES_DIRECT_DIR = "configuration";

	public static final String BARRE_DE_MENU_DIR = "barre_de_menu";

	public static final String FONCTIONS_RAPIDES_DIR = "fonctions_rapides";

	private static String s_defaultIconDirectory = ".";

	public static ZButton newButton(String iconName, String rolloverIconName, String description, Action action)
	{
		GraphicButton button = new GraphicButton();

		if (action != null)
		{
			button.setAction(action);
			button.setToolTipText(button.getText());
			button.setText(null);
		}

		if (iconName != null)
		{
			Icon normalIcon = getIcon(iconName);
			Icon rolloverIcon = getIcon(rolloverIconName);
			Icon grayIcon = getGrayIcon(iconName);

			button.setIcon(normalIcon);
			button.setPressedIcon(rolloverIcon);
			button.setRolloverIcon(rolloverIcon);
			button.setDisabledIcon(grayIcon);
		}

		if (description != null)
		{
			button.setToolTipText(description);
		}

		return button;
	}

	public static ZButton newButton(String iconName, String description, Action action)
	{
		GraphicButton button = new GraphicButton();

		if (action != null)
		{
			button.setAction(action);
			button.setToolTipText(button.getText());
			button.setText(null);
		}

		if (iconName != null)
		{
			Icon normalIcon = getIcon(iconName);
			Icon brightIcon = getBrightIcon(iconName);
			Icon grayIcon = getGrayIcon(iconName);

			button.setIcon(brightIcon);
			button.setPressedIcon(brightIcon);
			button.setRolloverIcon(normalIcon);
			button.setDisabledIcon(grayIcon);
		}

		if (description != null)
		{
			button.setToolTipText(description);
		}

		return button;
	}

	public static ZButton newButton(String iconName, String pressedIconName, String rolloverIconName,
	        String disableIconName, String description, Action action)
	{
		GraphicButton button = new GraphicButton();

		if (action != null)
		{
			button.setAction(action);
			button.setToolTipText(button.getText());
			button.setText(null);
		}

		if (iconName != null)
		{
			button.setIcon(getIcon(iconName));
		}

		if (pressedIconName != null)
		{
			button.setPressedIcon(getIcon(pressedIconName));
		}

		if (rolloverIconName != null)
		{
			button.setRolloverIcon(getIcon(rolloverIconName));
			button.setFocusPainted(true);
		}

		if (disableIconName != null)
		{
			button.setDisabledIcon(getIcon(disableIconName));
		}

		if (description != null)
		{
			button.setToolTipText(description);
		}

		return button;
	}

	public static Icon getIcon(String iconName)
	{
		return IconFactory.getIcon(getDefaultIconDirectory() + "/" + iconName);
	}

	public static Icon getBrightIcon(String iconName)
	{
		return IconFactory.getBrightIcon(getDefaultIconDirectory() + "/" + iconName);
	}

	public static Icon getGrayIcon(String iconName)
	{
		return IconFactory.getGrayIcon(getDefaultIconDirectory() + "/" + iconName);
	}

	public static void setDefaultIconDirectory(String directory)
	{
		s_defaultIconDirectory = directory;
	}

	public static String getDefaultIconDirectory()
	{
		return s_defaultIconDirectory;
	}

}
