package com.rennover.client.framework.valueobject.model;

import java.lang.ref.WeakReference;

/**
 * @author sanjeevkr
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WeakValueObjectListModelListener extends WeakReference implements ValueObjectListModelListener
{
	/**
	 * @param arg0
	 */
	public WeakValueObjectListModelListener(Object arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelListener#valueObjectListChanged(com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelEvent)
	 */
	public void valueObjectListChanged(ValueObjectListModelEvent event)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelListener#valueObjectAdded(com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelEvent)
	 */
	public void valueObjectAdded(ValueObjectListModelEvent event)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelListener#valueObjectRemoved(com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelEvent)
	 */
	public void valueObjectRemoved(ValueObjectListModelEvent event)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelListener#valueObjectUpdated(com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectListModelEvent)
	 */
	public void valueObjectUpdated(ValueObjectListModelEvent event)
	{
		// TODO Auto-generated method stub

	}

	public boolean isFree()
	{
		return get() == null;
	}

	public ValueObjectListModelListener getListener()
	{
		return (ValueObjectListModelListener) get();
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