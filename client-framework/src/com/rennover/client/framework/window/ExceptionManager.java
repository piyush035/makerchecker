package com.rennover.client.framework.window;

import java.awt.Component;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface ExceptionManager
{
	public void showException(Throwable throwable, Component parent);
}
