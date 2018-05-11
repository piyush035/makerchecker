package com.rennover.client.framework.widget.panel;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.rennover.client.framework.widget.base.ZPanel;

/**
 * @author tcaboste
 * @audit dattias 30/12/02
 */
public abstract class LockHelper
{
	private static final String READ_ONLY = "inputfield.readonly";

	private static final String LOCKABLE = "inputfield.lockable";

	public static void setReadOnly(JComponent component, boolean readonly)
	{
		component.putClientProperty(READ_ONLY, Boolean.valueOf(readonly));
		if (readonly)
		{
			component.setEnabled(false);
		}
		setLockable(component, !readonly);
	}

	public static boolean isReadOnly(JComponent component)
	{
		Boolean readonly = (Boolean) component.getClientProperty(READ_ONLY);
		return (readonly != null) ? readonly.booleanValue() : false;
	}

	public static boolean isReadOnly(Component component)
	{
		if (component instanceof JComponent)
		{
			return isReadOnly((JComponent) component);
		} else
		{
			return false;
		}
	}

	private static void setLockable(JComponent component, boolean lockable)
	{
		component.putClientProperty(LOCKABLE, Boolean.valueOf(lockable));
	}

	public static void setLockable(Component component, boolean lockable)
	{
		if (component instanceof JScrollPane)
		{
			JComponent realComponent = ((JScrollPane) component).getViewport();
			setLockable(realComponent, lockable);
		} else if (component instanceof JComponent)
		{
			setLockable((JComponent) component, lockable);
		}
	}

	// removed the commented method isDefaultLockableClass(Class
	// componentClass),isDefaultBrowsableContainerClass(Class componentClass)

	public static boolean isLockable(JComponent component)
	{
		Boolean lockable = (Boolean) component.getClientProperty(LOCKABLE);
		if (lockable == null)
		{
			if (component instanceof JScrollBar)
			{
				return false;
			}
		}
		return (lockable == null) ? true : lockable.booleanValue();
	}

	public static boolean isLockable(Component component)
	{
		if (component instanceof JComponent)
		{
			return isLockable((JComponent) component);
		} else
		{
			return false;
		}
	}

	public static boolean isBrowsableContainer(JComponent container)
	{
		Boolean browsable = (Boolean) container.getClientProperty(LOCKABLE);
		return (browsable == null) ? true : browsable.booleanValue();
	}

	public static boolean isBrowsableContainer(Component component)
	{
		if (component instanceof JComponent)
		{
			return isBrowsableContainer((JComponent) component);
		} else
		{
			return true;
		}
	}

	public static void setLocked(ZPanel panel, boolean locked)
	{
		setLocked((Container) panel, locked);
	}

	private static void setLocked(Component component, boolean locked)
	{
		if (isLockable(component) && !isReadOnly(component))
		{
			component.setEnabled(!locked);
		}

		if (component instanceof Container)
		{
			if (isBrowsableContainer(component) && !isReadOnly(component))
			{
				setLocked((Container) component, locked);
			}
		}
	}

	private static void setLocked(Container container, boolean locked)
	{
		int count = container.getComponentCount();
		for (int i = 0; i < count; i++)
		{
			Component component = (Component) container.getComponent(i);
			setLocked(component, locked);
		}
	}
}