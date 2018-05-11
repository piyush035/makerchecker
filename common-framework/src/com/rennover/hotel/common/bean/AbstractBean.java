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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rennover.hotel.common.helper.ReflectionHelper;

/**
 * ...
 * 
 * @author dattias
 */
public abstract class AbstractBean implements Serializable
{
	private transient PropertyChangeSupport m_listenerManager;

	public AbstractBean()
	{
	}

	public void firePropertyChange(String propertyName, byte oldValue, byte newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, new Byte(oldValue), new Byte(newValue));
		}
	}

	public void firePropertyChange(String propertyName, short oldValue, short newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, new Short(oldValue), new Short(newValue));
		}
	}

	public void firePropertyChange(String propertyName, int oldValue, int newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, new Integer(oldValue), new Integer(newValue));
		}
	}

	public void firePropertyChange(String propertyName, long oldValue, long newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, new Long(oldValue), new Long(newValue));
		}
	}

	public void firePropertyChange(String propertyName, float oldValue, float newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, new Float(oldValue), new Float(newValue));
		}
	}

	public void firePropertyChange(String propertyName, double oldValue, double newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, new Double(oldValue), new Double(newValue));
		}
	}

	public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, Boolean.valueOf(oldValue), Boolean.valueOf(newValue));
		}
	}

	public void firePropertyChange(String propertyName, char oldValue, char newValue)
	{
		if (m_listenerManager != null)
		{
			firePropertyChange(propertyName, new Character(oldValue), new Character(newValue));
		}
	}

	public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
	{
		if (m_listenerManager != null)
		{
			PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
			m_listenerManager.firePropertyChange(event);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		if (m_listenerManager == null)
		{
			m_listenerManager = new PropertyChangeSupport(this);
		}
		removeEmptyListeners(); // nettoyage
		m_listenerManager.addPropertyChangeListener(new WeakPropertyChangeListener(listener, m_listenerManager));
	}

	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		if (m_listenerManager == null)
		{
			m_listenerManager = new PropertyChangeSupport(this);
		}

		WeakPropertyChangeListener wl = searchWeakListener(listener);
		m_listenerManager.removePropertyChangeListener(wl);
	}

	private WeakPropertyChangeListener searchWeakListener(PropertyChangeListener listener)
	{
		// Recherche du WeakListener contenant le PropertyChangeListener
		PropertyChangeListener[] listeners = m_listenerManager.getPropertyChangeListeners();
		for (int i = 0; i < listeners.length; i++)
		{
			PropertyChangeListener l = listeners[i];
			if (l instanceof WeakPropertyChangeListener)
			{
				WeakPropertyChangeListener weakListener = (WeakPropertyChangeListener) l;
				PropertyChangeListener refListener = weakListener.getListener();
				if (refListener == listener)
				{
					return weakListener;
				}
			}
		}
		return null;
	}

	private void removeEmptyListeners()
	{
		List weakListenerList = new ArrayList();

		// Recherche des références libérées
		PropertyChangeListener[] listeners = m_listenerManager.getPropertyChangeListeners();
		for (int i = 0; i < listeners.length; i++)
		{
			PropertyChangeListener l = listeners[i];
			if (l instanceof WeakPropertyChangeListener)
			{
				WeakPropertyChangeListener weakListener = (WeakPropertyChangeListener) l;
				if (weakListener.isFree())
				{
					weakListenerList.add(weakListener);
				}
			}
		}

		// Suppression des références libérées
		for (int i = 0; i < weakListenerList.size(); i++)
		{
			WeakPropertyChangeListener weakListener = (WeakPropertyChangeListener) weakListenerList.get(i);
			m_listenerManager.removePropertyChangeListener(weakListener);
		}
	}

	public final void setProperty(String propertyName, Object value)
	{
		ReflectionHelper.setProperty(this, propertyName, value);
	}

	public final Object getProperty(String propertyName)
	{
		return ReflectionHelper.getProperty(this, propertyName);
	}

	public final Class getPropertyType(String propertyName)
	{
		return ReflectionHelper.getPropertyType(this.getClass(), propertyName);
	}

	public Object clone() throws CloneNotSupportedException
	{
		AbstractBean clone = (AbstractBean) super.clone();
		clone.m_listenerManager = null;
		return clone;
	}
}