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
package com.rennover.hotel.common.facade;

import java.util.EventListener;

/**
 * @author tcaboste
 * 
 * Class listener for the server call from the facade
 * 
 */
public interface CallListener extends EventListener
{
	/**
	 * Method called before a server call
	 * 
	 * @param e
	 *            event parameters
	 */
	public void doBefore(CallEvent e);

	/**
	 * Method called after a server call even if an error occurs during the call
	 * 
	 * @param e
	 *            event parameters
	 */
	public void doAfter(CallEvent e);

	/**
	 * Method called in the case of technical error in a server call
	 * NonTechnicalException are not seen with this method
	 * 
	 * @param e
	 *            event parameters
	 */
	public void doOnError(CallEvent e);
}
