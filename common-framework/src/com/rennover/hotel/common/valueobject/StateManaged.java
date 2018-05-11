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

public interface StateManaged
{
	/**
 * Indique si l'objet a été modifié depuis son instanciation 
 * ou depuis le chargement à partir de la base de données
 */
	public boolean isDirty();

	/**
 * Mets à jour la propriété qui indique si un objet a été déjà
 * modifié et donc s'il est de-synchronisé par rapport à la base.
 * 
 * @param isDirty
 */
	public void setDirty(boolean dirty);

	/**
 * Indique si un objet existe déjà dans la base de données ou non
 */
	public boolean isNewInBase();

	/**
 * Mets à jour la propriété qui indique si un objet existe déjà
 * dans la base de données ou non
 * 
 * @param newInBase
 */
	public void setNewInBase(boolean newInBase);

	/**
 * Indique à l'objet qu'il vient d'être sauvegardé dans la base.
 * Cette méthode équivaud à :
 * 
 *         setNewInBase(false);
 *         setDirty(false);
 */
	public void setSaved();
}