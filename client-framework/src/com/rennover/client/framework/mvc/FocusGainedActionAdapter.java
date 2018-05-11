package com.rennover.client.framework.mvc;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Action;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class FocusGainedActionAdapter extends ActionAdapter implements FocusListener
{
	/**
	 * @param action
	 * @param source
	 */
	public FocusGainedActionAdapter(Action action, Component source)
	{
		super(action, source);
	}

	/**
	 * @param action
	 * @param source
	 * @param command
	 */
	public FocusGainedActionAdapter(Action action, Component source, String command)
	{
		super(action, source, command);
	}

	public void focusGained(FocusEvent e)
	{
		actionPerformed();
	}

	public void focusLost(FocusEvent e)
	{
	}
}