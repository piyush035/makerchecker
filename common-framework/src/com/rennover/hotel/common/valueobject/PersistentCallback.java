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
 * lesquels on veut permettre  l'exécution d'actions antérieures ou postérieures à une
 * action de persistence. <br>
 * Concrètement, en implémentant cette interface, on a la possibilité de définir les
 * méthodes qui seront eéxutées :
 * 
 * <ul>
 * <li>
 * Après un SELECT
 * <ul>
 * <li>
 * Avant ou après un INSERT
 * </li>
 * <li>
 * Avant ou après un UPDATE ()
 * </li>
 * <li>
 * Avant ou après un DELETE ()
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