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
package com.rennover.hotel.common.pattern.state;

/**
 * Interface to manage of state pattern
 */
public interface Subject
{
	/**
	 * @param state
	 *            L'état courant du subject
	 */
	void setState(State state);

	/**
	 * @return L'état courant du subject
	 */
	State getState();
}