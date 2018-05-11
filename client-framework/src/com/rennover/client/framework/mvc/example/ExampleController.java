/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */


package com.rennover.client.framework.mvc.example;

import com.rennover.client.framework.mvc.Controller;
import com.rennover.client.framework.mvc.PanelController;

public class ExampleController extends PanelController
{
	public ExampleController(Controller parent)
	{
		super(parent);
	}

	/**
	 * @see com.rennover.client.framework.mvc.PanelController#init()
	 */
	protected void createModel()
	{
		m_model = new ExampleModel();
	}

	protected void createView()
	{
		m_view = new ExampleView(this);

		m_view.init();
	}

	public void doExecute()
	{
	}

	/**
	 * @see com.rennover.client.framework.mvc.PanelController#checkPreconditions()
	 */
	protected void checkPreconditions()
	{
	}

	/**
	 * @see com.rennover.client.framework.mvc.PanelController#doRecupererDonneesInitiales()
	 */
	protected void retrieveInitialData()
	{
	}
}