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
 * Interface des objets de type graphe, propres a la couche business.
 *
 * @pattern valueObject
 */
public abstract class GraphObject extends ValueObject
{
	protected Object wrappedObject;

	protected GraphObject()
	{
	}

	protected GraphObject(Object wrappedObject)
	{
		this.wrappedObject = wrappedObject;
	}

	public Object getWrappedObject()
	{
		return wrappedObject;
	}

	protected boolean equals2(ValueObject obj)
	{
		return this == obj;
	}

	public Object clone()
	{
		GraphObject graph = (GraphObject) super.clone();
		graph.wrappedObject = ((ValueObject) wrappedObject).clone();
		return graph;
	}

	public void copyFrom(ValueObject vo)
	{
		wrappedObject = ((GraphObject) vo).wrappedObject;
	}

	public String toString()
	{
		if (wrappedObject != null)
		{
			return wrappedObject.toString();
		}
		else
		{
			return PropertyHelper.EMPTY_STRING;
		}
	}
}