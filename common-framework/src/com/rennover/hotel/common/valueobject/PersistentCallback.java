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
 * Interface des objets {@link com.agefospme.nsicm.common.valueobject.Persistent} pour
 * lesquels on veut permettre  l'ex�cution d'actions ant�rieures ou post�rieures � une
 * action de persistence. <br>
 * Concr�tement, en impl�mentant cette interface, on a la possibilit� de d�finir les
 * m�thodes qui seront e�xut�es :
 * 
 * <ul>
 * <li>
 * Apr�s un SELECT
 * <ul>
 * <li>
 * Avant ou apr�s un INSERT
 * </li>
 * <li>
 * Avant ou apr�s un UPDATE ()
 * </li>
 * <li>
 * Avant ou apr�s un DELETE ()
 * </li>
 * </ul>
 */
public interface PersistentCallback
{
	public void afterSelect();

	public void beforeInsert();

	public void afterInsert();

	public void beforeUpdate();

	public void afterUpdate();

	public void beforeDelete();

	public void afterDelete();
}