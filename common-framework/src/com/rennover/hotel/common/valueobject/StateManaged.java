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
 * Indique si l'objet a �t� modifi� depuis son instanciation 
 * ou depuis le chargement � partir de la base de donn�es
 */
	public boolean isDirty();

	/**
 * Mets � jour la propri�t� qui indique si un objet a �t� d�j�
 * modifi� et donc s'il est de-synchronis� par rapport � la base.
 * 
 * @param isDirty
 */
	public void setDirty(boolean dirty);

	/**
 * Indique si un objet existe d�j� dans la base de donn�es ou non
 */
	public boolean isNewInBase();

	/**
 * Mets � jour la propri�t� qui indique si un objet existe d�j�
 * dans la base de donn�es ou non
 * 
 * @param newInBase
 */
	public void setNewInBase(boolean newInBase);

	/**
 * Indique � l'objet qu'il vient d'�tre sauvegard� dans la base.
 * Cette m�thode �quivaud � :
 * 
 *         setNewInBase(false);
 *         setDirty(false);
 */
	public void setSaved();
}