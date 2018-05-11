/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.print;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.rennover.client.framework.widget.icon.IconFactory;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PrintAction extends AbstractAction
{
	protected Component m_component;

	public PrintAction(Component component)
	{
		super("Imprimer...", IconFactory.getPrintIcon());
		m_component = component;
	}

	public PrintAction(Component component, String description)
	{
		this(component);
		putValue(SHORT_DESCRIPTION, description);
	}

	public PrintAction(Component component, String title, String description)
	{
		super(title, IconFactory.getPrintIcon());
		putValue(SHORT_DESCRIPTION, description);
		m_component = component;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (m_component != null)
		{
			PrintToolbox.print(m_component);
		}
	}

}
