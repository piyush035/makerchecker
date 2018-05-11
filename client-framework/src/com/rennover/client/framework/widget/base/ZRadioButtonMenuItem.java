package com.rennover.client.framework.widget.base;

import java.awt.Window;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rennover.client.framework.widget.panel.StatusBar;
import com.rennover.client.framework.window.DefaultFrame;
import com.rennover.client.framework.window.WindowManager;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ZRadioButtonMenuItem extends JRadioButtonMenuItem implements ChangeListener
{
	/**
	 * 
	 */
	public ZRadioButtonMenuItem()
	{
		super();
		addChangeListener(this);
	}

	/**
	 * @param icon
	 */
	public ZRadioButtonMenuItem(Icon icon)
	{
		super(icon);
		addChangeListener(this);
	}

	/**
	 * @param text
	 */
	public ZRadioButtonMenuItem(String text)
	{
		super(text);
		addChangeListener(this);
	}

	/**
	 * @param a
	 */
	public ZRadioButtonMenuItem(Action a)
	{
		super(a);
		addChangeListener(this);
	}

	/**
	 * @param text
	 * @param icon
	 */
	public ZRadioButtonMenuItem(String text, Icon icon)
	{
		super(text, icon);
		addChangeListener(this);
	}

	/**
	 * @param text
	 * @param selected
	 */
	public ZRadioButtonMenuItem(String text, boolean selected)
	{
		super(text, selected);
		addChangeListener(this);
	}

	/**
	 * @param icon
	 * @param selected
	 */
	public ZRadioButtonMenuItem(Icon icon, boolean selected)
	{
		super(icon, selected);
		addChangeListener(this);
	}

	/**
	 * @param text
	 * @param icon
	 * @param selected
	 */
	public ZRadioButtonMenuItem(String text, Icon icon, boolean selected)
	{
		super(text, icon, selected);
		addChangeListener(this);
	}

	public String getDescription()
	{
		// Récupération de la description dans l'action du menu
		Action action = getAction();
		String description = action != null ? (String) action.getValue(Action.LONG_DESCRIPTION) : getText();
		description = ((description != null) | "".equals(description)) ? description : getText();
		return description;
	}

	public void stateChanged(ChangeEvent e)
	{
		Window window = WindowManager.getOwningWindow(this);
		if (window instanceof DefaultFrame)
		{
			StatusBar statusBar = ((DefaultFrame) window).getStatusBar();
			if (isArmed())
			{
				statusBar.setText(getDescription());
			} else
			{
				statusBar.setText("");
			}
		}
	}
}