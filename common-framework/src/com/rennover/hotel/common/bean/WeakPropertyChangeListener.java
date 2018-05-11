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
package com.rennover.hotel.common.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.ref.WeakReference;

/**
 * 
 * @author dattias
 */
public class WeakPropertyChangeListener extends WeakReference implements PropertyChangeListener
{
	public WeakPropertyChangeListener(PropertyChangeListener listener)
	{
		super(listener);
	}

	PropertyChangeSupport m_listenerManager = null;

	public WeakPropertyChangeListener(PropertyChangeListener listener, PropertyChangeSupport listenerManager)
	{
		super(listener);
		m_listenerManager = listenerManager;
	}

	// Commmented code removed

	public void propertyChange(PropertyChangeEvent evt)
	{
		PropertyChangeListener listener = (PropertyChangeListener) get();
		if (listener != null)
		{
			listener.propertyChange(evt);
		}
	}

	public boolean isFree()
	{
		return get() == null;
	}

	public PropertyChangeListener getListener()
	{
		return (PropertyChangeListener) get();
	}

	public boolean equals(Object obj)
	{
		return get() == null ? super.equals(obj) : get().equals(obj);
	}

	public int hashCode()
	{
		return get() == null ? super.hashCode() : get().hashCode();
	}

	public String toString()
	{
		return get() == null ? "null" : get().toString();
	}
}
