package com.rennover.client.framework.widget.base;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

import javax.swing.JRadioButton;

public class ZRadioButton extends JRadioButton
{
	public ZRadioButton()
	{
		setModel(new ZRadioButtonModel());
	}

	public ZRadioButton(String text)
	{
		this();
		setText(text);
	}

	public static class ZRadioButtonModel extends ToggleButtonModel
	{
		/**
		 * Sets the pressed state of the toggle button.
		 */
		public void setPressed(boolean b)
		{
			if ((isPressed() == b) || !isEnabled())
			{
				return;
			}

			if (b == false && isArmed())
			{
				setSelected(true);
			}

			if (b)
			{
				stateMask |= PRESSED;
			} else
			{
				stateMask &= ~PRESSED;
			}

			fireStateChanged();

			if (!isPressed() && isArmed())
			{
				int modifiers = 0;
				AWTEvent currentEvent = EventQueue.getCurrentEvent();
				if (currentEvent instanceof InputEvent)
				{
					modifiers = ((InputEvent) currentEvent).getModifiers();
				} else if (currentEvent instanceof ActionEvent)
				{
					modifiers = ((ActionEvent) currentEvent).getModifiers();
				}
				fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getActionCommand(), EventQueue
				        .getMostRecentEventTime(), modifiers));
			}
		}
	}
}
