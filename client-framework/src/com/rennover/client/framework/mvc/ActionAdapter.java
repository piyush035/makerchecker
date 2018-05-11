/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.client.framework.mvc;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.Action;

/**
 * @author tcaboste
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ActionAdapter {
	private Action m_action;

	private Component m_source;

	private String m_command;

	public ActionAdapter(Action action, Component source) {
		m_action = action;
		m_source = source;
	}

	public ActionAdapter(Action action, Component source, String command) {
		m_action = action;
		m_source = source;
		m_command = command;
	}

	public void actionPerformed() {
		m_action.actionPerformed(new ActionEvent(m_source,
				ActionEvent.ACTION_PERFORMED, m_command));
	}
}