package com.rennover.client.framework.valueobject.model;

public class IntegerModel extends ValueModel
{
	public IntegerModel()
	{
		super(Integer.class);
	}

	public int intValue()
	{
		return getIntegerValue().intValue();
	}

	public Integer getIntegerValue()
	{
		return (Integer) getValue();
	}

	public void setValue(int value)
	{
		setValue(new Integer(value));
	}
}