package com.rennover.client.framework.valueobject.model;

import java.lang.ref.WeakReference;

/**
 * @author sanjeevkr
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WeakValueObjectModelListener extends WeakReference implements ValueObjectModelListener
{
	/**
	 * @param arg0
	 */
	public WeakValueObjectModelListener(Object arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectModelListener#valueObjectChanged(com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectModelEvent)
	 */
	public void valueObjectChanged(ValueObjectModelEvent event)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectModelListener#valueObjectPropertyChanged(com.agefospme.nsicm.client.framework.valueobject.model.ValueObjectModelEvent)
	 */
	public void valueObjectPropertyChanged(ValueObjectModelEvent event)
	{
		// TODO Auto-generated method stub

	}

	public boolean isFree()
	{
		return get() == null;
	}

	public ValueObjectModelListener getListener()
	{
		return (ValueObjectModelListener) get();
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
