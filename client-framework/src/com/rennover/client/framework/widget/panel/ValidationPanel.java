package com.rennover.client.framework.widget.panel;

import com.rennover.client.framework.widget.base.ZPanel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface ValidationPanel
{
	public ZPanel getPanel();

	public boolean doValidate();

	public boolean doCancel();
}