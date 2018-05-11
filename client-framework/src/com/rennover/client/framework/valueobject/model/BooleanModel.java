package com.rennover.client.framework.valueobject.model;

public class BooleanModel extends ValueModel
{
	public BooleanModel(boolean initValue)
	{
		super(Boolean.class);
		setValue(initValue);
	}

	public boolean booleanValue()
	{
		return getBooleanValue().booleanValue();
	}

	public Boolean getBooleanValue()
	{
		return (Boolean) getValue();
	}

	public void setValue(boolean value)
	{
		setValue(new Boolean(value));
	}
}