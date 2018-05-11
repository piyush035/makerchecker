package com.rennover.client.framework.widget.field;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JComponent;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZTabbedPane;
import com.rennover.client.framework.widget.panel.LockHelper;

/**
 * @author tcaboste
 * @audit dattias 30/12/02
 */
public abstract class InputFieldHelper
{
	/**
	 * Change l'aspect obligatoire d'un InputField.
	 */
	public static void doDisplayMandatory(InputField field, boolean mandatory)
	{
		ZLabel associatedLabel = field.getAssociatedLabel();
		if (associatedLabel != null)
		{
			int style = Font.PLAIN;

			if (field.isEnabled())
			{
				style = mandatory ? Font.BOLD : Font.PLAIN;
			}
			Font font = associatedLabel.getFont().deriveFont(style);
			associatedLabel.setFont(font);
		}
	}

	public static void setMandatory(ZLabel label, boolean mandatory, boolean enabled)
	{
		int style = (mandatory && enabled) ? Font.BOLD : Font.PLAIN;
		Font font = label.getFont().deriveFont(style);
		label.setFont(font);
	}

	/**
	 * Change l'aspect de validité d'un InputField
	 */
	public static void doDisplayValid(InputField field, boolean valid)
	{
		ZLabel associatedLabel = field.getAssociatedLabel();
		if (associatedLabel != null)
		{
			Color color = Color.BLACK;
			if (field.isEnabled())
			{
				color = valid ? Color.BLACK : Color.RED.darker();
			}
			associatedLabel.setForeground(color);
		} else
		{
			if (field instanceof NumericField)
			{
				((NumericField) field).setErrorBorder(!valid);
			}
		}

		updateParentValidity(field);
	}

	/**
	 * Remet à jour l'aspect d'un InputField
	 */
	public static void doDisplayState(InputField field)
	{
		doDisplayMandatory(field, field.isMandatoryField());
		doDisplayValid(field, field.isValidField());
	}

	public static void setReadOnly(JComponent component, boolean readonly)
	{
		LockHelper.setReadOnly(component, readonly);
	}

	public static boolean isReadOnly(JComponent component)
	{
		return LockHelper.isReadOnly(component);
	}

	public static boolean isReadOnly(Component component)
	{
		return LockHelper.isReadOnly(component);
	}

	public static void setLockable(JComponent component, boolean lockable)
	{
		LockHelper.setLockable(component, lockable);
	}

	public static void setLockable(Component component, boolean lockable)
	{
		LockHelper.setLockable(component, lockable);
	}

	public static boolean isLockable(JComponent component)
	{
		return LockHelper.isLockable(component);
	}

	public static boolean isLockable(Component component)
	{
		return LockHelper.isLockable(component);
	}

	/**
	 * @deprecated Use LockHelper.setLocked
	 * 
	 * @param panel
	 * @param locked
	 */
	public static void setLocked(ZPanel panel, boolean locked)
	{
		LockHelper.setLocked(panel, locked);
	}

	public static void updateParentValidity(InputField field)
	{
		Component component = (Component) field;
		boolean valid = (field.isValidField() && field.isValidValue()) || (!component.isEnabled());
		ZTabbedPane.updateParentValidity(component, valid);
	}
}