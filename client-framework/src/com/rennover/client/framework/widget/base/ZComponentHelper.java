package com.rennover.client.framework.widget.base;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class ZComponentHelper
{
	public static void printActionMap(JComponent component)
	{
		ActionMap actionMap = component.getActionMap();
		Object[] keys = actionMap.allKeys();
		if (keys != null)
		{
			Arrays.sort(keys, ComparatorHelper.createComparator());
			for (int i = 0; i < keys.length; i++)
			{
				Object key = keys[i];
				Object action = actionMap.get(key);

			}
		}
	}

	public static void printInputMap(JComponent component)
	{

		printInputMap(component.getInputMap(JComponent.WHEN_FOCUSED));

		printInputMap(component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));

		printInputMap(component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
	}

	public static void printInputMap(InputMap map)
	{
		Object[] keys = map.allKeys();
		if (keys != null)
		{
			Arrays.sort(keys, ComparatorHelper.createComparator());
			for (int i = 0; i < keys.length; i++)
			{
				KeyStroke key = (KeyStroke) keys[i];
				Object action = map.get(key);

			}
		}
	}

	public static void printKeyMap(JComponent component)
	{
		// List keystrokes in the WHEN_FOCUSED input map of the component
		InputMap map = component.getInputMap(JComponent.WHEN_FOCUSED);
		list(map, map.keys());

		// List keystrokes in the component and in all parent input maps
		list(map, map.allKeys());

		// List keystrokes in the WHEN_ANCESTOR_OF_FOCUSED_COMPONENT input map
		// of the component
		map = component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		list(map, map.keys());

		// List keystrokes in all related input maps
		list(map, map.allKeys());

		// List keystrokes in the WHEN_IN_FOCUSED_WINDOW input map of the
		// component
		map = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		list(map, map.keys());

		// List keystrokes in all related input maps
		list(map, map.allKeys());
	}

	public static void list(InputMap map, KeyStroke[] keys)
	{
		if (keys == null)
		{
			return;
		}
		for (int i = 0; i < keys.length; i++)
		{
			// This method is defined in e858 Converting a KeyStroke to a String
			String keystrokeStr = keys[i].toString();

			// Get the action name bound to this keystroke
			while (map.get(keys[i]) == null)
			{
				map = map.getParent();
			}
			if (map.get(keys[i]) instanceof String)
			{
				String actionName = (String) map.get(keys[i]);

			} else
			{
				Action action = (Action) map.get(keys[i]);

			}
		}
	}

	public static String debugWidget(Component c)
	{
		StringBuffer returnValue = new StringBuffer();
		if (c == null)
		{
			returnValue.append("Composant à tracer null");
			return returnValue.toString();
		}

		for (byte numeroContainer = 1; c.getParent() != null; numeroContainer++)
		{
			c = c.getParent();
			returnValue.append("Container " + numeroContainer + " : " + c + "\n");
		}
		return returnValue.toString();
	}

	public static void debugInputMap(Component component)
	{
		if (component == null)
		{
			return;
		}

		for (; component != null; component = component.getParent())
		{

			if (component instanceof JComponent)
			{
				ZComponentHelper.printInputMap((JComponent) component);
				ZComponentHelper.printActionMap((JComponent) component);
			}
		}
	}
}