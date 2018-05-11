package com.rennover.client.framework.valueobject.model;

import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 *
 * This class is a valueObject used in the ValueModel.
 * This ValueObject have just one property to store just one value.
 * 
 */
public class SimpleValueObject extends ValueObject
{
	public static final String VALUE = "value";
	private Object m_value = null;

	public void setValue(Object value)
	{
		Object oldValue = m_value;
		m_value = value;
		firePropertyChange(VALUE, oldValue, value);
	}

	public Object getValue()
	{
		return m_value;
	}

	protected boolean equals2(ValueObject obj)
	{
		return false;
	}
}