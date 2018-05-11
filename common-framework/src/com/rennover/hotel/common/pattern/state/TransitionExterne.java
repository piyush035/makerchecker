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

public class TransitionExterne extends Transition
{
	protected State m_target = null;// Changed from private to protected for
									// defect #8011 fix

	public TransitionExterne()
	{

	}

	public TransitionExterne(State targetState)
	{
		m_target = targetState;
	}

	public final State getTarget()
	{
		return m_target;
	}
}