/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.print;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;

import com.rennover.client.framework.window.WindowManager;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PrintScreenAction extends PrintAction
{
	public PrintScreenAction(Component component)
	{
		super(component);
	}

	public PrintScreenAction(Component component, String description)
	{
		super(component, description);
	}

	public PrintScreenAction(Component component, String title, String description)
	{
		super(component, title, description);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (m_component != null)
		{
			Window window = WindowManager.getOwningWindow(m_component);
			PrintToolbox.print(window);
		}
	}

}
