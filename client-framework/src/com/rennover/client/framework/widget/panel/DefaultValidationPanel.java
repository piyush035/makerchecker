package com.rennover.client.framework.widget.panel;

import com.rennover.client.framework.widget.base.ZPanel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DefaultValidationPanel implements ValidationPanel
{
	public ZPanel m_panel;

	public DefaultValidationPanel(ZPanel panel)
	{
		m_panel = panel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.widget.panel.ValidationPanel#getPanel()
	 */
	public ZPanel getPanel()
	{
		return m_panel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.widget.panel.ValidationPanel#doValidate()
	 */
	public boolean doValidate()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.widget.panel.ValidationPanel#doCancel()
	 */
	public boolean doCancel()
	{
		return true;
	}
}