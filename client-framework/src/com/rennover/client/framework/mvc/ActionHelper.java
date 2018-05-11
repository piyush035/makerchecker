/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

package com.rennover.client.framework.mvc;

import javax.swing.Action;
import javax.swing.Icon;

/**
 * @author Piyush
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ActionHelper
{
	public static final String DEFAULT = Action.DEFAULT;

	public static final String ACTION_COMMAND_KEY = Action.ACTION_COMMAND_KEY;

	public static final String ACCELERATOR_KEY = Action.ACCELERATOR_KEY;

	public static final String MNEMONIC_KEY = Action.MNEMONIC_KEY;

	public static final String LONG_DESCRIPTION = Action.LONG_DESCRIPTION;

	public static final String SHORT_DESCRIPTION = Action.SHORT_DESCRIPTION;

	public static final String SMALL_ICON = Action.SMALL_ICON;

	public static final String NAME = Action.NAME;

	public static final String ENABLED = "enabled";

	public static final String VISIBLE = "visible";

	/**
	 * @param action
	 * @param key
	 */
	public static void setAcceleratorKey(DefaultAction action, String key)
	{
		action.putValue(ACCELERATOR_KEY, key);
	}

	/**
	 * @param action
	 * @return
	 */
	public static String getAcceleratorKey(DefaultAction action)
	{
		return (String) action.getValue(ACCELERATOR_KEY);
	}

	/**
	 * @param action
	 * @param key
	 */
	public static void setActionCommandKey(DefaultAction action, String key)
	{
		action.putValue(ACTION_COMMAND_KEY, key);
	}

	/**
	 * @param action
	 * @return
	 */
	public static String getActionCommandKey(DefaultAction action)
	{
		return (String) action.getValue(ACTION_COMMAND_KEY);
	}

	/**
	 * Change the icon of the action.
	 * 
	 * @param action
	 * @param icon
	 */
	public static void setIcon(Action action, Icon icon)
	{
		action.putValue(SMALL_ICON, icon);
	}

	/**
	 * Returns the icon of the action.
	 * 
	 * @param action
	 * @return
	 */
	public static Icon getIcon(Action action)
	{
		Icon icon = (Icon) action.getValue(SMALL_ICON);
		return icon;
	}

	/**
	 * @param action
	 * @param description
	 */
	public static void setLongDescrition(DefaultAction action, String description)
	{
		action.putValue(LONG_DESCRIPTION, description);
	}

	/**
	 * @param action
	 * @return
	 */
	public static String getLongDescrition(DefaultAction action)
	{
		return (String) action.getValue(LONG_DESCRIPTION);
	}

	/**
	 * @param action
	 * @param key
	 */
	public static void setMnemonicKey(DefaultAction action, String key)
	{
		action.putValue(MNEMONIC_KEY, key);
	}

	/**
	 * @param action
	 * @return
	 */
	public static String getMnemonicKey(DefaultAction action)
	{
		return (String) action.getValue(MNEMONIC_KEY);
	}

	/**
	 * Change the name of an action and the language associated components.
	 * 
	 * @param action
	 * @param name
	 */
	public static void setName(Action action, String name)
	{
		action.putValue(NAME, name);
	}

	/**
	 * Returns the name of the action.
	 * 
	 * @param action
	 * @return
	 */
	public static String getName(Action action)
	{
		String name = (String) action.getValue(NAME);
		return name;
	}

	/**
	 * @param action
	 * @param description
	 */
	public static void setShortDescrition(DefaultAction action, String description)
	{
		action.putValue(SHORT_DESCRIPTION, description);
	}

	/**
	 * @param action
	 * @return
	 */
	public static String getShortDescrition(DefaultAction action)
	{
		return (String) action.getValue(SHORT_DESCRIPTION);
	}

	/**
	 * Change the visibility of components representing the action (button or menu).
	 * 
	 * @param action
	 * @param visible
	 */
	public static void setVisible(Action action, boolean visible)
	{
		action.putValue(VISIBLE, Boolean.valueOf(visible));
	}

	/**
	 * Specifies the visibility state of the action (and associated components).
	 * 
	 * @param action
	 * @return
	 */
	public static boolean isVisible(Action action)
	{
		Boolean visible = (Boolean) action.getValue(VISIBLE);
		return visible == null ? true : visible.booleanValue();
	}
}