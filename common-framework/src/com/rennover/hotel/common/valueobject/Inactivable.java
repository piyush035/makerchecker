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
package com.rennover.hotel.common.valueobject;

/**
 * @todo (dattias, 12 déc. 2003): Déplacer cette interface dans domain.common
 */
public interface Inactivable
{
	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void deactivate();

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void deactivate(DeactivationReason reason);

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void deactivateForever();

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public String getDeactivationReason();

	/**
	 * @deprecated replaced by isActif().
	 */
	public boolean isActive();

	/**
	 */
	public boolean isActif();

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public boolean isReactivated();

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void reactivate();
}