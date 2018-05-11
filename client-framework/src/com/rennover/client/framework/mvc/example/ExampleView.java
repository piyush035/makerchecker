/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.mvc.example;

import com.rennover.client.framework.mvc.PanelView;

public class ExampleView extends PanelView
{
	private ExampleController m_controller;

	public ExampleView(ExampleController controller)
	{
		super("Choisir le prospect/adhérent destinataire du règlement");
		m_controller = controller;
		instanciate();
	}

	/**
	 * @see com.rennover.client.framework.mvc.PanelView#instanciate()
	 */
	protected void instanciate()
	{
	}

	/**
	 * @see com.rennover.client.framework.mvc.PanelView#compose()
	 */
	protected void compose()
	{
	}
}