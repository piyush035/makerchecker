package com.rennover.client.framework.valueobject.model;

public class ShortModel extends ValueModel
{
	public ShortModel()
	{
		super(Short.class);
	}

	public short shortValue()
	{
		return getShortValue().shortValue();
	}

	public Short getShortValue()
	{
		return (Short) getValue();
	}

	public void setValue(short value)
	{
		setValue(new Short(value));
	}
}