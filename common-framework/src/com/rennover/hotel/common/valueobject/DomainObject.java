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

import java.util.Collection;
import java.util.Iterator;

/**
 * Interface des objets de type value, propres a la couche business.
 * @pattern valueObject
 */
public abstract class DomainObject extends ValueObject
	implements StateManaged
{
	//TODO : pourquoi ces attributs ne sont pas dans PersistentBusinessObject???
	public static final String DIRTY = "dirty";
	public static final String NEW_IN_BASE = "newInBase";
	private boolean dirty = false;
	private boolean newInBase = true;

	/**
* @param obj 
* precondition Les objets sont de même type
* precondition Les objets sont non nuls
*/
	protected boolean equals2(ValueObject obj)
	{
		return this == obj;
	}

	public boolean isDirty()
	{
		return dirty;
	}

	public void setDirty(boolean dirty)
	{
		this.dirty = dirty;
	}

	public boolean isNewInBase()
	{
		return newInBase;
	}

	public void setNewInBase(boolean newInBase)
	{
		this.newInBase = newInBase;
	}

	public void setSaved()
	{
		setNewInBase(false);
		setDirty(false);
	}

	public static void setSaved(StateManaged managed)
	{
		if (managed != null)
		{
			managed.setSaved();
		}
	}

	public static void setSaved(Collection managedCollection)
	{
		if (managedCollection != null)
		{
			for (Iterator iter = managedCollection.iterator(); iter.hasNext();)
			{
				setSaved((StateManaged) iter.next());
			}
		}
	}

	/**
 * Crée une copie parfaite de l'objet.
 */
	public Object clone()
	{
		DomainObject clone = (DomainObject) super.clone();

		clone.dirty = this.dirty;
		clone.newInBase = this.newInBase;

		return clone;
	}

	/**
* surchargé par les sous classes pour faire une copie d'objet attribut par attribut
*/
	public void copyFrom(ValueObject valueObject)
	{
		if (valueObject == null)
		{
			throw new IllegalArgumentException("Parameter valueObject null");
		}

		super.copyFrom(valueObject);

		DomainObject clone = (DomainObject) valueObject;
		this.dirty = clone.dirty;
		this.newInBase = clone.newInBase;
	}
}