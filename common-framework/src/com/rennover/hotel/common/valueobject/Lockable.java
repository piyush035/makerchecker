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
 * Cette interface doit être implémentée par tous les objets supportant le verrouillage pessimiste
 * Les classes implémentant Lockable doivent aussi avoir un attribut privé "locked
 */
public interface Lockable extends Versionnable
{
	public boolean isLocked();

	public String getLockUserLogin();
	
	public void setLockUserLogin(String userLogin);
}